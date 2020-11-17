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
import org.yangxin.pojo.query.SubmitOrderQuery;
import org.yangxin.service.AddressService;
import org.yangxin.service.ItemService;
import org.yangxin.service.OrderService;

import java.util.Date;

/**
 * 订单
 *
 * @author yangxin
 * 2019/12/06 10:44
 */
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
    public String createOrder(SubmitOrderQuery submitOrderQuery) {
        String userId = submitOrderQuery.getUserId();
        String addressId = submitOrderQuery.getAddressId();
        String itemSpecIds = submitOrderQuery.getItemSpecIds();
        Integer payMethod = submitOrderQuery.getPayMethod();
        String leftMsg = submitOrderQuery.getLeftMsg();
        // 包邮费用设置为0
        int postAmount = 0;

        // 用户订单上的地址
        UserAddress userAddress = addressService.queryUserAddress(userId, addressId);

        // 新订单数据保存
        Orders orders = Orders.builder()
                .id(sid.nextShort())
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
            OrderItems orderItems = OrderItems.builder()
                    .id(sid.nextShort())
                    .orderId(orders.getId())
                    .itemId(itemId)
                    .itemName(items.getItemName())
                    .itemImg(itemsImg == null ? "" : itemsImg.getUrl())
                    .buyCounts(buyCount)
                    .itemSpecId(itemSpecId)
                    .itemSpecName(itemsSpec.getName())
                    .price(itemsSpec.getPriceDiscount())
                    .build();
            orderItemsMapper.insert(orderItems);

            // 在用户提交订单以后，规格表中需要扣除库存
            itemService.decreaseItemSpecStock(itemSpecId, buyCount);
        }

        orders.setTotalAmount(totalAmount);
        orders.setRealPayAmount(realPayAmount);
        ordersMapper.insert(orders);

        // 保存订单状态表
        OrderStatus orderStatus = OrderStatus.builder()
                .orderId(orders.getId())
                .orderStatus(OrderStatusEnum.WAIT_PAY.getType())
                .createdTime(new Date())
                .build();
        orderStatusMapper.insert(orderStatus);

        return orders.getId();
    }

    @Override
    public void updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus paidStatus = OrderStatus.builder()
                .orderId(orderId)
                .orderStatus(orderStatus)
                .payTime(new Date())
                .build();

        orderStatusMapper.updateByPrimaryKeySelective(paidStatus);
    }

    @Override
    public OrderStatus queryOrderStatusInfo(String orderId) {
        return orderStatusMapper.selectByPrimaryKey(orderId);
    }
}
