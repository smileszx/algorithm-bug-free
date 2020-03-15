package com.algorithm.leetcode.constant;

/**
 * 正则表达式
 */
public class RegexConst {

    /**
     * %&',;=?$\"等字符
     */
    public static final String SPECIAL_CHAR_TRIM = "%&',;=?$&#@！~*";

    /**
     * %&',;=?$\"等字符 正则表达式
     */
    public static final String SPECIAL_CHAR = "[" + SPECIAL_CHAR_TRIM + "]+";

    /**
     * 只能输入由数字、26个英文字母、减号组成的字符串 [A-Za-z0-9\\-]+$
     */
    public static final String REGEX_01 = "[A-Za-z0-9\\-]+$";

    /**
     * 以字母开头、允许字母、数字、下划线 ^[a-zA-Z][a-zA-Z0-9_]*$
     */
    public static final String REGEX_KEY_STR = "^[a-zA-Z][a-zA-Z0-9_]*$";

    /**
     * unicode to uft
     */
    public static final String REGEX_UNICODE_TO = "(\\\\u(\\p{XDigit}{4}))";

}
