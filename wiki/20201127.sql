select * from order_items where order_id = '';

select ic.id as commentId,
       ic.content as content,
       ic.created_time as createdTime,
       ic.item_id as itemId,
       ic.item_name as itemName,
       ic.sepc_name as sepcName,
       ii.url as itemImg
from items_comments ic
left join items_img ii
on ic.item_id = ii.item_id
where ic.user_id = ''
and ii.is_main = 1
order by ic.created_time
desc;

select count(*)
from orders o
left join order_status os
on o.id = os.order_id
where o.user_id = ''
and os.order_status = 10;
# and o.is_comment = 0;

select os.order_id as orderId,
       os.order_status as orderStatus,
       os.created_time as createdTime,
       os.pay_time as payTime,
       os.deliver_time as deliverTime,
       os.success_time as sucessTime,
       os.close_time as closeTime,
       os.comment_time as commentTime
from orders o
left join order_status os
on o.id = os.order_id
where o.is_delete = 0
and o.user_id = ''
and os.order_status in (20, 30, 40)
order by os.order_id
desc;