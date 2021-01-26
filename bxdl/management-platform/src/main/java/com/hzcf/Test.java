package com.hzcf;

import com.alibaba.fastjson.JSON;
import com.hzcf.pojo.basic.SystemDict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<SystemDict> list = new ArrayList<>();
        SystemDict systemDict = new SystemDict();
        SystemDict systemDict1 = new SystemDict();
        SystemDict systemDict2 = new SystemDict();
        systemDict.setDictCode("1");
        systemDict.setDictName("字典1");

        systemDict1.setDictCode("2");
        systemDict1.setDictName("字典2");
        systemDict2.setDictCode("3");
        systemDict2.setDictName("字典3");
        list.add(systemDict);
        list.add(systemDict1);
        list.add(systemDict2);
        Map<String, String> resMap = list.stream().collect(Collectors.toMap(SystemDict::getDictCode, SystemDict::getDictName));
        System.out.println("args = " + JSON.toJSONString(resMap));

    }
}
