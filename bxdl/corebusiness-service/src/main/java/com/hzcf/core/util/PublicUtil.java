package com.hzcf.core.util;/**
 * Description:
 * Class:PublicUtil
 * Name:
 * Date:2019/11/11 10:31
 * Return:
 * Author:sxm
 */

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 Description:
 ClassName:PublicUtil
 Date:2019/11/11 10:31
 Author:sxm
 */
public class PublicUtil {
    /**
     * 批量插入工具
     * @param list
     * @param len
     * @return
     */
    public static List<List<Map>> splitListReturnListMap(JSONArray list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<Map> dataList = new ArrayList<Map>();
        list.forEach(forMap->{
            Map beMap = (Map) forMap;
            dataList.add(beMap);
            // insPolicyInsuredPersonMapper.addInsPolicyInsuredPerson(pipMap);
        });
        List<List<Map>> result = new ArrayList<List<Map>>();
        int size = dataList.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<Map> subList = dataList.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    public static List<List<?>> splitList(List<?> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<?>> result = new ArrayList<List<?>>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<?> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }
}
