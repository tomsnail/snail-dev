SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS test_data;
DROP TABLE IF EXISTS test_data_child;
DROP TABLE IF EXISTS test_data_main;
DROP TABLE IF EXISTS test_tree;




/* Create Tables */

CREATE TABLE qq_data
(
	id varchar(64) NOT NULL COMMENT '编号',
	testqq varchar(64) COMMENT 'test',
	create_by varchar(64) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(64) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = 'test';





