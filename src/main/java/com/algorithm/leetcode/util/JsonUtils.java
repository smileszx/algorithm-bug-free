package com.algorithm.leetcode.util;

import com.algorithm.leetcode.constant.NumberConst;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 
 * <p>
 * Title: json转换工具类
 * </p>
 * <p>
 * Description: json转换工具类
 * </p>
 */
@Slf4j
public class JsonUtils {

    public static <T> T json2BeanByType(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     * 
     * @param jsonString
     * @param beanCalss
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        T bean = (T)JSONObject.toBean(jsonObject, beanCalss);
        return bean;

    }

    /**
     * 将java对象转换成json字符串
     *
     * @param bean
     * @return
     */
    public static String beanToJson(Object bean) {
        JSONObject json = JSONObject.fromObject(bean);
        return json.toString();

    }

    /**
     * 将java对象转换成json字符串
     *
     * @param bean
     * @return
     */
    public static String beanToJsonNoDefaultValue(Object bean) {
        StringWriter str = new StringWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 属性对象为null值不参与序列化
            objectMapper.setSerializationInclusion(Include.NON_NULL);
            objectMapper.writeValue(str, bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    /**
     * 
     * @param excludes
     * @return
     */
    private static JsonConfig configJson(String[] excludes) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        jsonConfig.setIgnoreDefaultExcludes(false);
        // jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        // jsonConfig.registerJsonValueProcessor(Date.class,
        // new DateJsonValueProcessor(datePattern));
        return jsonConfig;

    }

    /**
     * 从json HASH表达式中获取一个map，改map支持嵌套功能
     * 
     * @param jsonString
     * @return
     */
    @SuppressWarnings({"unchecked"})
    public static Map<String, Object> jsonToMap(String jsonString) {

        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Iterator<String> keyIter = jsonObject.keys();
        Map<String, Object> valueMap = new HashMap<String, Object>(NumberConst.INIT_HASHMAP_SIZE);

        while (keyIter.hasNext()) {
            String key = (String)keyIter.next();
            Object value = jsonObject.get(key).toString();
            valueMap.put(key, value);
        }
        return valueMap;
    }

    /**
     * 从json数组对象java对象列表
     *
     * @param jsonArray
     * @param beanClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> jsonToBeanList(JSONArray jsonArray, Class<T> beanClass) {

        int size = jsonArray.size();
        List<T> list = new ArrayList<T>(size);
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            T bean = (T)JSONObject.toBean(jsonObject, beanClass);
            list.add(bean);
        }
        return list;
    }

    /**
     * 
     * @param json
     * @param typeReference
     * @return
     */
    public static <T> T jsonTo(String json, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            json = CommonCoreUtil.unicodeToString(json);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.error("json to map error:" + json, e);
            return null;
        }
    }

    /**
     * 
     * @param json
     * @param typeReference
     * @param isTranslate
     *            是否移除转义信息
     * @return
     */
    public static <T> T jsonTo(String json, TypeReference<T> typeReference, boolean isTranslate) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            json = StringEscapeUtils.unescapeJavaScript(json);
            json = CommonCoreUtil.unicodeToString(json);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.error("json to map error:" + json, e);
            return null;
        }
    }

    public static String listToJson(List<?> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();

    }
}
