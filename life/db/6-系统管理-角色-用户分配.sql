SELECT
    t1.user_id as id,
    t1.username as name,
    t2.role_id
FROM
    sys_user_table t1
        LEFT JOIN
    (SELECT
        a.user_id, a.username, b.role_id, b.role_name
    FROM
        sys_user_table a
    JOIN (sys_role_table b
    JOIN sys_role_user_table c ON c.role_id = b.role_id) ON c.user_id = a.user_id
    WHERE
        b.role_id = '1') t2 ON t2.user_id = t1.user_id