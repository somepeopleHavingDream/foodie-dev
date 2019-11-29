select i.id                    as itemId,
       i.item_name             as itemName,
       i.sell_counts           as sellCounts,
       ii.url                  as imgUrl,
       tempSpec.price_discount as price
from items i
         left join items_img ii
                   on i.id = ii.item_id
         left join
     (
         select item_id, min(price_discount) as price_discount
         from items_spec
         group by item_id
     ) tempSpec
     on i.id = tempSpec.item_id
where ii.is_main = 1;

select item_id, min(price_discount) as price_discount
from items_spec
group by item_id;
