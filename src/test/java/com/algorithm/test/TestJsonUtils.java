package com.algorithm.test;

import com.algorithm.leetcode.util.JsonUtils;
import com.algorithm.test.bean.BeanDemo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Test the JsonUtils Class
 */
@Slf4j
public class TestJsonUtils {
    @Test
    public void testBeanToJson () {

        BeanDemo demo = new BeanDemo();

        demo.setValue("xiaomi");

        String result = JsonUtils.beanToJson(demo);

        log.info("bean convert to json: {}" , result);


    }
}
