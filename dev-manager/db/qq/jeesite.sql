SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Indexes */

DROP INDEX oa_leave_create_by ON oa_test_act;
DROP INDEX oa_leave_process_instance_id ON oa_test_act;
DROP INDEX oa_leave_del_flag ON oa_test_act;
DROP INDEX test_data_del_flag ON qq_data;
DROP INDEX test_data_del_flag ON sub_qq_data;
DROP INDEX oa_leave_create_by ON yh_test_ptree;
DROP INDEX oa_leave_process_instance_id ON yh_test_ptree;
DROP INDEX oa_leave_del_flag ON yh_test_ptree;
DROP INDEX oa_leave_create_by ON yh_test_tree;
DROP INDEX oa_leave_process_instance_id ON yh_test_tree;
DROP INDEX oa_leave_del_flag ON yh_test_tree;



/* Drop Tables */

DROP TABLE IF EXISTS oa_test_act;
DROP TABLE IF EXISTS sub_qq_data;
DROP TABLE IF EXISTS qq_data;
DROP TABLE IF EXISTS yh_test_ptree;
DROP TABLE IF EXISTS yh_test_tree;




/* Create Tables */

-- 测试流程表
CREATE TABLE oa_test_act
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 流程实例编号
	process_instance_id varchar(64) COMMENT '流程实例编号',
	-- 开始时间
	start_time datetime COMMENT '开始时间',
	-- 结束时间
	end_time datetime COMMENT '结束时间',
	-- 请假理由
	reason varchar(255) COMMENT '请假理由',
	-- 申请时间
	apply_time datetime COMMENT '申请时间',
	-- 实际开始时间
	reality_start_time datetime COMMENT '实际开始时间',
	-- 实际结束时间
	reality_end_time datetime COMMENT '实际结束时间',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '测试流程表';


-- 业务数据表
CREATE TABLE qq_data
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- test
	subtestqq varchar(64) COMMENT 'test',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '业务数据表';


-- 业务数据表
CREATE TABLE sub_qq_data
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 主表编号
	mainid varchar(64) NOT NULL COMMENT '主表编号',
	-- test
	subtestqq varchar(64) COMMENT 'test',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '业务数据表';


-- 平台类目
CREATE TABLE yh_test_ptree
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 请假理由
	name varchar(255) COMMENT '请假理由',
	parent_id varchar(64),
	-- 所有父节点
	parent_ids varchar(2000) COMMENT '所有父节点',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	-- 基础类目ID
	base_id varchar(64) COMMENT '基础类目ID',
	PRIMARY KEY (id)
) COMMENT = '平台类目';


-- 树测试
CREATE TABLE yh_test_tree
(
	-- 编号
	id varchar(64) NOT NULL COMMENT '编号',
	-- 请假理由
	name varchar(255) COMMENT '请假理由',
	parent_id varchar(64),
	-- 所有父节点
	parent_ids varchar(2000) COMMENT '所有父节点',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '树测试';



/* Create Foreign Keys */

ALTER TABLE sub_qq_data
	ADD FOREIGN KEY (mainid)
	REFERENCES qq_data (id)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
;


ALTER TABLE yh_test_ptree ADD FOREIGN KEY (base_id) REFERENCES yh_test_tree (id) ON UPDATE NO ACTION ON DELETE NO ACTION;



/* Create Indexes */

CREATE INDEX oa_leave_create_by ON oa_test_act ();
CREATE INDEX oa_leave_process_instance_id ON oa_test_act ();
CREATE INDEX oa_leave_del_flag ON oa_test_act ();
CREATE INDEX test_data_del_flag ON qq_data ();
CREATE INDEX test_data_del_flag ON sub_qq_data ();
CREATE INDEX oa_leave_create_by ON yh_test_ptree ();
CREATE INDEX oa_leave_process_instance_id ON yh_test_ptree ();
CREATE INDEX oa_leave_del_flag ON yh_test_ptree ();
CREATE INDEX oa_leave_create_by ON yh_test_tree ();
CREATE INDEX oa_leave_process_instance_id ON yh_test_tree ();
CREATE INDEX oa_leave_del_flag ON yh_test_tree ();



