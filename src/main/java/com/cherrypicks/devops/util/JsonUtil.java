package com.cherrypicks.devops.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public final class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static Gson mapper = null; // can reuse, share

    static {
    	mapper = new Gson();
    }

    private JsonUtil() {
    }

    /**
     * 将对象转成json.
     *
     * @param obj
     *            对象
     * @return
     */
    public static String toJson(final Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return mapper.toJson(obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> List<T> toObject(final List<String> jsonList, final Class<T> valueType) {
        if (jsonList == null || jsonList.isEmpty()) {
        	return new ArrayList<>();
        }
        final List<T> list = new ArrayList<>();
        for (final String json : jsonList) {
            list.add(JsonUtil.toObject(json, valueType));
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(final String content) {
        return JsonUtil.toObject(content, Map.class);
    }

    @SuppressWarnings("unchecked")
    public static Set<Object> toSet(final String content) {
        return JsonUtil.toObject(content, Set.class);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toNotNullMap(final String json) {
        Map<String, Object> map = JsonUtil.toObject(json, Map.class);
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return map;
    }
    
    /**
     * 类型转换.
     *
     * @param obj
     * @param clazz
     * @return
     */
    public static <T> T convert(final Object obj, final Class<T> clazz) {
        final String json = JsonUtil.toJson(obj);
        return toObject(json, clazz);
    }

    /**
     * 将Json转换成对象.
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T toObject(final String json, final Class<T> clazz) {
        if (json == null || json.length() == 0) {
            return null;
        }
        try {
            return  mapper.fromJson(json, clazz);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static void print(final Object obj) {
        final String json = JsonUtil.toJson(obj);
        logger.debug("json:{}", json);
    }
}
