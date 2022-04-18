drop table if exists `user`;
create table `user`
(
    `id`         bigint auto_increment not null comment 'ID',
    `login_name` varchar(50) not null comment '登陆名',
    `name`       varchar(50) comment '昵称',
    `password`   char(32)    not null comment '密码',
    primary key (`id`),
    unique key `login_name_unique` (`login_name`)
) engine = innodb
  default charset = utf8mb4 comment ='用户';


drop table if exists `ebook`;
create table `ebook`
(
    `id`           bigint auto_increment not null comment 'ID',
    `name`         varchar(50) comment '名称',
    `category1_id` bigint comment '分类1',
    `category2_id` bigint comment '分类2',
    `description`  varchar(200) comment '描述',
    `cover`        varchar(200) comment '封面',
    `doc_count`    int not null default 0 comment '文档数',
    `view_count`   int not null default 0 comment '阅读数',
    `vote_count`   int not null default 0 comment '点赞数',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='电子书';


drop table if exists `category`;
create table `category`
(
    `id`     bigint auto_increment not null comment 'id',
    `parent` bigint      not null default 0 comment '父id',
    `name`   varchar(50) not null comment '名称',
    `sort`   int comment '顺序',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='分类';