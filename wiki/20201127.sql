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