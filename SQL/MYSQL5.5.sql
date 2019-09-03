CREATE TABLE `t_automissionreg` (
  `regkey` varchar(60) NOT NULL COMMENT '自动任务表主键',
  `exectime` varchar(8) DEFAULT NULL COMMENT '自动任务执行时间, 格式:HH:MI:SS; 例如：20:30:00，iscycle=1的时候不判断此处',
  `execPerMinu` varchar(2) DEFAULT NULL COMMENT 'cycle=1的时候，根据此配置为分钟，每小时执行一次',
  `regname` varchar(100) NOT NULL COMMENT '自动任务名字',
  `regclass` varchar(200) NOT NULL COMMENT '类名, 例如: com.xx.xx.xxx',
  `regmethod` varchar(100) NOT NULL COMMENT '执行方法, 非构造函数, 方法不能带参数',
  `regdate` datetime DEFAULT NULL COMMENT '注册时间',
  `isactive` varchar(1) NOT NULL DEFAULT '1' COMMENT '是否有效, 0-无效, 1-有效',
  `iscycle` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否循环执行: 0-否，1-是',
  `isholiday` varchar(1) DEFAULT '1' COMMENT '节假日是否执行：0-否，1-执行',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`regkey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO t_automissionreg VALUES ('QQQQQ', '23:40', '36', 'com.lqr.util.MissionOne', 'com.lqr.util.MissionOne', 'doThings', '2019-06-19 23:23:16', '1', '1', '1', 'test1');
INSERT INTO t_automissionreg VALUES ('QQQQQ2', '23:40', '36', 'com.lqr.util.MissionTwo', 'com.lqr.util.MissionTwo', 'doThingTwo', '2019-06-19 23:24:38', '1', '1', '1', 'test2');
