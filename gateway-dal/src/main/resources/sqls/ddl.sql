CREATE TABLE `telephone_bill_prepaid` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_no` varchar(20) DEFAULT NULL COMMENT '订单号',
  `channel_api` varchar(32) DEFAULT NULL COMMENT '渠道API',
  `phone_no` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `currency` varchar(3) DEFAULT 'CNY' COMMENT '交易币种',
  `amount` decimal(11,0) DEFAULT '0' COMMENT '应付额，单位：分',
  `status` varchar(16) DEFAULT NULL COMMENT '交易状态\r\nIT:初始\r\nAS:异步处理\r\nBP:已报银行\r\nBS:银行成功\r\nBF:银行失败\r\nBE:银行异常(需要对账或查询处理)',
  `trans_date` datetime DEFAULT NULL COMMENT '交易日期',
  `result_code` varchar(24) DEFAULT NULL COMMENT '结果编码',
  `result_message` varchar(255) DEFAULT NULL COMMENT '结果信息',
  `raw_add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据新增时间',
  `raw_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
