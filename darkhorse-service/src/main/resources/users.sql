CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(32) DEFAULT NULL,
  `login_name` varchar(32) DEFAULT NULL,
  `PASSWORD` varchar(32) DEFAULT NULL,
  `id_number` varchar(32) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT '0' COMMENT '0:默认，1:男，2:女',
  `photo_name` varchar(256) DEFAULT NULL,
  `user_type` tinyint(4) DEFAULT '0' COMMENT '0:普通用户，1:司机',
  `status` tinyint(4) DEFAULT '0' COMMENT '0:正常，1:删除',
  `city_code` varchar(16) DEFAULT NULL,
  `province_code` varchar(16) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `marks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `login_name_index` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=336 DEFAULT CHARSET=utf8;


INSERT INTO `users` (`id`, `real_name`, `login_name`, `PASSWORD`, `id_number`, `sex`, `photo_name`, `user_type`, `status`, `city_code`, `province_code`, `last_login_time`, `create_time`, `update_time`, `marks`) VALUES (333, 'xx', 'cguowei', '11111', '11111111111', 1, '1', 1, 1, '1', '1', '2017-11-12 11:06:34', '2017-11-12 11:06:15', '2017-11-30 11:06:18', 'd');
INSERT INTO `users` (`id`, `real_name`, `login_name`, `PASSWORD`, `id_number`, `sex`, `photo_name`, `user_type`, `status`, `city_code`, `province_code`, `last_login_time`, `create_time`, `update_time`, `marks`) VALUES (334, 'yy', 'cguowei', '22222', '22222222222', 1, '2', 12, 12, '12', '12', '2017-11-12 11:06:34', '2017-11-12 11:06:15', '2017-12-24 11:40:27', 'd2');
INSERT INTO `users` (`id`, `real_name`, `login_name`, `PASSWORD`, `id_number`, `sex`, `photo_name`, `user_type`, `status`, `city_code`, `province_code`, `last_login_time`, `create_time`, `update_time`, `marks`) VALUES (335, 'ww', 'wwww', 'www', 'www', 1, NULL, 0, 0, NULL, 'xxx', NULL, '2017-12-24 11:42:53', '2017-12-24 11:43:04', NULL);
