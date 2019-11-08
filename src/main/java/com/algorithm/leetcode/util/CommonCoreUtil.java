package com.algorithm.leetcode.util;

import com.algorithm.leetcode.constant.CoreConst;
import com.algorithm.leetcode.constant.NumberConst;
import com.algorithm.leetcode.constant.RegexConst;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共功能的工具类
 */

public class CommonCoreUtil {

    /**
     * 登录用户默认密码,只用于shiro认证
     * @param miliao
     * @return
     */
    public static String getCredentials(String miliao) {
        String hashAlgorithmName = "md5";
        // 密码
        String credentials = CoreConst.USER_SYS_PASSWORD;
        int hashIterations = 1024;
        // 账号
        ByteSource credentialsSalt = ByteSource.Util.bytes(miliao);
        String obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations).toHex();
        return obj;
    }

    /**
     * 
     * @param str
     * @return
     */
    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile(RegexConst.REGEX_UNICODE_TO);
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char)Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;

    }

    /**
     * 获取校验不通过的第一条信息
     * 
     * @param bindingResult
     * @return
     */
    public static String getErrorFirstStr(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            DefaultMessageSourceResolvable dmsr =
                (DefaultMessageSourceResolvable)bindingResult.getAllErrors().get(0).getArguments()[0];
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            String fieldName = dmsr.getDefaultMessage();
            return CommonCoreUtil.makeTips(fieldName, errorMessage);
        }
        return null;
    }

    /**
     * 
     * @param str
     *            待拆分的字符串，且都是数字类型
     * @return
     */
    public static List<Integer> listIntegerSplit(String str, String separator) {
        List<Integer> list = null;
        if (!StringUtils.isEmpty(str)) {
            list = new ArrayList<>();
            String[] array = str.split(separator);
            for (int i = 0; i < array.length; i++) {
                list.add(Integer.valueOf(array[i]));
            }
        }
        return list;
    }

    /**
     * 解析查询条件 支持空格/中文逗号/英文逗号
     * 
     * @param str
     * @return
     */
    public static List<String> listStrSearchDataSplit(String str) {
        List<String> list = null;
        if (!StringUtils.isEmpty(str)) {
            list = new ArrayList<>();
            str = str.replace(CoreConst.COMMA_CN, CoreConst.COMMA_US);
            str = str.replace(CoreConst.BLANK_SPACE, CoreConst.COMMA_US);
            String[] array = str.split(CoreConst.COMMA_US);
            for (int i = 0; i < array.length; i++) {
                list.add(array[i]);
            }
        }
        return list;
    }

    /**
     * 
     * @param str
     *            待拆分的字符串
     * @return
     */
    public static List<String> listStrSplit(String str, String separator) {
        List<String> list = null;
        if (!StringUtils.isEmpty(str)) {
            list = new ArrayList<>();
            String[] array = str.split(separator);
            for (int i = 0; i < array.length; i++) {
                list.add(array[i]);
            }
        }
        return list;
    }

    /**
     * 判断是否为整数
     * 
     * @param str
     *            传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        String re = "^[-\\+]?[\\d]*$";
        Pattern pattern = Pattern.compile(re);
        return pattern.matcher(str).matches();
    }

    /**
     * 对象深拷贝
     * 
     * @param src
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T> T deepCopy(T src) throws IOException, ClassNotFoundException {
        if (src == null) {
            return null;
        }
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        T dest = (T)in.readObject();
        return dest;
    }

    /**
     * 数组转为字符串表达形式
     * 
     * @param list
     *            字符串数组 not required
     * @return 例如1,2,3
     */
    public static String convertListToString(List<String> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            StringBuffer sb = new StringBuffer();
            for (String string : list) {
                sb.append(string).append(CoreConst.COMMA_US);
            }
            return sb.substring(0, sb.length() - 1);
        }
        return null;
    }

    /**
     * 把路径ID解析成数组形式（例如：树形结构的分类路径ID解析）
     * 
     * @param path
     *            路径ID的path
     * @return
     */
    public List<Integer> listTreePathId(String path) {
        if (StringUtils.isNotEmpty(path)) {
            List<String> pathId = CommonCoreUtil.listSplitStr(path, CoreConst.PATH_SEPARATOR);
            if (pathId.size() >= 2) {
                pathId = pathId.subList(1, pathId.size());
                return CommonCoreUtil.listConverStrToInteger(pathId);
            }
        }
        return null;
    }

    /**
     * 根据前后缀拼接信息
     * 
     * @param prefix
     *            前缀 required
     * @param suffix
     *            后缀 not required
     * @return prefix[suffix] or prefix
     */
    public static String makeTips(String prefix, String suffix) {
        String msg = prefix;
        if (StringUtils.isNotEmpty(prefix)) {
            msg = msg + CoreConst.ID_CONNECTOR_LEFT + suffix + CoreConst.ID_CONNECTOR_RIGHT;
        }
        return msg;
    }

    /**
     * 组织信息
     * 
     * @param input
     *            字符串信息 not required
     * @return [input] or ""
     */
    public static String makeTips(String input) {
        if (StringUtils.isNotEmpty(input)) {
            return CoreConst.ID_CONNECTOR_LEFT + input + CoreConst.ID_CONNECTOR_RIGHT;
        }
        return "";
    }

    /**
     * 分解字符串
     * 
     * @param str
     *            字符串 not required 例如1,2,3
     * @param splitSeparator
     *            要拆分的分隔符 not required 例如,
     * @return 返回拆分后的List字符串集合信息
     */
    public static List<String> listSplitStr(String str, String splitSeparator) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(splitSeparator)) {
            return null;
        }
        String[] array = str.split(splitSeparator);
        List<String> list = Arrays.asList(array);
        return list;
    }

    /**
     * String类型的List集合转为Integer类型的List集合
     * 
     * @param list
     *            String类型的List集合 not required
     * @return Integer类型的List集合 or null
     */
    public static List<Integer> listConverStrToInteger(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<Integer> intList = new ArrayList<>();
        for (String str : list) {
            intList.add(Integer.valueOf(str));
        }
        return intList;
    }

    /**
     * 字符串类型的List集合转为字符串表现形式
     * 
     * @param list
     *            字符串类型的List集合 not required
     * @param separator
     *            分隔符 not required
     * @return 字符串表现形式 例如 1,2,3 or null
     */
    public static String listConvertToStr(List<String> list, String separator) {
        if (list == null || list.isEmpty() || StringUtils.isEmpty(separator)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (String str : list) {
            if (StringUtils.isNotBlank(str)) {
                sb.append(str).append(separator);
            }
        }
        if (sb.length() > 1) {
            return sb.substring(0, sb.length() - separator.length());
        }
        return null;
    }

    /**
     * 判断字符串是否非空且非空字符串且非0(!=null && !="" && !="0")
     * 
     * 
     * @param input
     *            required
     * @return
     */
    public static boolean isNotEmptyZero(String input) {
        return StringUtils.isNotEmpty(input) && !CoreConst.ID_OTHER.toString().equals(input);
    }

    /**
     * 公共的参数不合法的Map结构形式
     *
     * @param key
     * @param value
     * @return
     */
    public static Map<String, Object> getCommonIllegalMapParam(String key, String value) {
        Map<String, Object> map = new HashMap<String, Object>(NumberConst.INIT_HASHMAP_MIN_SIZE);
        map.put("key", key);
        map.put("value", value);
        return map;
    }

    /**
     * 组织拥有一个key-value对的map
     *
     * @param key
     *            KEY
     * @param value
     *            VALUE
     * @return
     */
    public static Map<String, Object> makeOneKeyValueMap(String key, String value) {
        Map<String, Object> map = new HashMap<String, Object>(NumberConst.INIT_HASHMAP_MIN_SIZE);
        map.put(key, value);
        return map;
    }

    /**
     * 判断字符是否为英文字母
     * 
     * @param c
     *            字符
     * @return
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     * 
     * @param str
     *            字符串
     * @return int 得到的字符串长度
     */
    public static int length(String str) {
        if (str == null) {
            return 0;
        }
        char[] c = str.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }
}
