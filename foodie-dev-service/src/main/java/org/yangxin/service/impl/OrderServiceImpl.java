package org.yangxin.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.enums.YesNoEnum;
import org.yangxin.mapper.OrdersMapper;
import org.yangxin.pojo.Orders;
import org.yangxin.pojo.UserAddress;
import org.yangxin.pojo.query.SubmitOrderQuery;
import org.yangxin.service.AddressService;
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
    private final OrdersMapper ordersMapper;
    private final Sid sid;

    @Autowired
    public OrderServiceImpl(AddressService addressService, OrdersMapper ordersMapper, Sid sid) {
        this.addressService = addressService;
        this.ordersMapper = ordersMapper;
        this.sid = sid;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createOrder(SubmitOrderQuery submitOrderQuery) {
        String userId = submitOrderQuery.getUserId();
        String addressId = submitOrderQuery.getAddressId();
        String itemSpecIds = submitOrderQuery.getItemSpecIds();
        Integer payMethod = submitOrderQuery.getPayMethod();
        String leftMsg = submitOrderQuery.getLeftMsg();
        // 包邮费用设置为0
        Integer postAmount = 0;

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
        // 保存订单状态表
    }
}
