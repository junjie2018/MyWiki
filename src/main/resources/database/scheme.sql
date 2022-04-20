drop table if exists `t_user`;
create table `t_user`
(
    `id`          bigint auto_increment not null comment 'ID',
    `login_name`  varchar(50)           not null comment '登陆名',
    `name`        varchar(50) comment '昵称',
    `password`    char(32)              not null comment '密码',
    `creator`     bigint                not null default 0 comment '记录创建者',
    `modifier`    bigint                not null default 0 comment '记录编辑者',
    `create_time` timestamp             not null default 0 comment '记录创建时间',
    `modify_time` timestamp             not null default 0 comment '记录编辑时间',
    `version`     bigint                not null default 0 comment '乐观锁版本控制',
    `is_delete`   int                   not null default 0 comment '是否删除（0：未删除，1：已删除）',
    primary key (`id`),
    unique key `login_name_unique` (`login_name`)
) engine = innodb
  default charset = utf8mb4 comment ='用户';

drop table if exists `t_ebook`;
create table `t_ebook`
(
    `id`           bigint auto_increment not null comment 'ID',
    `name`         varchar(50) comment '名称',
    `category1_id` bigint comment '分类1',
    `category2_id` bigint comment '分类2',
    `description`  varchar(200) comment '描述',
    `cover`        varchar(200) comment '封面',
    `doc_count`    int                   not null default 0 comment '文档数',
    `view_count`   int                   not null default 0 comment '阅读数',
    `vote_count`   int                   not null default 0 comment '点赞数',
    `creator`      bigint                not null default 0 comment '记录创建者',
    `modifier`     bigint                not null default 0 comment '记录编辑者',
    `create_time`  timestamp             not null default 0 comment '记录创建时间',
    `modify_time`  timestamp             not null default 0 comment '记录编辑时间',
    `version`      bigint                not null default 0 comment '乐观锁版本控制',
    `is_delete`    int                   not null default 0 comment '是否删除（0：未删除，1：已删除）',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='电子书';

drop table if exists `t_category`;
create table `t_category`
(
    `id`          bigint auto_increment not null comment 'id',
    `parent`      bigint                not null default 0 comment '父id',
    `name`        varchar(50)           not null comment '名称',
    `creator`     bigint                not null default 0 comment '记录创建者',
    `modifier`    bigint                not null default 0 comment '记录编辑者',
    `create_time` timestamp             not null default 0 comment '记录创建时间',
    `modify_time` timestamp             not null default 0 comment '记录编辑时间',
    `version`     bigint                not null default 0 comment '乐观锁版本控制',
    `is_delete`   int                   not null default 0 comment '是否删除（0：未删除，1：已删除）',
    `sort`        int comment '顺序',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='分类';


-- 文档表
drop table if exists `t_doc`;
create table `t_doc`
(
    `id`          bigint      not null comment 'id',
    `ebook_id`    bigint      not null default 0 comment '电子书id',
    `parent`      bigint      not null default 0 comment '父id',
    `name`        varchar(50) not null comment '名称',
    `sort`        int comment '顺序',
    `view_count`  int                  default 0 comment '阅读数',
    `vote_count`  int                  default 0 comment '点赞数',
    `creator`     bigint      not null default 0 comment '记录创建者',
    `modifier`    bigint      not null default 0 comment '记录编辑者',
    `create_time` timestamp   not null default 0 comment '记录创建时间',
    `modify_time` timestamp   not null default 0 comment '记录编辑时间',
    `version`     bigint      not null default 0 comment '乐观锁版本控制',
    `is_delete`   int         not null default 0 comment '是否删除（0：未删除，1：已删除）',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='文档';

-- 文档内容
drop table if exists `t_content`;
create table `t_content`
(
    `id`          bigint     not null comment '文档id',
    `content`     mediumtext not null comment '内容',
    `creator`     bigint     not null default 0 comment '记录创建者',
    `modifier`    bigint     not null default 0 comment '记录编辑者',
    `create_time` timestamp  not null default 0 comment '记录创建时间',
    `modify_time` timestamp  not null default 0 comment '记录编辑时间',
    `version`     bigint     not null default 0 comment '乐观锁版本控制',
    `is_delete`   int        not null default 0 comment '是否删除（0：未删除，1：已删除）',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='文档内容';


-- 电子书快照表
drop table if exists `t_ebook_snapshot`;
create table `t_ebook_snapshot`
(
    `id`            bigint auto_increment not null comment 'id',
    `ebook_id`      bigint                not null default 0 comment '电子书id',
    `date`          date                  not null comment '快照日期',
    `view_count`    int                   not null default 0 comment '阅读数',
    `vote_count`    int                   not null default 0 comment '点赞数',
    `view_increase` int                   not null default 0 comment '阅读增长',
    `vote_increase` int                   not null default 0 comment '点赞增长',
    `creator`       bigint                not null default 0 comment '记录创建者',
    `modifier`      bigint                not null default 0 comment '记录编辑者',
    `create_time`   timestamp             not null default 0 comment '记录创建时间',
    `modify_time`   timestamp             not null default 0 comment '记录编辑时间',
    `version`       bigint                not null default 0 comment '乐观锁版本控制',
    `is_delete`     int                   not null default 0 comment '是否删除（0：未删除，1：已删除）',
    primary key (`id`),
    unique key `ebook_id_date_unique` (`ebook_id`, `date`)
) engine = innodb
  default charset = utf8mb4 comment ='电子书快照表';