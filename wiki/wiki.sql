select ic.comment_level as commentLevel,
    ic.content as content,
    ic.sepc_name as specName,
    ic.created_time as createdTime,
    u.face as userFace,
    u.nickname as nickname
from items_comments ic
left join users u
on ic.user_id = u.id
where ic.item_id = 'cake-1001'
and ic.comment_level = 1;