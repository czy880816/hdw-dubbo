/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : hdw_dubbo

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 13/11/2019 16:14:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('hdw-base-Scheduler', 'TASK_1194222031296348161', 'DEFAULT', '*/5 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('hdw-base-Scheduler', 'TASK_1194222031296348161', 'DEFAULT', NULL, 'com.hdw.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720024636F6D2E6864772E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000016E5F7774597874000D2A2F35202A202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078701092BA9C365D400174000474657374740006E6B58BE8AF95740012E6B58BE8AF95E5AE9AE697B6E4BBBBE58AA1737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('hdw-dss-Scheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('hdw-dss-Scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('das-Scheduler', 'WIN-7COM6EF6BDG1573437847561', 1573526307782, 15000);
INSERT INTO `qrtz_scheduler_state` VALUES ('hdw-base-Scheduler', 'WIN-7COM6EF6BDG1573631958592', 1573632878911, 15000);
INSERT INTO `qrtz_scheduler_state` VALUES ('hdw-dss-Scheduler', 'WIN-7COM6EF6BDG1573194731709', 1573195619932, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('hdw-base-Scheduler', 'TASK_1194222031296348161', 'DEFAULT', 'TASK_1194222031296348161', 'DEFAULT', NULL, 1573559720000, 1573559715000, 5, 'PAUSED', 'CRON', 1573559694000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for t_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `t_enterprise`;
CREATE TABLE `t_enterprise`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `prefix` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业id前缀',
  `business_license_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业注册码(工商注册码-三证合一)',
  `enterprise_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `enterprise_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `industry_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属行业',
  `area_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属区域',
  `enterprise_type` tinyint(4) NULL DEFAULT NULL COMMENT '企业类型(国企:0，民企:1，私企:2，外企:3)',
  `telephone` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业联系电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业邮箱',
  `zip_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `legal_person` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人',
  `main_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业负责人姓名',
  `main_person_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业负责人移动电话号码',
  `main_person_telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业负责人固定电话号码',
  `safe_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业安全负责人姓名',
  `safe_person_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业安全负责人移动电话号码',
  `safe_person_telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业安全负责人固定电话号码',
  `map_x` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'x坐标',
  `map_y` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'y坐标',
  `map_z` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'z坐标',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `is_sync` tinyint(4) NULL DEFAULT 1 COMMENT '数据是否同步(0:是,1:否)',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '企业状态（0-正常，1-禁用）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后修改时间',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录创建者(用户)',
  `update_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录最后修改者(用户)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `enterprise_id`(`enterprise_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_enterprise
-- ----------------------------
INSERT INTO `t_enterprise` VALUES ('1', 'ccty', '20181218', '96', '测试企业1', '0', '0', 1, '13888888888', 'tuminglong@126.com', '430071', '测试人', '测试人', '13888888888', '027-88888888', '测试人', '13888888888', '027-88888888', '100', '100', '100', '测试', 0, 0, '2018-12-18 10:56:46', '2019-11-12 09:04:17', NULL, 'admin');

-- ----------------------------
-- Table structure for t_enterprise_department
-- ----------------------------
DROP TABLE IF EXISTS `t_enterprise_department`;
CREATE TABLE `t_enterprise_department`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业部门父ID',
  `enterprise_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业ID(对应企业主表ID)',
  `department_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门代码(可添加多个部门ID，用逗号隔开，表示该部门可以管理多个部门)',
  `department_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后修改时间',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录创建者(用户)',
  `update_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录最后修改者(用户)',
  `parameter1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留1',
  `parameter2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留2',
  `is_sync` tinyint(4) NULL DEFAULT NULL COMMENT '数据是否同步(0:是,1:否)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `enterprise_id`(`enterprise_id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE,
  CONSTRAINT `t_enterprise_department_ibfk_1` FOREIGN KEY (`enterprise_id`) REFERENCES `t_enterprise` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_enterprise_department
-- ----------------------------
INSERT INTO `t_enterprise_department` VALUES ('HDWX05d31d573a374105', 'HDWX9997b6ed32af420a', '1', '3006', '采矿分公司协力工区', '2018-10-09 14:35:05', '2018-10-09 14:35:05', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX0e5b6fe512414a1c', 'HDWX9997b6ed32af420a', '1', '3008', '采矿分公司支护队', '2018-10-09 14:36:23', '2018-10-09 14:36:23', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX0fb216859fd548ae', 'HDWX9997b6ed32af420a', '1', '3007', '采矿分公司检修工区', '2018-10-09 14:35:49', '2018-10-09 14:35:50', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX0fce002cc5f849bf', 'HDWX7b3407a0a538479d', '1', '4005', '采矿分公司运转工区班组', '2018-10-19 15:32:23', '2018-10-19 15:32:23', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX2f5c5ef772f54f9a', 'HDWX47af07c64d324c19', '1', '4003', '采矿分公司井运工区班组', '2018-10-19 15:31:24', '2018-10-19 15:31:24', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX34fd73fdc8264d11', 'HDWX0e5b6fe512414a1c', '1', '4008', '采矿分公司支护队班组', '2018-10-19 15:34:09', '2018-10-19 15:34:09', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX3e09d8a174c54bb8', 'HDWX3fce6df608e04571', '1', '4002', '采矿分公司西采工区班组', '2018-10-19 15:30:45', '2018-10-19 15:30:45', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX3fce6df608e04571', 'HDWX9997b6ed32af420a', '1', '3002', '采矿分公司西采工区', '2018-10-09 14:28:59', '2018-10-09 14:31:40', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX42a69630f8d647dd', 'HDWX05d31d573a374105', '1', '4006', '采矿分公司协力工区班组', '2018-10-19 15:33:04', '2018-10-19 15:33:04', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX47af07c64d324c19', 'HDWX9997b6ed32af420a', '1', '3003', '采矿分公司井运工区', '2018-10-09 14:30:02', '2018-10-09 14:32:45', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX49d0b0ab995a40c3', 'HDWXdee3eb46abec43f9', '1', '4001', '采矿分公司东采工区班组', '2018-10-19 15:29:51', '2018-10-19 15:29:51', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX4e8576953a1f460a', 'HDWXb88f0f90d9694de0', '1', '1001', '测试部门', '2018-12-18 14:55:53', '2019-11-12 09:11:01', 'admin', 'admin', '', '', 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX673e46d94e2f495e', '0', '1', '1000', '总公司', '2018-09-18 11:10:23', '2018-12-18 14:26:59', 'admin', 'admin', NULL, NULL, 1);
INSERT INTO `t_enterprise_department` VALUES ('HDWX68a4d19600854580', 'HDWXebe994e258ff4145', '1', '4004', '采矿分公司提升工区班组', '2018-10-19 15:31:57', '2018-10-19 15:31:57', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX7b3407a0a538479d', 'HDWX9997b6ed32af420a', '1', '3005', '采矿分公司运转工区', '2018-10-09 14:31:02', '2018-10-09 14:32:58', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX95f6b6415d084faa', 'HDWXa53d95263a8349dc', '1', '2000', '采矿分公司', '2018-08-02 14:13:52', '2018-10-09 14:27:44', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWX9997b6ed32af420a', 'HDWX95f6b6415d084faa', '1', '2001', '采矿分公司办公室', '2018-10-09 14:25:26', '2018-10-09 14:28:01', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWXa53d95263a8349dc', 'HDWX673e46d94e2f495e', '1', '1001', '总公司办公室', '2018-09-17 14:47:18', '2018-10-09 14:20:03', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWXb88f0f90d9694de0', 'HDWXa53d95263a8349dc', '1', '2003', '选矿分公司', '2018-08-02 14:14:22', '2018-10-09 14:22:01', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWXd62572962fa74081', 'HDWX0fb216859fd548ae', '1', '4007', '采矿分公司检修工区班组', '2018-10-19 15:33:42', '2018-10-19 15:33:42', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWXdee3eb46abec43f9', 'HDWX9997b6ed32af420a', '1', '3001', '采矿分公司东采工区', '2018-10-09 14:26:34', '2018-10-09 14:32:03', 'admin', 'admin', NULL, NULL, 0);
INSERT INTO `t_enterprise_department` VALUES ('HDWXebe994e258ff4145', 'HDWX9997b6ed32af420a', '1', '3004', '采矿分公司提升工区', '2018-10-09 14:30:29', '2018-10-09 14:33:18', 'admin', 'admin', NULL, NULL, 0);

-- ----------------------------
-- Table structure for t_enterprise_job
-- ----------------------------
DROP TABLE IF EXISTS `t_enterprise_job`;
CREATE TABLE `t_enterprise_job`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业部门表ID',
  `job_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务代码',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务名称',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录最后更新时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录创建用户',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录最后更新用户',
  `parameter1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留1',
  `parameter2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留2',
  `is_sync` tinyint(4) NULL DEFAULT NULL COMMENT '是否同步（0：是，1：否）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `job_name`(`job_name`) USING BTREE,
  INDEX `job_code`(`job_code`) USING BTREE,
  INDEX `department_id`(`department_id`) USING BTREE,
  CONSTRAINT `t_enterprise_job_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `t_enterprise_department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业职务配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_enterprise_job
-- ----------------------------
INSERT INTO `t_enterprise_job` VALUES ('HDWX63c73550701448e6', 'HDWX4e8576953a1f460a', '10001', '测试工程师', '2019-11-12 09:15:54', '2019-11-12 09:15:54', 'admin', 'admin', '', '', 0);

-- ----------------------------
-- Table structure for t_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_job`;
CREATE TABLE `t_schedule_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1194222031296348162 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_schedule_job
-- ----------------------------
INSERT INTO `t_schedule_job` VALUES (1159404137228144642, 'smsTask', 'pushHomeUnreadMessage', '', '*/5 * * * * ?', 1, '推送首页最近5条消息及未读消息总数', '2019-08-08 18:01:01');
INSERT INTO `t_schedule_job` VALUES (1159404328370966530, 'smsTask', 'pushPageMsg', '', '*/5 * * * * ?', 0, '推送实时页面消息', '2019-08-08 18:01:47');
INSERT INTO `t_schedule_job` VALUES (1194222031296348161, 'testTask', 'test', '测试', '*/5 * * * * ?', 1, '测试定时任务', '2019-11-12 19:54:54');

-- ----------------------------
-- Table structure for t_schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_job_log`;
CREATE TABLE `t_schedule_job_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1194222121725542402 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_schedule_job_log
-- ----------------------------
INSERT INTO `t_schedule_job_log` VALUES (1194222037952708610, 1194222031296348161, 'testTask', 'test', '测试', 0, NULL, 1001, '2019-11-12 19:54:55');
INSERT INTO `t_schedule_job_log` VALUES (1194222058764845057, 1194222031296348161, 'testTask', 'test', '测试', 0, NULL, 1000, '2019-11-12 19:55:00');
INSERT INTO `t_schedule_job_log` VALUES (1194222080956907522, 1194222031296348161, 'testTask', 'test', '测试', 0, NULL, 1001, '2019-11-12 19:55:05');
INSERT INTO `t_schedule_job_log` VALUES (1194222100770799617, 1194222031296348161, 'testTask', 'test', '测试', 0, NULL, 1002, '2019-11-12 19:55:10');
INSERT INTO `t_schedule_job_log` VALUES (1194222121725542401, 1194222031296348161, 'testTask', 'test', '测试', 0, NULL, 1001, '2019-11-12 19:55:15');

-- ----------------------------
-- Table structure for t_sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dic`;
CREATE TABLE `t_sys_dic`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) NOT NULL COMMENT '父变量ID',
  `var_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变量代码',
  `var_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变量名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录创建者（用户）',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录最后修改者（用户）',
  `is_sync` tinyint(4) NULL DEFAULT NULL COMMENT '数据是否同步(0:是,1:否)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_dic_code`(`var_code`) USING BTREE,
  INDEX `index_dic_name`(`var_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_dic
-- ----------------------------
INSERT INTO `t_sys_dic` VALUES (9, 0, '2017052', '行业', '2018-05-07 20:27:53', '2018-06-14 19:19:12', 'admin', 'admin', NULL);
INSERT INTO `t_sys_dic` VALUES (16, 0, '20184049', '区域', '2018-05-07 20:28:52', '2018-05-07 20:28:54', 'admin', 'admin', NULL);

-- ----------------------------
-- Table structure for t_sys_file
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file`;
CREATE TABLE `t_sys_file`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `table_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件类型(哪个表的附件)',
  `record_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attachment_group` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表的记录Id下的附件分组的组名',
  `attachment_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件路径',
  `attachment_type` tinyint(4) NULL DEFAULT NULL COMMENT '附件类型(0-word,1-excel,2-pdf,3-jpg,png,4-其他)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后修改时间',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录创建者(用户)',
  `update_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录最后修改者(用户)',
  `is_sync` tinyint(4) NULL DEFAULT NULL COMMENT '数据是否同步(0:是,1:否)',
  `enterprise_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `save_type` tinyint(4) NULL DEFAULT NULL COMMENT '存储类型（0：本地存储，1:fastdfs）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_sys_file_table_id`(`table_id`) USING BTREE,
  INDEX `t_sys_file_rrecord_id`(`record_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_file
-- ----------------------------
INSERT INTO `t_sys_file` VALUES ('1181777897439088641', 't_lives', 'dss_6479201903121903445', '1', '1828f6c0-8910-4f8f-84e6-78bb244e6740.png', '/static/upload/qr/1828f6c0-8910-4f8f-84e6-78bb244e6740.png', 3, '2019-10-09 11:46:22', '2019-10-09 11:46:22', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1181778288348221442', 't_lives', 'dss_9416201906202006372', '1', '56c0f39c-4b8f-47e8-8871-aebff62cd570.png', '/static/upload/qr/56c0f39c-4b8f-47e8-8871-aebff62cd570.png', 3, '2019-10-09 11:47:55', '2019-10-09 11:47:55', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1181904405734305793', 't_lives', 'dss_3490201903121903245', '1', '15cc740b-d76d-478f-b68a-f5ab9dfffcaa.png', '/static/upload/qr/15cc740b-d76d-478f-b68a-f5ab9dfffcaa.png', 3, '2019-10-09 20:09:04', '2019-10-09 20:09:04', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1181977623132344321', 't_snaps', '1181505897801928706', 'snap', 'dss_3287201907221907170_1570640400087.jpg', 'http://172.16.5.94:81/snap/dss_3287201907221907170/dss_3287201907221907170_1570640400087.jpg', 3, '2019-10-10 01:00:00', '2019-10-10 01:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1181977623182675970', 't_snaps', '1181506032111931393', 'snap', 'dss_1204201907221907624_1570640400101.jpg', 'http://172.16.5.94:81/snap/dss_1204201907221907624/dss_1204201907221907624_1570640400101.jpg', 3, '2019-10-10 01:00:00', '2019-10-10 01:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1181977623258173442', 't_snaps', '1181524808593264641', 'snap', 'dss_6674201903121903133_1570640400121.jpg', 'http://172.16.5.94:81/snap/dss_6674201903121903133/dss_6674201903121903133_1570640400121.jpg', 3, '2019-10-10 01:00:00', '2019-10-10 01:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1181992722588995585', 't_snaps', '1181505897801928706', 'snap', 'dss_3287201907221907170_1570644000077.jpg', 'http://172.16.5.94:81/snap/dss_3287201907221907170/dss_3287201907221907170_1570644000077.jpg', 3, '2019-10-10 02:00:00', '2019-10-10 02:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1181992722639327233', 't_snaps', '1181506032111931393', 'snap', 'dss_1204201907221907624_1570644000092.jpg', 'http://172.16.5.94:81/snap/dss_1204201907221907624/dss_1204201907221907624_1570644000092.jpg', 3, '2019-10-10 02:00:00', '2019-10-10 02:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1181992722693853186', 't_snaps', '1181524808593264641', 'snap', 'dss_6674201903121903133_1570644000105.jpg', 'http://172.16.5.94:81/snap/dss_6674201903121903133/dss_6674201903121903133_1570644000105.jpg', 3, '2019-10-10 02:00:00', '2019-10-10 02:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182007822167281665', 't_snaps', '1181505897801928706', 'snap', 'dss_3287201907221907170_1570647600092.jpg', 'http://172.16.5.94:81/snap/dss_3287201907221907170/dss_3287201907221907170_1570647600092.jpg', 3, '2019-10-10 03:00:00', '2019-10-10 03:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182007822242779137', 't_snaps', '1181506032111931393', 'snap', 'dss_1204201907221907624_1570647600113.jpg', 'http://172.16.5.94:81/snap/dss_1204201907221907624/dss_1204201907221907624_1570647600113.jpg', 3, '2019-10-10 03:00:00', '2019-10-10 03:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182007822314082305', 't_snaps', '1181524808593264641', 'snap', 'dss_6674201903121903133_1570647600138.jpg', 'http://172.16.5.94:81/snap/dss_6674201903121903133/dss_6674201903121903133_1570647600138.jpg', 3, '2019-10-10 03:00:00', '2019-10-10 03:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182022921955282945', 't_snaps', '1181505897801928706', 'snap', 'dss_3287201907221907170_1570651200168.jpg', 'http://172.16.5.94:81/snap/dss_3287201907221907170/dss_3287201907221907170_1570651200168.jpg', 3, '2019-10-10 04:00:00', '2019-10-10 04:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182022922009808898', 't_snaps', '1181506032111931393', 'snap', 'dss_1204201907221907624_1570651200183.jpg', 'http://172.16.5.94:81/snap/dss_1204201907221907624/dss_1204201907221907624_1570651200183.jpg', 3, '2019-10-10 04:00:00', '2019-10-10 04:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182022922139832321', 't_snaps', '1181524808593264641', 'snap', 'dss_6674201903121903133_1570651200203.jpg', 'http://172.16.5.94:81/snap/dss_6674201903121903133/dss_6674201903121903133_1570651200203.jpg', 3, '2019-10-10 04:00:00', '2019-10-10 04:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182038021202219009', 't_snaps', '1181505897801928706', 'snap', 'dss_3287201907221907170_1570654800097.jpg', 'http://172.16.5.94:81/snap/dss_3287201907221907170/dss_3287201907221907170_1570654800097.jpg', 3, '2019-10-10 05:00:00', '2019-10-10 05:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182038021378379777', 't_snaps', '1181506032111931393', 'snap', 'dss_1204201907221907624_1570654800143.jpg', 'http://172.16.5.94:81/snap/dss_1204201907221907624/dss_1204201907221907624_1570654800143.jpg', 3, '2019-10-10 05:00:00', '2019-10-10 05:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182038021466460162', 't_snaps', '1181524808593264641', 'snap', 'dss_6674201903121903133_1570654800177.jpg', 'http://172.16.5.94:81/snap/dss_6674201903121903133/dss_6674201903121903133_1570654800177.jpg', 3, '2019-10-10 05:00:00', '2019-10-10 05:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182053120663064577', 't_snaps', '1181505897801928706', 'snap', 'dss_3287201907221907170_1570658400102.jpg', 'http://172.16.5.94:81/snap/dss_3287201907221907170/dss_3287201907221907170_1570658400102.jpg', 3, '2019-10-10 06:00:00', '2019-10-10 06:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182053120717590530', 't_snaps', '1181506032111931393', 'snap', 'dss_1204201907221907624_1570658400114.jpg', 'http://172.16.5.94:81/snap/dss_1204201907221907624/dss_1204201907221907624_1570658400114.jpg', 3, '2019-10-10 06:00:00', '2019-10-10 06:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182053120843419649', 't_snaps', '1181524808593264641', 'snap', 'dss_6674201903121903133_1570658400136.jpg', 'http://172.16.5.94:81/snap/dss_6674201903121903133/dss_6674201903121903133_1570658400136.jpg', 3, '2019-10-10 06:00:00', '2019-10-10 06:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182068220115521538', 't_snaps', '1181505897801928706', 'snap', 'dss_3287201907221907170_1570662000087.jpg', 'http://172.16.5.94:81/snap/dss_3287201907221907170/dss_3287201907221907170_1570662000087.jpg', 3, '2019-10-10 07:00:00', '2019-10-10 07:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182068220170047489', 't_snaps', '1181506032111931393', 'snap', 'dss_1204201907221907624_1570662000103.jpg', 'http://172.16.5.94:81/snap/dss_1204201907221907624/dss_1204201907221907624_1570662000103.jpg', 3, '2019-10-10 07:00:00', '2019-10-10 07:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182068220207796226', 't_snaps', '1181524808593264641', 'snap', 'dss_6674201903121903133_1570662000118.jpg', 'http://172.16.5.94:81/snap/dss_6674201903121903133/dss_6674201903121903133_1570662000118.jpg', 3, '2019-10-10 07:00:00', '2019-10-10 07:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182083319735750658', 't_snaps', '1181505897801928706', 'snap', 'dss_3287201907221907170_1570665600086.jpg', 'http://172.16.5.94:81/snap/dss_3287201907221907170/dss_3287201907221907170_1570665600086.jpg', 3, '2019-10-10 08:00:00', '2019-10-10 08:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182083319781888001', 't_snaps', '1181506032111931393', 'snap', 'dss_1204201907221907624_1570665600134.jpg', 'http://172.16.5.94:81/snap/dss_1204201907221907624/dss_1204201907221907624_1570665600134.jpg', 3, '2019-10-10 08:00:00', '2019-10-10 08:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182083319823831041', 't_snaps', '1181524808593264641', 'snap', 'dss_6674201903121903133_1570665600146.jpg', 'http://172.16.5.94:81/snap/dss_6674201903121903133/dss_6674201903121903133_1570665600146.jpg', 3, '2019-10-10 08:00:00', '2019-10-10 08:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182098419045601281', 't_snaps', '1181505897801928706', 'snap', 'dss_3287201907221907170_1570669200076.jpg', 'http://172.16.5.94:81/snap/dss_3287201907221907170/dss_3287201907221907170_1570669200076.jpg', 3, '2019-10-10 09:00:00', '2019-10-10 09:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182098419116904449', 't_snaps', '1181506032111931393', 'snap', 'dss_1204201907221907624_1570669200090.jpg', 'http://172.16.5.94:81/snap/dss_1204201907221907624/dss_1204201907221907624_1570669200090.jpg', 3, '2019-10-10 09:00:00', '2019-10-10 09:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182098419171430402', 't_snaps', '1181524808593264641', 'snap', 'dss_6674201903121903133_1570669200111.jpg', 'http://172.16.5.94:81/snap/dss_6674201903121903133/dss_6674201903121903133_1570669200111.jpg', 3, '2019-10-10 09:00:00', '2019-10-10 09:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182128619603070977', 't_snaps', '1181505897801928706', 'snap', 'dss_3287201907221907170_1570676400433.jpg', 'http://172.16.5.94:81/snap/dss_3287201907221907170/dss_3287201907221907170_1570676400433.jpg', 3, '2019-10-10 11:00:00', '2019-10-10 11:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182128619674374146', 't_snaps', '1181506032111931393', 'snap', 'dss_1204201907221907624_1570676400469.jpg', 'http://172.16.5.94:81/snap/dss_1204201907221907624/dss_1204201907221907624_1570676400469.jpg', 3, '2019-10-10 11:00:00', '2019-10-10 11:00:00', NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_sys_file` VALUES ('1182128830060662786', 't_snaps', '1181524808593264641', 'snap', 'dss_6674201903121903133_1570676400482.jpg', 'http://172.16.5.94:81/snap/dss_6674201903121903133/dss_6674201903121903133_1570676400482.jpg', 3, '2019-10-10 11:00:51', '2019-10-10 11:00:51', NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆名',
  `log_type` tinyint(4) NULL DEFAULT NULL COMMENT '日志类型（0:操作日志，1：登录日志）',
  `log_content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志内容',
  `operate_type` tinyint(4) NULL DEFAULT NULL COMMENT '操作类型（1查询，2添加，3修改，4删除）',
  `class_name` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名',
  `method` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(19) NULL DEFAULT NULL COMMENT '执行时长',
  `client_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端ip',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1194528978369331203 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级资源ID',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源路径',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源图标',
  `seq` tinyint(4) NOT NULL DEFAULT 0 COMMENT '排序',
  `resource_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '资源类别(0：菜单，1：按钮)',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态(0：开，1：关）',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录最后修改时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录创建用户',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录最后修改用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1193731450753265666 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_resource
-- ----------------------------
INSERT INTO `t_sys_resource` VALUES (1, 0, '系统管理', '', '系统管理', 'config', 3, 0, 0, '2019-08-01 15:16:55', '2019-08-01 15:16:55', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (11, 1, '菜单管理', 'sys/menu', '菜单管理', 'menu', 3, 1, 0, '2019-08-08 15:47:19', '2019-08-08 15:47:19', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (12, 1, '角色管理', 'sys/role', '角色管理', 'role', 2, 1, 0, '2018-12-17 19:11:24', '2018-12-17 19:11:25', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (13, 1, '用户管理', 'sys/user', '用户管理', 'admin', 1, 1, 0, '2018-12-17 19:11:13', '2018-12-17 19:11:14', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (111, 11, '列表', 'sys/menu/list', '资源列表', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (112, 11, '添加', 'sys/menu/save', '资源添加', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (113, 11, '编辑', 'sys/menu/update', '资源编辑', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (114, 11, '删除', 'sys/menu/delete', '资源删除', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (115, 11, '选择', 'sys/menu/select', '菜单选择', NULL, 4, 2, 0, '2018-12-14 16:44:29', '2018-12-14 16:44:32', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (116, 11, '信息', 'sys/menu/info', '菜单信息', NULL, 5, 2, 0, '2018-12-14 16:45:04', '2018-12-14 16:45:06', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (121, 12, '列表', 'sys/role/list', '角色列表', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (122, 12, '添加', 'sys/role/save', '角色添加', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (123, 12, '编辑', 'sys/role/update', '角色编辑', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (124, 12, '删除', 'sys/role/delete', '角色删除', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (125, 12, '选择', 'sys/role/select', '角色选择', NULL, 5, 2, 0, '2018-12-14 13:52:39', '2018-12-14 13:52:42', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (126, 12, '信息', 'sys/role/info', '角色信息', NULL, 6, 2, 0, '2018-12-14 14:32:15', '2018-12-14 14:32:15', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (131, 13, '列表', 'sys/user/list', '用户列表', '', 1, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (132, 13, '添加', 'sys/user/save', '用户添加', '', 2, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (133, 13, '编辑', 'sys/user/update', '用户编辑', '', 3, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (134, 13, '删除', 'sys/user/delete', '用户删除', '', 4, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (135, 13, '信息', 'sys/user/info', '用户信息', NULL, 5, 2, 0, '2018-12-14 14:05:39', '2018-12-14 14:05:41', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (227, 1, '日志管理', 'sys/log', '日志管理', 'log', 29, 1, 0, '2019-11-12 19:53:33', '2019-11-12 19:53:33', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (228, 1, 'Druid监控', 'http://localhost:8182/druid', 'Druid监控', 'sql', 30, 1, 0, '2019-08-09 10:51:04', '2019-08-09 10:51:05', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (234, 1, '字典管理', 'sys/dic', '字典管理', 'menu', 4, 1, 0, '2018-12-17 19:12:03', '2018-12-17 19:12:04', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (235, 234, '数据字典列表', 'sys/dic/list', '数据字典列表', '', 0, 2, 0, '2018-12-13 19:37:23', '2018-12-13 19:37:23', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (236, 234, '添加', 'sys/dic/save', '数据字典添加', '', 1, 2, 0, '2018-12-13 19:37:03', '2018-12-13 19:37:03', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (237, 234, '编辑', 'sys/dic/update', '数据字典编辑', '', 2, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (238, 234, '删除', 'sys/dic/delete', '数据字典删除', '', 3, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (239, 1, '定时任务', 'sys/schedule', NULL, 'job', 5, 1, 0, '2018-12-15 19:10:38', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (240, 239, '列表', 'sys/schedule/list', NULL, '', 1, 2, 0, '2018-12-15 19:15:10', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (241, 239, '添加', 'sys/schedule/save', NULL, '', 2, 2, 0, '2018-12-15 19:16:20', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (242, 239, '编辑', 'sys/schedule/update', '定时任务编辑', '', 3, 2, 0, '2018-12-15 19:22:18', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (243, 239, '删除', 'sys/schedule/delete', '定时任务删除', '', 4, 2, 0, '2018-12-15 19:23:09', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (244, 239, '信息', 'sys/schedule/info', '定时任务信息', '', 5, 2, 0, '2018-12-15 19:23:33', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (245, 239, '暂停', 'sys/schedule/pause', '定时任务暂停', '', 6, 2, 0, '2018-12-15 19:25:39', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (246, 239, '恢复', 'sys/schedule/resume', '定时任务恢复', '', 7, 2, 0, '2018-12-15 19:26:19', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (247, 239, '立即执行', 'sys/schedule/run', '定时任务立即执行', '', 8, 2, 0, '2018-12-15 19:27:46', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (248, 239, '日志', 'sys/schedule/log', '定时任务日志', '', 9, 2, 0, '2018-12-15 19:28:40', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (249, 0, '企业管理', '', '企业管理', 'menu', 1, 0, 0, '2018-12-17 15:23:12', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (250, 249, '企业信息', 'enterprise/enterprise', '企业信息', 'menu', 1, 1, 0, '2018-12-17 15:25:18', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (251, 250, '列表', 'enterprise/enterprise/list', '列表', '', 1, 2, 0, '2018-12-17 15:25:59', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (252, 250, '添加', 'enterprise/enterprise/save', '添加', '', 2, 2, 0, '2018-12-17 15:26:34', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (253, 250, '编辑', 'enterprise/enterprise/update', '编辑', '', 3, 2, 0, '2018-12-17 15:27:14', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (254, 250, '删除', 'enterprise/enterprise/delete', '删除', '', 4, 2, 0, '2018-12-17 15:27:35', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (255, 250, '信息', 'enterprise/enterprise/info', '信息', '', 5, 2, 0, '2018-12-17 15:28:04', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (256, 1, '企业部门管理', 'enterprise/enterpriseDepartment', '企业部门管理', 'menu', 6, 1, 0, '2018-12-17 15:30:15', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (257, 1, '企业职务管理', 'enterprise/enterpriseJob', '企业职务管理', 'menu', 7, 1, 0, '2018-12-17 15:30:45', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (258, 256, '列表', 'enterprise/enterpriseDepartment/list', '企业部门列表', '', 1, 2, 0, '2018-12-17 19:05:51', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (259, 256, '添加', 'enterprise/enterpriseDepartment/save', '企业部门添加', '', 2, 2, 0, '2018-12-17 19:06:09', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (260, 256, '编辑', 'enterprise/enterpriseDepartment/update', '企业部门编辑', '', 3, 2, 0, '2018-12-17 19:06:34', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (261, 256, '删除', 'enterprise/enterpriseDepartment/delete', '企业部门删除', '', 4, 2, 0, '2018-12-17 19:07:01', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (262, 256, '信息', 'enterprise/enterpriseDepartment/info', '企业部门信息', '', 5, 2, 0, '2018-12-17 19:07:24', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (263, 257, '列表', 'enterprise/enterpriseJob/list', '企业职务', '', 1, 2, 0, '2018-12-17 19:10:45', '2018-12-17 19:10:46', 'admin', 'admin');
INSERT INTO `t_sys_resource` VALUES (264, 257, '添加', 'enterprise/enterpriseJob/save', '企业职务添加', '', 2, 2, 0, '2018-12-17 19:08:53', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (265, 257, '编辑', 'enterprise/enterpriseJob/update', '企业职务编辑', '', 3, 2, 0, '2018-12-17 19:09:20', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (266, 257, '删除', 'enterprise/enterpriseJob/delete', '企业职务删除', '', 4, 2, 0, '2018-12-17 19:09:48', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (267, 257, '信息', 'enterprise/enterpriseJob/info', '企业职务信息', '', 5, 2, 0, '2018-12-17 19:10:15', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (268, 1, 'API接口', 'http://localhost:8182/doc.html', 'API接口', 'config', 31, 1, 0, '2019-08-09 10:50:53', '2019-08-09 10:50:53', 'admin', 'admin');
INSERT INTO `t_sys_resource` VALUES (269, 0, '消息管理', '', '消息管理', 'menu', 2, 0, 0, '2019-08-01 15:17:28', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (270, 269, '消息信息', 'sms/sms', '消息信息', 'config', 1, 1, 0, '2019-08-01 15:20:47', '2019-08-01 15:20:47', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (271, 270, '列表', 'sms/sms/list', NULL, '', 1, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (272, 270, '新增', 'sms/sms/save', NULL, '', 2, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (273, 270, '修改', 'sms/sms/update', NULL, '', 3, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (274, 270, '删除', 'sms/sms/delete', NULL, '', 4, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (275, 270, '信息', 'sms/sms/info', NULL, '', 5, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (276, 270, '选择', 'sms/sms/select', NULL, '', 6, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (277, 269, '消息日志', 'sms/smsRecord', '消息日志', 'config', 3, 1, 0, '2019-08-01 15:20:29', '2019-08-01 15:20:30', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (278, 277, '列表', 'sms/smsRecord/list', NULL, '', 1, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (279, 277, '新增', 'sms/smsRecord/save', NULL, '', 2, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (280, 277, '修改', 'sms/smsRecord/update', NULL, '', 3, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (281, 277, '删除', 'sms/smsRecord/delete', NULL, '', 4, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (282, 277, '信息', 'sms/smsRecord/info', NULL, '', 5, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (283, 277, '选择', 'sms/smsRecord/select', NULL, '', 6, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (284, 269, '消息类型信息', 'sms/smsType', '消息类型信息', 'config', 2, 1, 0, '2019-08-01 15:20:09', '2019-08-01 15:20:09', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (285, 284, '列表', 'sms/smsType/list', NULL, '', 1, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (286, 284, '新增', 'sms/smsType/save', NULL, '', 2, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (287, 284, '修改', 'sms/smsType/update', NULL, '', 3, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (288, 284, '删除', 'sms/smsType/delete', NULL, '', 4, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (289, 284, '信息', 'sms/smsType/info', NULL, '', 5, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (290, 284, '选择', 'sms/smsType/select', NULL, '', 6, 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (1193712807411724290, 1, '性能监测', 'sysMonitor/redis', '性能监测', 'config', 8, 0, 0, '2019-11-11 10:11:46', '2019-11-11 10:11:47', 'admin', 'admin');
INSERT INTO `t_sys_resource` VALUES (1193713024584396801, 1193712807411724290, 'Redis监控', 'sysMonitor/redis', 'Redis监控', 'menu', 1, 1, 0, '2019-11-11 10:12:18', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (1193722636930920450, 1193712807411724290, 'Tomcat信息', 'sysMonitor/tomcat', 'Tomcat信息', 'menu', 2, 1, 0, '2019-11-11 10:51:22', '2019-11-11 10:51:23', 'admin', 'admin');
INSERT INTO `t_sys_resource` VALUES (1193730814687064065, 1193712807411724290, '服务器信息', 'sysMonitor/server', '服务器信息', 'menu', 3, 1, 0, '2019-11-11 11:22:59', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (1193731016772825090, 1193712807411724290, 'JVM信息', 'sysMonitor/jvm', 'JVM信息', 'menu', 4, 1, 0, '2019-11-11 11:23:48', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (1193731242564792321, 1193712807411724290, '请求追踪', 'sysMonitor/httpTrace', '请求追踪', 'menu', 5, 1, 0, '2019-11-11 11:24:41', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (1193731450753265665, 1193712807411724290, '磁盘监控', 'sysMonitor/disk', '磁盘监控', 'menu', 6, 1, 0, '2019-11-11 11:25:31', NULL, 'admin', NULL);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `seq` tinyint(2) NOT NULL DEFAULT 0 COMMENT '排序号',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态(0：开启，1：关闭)',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录最后修改时间',
  `create_user_id` bigint(19) NULL DEFAULT NULL COMMENT '记录创建者ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, 'admin', 1, '超级管理员', 0, '2019-09-26 17:26:30', '2019-09-26 17:26:31', 1);
INSERT INTO `t_sys_role` VALUES (2, '应用管理员', 2, '应用管理员', 0, '2019-09-26 17:27:40', '2019-09-26 17:27:40', 1);
INSERT INTO `t_sys_role` VALUES (3, '测试人员', 3, '测试人员', 0, '2019-09-27 14:02:38', '2019-09-27 14:02:38', 1);
INSERT INTO `t_sys_role` VALUES (4, '运维人员', 4, '运维人员', 0, '2019-09-26 17:28:30', '2019-09-26 17:28:30', 1);
INSERT INTO `t_sys_role` VALUES (5, '运营人员', 5, '运营人员', 0, '2019-09-26 17:29:46', '2019-09-26 17:29:46', 1);

-- ----------------------------
-- Table structure for t_sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_resource`;
CREATE TABLE `t_sys_role_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `resource_id` bigint(20) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_resource_ids`(`role_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89669 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_resource
-- ----------------------------
INSERT INTO `t_sys_role_resource` VALUES (89246, 1, 1);
INSERT INTO `t_sys_role_resource` VALUES (89260, 1, 11);
INSERT INTO `t_sys_role_resource` VALUES (89253, 1, 12);
INSERT INTO `t_sys_role_resource` VALUES (89247, 1, 13);
INSERT INTO `t_sys_role_resource` VALUES (89261, 1, 111);
INSERT INTO `t_sys_role_resource` VALUES (89262, 1, 112);
INSERT INTO `t_sys_role_resource` VALUES (89263, 1, 113);
INSERT INTO `t_sys_role_resource` VALUES (89264, 1, 114);
INSERT INTO `t_sys_role_resource` VALUES (89265, 1, 115);
INSERT INTO `t_sys_role_resource` VALUES (89266, 1, 116);
INSERT INTO `t_sys_role_resource` VALUES (89254, 1, 121);
INSERT INTO `t_sys_role_resource` VALUES (89255, 1, 122);
INSERT INTO `t_sys_role_resource` VALUES (89256, 1, 123);
INSERT INTO `t_sys_role_resource` VALUES (89257, 1, 124);
INSERT INTO `t_sys_role_resource` VALUES (89258, 1, 125);
INSERT INTO `t_sys_role_resource` VALUES (89259, 1, 126);
INSERT INTO `t_sys_role_resource` VALUES (89248, 1, 131);
INSERT INTO `t_sys_role_resource` VALUES (89249, 1, 132);
INSERT INTO `t_sys_role_resource` VALUES (89250, 1, 133);
INSERT INTO `t_sys_role_resource` VALUES (89251, 1, 134);
INSERT INTO `t_sys_role_resource` VALUES (89252, 1, 135);
INSERT INTO `t_sys_role_resource` VALUES (89294, 1, 227);
INSERT INTO `t_sys_role_resource` VALUES (89295, 1, 228);
INSERT INTO `t_sys_role_resource` VALUES (89267, 1, 234);
INSERT INTO `t_sys_role_resource` VALUES (89268, 1, 235);
INSERT INTO `t_sys_role_resource` VALUES (89269, 1, 236);
INSERT INTO `t_sys_role_resource` VALUES (89270, 1, 237);
INSERT INTO `t_sys_role_resource` VALUES (89271, 1, 238);
INSERT INTO `t_sys_role_resource` VALUES (89272, 1, 239);
INSERT INTO `t_sys_role_resource` VALUES (89273, 1, 240);
INSERT INTO `t_sys_role_resource` VALUES (89274, 1, 241);
INSERT INTO `t_sys_role_resource` VALUES (89275, 1, 242);
INSERT INTO `t_sys_role_resource` VALUES (89276, 1, 243);
INSERT INTO `t_sys_role_resource` VALUES (89277, 1, 244);
INSERT INTO `t_sys_role_resource` VALUES (89278, 1, 245);
INSERT INTO `t_sys_role_resource` VALUES (89279, 1, 246);
INSERT INTO `t_sys_role_resource` VALUES (89280, 1, 247);
INSERT INTO `t_sys_role_resource` VALUES (89281, 1, 248);
INSERT INTO `t_sys_role_resource` VALUES (89217, 1, 249);
INSERT INTO `t_sys_role_resource` VALUES (89218, 1, 250);
INSERT INTO `t_sys_role_resource` VALUES (89219, 1, 251);
INSERT INTO `t_sys_role_resource` VALUES (89220, 1, 252);
INSERT INTO `t_sys_role_resource` VALUES (89221, 1, 253);
INSERT INTO `t_sys_role_resource` VALUES (89222, 1, 254);
INSERT INTO `t_sys_role_resource` VALUES (89223, 1, 255);
INSERT INTO `t_sys_role_resource` VALUES (89282, 1, 256);
INSERT INTO `t_sys_role_resource` VALUES (89288, 1, 257);
INSERT INTO `t_sys_role_resource` VALUES (89283, 1, 258);
INSERT INTO `t_sys_role_resource` VALUES (89284, 1, 259);
INSERT INTO `t_sys_role_resource` VALUES (89285, 1, 260);
INSERT INTO `t_sys_role_resource` VALUES (89286, 1, 261);
INSERT INTO `t_sys_role_resource` VALUES (89287, 1, 262);
INSERT INTO `t_sys_role_resource` VALUES (89289, 1, 263);
INSERT INTO `t_sys_role_resource` VALUES (89290, 1, 264);
INSERT INTO `t_sys_role_resource` VALUES (89291, 1, 265);
INSERT INTO `t_sys_role_resource` VALUES (89292, 1, 266);
INSERT INTO `t_sys_role_resource` VALUES (89293, 1, 267);
INSERT INTO `t_sys_role_resource` VALUES (89296, 1, 268);
INSERT INTO `t_sys_role_resource` VALUES (89224, 1, 269);
INSERT INTO `t_sys_role_resource` VALUES (89225, 1, 270);
INSERT INTO `t_sys_role_resource` VALUES (89226, 1, 271);
INSERT INTO `t_sys_role_resource` VALUES (89227, 1, 272);
INSERT INTO `t_sys_role_resource` VALUES (89228, 1, 273);
INSERT INTO `t_sys_role_resource` VALUES (89229, 1, 274);
INSERT INTO `t_sys_role_resource` VALUES (89230, 1, 275);
INSERT INTO `t_sys_role_resource` VALUES (89231, 1, 276);
INSERT INTO `t_sys_role_resource` VALUES (89239, 1, 277);
INSERT INTO `t_sys_role_resource` VALUES (89240, 1, 278);
INSERT INTO `t_sys_role_resource` VALUES (89241, 1, 279);
INSERT INTO `t_sys_role_resource` VALUES (89242, 1, 280);
INSERT INTO `t_sys_role_resource` VALUES (89243, 1, 281);
INSERT INTO `t_sys_role_resource` VALUES (89244, 1, 282);
INSERT INTO `t_sys_role_resource` VALUES (89245, 1, 283);
INSERT INTO `t_sys_role_resource` VALUES (89232, 1, 284);
INSERT INTO `t_sys_role_resource` VALUES (89233, 1, 285);
INSERT INTO `t_sys_role_resource` VALUES (89234, 1, 286);
INSERT INTO `t_sys_role_resource` VALUES (89235, 1, 287);
INSERT INTO `t_sys_role_resource` VALUES (89236, 1, 288);
INSERT INTO `t_sys_role_resource` VALUES (89237, 1, 289);
INSERT INTO `t_sys_role_resource` VALUES (89238, 1, 290);
INSERT INTO `t_sys_role_resource` VALUES (89618, 2, 1);
INSERT INTO `t_sys_role_resource` VALUES (89632, 2, 11);
INSERT INTO `t_sys_role_resource` VALUES (89625, 2, 12);
INSERT INTO `t_sys_role_resource` VALUES (89619, 2, 13);
INSERT INTO `t_sys_role_resource` VALUES (89633, 2, 111);
INSERT INTO `t_sys_role_resource` VALUES (89634, 2, 112);
INSERT INTO `t_sys_role_resource` VALUES (89635, 2, 113);
INSERT INTO `t_sys_role_resource` VALUES (89636, 2, 114);
INSERT INTO `t_sys_role_resource` VALUES (89637, 2, 115);
INSERT INTO `t_sys_role_resource` VALUES (89638, 2, 116);
INSERT INTO `t_sys_role_resource` VALUES (89626, 2, 121);
INSERT INTO `t_sys_role_resource` VALUES (89627, 2, 122);
INSERT INTO `t_sys_role_resource` VALUES (89628, 2, 123);
INSERT INTO `t_sys_role_resource` VALUES (89629, 2, 124);
INSERT INTO `t_sys_role_resource` VALUES (89630, 2, 125);
INSERT INTO `t_sys_role_resource` VALUES (89631, 2, 126);
INSERT INTO `t_sys_role_resource` VALUES (89620, 2, 131);
INSERT INTO `t_sys_role_resource` VALUES (89621, 2, 132);
INSERT INTO `t_sys_role_resource` VALUES (89622, 2, 133);
INSERT INTO `t_sys_role_resource` VALUES (89623, 2, 134);
INSERT INTO `t_sys_role_resource` VALUES (89624, 2, 135);
INSERT INTO `t_sys_role_resource` VALUES (89666, 2, 227);
INSERT INTO `t_sys_role_resource` VALUES (89667, 2, 228);
INSERT INTO `t_sys_role_resource` VALUES (89639, 2, 234);
INSERT INTO `t_sys_role_resource` VALUES (89640, 2, 235);
INSERT INTO `t_sys_role_resource` VALUES (89641, 2, 236);
INSERT INTO `t_sys_role_resource` VALUES (89642, 2, 237);
INSERT INTO `t_sys_role_resource` VALUES (89643, 2, 238);
INSERT INTO `t_sys_role_resource` VALUES (89644, 2, 239);
INSERT INTO `t_sys_role_resource` VALUES (89645, 2, 240);
INSERT INTO `t_sys_role_resource` VALUES (89646, 2, 241);
INSERT INTO `t_sys_role_resource` VALUES (89647, 2, 242);
INSERT INTO `t_sys_role_resource` VALUES (89648, 2, 243);
INSERT INTO `t_sys_role_resource` VALUES (89649, 2, 244);
INSERT INTO `t_sys_role_resource` VALUES (89650, 2, 245);
INSERT INTO `t_sys_role_resource` VALUES (89651, 2, 246);
INSERT INTO `t_sys_role_resource` VALUES (89652, 2, 247);
INSERT INTO `t_sys_role_resource` VALUES (89653, 2, 248);
INSERT INTO `t_sys_role_resource` VALUES (89589, 2, 249);
INSERT INTO `t_sys_role_resource` VALUES (89590, 2, 250);
INSERT INTO `t_sys_role_resource` VALUES (89591, 2, 251);
INSERT INTO `t_sys_role_resource` VALUES (89592, 2, 252);
INSERT INTO `t_sys_role_resource` VALUES (89593, 2, 253);
INSERT INTO `t_sys_role_resource` VALUES (89594, 2, 254);
INSERT INTO `t_sys_role_resource` VALUES (89595, 2, 255);
INSERT INTO `t_sys_role_resource` VALUES (89654, 2, 256);
INSERT INTO `t_sys_role_resource` VALUES (89660, 2, 257);
INSERT INTO `t_sys_role_resource` VALUES (89655, 2, 258);
INSERT INTO `t_sys_role_resource` VALUES (89656, 2, 259);
INSERT INTO `t_sys_role_resource` VALUES (89657, 2, 260);
INSERT INTO `t_sys_role_resource` VALUES (89658, 2, 261);
INSERT INTO `t_sys_role_resource` VALUES (89659, 2, 262);
INSERT INTO `t_sys_role_resource` VALUES (89661, 2, 263);
INSERT INTO `t_sys_role_resource` VALUES (89662, 2, 264);
INSERT INTO `t_sys_role_resource` VALUES (89663, 2, 265);
INSERT INTO `t_sys_role_resource` VALUES (89664, 2, 266);
INSERT INTO `t_sys_role_resource` VALUES (89665, 2, 267);
INSERT INTO `t_sys_role_resource` VALUES (89668, 2, 268);
INSERT INTO `t_sys_role_resource` VALUES (89596, 2, 269);
INSERT INTO `t_sys_role_resource` VALUES (89597, 2, 270);
INSERT INTO `t_sys_role_resource` VALUES (89598, 2, 271);
INSERT INTO `t_sys_role_resource` VALUES (89599, 2, 272);
INSERT INTO `t_sys_role_resource` VALUES (89600, 2, 273);
INSERT INTO `t_sys_role_resource` VALUES (89601, 2, 274);
INSERT INTO `t_sys_role_resource` VALUES (89602, 2, 275);
INSERT INTO `t_sys_role_resource` VALUES (89603, 2, 276);
INSERT INTO `t_sys_role_resource` VALUES (89611, 2, 277);
INSERT INTO `t_sys_role_resource` VALUES (89612, 2, 278);
INSERT INTO `t_sys_role_resource` VALUES (89613, 2, 279);
INSERT INTO `t_sys_role_resource` VALUES (89614, 2, 280);
INSERT INTO `t_sys_role_resource` VALUES (89615, 2, 281);
INSERT INTO `t_sys_role_resource` VALUES (89616, 2, 282);
INSERT INTO `t_sys_role_resource` VALUES (89617, 2, 283);
INSERT INTO `t_sys_role_resource` VALUES (89604, 2, 284);
INSERT INTO `t_sys_role_resource` VALUES (89605, 2, 285);
INSERT INTO `t_sys_role_resource` VALUES (89606, 2, 286);
INSERT INTO `t_sys_role_resource` VALUES (89607, 2, 287);
INSERT INTO `t_sys_role_resource` VALUES (89608, 2, 288);
INSERT INTO `t_sys_role_resource` VALUES (89609, 2, 289);
INSERT INTO `t_sys_role_resource` VALUES (89610, 2, 290);
INSERT INTO `t_sys_role_resource` VALUES (89562, 5, 249);
INSERT INTO `t_sys_role_resource` VALUES (89563, 5, 250);
INSERT INTO `t_sys_role_resource` VALUES (89564, 5, 251);
INSERT INTO `t_sys_role_resource` VALUES (89565, 5, 252);
INSERT INTO `t_sys_role_resource` VALUES (89566, 5, 253);
INSERT INTO `t_sys_role_resource` VALUES (89567, 5, 254);
INSERT INTO `t_sys_role_resource` VALUES (89568, 5, 255);
INSERT INTO `t_sys_role_resource` VALUES (89587, 5, 269);
INSERT INTO `t_sys_role_resource` VALUES (89569, 5, 270);
INSERT INTO `t_sys_role_resource` VALUES (89570, 5, 271);
INSERT INTO `t_sys_role_resource` VALUES (89571, 5, 272);
INSERT INTO `t_sys_role_resource` VALUES (89572, 5, 273);
INSERT INTO `t_sys_role_resource` VALUES (89573, 5, 274);
INSERT INTO `t_sys_role_resource` VALUES (89574, 5, 275);
INSERT INTO `t_sys_role_resource` VALUES (89575, 5, 276);
INSERT INTO `t_sys_role_resource` VALUES (89588, 5, 277);
INSERT INTO `t_sys_role_resource` VALUES (89583, 5, 278);
INSERT INTO `t_sys_role_resource` VALUES (89584, 5, 279);
INSERT INTO `t_sys_role_resource` VALUES (89585, 5, 280);
INSERT INTO `t_sys_role_resource` VALUES (89586, 5, 281);
INSERT INTO `t_sys_role_resource` VALUES (89576, 5, 284);
INSERT INTO `t_sys_role_resource` VALUES (89577, 5, 285);
INSERT INTO `t_sys_role_resource` VALUES (89578, 5, 286);
INSERT INTO `t_sys_role_resource` VALUES (89579, 5, 287);
INSERT INTO `t_sys_role_resource` VALUES (89580, 5, 288);
INSERT INTO `t_sys_role_resource` VALUES (89581, 5, 289);
INSERT INTO `t_sys_role_resource` VALUES (89582, 5, 290);

-- ----------------------------
-- Table structure for t_sys_sms
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_sms`;
CREATE TABLE `t_sys_sms`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sms_type_id` bigint(20) NULL DEFAULT NULL COMMENT '消息类型ID',
  `title` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `sms_time` datetime(0) NULL DEFAULT NULL COMMENT '推送时间',
  `sms_count` int(19) NULL DEFAULT NULL COMMENT '推送次数',
  `interval_time` int(19) NULL DEFAULT NULL COMMENT '推送间隔时间（秒）',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态（0-正常，1-禁止）	',
  `real_time` datetime(0) NULL DEFAULT NULL COMMENT '每次推送时间',
  `real_count` int(4) NULL DEFAULT NULL COMMENT '推送真实次数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(19) NULL DEFAULT NULL COMMENT '创建者',
  `update_user` bigint(19) NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1194058475401654275 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_sms
-- ----------------------------
INSERT INTO `t_sys_sms` VALUES (1194058475401654274, 2, '22', '22', '2019-11-12 09:05:49', 2, 2, 0, NULL, NULL, '2019-11-12 09:05:00', '2019-11-12 09:05:15', 1, 1);

-- ----------------------------
-- Table structure for t_sys_sms_record
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_sms_record`;
CREATE TABLE `t_sys_sms_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '账号',
  `sms_id` bigint(20) NULL DEFAULT NULL COMMENT '消息ID',
  `sms_time` datetime(0) NULL DEFAULT NULL COMMENT '要求推送时间',
  `push_time` datetime(0) NULL DEFAULT NULL COMMENT '实际推送时间',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态（0-待推送，1-推送成功，2-推送失败，3-已读）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_sms_type
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_sms_type`;
CREATE TABLE `t_sys_sms_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `target_list` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号列表',
  `is_send_sms` tinyint(4) NULL DEFAULT 1 COMMENT '是否发送短信（0-是，0-否）',
  `is_send_email` tinyint(4) NULL DEFAULT 1 COMMENT '是否发送邮件（0-是，1-否）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(19) NULL DEFAULT NULL COMMENT '创建者',
  `update_user` bigint(19) NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1156457361573941250 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息类型与用户关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_sms_type
-- ----------------------------
INSERT INTO `t_sys_sms_type` VALUES (1, 'MQ消息', '1,2', 0, 0, '2019-07-16 17:37:02', '2019-08-01 16:50:25', 521677655146233856, 1);
INSERT INTO `t_sys_sms_type` VALUES (2, 'Kettle消息', '1', 1, 1, '2019-07-16 23:05:39', NULL, 521677655146233856, NULL);
INSERT INTO `t_sys_sms_type` VALUES (3, '页面消息', '1', 1, 1, '2019-07-16 23:25:15', NULL, 521677655146233856, NULL);
INSERT INTO `t_sys_sms_type` VALUES (1156105807926923265, '11', '1,2', 1, 1, '2019-07-30 15:34:39', '2019-08-06 17:49:26', 521677655146233856, 1);
INSERT INTO `t_sys_sms_type` VALUES (1156457361573941249, 'test1', '1', 0, 0, '2019-07-31 14:51:36', '2019-11-12 09:05:37', 521677655146233856, 1);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆名',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加密盐',
  `sex` tinyint(4) NOT NULL DEFAULT 0 COMMENT '性别(0:男，1：女)',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '用户类别（0：超级管理员，1：企业用户，2：监管用户）',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '用户状态(0：正常，1：不正常)',
  `expired` tinyint(4) NULL DEFAULT 0 COMMENT '过期字段（0-不过期，1-过期）',
  `enterprise_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属企业',
  `department_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '所属部门',
  `job_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户职务',
  `is_leader` tinyint(4) NULL DEFAULT NULL COMMENT '是否领导（0-是，1-否）',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录最后修改时间',
  `create_user_id` bigint(19) NULL DEFAULT NULL COMMENT '记录创建用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (1, 'admin', 'admin', 'b2ccd2d71e04f7dd479d79c5fe886c8f', 'b4752b4b73034de06afb2db30fe19061', 0, '18627026982', 'tuminglong@126.com', 0, 0, 0, '', '', NULL, 0, '2019-09-27 14:43:30', '2019-09-27 14:43:30', 1);
INSERT INTO `t_sys_user` VALUES (2, 'test', 'test', '271071d3b814220a33bbf85b619418c8', '13608cadcaf242a5b8130182ef8c8d84', 0, '18627026982', 'tuminglong@126.com', 1, 0, 0, '1', 'HDWX4e8576953a1f460a', 'HDWX63c73550701448e6', 1, '2019-08-08 17:55:56', '2019-08-08 17:55:56', 1);
INSERT INTO `t_sys_user` VALUES (3, 'manager', 'manager', '4259eb4f7386e2145699fac9e4b127af', '5d0fa374af44f73972c4a75c5a746fa7', 0, '18627026982', 'tuminglong@126.com', 2, 0, 1, '', '', '', 1, '2019-08-08 11:29:43', '2019-08-08 11:29:43', 1);
INSERT INTO `t_sys_user` VALUES (7, 'testtt', '100012', 'aac7e2aeb237a5af3d2292ab76811c18', 'f92f61c0b56ef9e3ec97893be9cbd976', 0, '13125259698', '123@qq.com', 1, 1, 0, '1', '', 'HDWX63c73550701448e6', 1, '2019-11-12 09:06:36', '2019-11-12 09:06:37', 1);

-- ----------------------------
-- Table structure for t_sys_user_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_enterprise`;
CREATE TABLE `t_sys_user_enterprise`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `enterprise_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `enterprise_id`(`enterprise_id`) USING BTREE,
  CONSTRAINT `t_sys_user_enterprise_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_sys_user_enterprise_ibfk_2` FOREIGN KEY (`enterprise_id`) REFERENCES `t_enterprise` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '监管用户与企业关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_enterprise
-- ----------------------------
INSERT INTO `t_sys_user_enterprise` VALUES (2, 3, '1');

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_role_ids`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1194058882400137218 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES (2541, 1, 1);
INSERT INTO `t_sys_user_role` VALUES (2536, 2, 2);
INSERT INTO `t_sys_user_role` VALUES (2533, 3, 3);
INSERT INTO `t_sys_user_role` VALUES (1194058882400137217, 7, 2);
INSERT INTO `t_sys_user_role` VALUES (151, 57, 8);
INSERT INTO `t_sys_user_role` VALUES (153, 59, 8);
INSERT INTO `t_sys_user_role` VALUES (239, 61, 8);
INSERT INTO `t_sys_user_role` VALUES (2527, 67, 2);
INSERT INTO `t_sys_user_role` VALUES (221, 71, 1);
INSERT INTO `t_sys_user_role` VALUES (217, 73, 1);
INSERT INTO `t_sys_user_role` VALUES (256, 81, 1);
INSERT INTO `t_sys_user_role` VALUES (2524, 101, 2);
INSERT INTO `t_sys_user_role` VALUES (2457, 105, 8);
INSERT INTO `t_sys_user_role` VALUES (403, 114, 8);
INSERT INTO `t_sys_user_role` VALUES (411, 118, 22);
INSERT INTO `t_sys_user_role` VALUES (2529, 120, 22);
INSERT INTO `t_sys_user_role` VALUES (415, 122, 22);
INSERT INTO `t_sys_user_role` VALUES (417, 124, 22);
INSERT INTO `t_sys_user_role` VALUES (419, 126, 22);
INSERT INTO `t_sys_user_role` VALUES (421, 128, 22);
INSERT INTO `t_sys_user_role` VALUES (423, 130, 22);
INSERT INTO `t_sys_user_role` VALUES (425, 132, 22);
INSERT INTO `t_sys_user_role` VALUES (427, 134, 22);
INSERT INTO `t_sys_user_role` VALUES (429, 136, 22);
INSERT INTO `t_sys_user_role` VALUES (431, 138, 22);
INSERT INTO `t_sys_user_role` VALUES (433, 140, 22);
INSERT INTO `t_sys_user_role` VALUES (435, 142, 22);
INSERT INTO `t_sys_user_role` VALUES (437, 144, 22);
INSERT INTO `t_sys_user_role` VALUES (439, 146, 22);
INSERT INTO `t_sys_user_role` VALUES (441, 148, 22);
INSERT INTO `t_sys_user_role` VALUES (443, 150, 22);
INSERT INTO `t_sys_user_role` VALUES (445, 152, 22);
INSERT INTO `t_sys_user_role` VALUES (447, 154, 22);
INSERT INTO `t_sys_user_role` VALUES (449, 156, 22);
INSERT INTO `t_sys_user_role` VALUES (451, 158, 22);
INSERT INTO `t_sys_user_role` VALUES (453, 160, 22);
INSERT INTO `t_sys_user_role` VALUES (455, 162, 25);
INSERT INTO `t_sys_user_role` VALUES (457, 164, 25);
INSERT INTO `t_sys_user_role` VALUES (459, 166, 25);
INSERT INTO `t_sys_user_role` VALUES (461, 168, 25);
INSERT INTO `t_sys_user_role` VALUES (463, 170, 25);
INSERT INTO `t_sys_user_role` VALUES (465, 172, 22);
INSERT INTO `t_sys_user_role` VALUES (467, 174, 22);
INSERT INTO `t_sys_user_role` VALUES (469, 176, 22);
INSERT INTO `t_sys_user_role` VALUES (471, 178, 23);
INSERT INTO `t_sys_user_role` VALUES (473, 180, 23);
INSERT INTO `t_sys_user_role` VALUES (475, 182, 22);
INSERT INTO `t_sys_user_role` VALUES (477, 184, 23);
INSERT INTO `t_sys_user_role` VALUES (479, 186, 23);
INSERT INTO `t_sys_user_role` VALUES (481, 188, 23);
INSERT INTO `t_sys_user_role` VALUES (483, 190, 23);
INSERT INTO `t_sys_user_role` VALUES (485, 192, 23);
INSERT INTO `t_sys_user_role` VALUES (487, 194, 23);
INSERT INTO `t_sys_user_role` VALUES (489, 196, 23);
INSERT INTO `t_sys_user_role` VALUES (491, 198, 23);
INSERT INTO `t_sys_user_role` VALUES (493, 200, 23);
INSERT INTO `t_sys_user_role` VALUES (495, 202, 23);
INSERT INTO `t_sys_user_role` VALUES (497, 204, 23);
INSERT INTO `t_sys_user_role` VALUES (499, 206, 23);
INSERT INTO `t_sys_user_role` VALUES (501, 208, 23);
INSERT INTO `t_sys_user_role` VALUES (503, 210, 23);
INSERT INTO `t_sys_user_role` VALUES (505, 212, 23);
INSERT INTO `t_sys_user_role` VALUES (507, 214, 23);
INSERT INTO `t_sys_user_role` VALUES (509, 216, 23);
INSERT INTO `t_sys_user_role` VALUES (511, 218, 23);
INSERT INTO `t_sys_user_role` VALUES (513, 220, 23);
INSERT INTO `t_sys_user_role` VALUES (515, 222, 23);
INSERT INTO `t_sys_user_role` VALUES (517, 224, 23);
INSERT INTO `t_sys_user_role` VALUES (519, 226, 23);
INSERT INTO `t_sys_user_role` VALUES (521, 228, 23);
INSERT INTO `t_sys_user_role` VALUES (523, 230, 23);
INSERT INTO `t_sys_user_role` VALUES (525, 232, 23);
INSERT INTO `t_sys_user_role` VALUES (527, 234, 23);
INSERT INTO `t_sys_user_role` VALUES (529, 236, 23);
INSERT INTO `t_sys_user_role` VALUES (531, 238, 23);
INSERT INTO `t_sys_user_role` VALUES (533, 240, 23);
INSERT INTO `t_sys_user_role` VALUES (535, 242, 22);
INSERT INTO `t_sys_user_role` VALUES (537, 244, 23);
INSERT INTO `t_sys_user_role` VALUES (539, 246, 22);
INSERT INTO `t_sys_user_role` VALUES (541, 248, 22);
INSERT INTO `t_sys_user_role` VALUES (543, 250, 22);
INSERT INTO `t_sys_user_role` VALUES (545, 252, 22);
INSERT INTO `t_sys_user_role` VALUES (547, 254, 22);
INSERT INTO `t_sys_user_role` VALUES (549, 256, 22);
INSERT INTO `t_sys_user_role` VALUES (551, 258, 23);
INSERT INTO `t_sys_user_role` VALUES (553, 260, 22);
INSERT INTO `t_sys_user_role` VALUES (555, 262, 22);
INSERT INTO `t_sys_user_role` VALUES (557, 264, 22);
INSERT INTO `t_sys_user_role` VALUES (559, 266, 22);
INSERT INTO `t_sys_user_role` VALUES (561, 268, 22);
INSERT INTO `t_sys_user_role` VALUES (563, 270, 23);
INSERT INTO `t_sys_user_role` VALUES (565, 272, 23);
INSERT INTO `t_sys_user_role` VALUES (567, 274, 23);
INSERT INTO `t_sys_user_role` VALUES (569, 276, 23);
INSERT INTO `t_sys_user_role` VALUES (571, 278, 23);
INSERT INTO `t_sys_user_role` VALUES (573, 280, 23);
INSERT INTO `t_sys_user_role` VALUES (575, 282, 23);
INSERT INTO `t_sys_user_role` VALUES (577, 284, 23);
INSERT INTO `t_sys_user_role` VALUES (579, 286, 23);
INSERT INTO `t_sys_user_role` VALUES (581, 288, 23);
INSERT INTO `t_sys_user_role` VALUES (583, 290, 25);
INSERT INTO `t_sys_user_role` VALUES (585, 292, 25);
INSERT INTO `t_sys_user_role` VALUES (587, 294, 25);
INSERT INTO `t_sys_user_role` VALUES (589, 296, 25);
INSERT INTO `t_sys_user_role` VALUES (591, 298, 25);
INSERT INTO `t_sys_user_role` VALUES (593, 300, 25);
INSERT INTO `t_sys_user_role` VALUES (595, 302, 25);
INSERT INTO `t_sys_user_role` VALUES (597, 304, 25);
INSERT INTO `t_sys_user_role` VALUES (599, 306, 25);
INSERT INTO `t_sys_user_role` VALUES (601, 308, 25);
INSERT INTO `t_sys_user_role` VALUES (603, 310, 25);
INSERT INTO `t_sys_user_role` VALUES (605, 312, 25);
INSERT INTO `t_sys_user_role` VALUES (607, 314, 25);
INSERT INTO `t_sys_user_role` VALUES (609, 316, 25);
INSERT INTO `t_sys_user_role` VALUES (611, 318, 25);
INSERT INTO `t_sys_user_role` VALUES (613, 320, 25);
INSERT INTO `t_sys_user_role` VALUES (615, 322, 25);
INSERT INTO `t_sys_user_role` VALUES (617, 324, 25);
INSERT INTO `t_sys_user_role` VALUES (619, 326, 25);
INSERT INTO `t_sys_user_role` VALUES (621, 328, 25);
INSERT INTO `t_sys_user_role` VALUES (623, 330, 25);
INSERT INTO `t_sys_user_role` VALUES (625, 332, 25);
INSERT INTO `t_sys_user_role` VALUES (627, 334, 25);
INSERT INTO `t_sys_user_role` VALUES (629, 336, 25);
INSERT INTO `t_sys_user_role` VALUES (631, 338, 25);
INSERT INTO `t_sys_user_role` VALUES (633, 340, 25);
INSERT INTO `t_sys_user_role` VALUES (635, 342, 25);
INSERT INTO `t_sys_user_role` VALUES (637, 344, 25);
INSERT INTO `t_sys_user_role` VALUES (639, 346, 25);
INSERT INTO `t_sys_user_role` VALUES (641, 348, 25);
INSERT INTO `t_sys_user_role` VALUES (643, 350, 25);
INSERT INTO `t_sys_user_role` VALUES (645, 352, 25);
INSERT INTO `t_sys_user_role` VALUES (647, 354, 25);
INSERT INTO `t_sys_user_role` VALUES (649, 356, 25);
INSERT INTO `t_sys_user_role` VALUES (651, 358, 25);
INSERT INTO `t_sys_user_role` VALUES (653, 360, 25);
INSERT INTO `t_sys_user_role` VALUES (655, 362, 25);
INSERT INTO `t_sys_user_role` VALUES (657, 364, 25);
INSERT INTO `t_sys_user_role` VALUES (659, 366, 25);
INSERT INTO `t_sys_user_role` VALUES (661, 368, 25);
INSERT INTO `t_sys_user_role` VALUES (663, 370, 25);
INSERT INTO `t_sys_user_role` VALUES (665, 372, 25);
INSERT INTO `t_sys_user_role` VALUES (667, 374, 25);
INSERT INTO `t_sys_user_role` VALUES (669, 376, 25);
INSERT INTO `t_sys_user_role` VALUES (671, 378, 25);
INSERT INTO `t_sys_user_role` VALUES (673, 380, 25);
INSERT INTO `t_sys_user_role` VALUES (675, 382, 25);
INSERT INTO `t_sys_user_role` VALUES (677, 384, 25);
INSERT INTO `t_sys_user_role` VALUES (679, 386, 25);
INSERT INTO `t_sys_user_role` VALUES (681, 388, 25);
INSERT INTO `t_sys_user_role` VALUES (683, 390, 25);
INSERT INTO `t_sys_user_role` VALUES (685, 392, 25);
INSERT INTO `t_sys_user_role` VALUES (687, 394, 25);
INSERT INTO `t_sys_user_role` VALUES (689, 396, 25);
INSERT INTO `t_sys_user_role` VALUES (691, 398, 25);
INSERT INTO `t_sys_user_role` VALUES (693, 400, 25);
INSERT INTO `t_sys_user_role` VALUES (695, 402, 25);
INSERT INTO `t_sys_user_role` VALUES (697, 404, 25);
INSERT INTO `t_sys_user_role` VALUES (699, 406, 25);
INSERT INTO `t_sys_user_role` VALUES (701, 408, 25);
INSERT INTO `t_sys_user_role` VALUES (703, 410, 25);
INSERT INTO `t_sys_user_role` VALUES (705, 412, 25);
INSERT INTO `t_sys_user_role` VALUES (707, 414, 25);
INSERT INTO `t_sys_user_role` VALUES (709, 416, 25);
INSERT INTO `t_sys_user_role` VALUES (711, 418, 25);
INSERT INTO `t_sys_user_role` VALUES (713, 420, 25);
INSERT INTO `t_sys_user_role` VALUES (715, 422, 25);
INSERT INTO `t_sys_user_role` VALUES (717, 424, 25);
INSERT INTO `t_sys_user_role` VALUES (719, 426, 25);
INSERT INTO `t_sys_user_role` VALUES (721, 428, 25);
INSERT INTO `t_sys_user_role` VALUES (723, 430, 25);
INSERT INTO `t_sys_user_role` VALUES (725, 432, 25);
INSERT INTO `t_sys_user_role` VALUES (727, 434, 25);
INSERT INTO `t_sys_user_role` VALUES (729, 436, 25);
INSERT INTO `t_sys_user_role` VALUES (731, 438, 25);
INSERT INTO `t_sys_user_role` VALUES (733, 440, 23);
INSERT INTO `t_sys_user_role` VALUES (735, 442, 23);
INSERT INTO `t_sys_user_role` VALUES (737, 444, 22);
INSERT INTO `t_sys_user_role` VALUES (739, 446, 22);
INSERT INTO `t_sys_user_role` VALUES (741, 448, 22);
INSERT INTO `t_sys_user_role` VALUES (743, 450, 22);
INSERT INTO `t_sys_user_role` VALUES (745, 452, 23);
INSERT INTO `t_sys_user_role` VALUES (747, 454, 23);
INSERT INTO `t_sys_user_role` VALUES (749, 456, 23);
INSERT INTO `t_sys_user_role` VALUES (751, 458, 23);
INSERT INTO `t_sys_user_role` VALUES (753, 460, 23);
INSERT INTO `t_sys_user_role` VALUES (755, 462, 23);
INSERT INTO `t_sys_user_role` VALUES (757, 464, 25);
INSERT INTO `t_sys_user_role` VALUES (759, 466, 25);
INSERT INTO `t_sys_user_role` VALUES (761, 468, 23);
INSERT INTO `t_sys_user_role` VALUES (763, 470, 25);
INSERT INTO `t_sys_user_role` VALUES (765, 472, 25);
INSERT INTO `t_sys_user_role` VALUES (767, 474, 25);
INSERT INTO `t_sys_user_role` VALUES (769, 476, 25);
INSERT INTO `t_sys_user_role` VALUES (771, 478, 25);
INSERT INTO `t_sys_user_role` VALUES (773, 480, 25);
INSERT INTO `t_sys_user_role` VALUES (775, 482, 25);
INSERT INTO `t_sys_user_role` VALUES (777, 484, 25);
INSERT INTO `t_sys_user_role` VALUES (779, 486, 25);
INSERT INTO `t_sys_user_role` VALUES (781, 488, 25);
INSERT INTO `t_sys_user_role` VALUES (783, 490, 25);
INSERT INTO `t_sys_user_role` VALUES (785, 492, 25);
INSERT INTO `t_sys_user_role` VALUES (787, 494, 23);
INSERT INTO `t_sys_user_role` VALUES (789, 496, 25);
INSERT INTO `t_sys_user_role` VALUES (791, 498, 25);
INSERT INTO `t_sys_user_role` VALUES (793, 500, 25);
INSERT INTO `t_sys_user_role` VALUES (795, 502, 25);
INSERT INTO `t_sys_user_role` VALUES (797, 504, 25);
INSERT INTO `t_sys_user_role` VALUES (799, 506, 25);
INSERT INTO `t_sys_user_role` VALUES (801, 508, 25);
INSERT INTO `t_sys_user_role` VALUES (803, 510, 25);
INSERT INTO `t_sys_user_role` VALUES (805, 512, 25);
INSERT INTO `t_sys_user_role` VALUES (807, 514, 25);
INSERT INTO `t_sys_user_role` VALUES (809, 516, 25);
INSERT INTO `t_sys_user_role` VALUES (811, 518, 25);
INSERT INTO `t_sys_user_role` VALUES (813, 520, 25);
INSERT INTO `t_sys_user_role` VALUES (815, 522, 25);
INSERT INTO `t_sys_user_role` VALUES (817, 524, 23);
INSERT INTO `t_sys_user_role` VALUES (819, 526, 25);
INSERT INTO `t_sys_user_role` VALUES (821, 528, 25);
INSERT INTO `t_sys_user_role` VALUES (823, 530, 25);
INSERT INTO `t_sys_user_role` VALUES (825, 532, 25);
INSERT INTO `t_sys_user_role` VALUES (827, 534, 25);
INSERT INTO `t_sys_user_role` VALUES (829, 536, 25);
INSERT INTO `t_sys_user_role` VALUES (831, 538, 25);
INSERT INTO `t_sys_user_role` VALUES (833, 540, 25);
INSERT INTO `t_sys_user_role` VALUES (835, 542, 25);
INSERT INTO `t_sys_user_role` VALUES (837, 544, 25);
INSERT INTO `t_sys_user_role` VALUES (839, 546, 25);
INSERT INTO `t_sys_user_role` VALUES (841, 548, 25);
INSERT INTO `t_sys_user_role` VALUES (843, 550, 25);
INSERT INTO `t_sys_user_role` VALUES (845, 552, 25);
INSERT INTO `t_sys_user_role` VALUES (847, 554, 25);
INSERT INTO `t_sys_user_role` VALUES (849, 556, 22);
INSERT INTO `t_sys_user_role` VALUES (851, 558, 25);
INSERT INTO `t_sys_user_role` VALUES (853, 560, 23);
INSERT INTO `t_sys_user_role` VALUES (855, 562, 25);
INSERT INTO `t_sys_user_role` VALUES (857, 564, 25);
INSERT INTO `t_sys_user_role` VALUES (859, 566, 25);
INSERT INTO `t_sys_user_role` VALUES (861, 568, 25);
INSERT INTO `t_sys_user_role` VALUES (863, 570, 25);
INSERT INTO `t_sys_user_role` VALUES (865, 572, 25);
INSERT INTO `t_sys_user_role` VALUES (867, 574, 25);
INSERT INTO `t_sys_user_role` VALUES (869, 576, 25);
INSERT INTO `t_sys_user_role` VALUES (871, 578, 25);
INSERT INTO `t_sys_user_role` VALUES (873, 580, 25);
INSERT INTO `t_sys_user_role` VALUES (875, 582, 25);
INSERT INTO `t_sys_user_role` VALUES (877, 584, 25);
INSERT INTO `t_sys_user_role` VALUES (879, 586, 25);
INSERT INTO `t_sys_user_role` VALUES (881, 588, 25);
INSERT INTO `t_sys_user_role` VALUES (883, 590, 25);
INSERT INTO `t_sys_user_role` VALUES (885, 592, 25);
INSERT INTO `t_sys_user_role` VALUES (887, 594, 23);
INSERT INTO `t_sys_user_role` VALUES (889, 596, 25);
INSERT INTO `t_sys_user_role` VALUES (891, 598, 25);
INSERT INTO `t_sys_user_role` VALUES (893, 600, 25);
INSERT INTO `t_sys_user_role` VALUES (895, 602, 25);
INSERT INTO `t_sys_user_role` VALUES (897, 604, 25);
INSERT INTO `t_sys_user_role` VALUES (899, 606, 25);
INSERT INTO `t_sys_user_role` VALUES (901, 608, 25);
INSERT INTO `t_sys_user_role` VALUES (903, 610, 25);
INSERT INTO `t_sys_user_role` VALUES (905, 612, 25);
INSERT INTO `t_sys_user_role` VALUES (907, 614, 25);
INSERT INTO `t_sys_user_role` VALUES (909, 616, 25);
INSERT INTO `t_sys_user_role` VALUES (911, 618, 25);
INSERT INTO `t_sys_user_role` VALUES (913, 620, 25);
INSERT INTO `t_sys_user_role` VALUES (915, 622, 25);
INSERT INTO `t_sys_user_role` VALUES (917, 624, 25);
INSERT INTO `t_sys_user_role` VALUES (919, 626, 25);
INSERT INTO `t_sys_user_role` VALUES (921, 628, 25);
INSERT INTO `t_sys_user_role` VALUES (923, 630, 25);
INSERT INTO `t_sys_user_role` VALUES (925, 632, 25);
INSERT INTO `t_sys_user_role` VALUES (927, 634, 25);
INSERT INTO `t_sys_user_role` VALUES (929, 636, 25);
INSERT INTO `t_sys_user_role` VALUES (931, 638, 25);
INSERT INTO `t_sys_user_role` VALUES (933, 640, 25);
INSERT INTO `t_sys_user_role` VALUES (935, 642, 25);
INSERT INTO `t_sys_user_role` VALUES (937, 644, 22);
INSERT INTO `t_sys_user_role` VALUES (939, 646, 25);
INSERT INTO `t_sys_user_role` VALUES (941, 648, 22);
INSERT INTO `t_sys_user_role` VALUES (943, 650, 23);
INSERT INTO `t_sys_user_role` VALUES (945, 652, 22);
INSERT INTO `t_sys_user_role` VALUES (947, 654, 25);
INSERT INTO `t_sys_user_role` VALUES (949, 656, 25);
INSERT INTO `t_sys_user_role` VALUES (951, 658, 25);
INSERT INTO `t_sys_user_role` VALUES (953, 660, 25);
INSERT INTO `t_sys_user_role` VALUES (955, 662, 25);
INSERT INTO `t_sys_user_role` VALUES (957, 664, 25);
INSERT INTO `t_sys_user_role` VALUES (959, 666, 25);
INSERT INTO `t_sys_user_role` VALUES (961, 668, 25);
INSERT INTO `t_sys_user_role` VALUES (963, 670, 25);
INSERT INTO `t_sys_user_role` VALUES (965, 672, 25);
INSERT INTO `t_sys_user_role` VALUES (967, 674, 22);
INSERT INTO `t_sys_user_role` VALUES (969, 676, 23);
INSERT INTO `t_sys_user_role` VALUES (971, 678, 22);
INSERT INTO `t_sys_user_role` VALUES (973, 680, 22);
INSERT INTO `t_sys_user_role` VALUES (975, 682, 22);
INSERT INTO `t_sys_user_role` VALUES (977, 684, 22);
INSERT INTO `t_sys_user_role` VALUES (979, 686, 23);
INSERT INTO `t_sys_user_role` VALUES (981, 688, 23);
INSERT INTO `t_sys_user_role` VALUES (983, 690, 25);
INSERT INTO `t_sys_user_role` VALUES (985, 692, 25);
INSERT INTO `t_sys_user_role` VALUES (987, 694, 25);
INSERT INTO `t_sys_user_role` VALUES (989, 696, 25);
INSERT INTO `t_sys_user_role` VALUES (991, 698, 25);
INSERT INTO `t_sys_user_role` VALUES (993, 700, 25);
INSERT INTO `t_sys_user_role` VALUES (995, 702, 25);
INSERT INTO `t_sys_user_role` VALUES (997, 704, 25);
INSERT INTO `t_sys_user_role` VALUES (999, 706, 25);
INSERT INTO `t_sys_user_role` VALUES (1001, 708, 25);
INSERT INTO `t_sys_user_role` VALUES (1003, 710, 25);
INSERT INTO `t_sys_user_role` VALUES (1005, 712, 23);
INSERT INTO `t_sys_user_role` VALUES (1007, 714, 25);
INSERT INTO `t_sys_user_role` VALUES (1009, 716, 25);
INSERT INTO `t_sys_user_role` VALUES (1011, 718, 25);
INSERT INTO `t_sys_user_role` VALUES (1013, 720, 25);
INSERT INTO `t_sys_user_role` VALUES (1015, 722, 25);
INSERT INTO `t_sys_user_role` VALUES (1017, 724, 25);
INSERT INTO `t_sys_user_role` VALUES (1019, 726, 25);
INSERT INTO `t_sys_user_role` VALUES (1021, 728, 23);
INSERT INTO `t_sys_user_role` VALUES (1023, 730, 25);
INSERT INTO `t_sys_user_role` VALUES (1025, 732, 25);
INSERT INTO `t_sys_user_role` VALUES (1027, 734, 25);
INSERT INTO `t_sys_user_role` VALUES (1029, 736, 25);
INSERT INTO `t_sys_user_role` VALUES (1031, 738, 25);
INSERT INTO `t_sys_user_role` VALUES (1033, 740, 23);
INSERT INTO `t_sys_user_role` VALUES (1035, 742, 23);
INSERT INTO `t_sys_user_role` VALUES (1037, 744, 25);
INSERT INTO `t_sys_user_role` VALUES (1039, 746, 25);
INSERT INTO `t_sys_user_role` VALUES (1041, 748, 25);
INSERT INTO `t_sys_user_role` VALUES (1043, 750, 25);
INSERT INTO `t_sys_user_role` VALUES (1045, 752, 25);
INSERT INTO `t_sys_user_role` VALUES (1047, 754, 25);
INSERT INTO `t_sys_user_role` VALUES (1049, 756, 25);
INSERT INTO `t_sys_user_role` VALUES (1051, 758, 25);
INSERT INTO `t_sys_user_role` VALUES (1053, 760, 25);
INSERT INTO `t_sys_user_role` VALUES (1055, 762, 25);
INSERT INTO `t_sys_user_role` VALUES (1057, 764, 25);
INSERT INTO `t_sys_user_role` VALUES (1059, 766, 25);
INSERT INTO `t_sys_user_role` VALUES (1061, 768, 25);
INSERT INTO `t_sys_user_role` VALUES (1063, 770, 25);
INSERT INTO `t_sys_user_role` VALUES (1065, 772, 25);
INSERT INTO `t_sys_user_role` VALUES (1067, 774, 25);
INSERT INTO `t_sys_user_role` VALUES (1069, 776, 25);
INSERT INTO `t_sys_user_role` VALUES (1071, 778, 25);
INSERT INTO `t_sys_user_role` VALUES (1073, 780, 25);
INSERT INTO `t_sys_user_role` VALUES (1075, 782, 25);
INSERT INTO `t_sys_user_role` VALUES (1077, 784, 25);
INSERT INTO `t_sys_user_role` VALUES (1079, 786, 25);
INSERT INTO `t_sys_user_role` VALUES (1081, 788, 25);
INSERT INTO `t_sys_user_role` VALUES (1083, 790, 25);
INSERT INTO `t_sys_user_role` VALUES (1085, 792, 25);
INSERT INTO `t_sys_user_role` VALUES (1087, 794, 25);
INSERT INTO `t_sys_user_role` VALUES (1089, 796, 23);
INSERT INTO `t_sys_user_role` VALUES (1091, 798, 25);
INSERT INTO `t_sys_user_role` VALUES (1093, 800, 25);
INSERT INTO `t_sys_user_role` VALUES (1095, 802, 25);
INSERT INTO `t_sys_user_role` VALUES (1097, 804, 25);
INSERT INTO `t_sys_user_role` VALUES (1099, 806, 25);
INSERT INTO `t_sys_user_role` VALUES (1101, 808, 25);
INSERT INTO `t_sys_user_role` VALUES (1103, 810, 25);
INSERT INTO `t_sys_user_role` VALUES (1105, 812, 25);
INSERT INTO `t_sys_user_role` VALUES (1107, 814, 25);
INSERT INTO `t_sys_user_role` VALUES (1109, 816, 23);
INSERT INTO `t_sys_user_role` VALUES (1111, 818, 23);
INSERT INTO `t_sys_user_role` VALUES (1113, 820, 23);
INSERT INTO `t_sys_user_role` VALUES (1115, 822, 25);
INSERT INTO `t_sys_user_role` VALUES (1117, 824, 22);
INSERT INTO `t_sys_user_role` VALUES (1119, 826, 25);
INSERT INTO `t_sys_user_role` VALUES (1121, 828, 25);
INSERT INTO `t_sys_user_role` VALUES (1123, 830, 25);
INSERT INTO `t_sys_user_role` VALUES (1125, 832, 25);
INSERT INTO `t_sys_user_role` VALUES (1127, 834, 25);
INSERT INTO `t_sys_user_role` VALUES (1129, 836, 25);
INSERT INTO `t_sys_user_role` VALUES (1131, 838, 25);
INSERT INTO `t_sys_user_role` VALUES (1133, 840, 25);
INSERT INTO `t_sys_user_role` VALUES (1135, 842, 25);
INSERT INTO `t_sys_user_role` VALUES (1137, 844, 25);
INSERT INTO `t_sys_user_role` VALUES (1139, 846, 25);
INSERT INTO `t_sys_user_role` VALUES (1141, 848, 23);
INSERT INTO `t_sys_user_role` VALUES (1143, 850, 25);
INSERT INTO `t_sys_user_role` VALUES (1145, 852, 25);
INSERT INTO `t_sys_user_role` VALUES (1147, 854, 25);
INSERT INTO `t_sys_user_role` VALUES (1149, 856, 25);
INSERT INTO `t_sys_user_role` VALUES (1151, 858, 25);
INSERT INTO `t_sys_user_role` VALUES (1153, 860, 25);
INSERT INTO `t_sys_user_role` VALUES (1155, 862, 25);
INSERT INTO `t_sys_user_role` VALUES (1157, 864, 25);
INSERT INTO `t_sys_user_role` VALUES (1159, 866, 25);
INSERT INTO `t_sys_user_role` VALUES (1161, 868, 25);
INSERT INTO `t_sys_user_role` VALUES (1163, 870, 25);
INSERT INTO `t_sys_user_role` VALUES (1165, 872, 25);
INSERT INTO `t_sys_user_role` VALUES (1167, 874, 25);
INSERT INTO `t_sys_user_role` VALUES (1169, 876, 25);
INSERT INTO `t_sys_user_role` VALUES (1171, 878, 25);
INSERT INTO `t_sys_user_role` VALUES (1173, 880, 25);
INSERT INTO `t_sys_user_role` VALUES (1175, 882, 25);
INSERT INTO `t_sys_user_role` VALUES (1177, 884, 25);
INSERT INTO `t_sys_user_role` VALUES (1179, 886, 25);
INSERT INTO `t_sys_user_role` VALUES (1181, 888, 25);
INSERT INTO `t_sys_user_role` VALUES (1183, 890, 25);
INSERT INTO `t_sys_user_role` VALUES (1185, 892, 25);
INSERT INTO `t_sys_user_role` VALUES (1187, 894, 25);
INSERT INTO `t_sys_user_role` VALUES (1189, 896, 25);
INSERT INTO `t_sys_user_role` VALUES (1191, 898, 25);
INSERT INTO `t_sys_user_role` VALUES (1193, 900, 25);
INSERT INTO `t_sys_user_role` VALUES (1195, 902, 25);
INSERT INTO `t_sys_user_role` VALUES (1197, 904, 25);
INSERT INTO `t_sys_user_role` VALUES (1199, 906, 25);
INSERT INTO `t_sys_user_role` VALUES (1201, 908, 25);
INSERT INTO `t_sys_user_role` VALUES (1203, 910, 25);
INSERT INTO `t_sys_user_role` VALUES (1205, 912, 25);
INSERT INTO `t_sys_user_role` VALUES (1207, 914, 25);
INSERT INTO `t_sys_user_role` VALUES (1209, 916, 25);
INSERT INTO `t_sys_user_role` VALUES (1211, 918, 25);
INSERT INTO `t_sys_user_role` VALUES (1213, 920, 25);
INSERT INTO `t_sys_user_role` VALUES (1215, 922, 25);
INSERT INTO `t_sys_user_role` VALUES (1217, 924, 25);
INSERT INTO `t_sys_user_role` VALUES (1219, 926, 25);
INSERT INTO `t_sys_user_role` VALUES (1221, 928, 25);
INSERT INTO `t_sys_user_role` VALUES (1223, 930, 25);
INSERT INTO `t_sys_user_role` VALUES (1225, 932, 25);
INSERT INTO `t_sys_user_role` VALUES (1227, 934, 25);
INSERT INTO `t_sys_user_role` VALUES (1229, 936, 25);
INSERT INTO `t_sys_user_role` VALUES (1231, 938, 25);
INSERT INTO `t_sys_user_role` VALUES (1233, 940, 25);
INSERT INTO `t_sys_user_role` VALUES (1235, 942, 25);
INSERT INTO `t_sys_user_role` VALUES (1237, 944, 25);
INSERT INTO `t_sys_user_role` VALUES (1239, 946, 25);
INSERT INTO `t_sys_user_role` VALUES (1241, 948, 25);
INSERT INTO `t_sys_user_role` VALUES (1243, 950, 25);
INSERT INTO `t_sys_user_role` VALUES (1245, 952, 25);
INSERT INTO `t_sys_user_role` VALUES (1247, 954, 25);
INSERT INTO `t_sys_user_role` VALUES (1249, 956, 25);
INSERT INTO `t_sys_user_role` VALUES (1251, 958, 25);
INSERT INTO `t_sys_user_role` VALUES (1253, 960, 25);
INSERT INTO `t_sys_user_role` VALUES (1255, 962, 25);
INSERT INTO `t_sys_user_role` VALUES (1257, 964, 25);
INSERT INTO `t_sys_user_role` VALUES (1259, 966, 25);
INSERT INTO `t_sys_user_role` VALUES (1261, 968, 25);
INSERT INTO `t_sys_user_role` VALUES (1263, 970, 25);
INSERT INTO `t_sys_user_role` VALUES (1265, 972, 25);
INSERT INTO `t_sys_user_role` VALUES (1267, 974, 25);
INSERT INTO `t_sys_user_role` VALUES (1269, 976, 25);
INSERT INTO `t_sys_user_role` VALUES (1271, 978, 25);
INSERT INTO `t_sys_user_role` VALUES (1273, 980, 25);
INSERT INTO `t_sys_user_role` VALUES (1275, 982, 25);
INSERT INTO `t_sys_user_role` VALUES (1277, 984, 25);
INSERT INTO `t_sys_user_role` VALUES (1279, 986, 25);
INSERT INTO `t_sys_user_role` VALUES (1281, 988, 25);
INSERT INTO `t_sys_user_role` VALUES (1283, 990, 25);
INSERT INTO `t_sys_user_role` VALUES (1285, 992, 25);
INSERT INTO `t_sys_user_role` VALUES (1287, 994, 25);
INSERT INTO `t_sys_user_role` VALUES (1289, 996, 25);
INSERT INTO `t_sys_user_role` VALUES (1291, 998, 25);
INSERT INTO `t_sys_user_role` VALUES (1293, 1000, 25);
INSERT INTO `t_sys_user_role` VALUES (1295, 1002, 25);
INSERT INTO `t_sys_user_role` VALUES (1297, 1004, 25);
INSERT INTO `t_sys_user_role` VALUES (1299, 1006, 25);
INSERT INTO `t_sys_user_role` VALUES (1301, 1008, 25);
INSERT INTO `t_sys_user_role` VALUES (1303, 1010, 25);
INSERT INTO `t_sys_user_role` VALUES (1305, 1012, 25);
INSERT INTO `t_sys_user_role` VALUES (1307, 1014, 25);
INSERT INTO `t_sys_user_role` VALUES (1309, 1016, 25);
INSERT INTO `t_sys_user_role` VALUES (1311, 1018, 25);
INSERT INTO `t_sys_user_role` VALUES (1313, 1020, 25);
INSERT INTO `t_sys_user_role` VALUES (1315, 1022, 25);
INSERT INTO `t_sys_user_role` VALUES (1317, 1024, 25);
INSERT INTO `t_sys_user_role` VALUES (1319, 1026, 25);
INSERT INTO `t_sys_user_role` VALUES (1321, 1028, 25);
INSERT INTO `t_sys_user_role` VALUES (1323, 1030, 25);
INSERT INTO `t_sys_user_role` VALUES (1325, 1032, 25);
INSERT INTO `t_sys_user_role` VALUES (1327, 1034, 25);
INSERT INTO `t_sys_user_role` VALUES (1329, 1036, 25);
INSERT INTO `t_sys_user_role` VALUES (1331, 1038, 25);
INSERT INTO `t_sys_user_role` VALUES (1333, 1040, 25);
INSERT INTO `t_sys_user_role` VALUES (1335, 1042, 25);
INSERT INTO `t_sys_user_role` VALUES (1337, 1044, 25);
INSERT INTO `t_sys_user_role` VALUES (1339, 1046, 25);
INSERT INTO `t_sys_user_role` VALUES (1341, 1048, 25);
INSERT INTO `t_sys_user_role` VALUES (1343, 1050, 25);
INSERT INTO `t_sys_user_role` VALUES (1345, 1052, 22);
INSERT INTO `t_sys_user_role` VALUES (1347, 1054, 23);
INSERT INTO `t_sys_user_role` VALUES (1349, 1056, 22);
INSERT INTO `t_sys_user_role` VALUES (1351, 1058, 22);
INSERT INTO `t_sys_user_role` VALUES (1353, 1060, 22);
INSERT INTO `t_sys_user_role` VALUES (1355, 1062, 23);
INSERT INTO `t_sys_user_role` VALUES (1357, 1064, 23);
INSERT INTO `t_sys_user_role` VALUES (1359, 1066, 23);
INSERT INTO `t_sys_user_role` VALUES (1361, 1068, 23);
INSERT INTO `t_sys_user_role` VALUES (1363, 1070, 23);
INSERT INTO `t_sys_user_role` VALUES (1365, 1072, 23);
INSERT INTO `t_sys_user_role` VALUES (1367, 1074, 23);
INSERT INTO `t_sys_user_role` VALUES (1369, 1076, 23);
INSERT INTO `t_sys_user_role` VALUES (1371, 1078, 23);
INSERT INTO `t_sys_user_role` VALUES (1373, 1080, 23);
INSERT INTO `t_sys_user_role` VALUES (1375, 1082, 23);
INSERT INTO `t_sys_user_role` VALUES (1377, 1084, 23);
INSERT INTO `t_sys_user_role` VALUES (1379, 1086, 23);
INSERT INTO `t_sys_user_role` VALUES (1381, 1088, 22);
INSERT INTO `t_sys_user_role` VALUES (1383, 1090, 25);
INSERT INTO `t_sys_user_role` VALUES (1385, 1092, 25);
INSERT INTO `t_sys_user_role` VALUES (1387, 1094, 25);
INSERT INTO `t_sys_user_role` VALUES (1389, 1096, 25);
INSERT INTO `t_sys_user_role` VALUES (1391, 1098, 25);
INSERT INTO `t_sys_user_role` VALUES (1393, 1100, 25);
INSERT INTO `t_sys_user_role` VALUES (1395, 1102, 25);
INSERT INTO `t_sys_user_role` VALUES (1397, 1104, 23);
INSERT INTO `t_sys_user_role` VALUES (1399, 1106, 23);
INSERT INTO `t_sys_user_role` VALUES (1401, 1108, 25);
INSERT INTO `t_sys_user_role` VALUES (1403, 1110, 25);
INSERT INTO `t_sys_user_role` VALUES (1405, 1112, 25);
INSERT INTO `t_sys_user_role` VALUES (1407, 1114, 25);
INSERT INTO `t_sys_user_role` VALUES (1409, 1116, 25);
INSERT INTO `t_sys_user_role` VALUES (1411, 1118, 25);
INSERT INTO `t_sys_user_role` VALUES (1413, 1120, 25);
INSERT INTO `t_sys_user_role` VALUES (1415, 1122, 25);
INSERT INTO `t_sys_user_role` VALUES (1417, 1124, 23);
INSERT INTO `t_sys_user_role` VALUES (1419, 1126, 25);
INSERT INTO `t_sys_user_role` VALUES (1421, 1128, 25);
INSERT INTO `t_sys_user_role` VALUES (1423, 1130, 25);
INSERT INTO `t_sys_user_role` VALUES (1425, 1132, 25);
INSERT INTO `t_sys_user_role` VALUES (1427, 1134, 25);
INSERT INTO `t_sys_user_role` VALUES (1429, 1136, 25);
INSERT INTO `t_sys_user_role` VALUES (1431, 1138, 25);
INSERT INTO `t_sys_user_role` VALUES (1433, 1140, 25);
INSERT INTO `t_sys_user_role` VALUES (1435, 1142, 25);
INSERT INTO `t_sys_user_role` VALUES (1437, 1144, 23);
INSERT INTO `t_sys_user_role` VALUES (1439, 1146, 25);
INSERT INTO `t_sys_user_role` VALUES (1441, 1148, 25);
INSERT INTO `t_sys_user_role` VALUES (1443, 1150, 25);
INSERT INTO `t_sys_user_role` VALUES (1445, 1152, 25);
INSERT INTO `t_sys_user_role` VALUES (1447, 1154, 25);
INSERT INTO `t_sys_user_role` VALUES (1449, 1156, 25);
INSERT INTO `t_sys_user_role` VALUES (1451, 1158, 25);
INSERT INTO `t_sys_user_role` VALUES (1453, 1160, 25);
INSERT INTO `t_sys_user_role` VALUES (1455, 1162, 25);
INSERT INTO `t_sys_user_role` VALUES (1457, 1164, 25);
INSERT INTO `t_sys_user_role` VALUES (1459, 1166, 25);
INSERT INTO `t_sys_user_role` VALUES (1461, 1168, 25);
INSERT INTO `t_sys_user_role` VALUES (1463, 1170, 25);
INSERT INTO `t_sys_user_role` VALUES (1465, 1172, 25);
INSERT INTO `t_sys_user_role` VALUES (1467, 1174, 25);
INSERT INTO `t_sys_user_role` VALUES (1469, 1176, 25);
INSERT INTO `t_sys_user_role` VALUES (1471, 1178, 25);
INSERT INTO `t_sys_user_role` VALUES (1473, 1180, 25);
INSERT INTO `t_sys_user_role` VALUES (1475, 1182, 25);
INSERT INTO `t_sys_user_role` VALUES (1477, 1184, 25);
INSERT INTO `t_sys_user_role` VALUES (1479, 1186, 25);
INSERT INTO `t_sys_user_role` VALUES (1481, 1188, 25);
INSERT INTO `t_sys_user_role` VALUES (1483, 1190, 25);
INSERT INTO `t_sys_user_role` VALUES (1485, 1192, 25);
INSERT INTO `t_sys_user_role` VALUES (1487, 1194, 25);
INSERT INTO `t_sys_user_role` VALUES (1489, 1196, 25);
INSERT INTO `t_sys_user_role` VALUES (1491, 1198, 25);
INSERT INTO `t_sys_user_role` VALUES (1493, 1200, 25);
INSERT INTO `t_sys_user_role` VALUES (1495, 1202, 25);
INSERT INTO `t_sys_user_role` VALUES (1497, 1204, 25);
INSERT INTO `t_sys_user_role` VALUES (1499, 1206, 25);
INSERT INTO `t_sys_user_role` VALUES (1501, 1208, 25);
INSERT INTO `t_sys_user_role` VALUES (1503, 1210, 25);
INSERT INTO `t_sys_user_role` VALUES (1505, 1212, 25);
INSERT INTO `t_sys_user_role` VALUES (1507, 1214, 25);
INSERT INTO `t_sys_user_role` VALUES (1509, 1216, 25);
INSERT INTO `t_sys_user_role` VALUES (1511, 1218, 25);
INSERT INTO `t_sys_user_role` VALUES (1513, 1220, 25);
INSERT INTO `t_sys_user_role` VALUES (1515, 1222, 25);
INSERT INTO `t_sys_user_role` VALUES (1517, 1224, 25);
INSERT INTO `t_sys_user_role` VALUES (1519, 1226, 25);
INSERT INTO `t_sys_user_role` VALUES (1521, 1228, 25);
INSERT INTO `t_sys_user_role` VALUES (1523, 1230, 22);
INSERT INTO `t_sys_user_role` VALUES (1525, 1232, 25);
INSERT INTO `t_sys_user_role` VALUES (1527, 1234, 25);
INSERT INTO `t_sys_user_role` VALUES (1529, 1236, 25);
INSERT INTO `t_sys_user_role` VALUES (1531, 1238, 25);
INSERT INTO `t_sys_user_role` VALUES (1533, 1240, 25);
INSERT INTO `t_sys_user_role` VALUES (1535, 1242, 25);
INSERT INTO `t_sys_user_role` VALUES (1537, 1244, 25);
INSERT INTO `t_sys_user_role` VALUES (1539, 1246, 25);
INSERT INTO `t_sys_user_role` VALUES (1541, 1248, 25);
INSERT INTO `t_sys_user_role` VALUES (1543, 1250, 25);
INSERT INTO `t_sys_user_role` VALUES (1545, 1252, 25);
INSERT INTO `t_sys_user_role` VALUES (1547, 1254, 25);
INSERT INTO `t_sys_user_role` VALUES (1549, 1256, 25);
INSERT INTO `t_sys_user_role` VALUES (1551, 1258, 25);
INSERT INTO `t_sys_user_role` VALUES (1553, 1260, 25);
INSERT INTO `t_sys_user_role` VALUES (1555, 1262, 23);
INSERT INTO `t_sys_user_role` VALUES (1557, 1264, 25);
INSERT INTO `t_sys_user_role` VALUES (1559, 1266, 25);
INSERT INTO `t_sys_user_role` VALUES (1561, 1268, 25);
INSERT INTO `t_sys_user_role` VALUES (1563, 1270, 25);
INSERT INTO `t_sys_user_role` VALUES (1565, 1272, 25);
INSERT INTO `t_sys_user_role` VALUES (1567, 1274, 25);
INSERT INTO `t_sys_user_role` VALUES (1569, 1276, 25);
INSERT INTO `t_sys_user_role` VALUES (1571, 1278, 25);
INSERT INTO `t_sys_user_role` VALUES (1573, 1280, 25);
INSERT INTO `t_sys_user_role` VALUES (1575, 1282, 25);
INSERT INTO `t_sys_user_role` VALUES (1577, 1284, 23);
INSERT INTO `t_sys_user_role` VALUES (1579, 1286, 25);
INSERT INTO `t_sys_user_role` VALUES (1581, 1288, 25);
INSERT INTO `t_sys_user_role` VALUES (1583, 1290, 25);
INSERT INTO `t_sys_user_role` VALUES (1585, 1292, 25);
INSERT INTO `t_sys_user_role` VALUES (1587, 1294, 25);
INSERT INTO `t_sys_user_role` VALUES (1589, 1296, 25);
INSERT INTO `t_sys_user_role` VALUES (1591, 1298, 25);
INSERT INTO `t_sys_user_role` VALUES (1593, 1300, 25);
INSERT INTO `t_sys_user_role` VALUES (1595, 1302, 25);
INSERT INTO `t_sys_user_role` VALUES (1597, 1304, 25);
INSERT INTO `t_sys_user_role` VALUES (1599, 1306, 25);
INSERT INTO `t_sys_user_role` VALUES (1601, 1308, 25);
INSERT INTO `t_sys_user_role` VALUES (1603, 1310, 25);
INSERT INTO `t_sys_user_role` VALUES (1605, 1312, 25);
INSERT INTO `t_sys_user_role` VALUES (1607, 1314, 25);
INSERT INTO `t_sys_user_role` VALUES (1609, 1316, 25);
INSERT INTO `t_sys_user_role` VALUES (1611, 1318, 25);
INSERT INTO `t_sys_user_role` VALUES (1613, 1320, 25);
INSERT INTO `t_sys_user_role` VALUES (1615, 1322, 25);
INSERT INTO `t_sys_user_role` VALUES (1617, 1324, 23);
INSERT INTO `t_sys_user_role` VALUES (1619, 1326, 23);
INSERT INTO `t_sys_user_role` VALUES (1621, 1328, 23);
INSERT INTO `t_sys_user_role` VALUES (1623, 1330, 23);
INSERT INTO `t_sys_user_role` VALUES (1625, 1332, 23);
INSERT INTO `t_sys_user_role` VALUES (1627, 1334, 25);
INSERT INTO `t_sys_user_role` VALUES (1629, 1336, 25);
INSERT INTO `t_sys_user_role` VALUES (1631, 1338, 25);
INSERT INTO `t_sys_user_role` VALUES (1633, 1340, 25);
INSERT INTO `t_sys_user_role` VALUES (1635, 1342, 25);
INSERT INTO `t_sys_user_role` VALUES (1637, 1344, 25);
INSERT INTO `t_sys_user_role` VALUES (1639, 1346, 25);
INSERT INTO `t_sys_user_role` VALUES (1641, 1348, 25);
INSERT INTO `t_sys_user_role` VALUES (1643, 1350, 25);
INSERT INTO `t_sys_user_role` VALUES (1645, 1352, 25);
INSERT INTO `t_sys_user_role` VALUES (1647, 1354, 25);
INSERT INTO `t_sys_user_role` VALUES (1649, 1356, 23);
INSERT INTO `t_sys_user_role` VALUES (1651, 1358, 25);
INSERT INTO `t_sys_user_role` VALUES (1653, 1360, 25);
INSERT INTO `t_sys_user_role` VALUES (1655, 1362, 25);
INSERT INTO `t_sys_user_role` VALUES (1657, 1364, 25);
INSERT INTO `t_sys_user_role` VALUES (1659, 1366, 25);
INSERT INTO `t_sys_user_role` VALUES (1661, 1368, 25);
INSERT INTO `t_sys_user_role` VALUES (1663, 1370, 25);
INSERT INTO `t_sys_user_role` VALUES (1665, 1372, 25);
INSERT INTO `t_sys_user_role` VALUES (1667, 1374, 25);
INSERT INTO `t_sys_user_role` VALUES (1669, 1376, 25);
INSERT INTO `t_sys_user_role` VALUES (1671, 1378, 25);
INSERT INTO `t_sys_user_role` VALUES (1673, 1380, 25);
INSERT INTO `t_sys_user_role` VALUES (1675, 1382, 25);
INSERT INTO `t_sys_user_role` VALUES (1677, 1384, 25);
INSERT INTO `t_sys_user_role` VALUES (1679, 1386, 25);
INSERT INTO `t_sys_user_role` VALUES (1681, 1388, 25);
INSERT INTO `t_sys_user_role` VALUES (1683, 1390, 25);
INSERT INTO `t_sys_user_role` VALUES (1685, 1392, 25);
INSERT INTO `t_sys_user_role` VALUES (1687, 1394, 25);
INSERT INTO `t_sys_user_role` VALUES (1689, 1396, 23);
INSERT INTO `t_sys_user_role` VALUES (1691, 1398, 25);
INSERT INTO `t_sys_user_role` VALUES (1693, 1400, 25);
INSERT INTO `t_sys_user_role` VALUES (1695, 1402, 25);
INSERT INTO `t_sys_user_role` VALUES (1697, 1404, 25);
INSERT INTO `t_sys_user_role` VALUES (1699, 1406, 25);
INSERT INTO `t_sys_user_role` VALUES (1701, 1408, 25);
INSERT INTO `t_sys_user_role` VALUES (1703, 1410, 25);
INSERT INTO `t_sys_user_role` VALUES (1705, 1412, 25);
INSERT INTO `t_sys_user_role` VALUES (1707, 1414, 25);
INSERT INTO `t_sys_user_role` VALUES (1709, 1416, 25);
INSERT INTO `t_sys_user_role` VALUES (1711, 1418, 25);
INSERT INTO `t_sys_user_role` VALUES (1713, 1420, 25);
INSERT INTO `t_sys_user_role` VALUES (1715, 1422, 25);
INSERT INTO `t_sys_user_role` VALUES (1717, 1424, 25);
INSERT INTO `t_sys_user_role` VALUES (1719, 1426, 22);
INSERT INTO `t_sys_user_role` VALUES (1721, 1428, 23);
INSERT INTO `t_sys_user_role` VALUES (1723, 1430, 22);
INSERT INTO `t_sys_user_role` VALUES (1725, 1432, 22);
INSERT INTO `t_sys_user_role` VALUES (1727, 1434, 22);
INSERT INTO `t_sys_user_role` VALUES (1729, 1436, 22);
INSERT INTO `t_sys_user_role` VALUES (1731, 1438, 22);
INSERT INTO `t_sys_user_role` VALUES (1733, 1440, 22);
INSERT INTO `t_sys_user_role` VALUES (1735, 1442, 25);
INSERT INTO `t_sys_user_role` VALUES (1737, 1444, 25);
INSERT INTO `t_sys_user_role` VALUES (1739, 1446, 25);
INSERT INTO `t_sys_user_role` VALUES (1741, 1448, 25);
INSERT INTO `t_sys_user_role` VALUES (1743, 1450, 25);
INSERT INTO `t_sys_user_role` VALUES (1745, 1452, 25);
INSERT INTO `t_sys_user_role` VALUES (1747, 1454, 25);
INSERT INTO `t_sys_user_role` VALUES (1749, 1456, 25);
INSERT INTO `t_sys_user_role` VALUES (1751, 1458, 25);
INSERT INTO `t_sys_user_role` VALUES (1753, 1460, 25);
INSERT INTO `t_sys_user_role` VALUES (1755, 1462, 25);
INSERT INTO `t_sys_user_role` VALUES (1757, 1464, 25);
INSERT INTO `t_sys_user_role` VALUES (1759, 1466, 25);
INSERT INTO `t_sys_user_role` VALUES (1761, 1468, 25);
INSERT INTO `t_sys_user_role` VALUES (1763, 1470, 25);
INSERT INTO `t_sys_user_role` VALUES (1765, 1472, 25);
INSERT INTO `t_sys_user_role` VALUES (1767, 1474, 25);
INSERT INTO `t_sys_user_role` VALUES (1769, 1476, 25);
INSERT INTO `t_sys_user_role` VALUES (1771, 1478, 25);
INSERT INTO `t_sys_user_role` VALUES (1773, 1480, 23);
INSERT INTO `t_sys_user_role` VALUES (1775, 1482, 23);
INSERT INTO `t_sys_user_role` VALUES (1777, 1484, 25);
INSERT INTO `t_sys_user_role` VALUES (1779, 1486, 25);
INSERT INTO `t_sys_user_role` VALUES (1781, 1488, 25);
INSERT INTO `t_sys_user_role` VALUES (1783, 1490, 25);
INSERT INTO `t_sys_user_role` VALUES (1785, 1492, 25);
INSERT INTO `t_sys_user_role` VALUES (1787, 1494, 25);
INSERT INTO `t_sys_user_role` VALUES (1789, 1496, 25);
INSERT INTO `t_sys_user_role` VALUES (1791, 1498, 25);
INSERT INTO `t_sys_user_role` VALUES (1793, 1500, 25);
INSERT INTO `t_sys_user_role` VALUES (1795, 1502, 25);
INSERT INTO `t_sys_user_role` VALUES (1797, 1504, 25);
INSERT INTO `t_sys_user_role` VALUES (1799, 1506, 25);
INSERT INTO `t_sys_user_role` VALUES (1801, 1508, 25);
INSERT INTO `t_sys_user_role` VALUES (1803, 1510, 25);
INSERT INTO `t_sys_user_role` VALUES (1805, 1512, 25);
INSERT INTO `t_sys_user_role` VALUES (1807, 1514, 25);
INSERT INTO `t_sys_user_role` VALUES (1809, 1516, 22);
INSERT INTO `t_sys_user_role` VALUES (1811, 1518, 25);
INSERT INTO `t_sys_user_role` VALUES (1813, 1520, 22);
INSERT INTO `t_sys_user_role` VALUES (1815, 1522, 23);
INSERT INTO `t_sys_user_role` VALUES (1817, 1524, 22);
INSERT INTO `t_sys_user_role` VALUES (2460, 1526, 8);
INSERT INTO `t_sys_user_role` VALUES (2461, 1527, 1);
INSERT INTO `t_sys_user_role` VALUES (2519, 1528, 1);
INSERT INTO `t_sys_user_role` VALUES (2463, 1529, 1);
INSERT INTO `t_sys_user_role` VALUES (2465, 1530, 1);
INSERT INTO `t_sys_user_role` VALUES (2467, 1531, 1);
INSERT INTO `t_sys_user_role` VALUES (2469, 1532, 1);
INSERT INTO `t_sys_user_role` VALUES (2471, 1533, 1);
INSERT INTO `t_sys_user_role` VALUES (2473, 1534, 1);
INSERT INTO `t_sys_user_role` VALUES (2475, 1535, 1);
INSERT INTO `t_sys_user_role` VALUES (2477, 1536, 1);
INSERT INTO `t_sys_user_role` VALUES (2479, 1537, 1);
INSERT INTO `t_sys_user_role` VALUES (2481, 1538, 1);
INSERT INTO `t_sys_user_role` VALUES (2483, 1539, 1);
INSERT INTO `t_sys_user_role` VALUES (2485, 1540, 1);
INSERT INTO `t_sys_user_role` VALUES (2487, 1541, 1);
INSERT INTO `t_sys_user_role` VALUES (2489, 1542, 1);
INSERT INTO `t_sys_user_role` VALUES (2491, 1543, 1);
INSERT INTO `t_sys_user_role` VALUES (2493, 1544, 1);
INSERT INTO `t_sys_user_role` VALUES (2495, 1545, 1);
INSERT INTO `t_sys_user_role` VALUES (2497, 1546, 1);
INSERT INTO `t_sys_user_role` VALUES (2499, 1547, 1);
INSERT INTO `t_sys_user_role` VALUES (2501, 1548, 1);
INSERT INTO `t_sys_user_role` VALUES (2503, 1549, 1);
INSERT INTO `t_sys_user_role` VALUES (2505, 1550, 1);
INSERT INTO `t_sys_user_role` VALUES (2507, 1551, 1);
INSERT INTO `t_sys_user_role` VALUES (2509, 1552, 1);
INSERT INTO `t_sys_user_role` VALUES (2511, 1553, 1);
INSERT INTO `t_sys_user_role` VALUES (2513, 1554, 1);
INSERT INTO `t_sys_user_role` VALUES (2515, 1555, 1);

SET FOREIGN_KEY_CHECKS = 1;
