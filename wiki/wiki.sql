select
       c.id as rootCatId,
       c.name as rootCatName,
       c.slogan as slogan,
       c.cat_image as catImage,
       c.bg_color as bgColor,
       i.id as itemId,
       i.item_name as itemName,
       ii.url as itemUrl,
       i.created_time as createdTime
from category c
left join items i
on c.id = i.root_cat_id
left join items_img ii
on i.id = ii.item_id
where c.type = 1
and i.root_cat_id = 7
and ii.is_main = 1
order by i.created_time desc
limit 0, 6;