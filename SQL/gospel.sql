create table course
(
    id          int auto_increment
        primary key,
    course_name varchar(50)   null,
    teacher_id  int           null,
    `group`     varchar(100)  null,
    status      int default 1 null,
    constraint id
        unique (id)
);

create table friend
(
    id          int auto_increment
        primary key,
    user_from   int      null,
    friend_id   int      null,
    create_time datetime null,
    constraint id
        unique (id)
);

create table friend_request
(
    id        int auto_increment
        primary key,
    user_from int      null,
    user_to   int      null,
    status    int      null,
    send_time datetime null,
    constraint id
        unique (id)
);

create table `group`
(
    id          int auto_increment
        primary key,
    group_name  varchar(50)   null,
    photo       varchar(1500) null,
    create_time datetime      null,
    profile     varchar(400)  null comment '简介'
)
    comment '群表';

create table group_member
(
    id            int auto_increment
        primary key,
    group_id      int           null,
    user_id       int           null,
    member_type   varchar(50)   null,
    member_status int default 1 null comment '是否被移除群聊'
)
    comment '群成员表';

create table group_message
(
    id              int auto_increment
        primary key,
    user_from       int            null,
    group_id        int            null,
    sender_nickname varchar(100)   null,
    sender_photo    varchar(2000)  null,
    send_time       datetime       null,
    file_raw_name   varchar(1000)  null,
    message_type    varchar(100)   null,
    message         varchar(10000) null,
    is_read         varchar(1000)  null,
    constraint id
        unique (id)
);

create table major
(
    id      int auto_increment
        primary key,
    college varchar(50) null,
    major   varchar(50) null,
    constraint id
        unique (id)
);

create table single_message
(
    id              int auto_increment
        primary key,
    user_from       int            null,
    user_to         int            null,
    sender_nickname varchar(100)   null,
    sender_photo    varchar(2000)  null,
    send_time       datetime       null,
    file_raw_name   varchar(1000)  null,
    message_type    varchar(100)   null,
    message         varchar(10000) null,
    is_read         int            null
)
    comment '消息表';

create table user
(
    id       int auto_increment
        primary key,
    number   varchar(30)    null comment '学号/教师工号',
    name     varchar(30)    null comment '姓名',
    password varchar(100)   null comment '密码',
    flag     int            null comment '管理员/老师/学生',
    photo    varchar(2000)  null comment '头像',
    college  varchar(50)    null,
    major    varchar(50)    null,
    profile  varchar(10000) null comment '简介',
    status   int            null comment '登录状态',
    constraint id
        unique (id)
);


