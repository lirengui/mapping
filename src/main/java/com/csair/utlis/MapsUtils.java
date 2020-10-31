package com.csair.utlis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * Url映射配置加载辅助工具类
 *
 * @author LiRenGui
 * @date 2020年10月31日18:30:47
 */

public class MapsUtils {

    private static final Logger log = LoggerFactory.getLogger(MapsUtils.class);

    private static Map<String, String> propertiesMap = null;

    private static void processProperties(Properties properties) {
        propertiesMap = new HashMap<String, String>();
        for (Object key : properties.keySet()) {
            String keyStr = key.toString();
            try {
                propertiesMap.put(keyStr, new String(properties.getProperty(keyStr).getBytes("ISO-8859-1"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void loadAllProperties(String propertiesFileName) {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties(propertiesFileName);
            processProperties(properties);
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name) == null ? "" : propertiesMap.get(name);
    }

    public static Map<String, String> getAllProperty() {
        return propertiesMap;
    }
}

