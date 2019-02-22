package com.example.iblog.common;

import com.alibaba.fastjson.JSONObject;

import java.util.Collections;
import java.util.List;

public class ConvertUtils {
    public static <N> N convertFromObject(Object object, Class<N> clazz) {
        String jsonString = JSONObject.toJSONString(object);
        if (jsonString == null) {
            return null;
        }
        N result = JSONObject.parseObject(jsonString, clazz);
        return result;
    }

    public static <O, N> List<N> convertFromList(List<O> oldList, Class<N> clazz) {
        if (oldList.size() == 0 || oldList == null) {
            return Collections.emptyList();
        }
        String jsonString = JSONObject.toJSONString(oldList);
        List<N> result = JSONObject.parseArray(jsonString, clazz);
        return result;
    }

    public static String convertSex(int sexId) {
        if (sexId == 1) return "男";
        if (sexId == 2) return "女";
        return "未知";
    }
}
