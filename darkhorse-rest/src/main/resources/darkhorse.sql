drop darkhorse if exists darkhorse;
create database darkhorse character set utf8;
use darkhorse;
set names utf8;

drop table if exists car_info;
create table car_info (
  id bigint not null auto_increment,
  driver_id bigint default null,
  car_color varchar(8) default null comment '车辆颜色',
  car_type varchar(16) default null comment '车型，如：奥迪A4L',
  car_no varchar(16) default null comment '行驶证',
  car_no_img varchar(16) default null comment '行驶证图片',
  license_no varchar(16) default null comment '车牌号',
  seat_size tinyint(4) default null comment '座位数',
  del_status tinyint(4) default 0 comment '数据状态，0:正常，1:删除',
  marks varchar(256) default null,
  create_time datetime not null default current_timestamp,
  update_time datetime not null default current_timestamp on update current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8 comment '车辆信息表';

-- ----------------------------
-- table structure for journey
-- ----------------------------
drop table if exists driver_journey;
create table driver_journey (
  id bigint not null auto_increment,
  driver_id bigint default null comment '车主id',
  start_city varchar(16) default null comment '出发城市',
  start_addr varchar(16) default null comment '出发地址',
  start_time datetime default null null comment '出发时间',
  end_city varchar(16) default null comment '到达城市',
  end_addr varchar(16) default null comment '到达地址',
  seat_size tinyint(4) default null comment '总座位数',
  booked_seat_size tinyint(4) default null comment '已订座位数',
  price decimal(8,2) default null comment '座位价格',
  extra_price decimal(8,2) default null comment '额外价格',
  journey_type tinyint(4) default 0 comment '行程类型，0:车主的发布，1:乘客的发布',
  journey_status tinyint(4) default 0 comment '行程状态，0:完成，1:未完成',
  del_status tinyint(4) default '0' comment '0:正常，1:删除',
  marks varchar(256) default null,
  create_time datetime not null default current_timestamp,
  update_time datetime not null default current_timestamp on update current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8 comment '司机行程信息表';

-- ----------------------------
-- table structure for passenger_journey
-- ----------------------------
drop table if exists passenger_journey;
create table passenger_journey (
  id bigint not null auto_increment,
  journey_id bigint default null,
  driver_id bigint default null comment '车主id',
  driver_status tinyint(4) default '0' comment '司机状态，0:未到达出发地点，1:已到达出发地点，2：行程中，3：到达目的地',
  passenger_id bigint default null comment '乘客id',
  passenger_status tinyint(4) default '0' comment '乘客状态，0:未上车，1:已上车，2：行程中，3：已付款',
  driver_comment tinyint default null comment '司机评价：0：未评价，1：一星，2：二星。。。',
  passenger_comment tinyint default null comment '乘客评价：0：未评价，1：一星，2：二星。。。',
  driver_marks varchar(256) default null comment '司机备注',
  passenger_marks varchar(256) default null comment '乘客备注',
  del_status tinyint(4) default '0' comment '0:正常，1:删除',
  create_time datetime not null default current_timestamp,
  update_time datetime not null default current_timestamp on update current_timestamp,
  primary key (id)
) engine=innodb default charset=utf8 comment '乘客打车信息表';

-- ----------------------------
-- table structure for region
-- ----------------------------
drop table if exists region;
create table region (
  id varchar(255) default null,
  name varchar(255) default null,
  parent_id varchar(255) default null,
  short_name varchar(255) default null,
  level_type varchar(255) default null,
  city_code varchar(255) default null,
  zip_code varchar(255) default null,
  merger_name varchar(255) default null,
  lng varchar(255) default null,
  lat varchar(255) default null,
  pinyin varchar(255) default null
) engine=innodb default charset=utf8;

-- ----------------------------
-- table structure for users
-- ----------------------------
drop table if exists users;
create table users (
  id bigint(20) not null auto_increment,
  real_name varchar(32) default null,
  login_name varchar(32) default null,
  password varchar(32) default null,
  id_number varchar(32) default null,
  driving_license varchar(32) default null comment '驾照编号',
  driving_license_img varchar(32) default null comment '驾照图片',
  sex tinyint(4) default '0' comment '0:默认，1:男，2:女',
  photo_name varchar(256) default null,
  user_type tinyint(4) default '0' comment '0:普通用户，1:司机用户',
  credit_score float comment '信用分',
  city varchar(16) default null,
  province varchar(16) default null,
  last_login_time datetime default null,
  create_time datetime not null default current_timestamp,
  update_time datetime not null default current_timestamp on update current_timestamp,
  del_status tinyint(4) default '0' comment '0:正常，1:删除',
  marks varchar(256) default null,
  primary key (id),
  key login_name_index (login_name)
) engine=innodb default charset=utf8 comment '用户表';

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `totle_amount` decimal(16,2) DEFAULT NULL,
  `available_amount` decimal(16,2) DEFAULT NULL,
  `freeze_amount` decimal(16,2) DEFAULT NULL,
   del_status tinyint(4) default '0' comment '0:正常，1:删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `marks` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户表';

CREATE TABLE `bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_no` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `user_id` bigint(20) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `amount` decimal(16,2) DEFAULT NULL,
  `transaction_type` tinyint(4) DEFAULT NULL COMMENT '交易类型，1:充值，2：转账，3：提现',
  `channel_type` tinyint(4) DEFAULT NULL COMMENT '渠道类型，1:微信，2:支付宝',
  `pay_account` varchar(64) DEFAULT NULL COMMENT '支付账号',
  `pay_bill_no` varchar(64) DEFAULT NULL COMMENT '支付平台订单号',
  `status` tinyint(4) DEFAULT NULL COMMENT '交易状态，1:成功，2:失败，3：进行中',
   del_status tinyint(4) default '0' comment '0:正常，1:删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `marks` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1172 DEFAULT CHARSET=utf8 COMMENT='账单表';
