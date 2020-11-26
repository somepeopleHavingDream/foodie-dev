select o.id as orderId,
       o.created_time as createdTime,
       o.pay_method as payMethod,
       o.real_pay_amount as payMethod,
       o.post_amount as postAmount,
       os.order_status as orderStatus,
       oi.item_id as itemId,
       oi.item_name as itemName,
       oi.item_img as itemImg,
       oi.item_spec_id as itemSpecId,
       oi.item_spec_name as itemSpecName,
       oi.buy_counts as buyCounts,
       oi.price as price
from orders o
left join order_status os
on o.id = os.order_id
left join order_items oi
on o.id = oi.order_id
where o.user_id = '1908189H7TNWDTXP'
and o.is_delete = 0
order by o.updated_time;

update order_status
set order_status = 30,
    deliver_time = ''
where order_id = '';

select *
from orders
where user_id = ''
and id = ''
and is_delete = 0;

update order_status
set order_status = 40,
    success_time = ''
where order_id = ''
and order_status = 30;

update orders
set is_delete = 1,
    updated_time = ''
where id = ''
  and user_id = '';