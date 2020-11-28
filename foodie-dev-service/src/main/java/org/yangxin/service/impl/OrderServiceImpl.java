package org.yangxin.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.enums.OrderStatusEnum;
import org.yangxin.enums.YesNoEnum;
import org.yangxin.mapper.OrderItemsMapper;
import org.yangxin.mapper.OrderStatusMapper;
import org.yangxin.mapper.OrdersMapper;
import org.yangxin.pojo.*;
import org.yangxin.pojo.bo.SubmitOrderBO;
import org.yangxin.pojo.vo.order.MerchantOrdersVO;
import org.yangxin.pojo.vo.order.OrderVO;
import org.yangxin.service.AddressService;
import org.yangxin.service.ItemService;
import org.yangxin.service.OrderService;
import org.yangxin.utils.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * 订单
 *
 * @author yangxin
 * 2019/12/06 10:44
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class OrderServiceImpl implements OrderService {

    private final AddressService addressService;
    private final ItemService itemService;
    private final OrdersMapper ordersMapper;
    private final OrderItemsMapper orderItemsMapper;
    private final OrderStatusMapper orderStatusMapper;
    private final Sid sid;

    @Autowired
    public OrderServiceImpl(AddressService addressService, ItemService itemService, OrdersMapper ordersMapper, OrderItemsMapper orderItemsMapper, OrderStatusMapper orderStatusMapper, Sid sid) {
        this.addressService = addressService;
        this.itemService = itemService;
        this.ordersMapper = ordersMapper;
        this.orderItemsMapper = orderItemsMapper;
        this.orderStatusMapper = orderStatusMapper;
        this.sid = sid;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderVO createOrder(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();
        // 包邮费用设置为0
        int postAmount = 0;

        // 订单id
        String orderId = sid.nextShort();

        // 用户订单上的地址
        UserAddress userAddress = addressService.queryUserAddress(userId, addressId);

        // 新订单数据保存
        Order order = Order.builder()
                .id(orderId)
                .userId(userId)
                .receiverName(userAddress.getReceiver())
                .receiverMobile(userAddress.getMobile())
                .receiverAddress(userAddress.getProvince() + " "
                        + userAddress.getCity() + " "
                        + userAddress.getDistrict() + " "
                        + userAddress.getDetail())
                .postAmount(postAmount)
                .payMethod(payMethod)
                .leftMsg(leftMsg)
                .isComment(YesNoEnum.NO.getType())
                .isDelete(YesNoEnum.NO.getType())
                .createdTime(new Date())
                .updatedTime(new Date())
                .build();

        // 循环根据itemSpecIds保存订单商品信息表
        // 商品原价累积
        int totalAmount = 0;
        // 优惠后的实际支付价格累计
        int realPayAmount = 0;
        for (String itemSpecId : itemSpecIds.split(",")) {
            // todo 整合redis后，商品购买的数量重新从redis的购物车中获取
            int buyCount = 1;

            // 根据规格id，查询规格的具体信息，主要获取价格
            ItemsSpec itemsSpec = itemService.queryItemSpecById(itemSpecId);
            totalAmount += itemsSpec.getPriceNormal() * buyCount;
            realPayAmount += itemsSpec.getPriceDiscount() * buyCount;

            // 根据商品Id，获取商品信息以及商品图片
            String itemId = itemsSpec.getItemId();
            Items items = itemService.queryItemById(itemId);
            ItemsImg itemsImg = itemService.queryItemMainImageById(itemId);

            // 循环保存子订单数据到数据库
            OrderItem orderItem = OrderItem.builder()
                    .id(sid.nextShort())
                    .orderId(order.getId())
                    .itemId(itemId)
                    .itemName(items.getItemName())
                    .itemImg(itemsImg == null ? "" : itemsImg.getUrl())
                    .buyCounts(buyCount)
                    .itemSpecId(itemSpecId)
                    .itemSpecName(itemsSpec.getName())
                    .price(itemsSpec.getPriceDiscount())
                    .build();
            orderItemsMapper.insert(orderItem);

            // 在用户提交订单以后，规格表中需要扣除库存
            itemService.decreaseItemSpecStock(itemSpecId, buyCount);
        }

        order.setTotalAmount(totalAmount);
        order.setRealPayAmount(realPayAmount);
        ordersMapper.insert(order);

        // 保存订单状态表
        OrderStatus orderStatus = OrderStatus.builder()
                .orderId(order.getId())
                .orderStatus(OrderStatusEnum.WAIT_PAY.getType())
                .createdTime(new Date())
                .build();
        orderStatusMapper.insert(orderStatus);

        // 构建商户订单，用于传给支付中心
        MerchantOrdersVO merchantOrdersVO = MerchantOrdersVO.builder()
                .merchantOrderId(orderId)
                .merchantUserId(userId)
                .amount(realPayAmount + postAmount)
                .payMethod(payMethod)
                .build();

        // 构建自定义订单vo
        return OrderVO.builder()
                .orderId(orderId)
                .merchantOrdersVO(merchantOrdersVO)
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus paidStatus = OrderStatus.builder()
                .orderId(orderId)
                .orderStatus(orderStatus)
                .payTime(new Date())
                .build();

        orderStatusMapper.updateByPrimaryKeySelective(paidStatus);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public OrderStatus queryOrderStatusInfo(String orderId) {
        return orderStatusMapper.selectByPrimaryKey(orderId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void closeOrder() {
        // 查询所有未付款订单，判断时间是否超过（1天），超过则关闭交易
//        OrderStatus queryOrder = new OrderStatus();
//        OrderStatus queryOrder = OrderStatus.builder().build();
//        queryOrder.setOrderStatus(OrderStatusEnum.WAIT_PAY.getType());
        OrderStatus queryOrder = OrderStatus.builder()
                .orderStatus(OrderStatusEnum.WAIT_PAY.getType())
                .build();
        List<OrderStatus> orderStatusList = orderStatusMapper.select(queryOrder);

        for (OrderStatus orderStatus : orderStatusList) {
            // 获得订单创建时间
            Date createdTime = orderStatus.getCreatedTime();
            // 和当前时间进行对比
            int days = DateUtil.daysBetween(createdTime, new Date());
            if (days >= 1) {
                // 超过1天，关闭订单
                doCloseOrder(orderStatus.getOrderId());
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void doCloseOrder(String orderId) {
        OrderStatus orderStatus = OrderStatus.builder()
                .orderId(orderId)
                .orderStatus(OrderStatusEnum.CLOSE.getType())
                .closeTime(new Date())
                .build();
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }
}
