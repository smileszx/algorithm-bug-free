package com.algorithm.leetcode.constant;

/**
 * 
 * <p>
 * Title: 系统相关的常量规范配置
 * </p>
 * <p>
 * Description: 系统相关的常量规范配置
 * </p>
 * 
 */
public interface CoreConst {

    /** 系统用户默认自定义密码-shiro做用户相关开发时自定义的用户密码 */
    String USER_SYS_PASSWORD = "123456";

    /** 用户名的key */
    String USERNAME = "username";

    /** 用户id的key */
    String USER_ID = "userId";

    /** 路径分隔符 */
    String PATH_SEPARATOR = "-";

    /** 返回给前端的路径分隔符 */
    String PATH_SEPARATOR_CLIENT = ">";

    /** 英文逗号（前端参数多值分隔符） */
    String COMMA_US = ",";

    /** 中文逗号 */
    String COMMA_CN = "，";

    /** 空格 */
    String BLANK_SPACE = " ";

    /** 拼接id的左连接符 */
    String ID_CONNECTOR_LEFT = "[";

    /** 拼接id的右连接符 */
    String ID_CONNECTOR_RIGHT = "]";

    /** 异常提示信息左连接符 */
    String SYMBOL_LEFT_BRACE = "{";

    /** 异常信息右连接符 */
    String SYMBOL_RIGHT_BRACE = "}";

    Integer PARENT_ID = 0;

    String PATH_PREFIX = PARENT_ID + "-";

    /** 拥有所有的权限 */
    String AUTH_ALL_SYMBOL = "*";

    /** 数据库中不存在的ID主键，会用于查询空数据 */
    Integer ID_NOT_EXISTS = -1;

    /** 其他ID */
    Integer ID_OTHER = 0;

    /** 其他中文名称 */
    String NAME_CN_OTHER = "其他";

    /** 中国 */
    String NAME_CN = "中国";

    /** 销售版本 */
    String PROP_NAME_SALES_VERSION = "销售版本";

    /** 销售版本-中国默认key */
    String VALUE_KEY_SALES_VERSION_CN = "CN";

    /** 其他英文名称 */
    String NAME_EN_OTHER = "Other";

    /** 数据库中boolean类型字段的默认值，表示未选择 */
    int JDBC_BOOLEAN_DEFAULT_VALUE = -1;

    /**
     * 邮箱分隔符
     */
    String EMAIL_SEPARATOR = "@";

    /**
     * $分隔符
     */
    String SEPARATOR_$ = "$";

    /**
     * 空字符串
     */
    String STRING_EMPTY = "";

    /** #号 */
    String SYMBOL_JH = "#";

    /** 系统用户 */
    String SYSTEM_USER = "system";

    /** 日期格式化 */
    String DATE_PATTERN_YMD = "yyyy-MM-dd";

    /** 时间格式化 */
    String DATE_TIME_PATTERN_YMD = "yyyy-MM-dd HH:mm:ss";
}