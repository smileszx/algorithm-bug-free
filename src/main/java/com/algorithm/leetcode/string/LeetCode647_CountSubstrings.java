package com.algorithm.leetcode.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 *
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 *
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 注意:
 *
 * 输入的字符串长度不会超过1000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author victor su
 * @Date 2019/10/2 21:14
 **/
public class LeetCode647_CountSubstrings {
    private static final Logger LOGGER = LoggerFactory.getLogger(LeetCode647_CountSubstrings.class);

    public static void main(String[] args) {

        String s = "abccba";
        Integer result = countSubstrings(s);
        LOGGER.info("========> 回文字符串: {}, 回文子串数：{}", s, result);
    }

    /**
     * 中心扩展法
     * @param s
     * @return
     */
    public static Integer countSubstrings (String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            //以当前点i位置，向两边扩展,以i i+1位置向两边扩展
            result += countSegment(s, i, i);
            result += countSegment(s, i, i + 1);
        }
        return result;
    }

    /**
     * 判断当前字符串是否为回文字符串
     * @param s
     * @param start
     * @param end
     * @return
     */
    public static int countSegment(String s, int start, int end) {
        int count = 0;
        //start往左边跑，end往右边跑，注意边界
        while (start >= 0 && end < s.length() && s.charAt(start--) == s.charAt(end++)) {
            count++;
        }
        LOGGER.info("==> 包含 {} 个回文子串。", count);
        return count;
    }

}
