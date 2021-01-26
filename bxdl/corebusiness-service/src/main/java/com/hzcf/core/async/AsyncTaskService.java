package com.hzcf.core.async;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzcf.core.constants.Constants;
import com.hzcf.core.mapper.InsuranceSalesInfoMapper;
import com.hzcf.core.util.AESUtil;
import com.hzcf.core.util.PublicUtil;
import com.hzcf.pojo.insurance.SalesOrgInfo;
import com.hzcf.pojo.insurance.sales.InsuranceSalesInfo;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Created by qin lina on 2020/8/6.
 */
@Component
public class AsyncTaskService {
private Logger logger = LoggerFactory.getLogger(AsyncTaskService.class);
@Autowired
private InsuranceSalesInfoMapper insuranceSalesInfoMapper;
    @Value("${HK.USER.HTTP.URL}")
	private String hkUserUrl;

     @Value("${HK.ORG.HTTP.URL}")
	private String hkOrgUrl;

    @Async
    public void asyncSalesHkNotice(List<InsuranceSalesInfo> insuranceSalesInfos) {
        salesHkNotice(insuranceSalesInfos);
    }

    public void salesHkNotice(List<InsuranceSalesInfo> insuranceSalesInfos){
        List<Map> userList = new ArrayList<>();
        insuranceSalesInfos.forEach(insuranceSalesInfo -> {
            Map<String, Object> map = new HashMap<>();
            map.put("salesRank", insuranceSalesInfo.getSalesOrgId());
            map.put("idCard", insuranceSalesInfo.getCardNo());
            map.put("registerSource", "4");
            map.put("userName", insuranceSalesInfo.getInsuranceSaler());
            map.put("jobNumber", insuranceSalesInfo.getInsuranceSalerNo());
            String salesStatus = insuranceSalesInfo.getSalesStatus();
            if (Constants.SALES_STATUS_0.equals(salesStatus) || Constants.SALES_STATUS_1.equals(salesStatus)) {
                map.put("useStatus", "0");
            } else {
                map.put("useStatus", "1");
            }
            map.put("mobile", insuranceSalesInfo.getMobile());
            userList.add(map);
        });
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("users", userList);
        String userParm = JSON.toJSONString(parmMap);
        //加密参数
        try {
            userParm = AESUtil.enCrypt(userParm, Constants.STR_KEY_HK);
        } catch (Exception e) {
            logger.error("同步销售人员加密失败=============", e);
            return;
        }
        String post = HttpUtil.post(hkUserUrl, userParm);
        if (StrUtil.isNotBlank(post)) {
            try {
               post = AESUtil.deCrypt(post,Constants.STR_KEY_HK);
            } catch (Exception e) {
                logger.error("获取同步销售人员返回参数解密失败==========",e);
                return;
            }
             logger.info("返回解密信息:"+post);
            JSONObject jsonObject = JSON.parseObject(post);
            String succDate = JSON.toJSONString(jsonObject.get("data"));
            if (StrUtil.isNotBlank(succDate) && !"null".equals(succDate)) {
                List list = JSONArray.parseArray(succDate);
                String joinList = String.join("\',\'", list);
                String salerNos = "(\'" + joinList + "\')";
                Map<String, Object> map = new HashMap<>();
                map.put("salerNos", salerNos);
                //修改通知成功的状态
                insuranceSalesInfoMapper.updateSalesHkNoticeLog(map);
            }
        }
        logger.info(Thread.currentThread().getName() + ":推送完毕==========");
    }

    @Async
    public void asyncSalesHkNoticeBatch(Object salesInfo) {
         JSONArray objects = JSONObject.parseArray(salesInfo.toString());
       List<List<Map>> lists = PublicUtil.splitListReturnListMap(objects, 100);
       if (CollUtil.isNotEmpty(lists)) {
           lists.forEach((List<Map> list) -> {
               //组装参数
               List<Map> listMap = new ArrayList<>();
               list.forEach(map -> {
                   Map<String, Object> noticyMap = new HashMap<>();
                   noticyMap.put("salesRank", map.get("salesOrgId"));
                   noticyMap.put("idCard", map.get("cardNo"));
                   noticyMap.put("registerSource", "4");
                   noticyMap.put("userName", map.get("insuranceSaler"));
                   noticyMap.put("jobNumber", map.get("insuranceSalerNo"));
                   if (Constants.SALES_STATUS_0.equals(map.get("salesStatus")) || Constants.SALES_STATUS_1.equals(map.get("salesStatus"))) {
                       noticyMap.put("useStatus", "0");
                   } else {
                       noticyMap.put("useStatus", "1");
                   }
                   noticyMap.put("mobile", map.get("mobile"));
                   listMap.add(noticyMap);
               });
               Map<String, Object> noticyMap = new HashMap<>();
               noticyMap.put("users", listMap);
               String noticyStr = JSON.toJSONString(noticyMap);
               //加密参数
                try {
                    noticyStr = AESUtil.enCrypt(noticyStr, Constants.STR_KEY_HK);
                } catch (Exception e) {
                    logger.error("同步销售人员加密失败=============", e);
                    return;
                }
               String post = HttpUtil.post(hkUserUrl, noticyStr);
               if (StrUtil.isNotBlank(post)) {
                    try {
                      post = AESUtil.deCrypt(post,Constants.STR_KEY_HK);
                    } catch (Exception e) {
                        logger.error("获取同步销售人员返回参数解密失败==========",e);
                        return;
                    }
                    logger.info("返回解密信息=========:"+post);
                     JSONObject jsonObject = JSON.parseObject(post);
                    String succDate = JSON.toJSONString(jsonObject.get("data"));
                    if (StrUtil.isNotBlank(succDate) && !"null".equals(succDate)) {
                        List listPost = JSONArray.parseArray(succDate);
                        String joinList = String.join("\',\'", listPost);
                        String salerNos = "(\'" + joinList + "\')";
                        Map<String, Object> map = new HashMap<>();
                        map.put("salerNos", salerNos);
                        //修改通知成功的状态
                        insuranceSalesInfoMapper.updateSalesHkNoticeLog(map);
                    }

               }
               logger.info(Thread.currentThread().getName() + ":推送完毕==========");
           });
       }
    }

    @Async
    public void asyncOrgsHkNotice(List<SalesOrgInfo> orgInfos) {
        orgsHkNotice(orgInfos);
    }

    public void  orgsHkNotice(List<SalesOrgInfo> orgInfos){
        List<Map> orgList = new ArrayList<>();
        orgInfos.forEach(salesOrgInfo -> {
            Map<String, Object> map = new HashMap<>();
            map.put("salesOrgId",Long.valueOf(salesOrgInfo.getSalesOrgId()));
            map.put("parentSalesOrgCode",salesOrgInfo.getParentSalesOrgCode());
            map.put("salesOrgCode",salesOrgInfo.getSalesOrgCode());
            map.put("salesOrgLevel",salesOrgInfo.getSalesOrgLevel());
            map.put("salesOrgName",salesOrgInfo.getSalesOrgName());
            orgList.add(map);
        });

        Map<String, Object> noticyDateMap = new HashMap<>();
        noticyDateMap.put("saleRanks",orgList);
        String jsonStr = JSONUtil.toJsonStr(noticyDateMap);
        try {
            jsonStr = AESUtil.enCrypt(jsonStr, Constants.STR_KEY_HK);
        } catch (Exception e) {
            logger.error("同步组织机构加密失败=============", e);
            return;
        }
        String post = HttpUtil.post(hkOrgUrl, jsonStr);
        if (StrUtil.isNotBlank(post)) {
            try {
                post = AESUtil.deCrypt(post, Constants.STR_KEY_HK);
            } catch (Exception e) {
                logger.error("获取同步组织机构返回参数解密失败==========", e);
                return;
            }
            logger.info("返回解密信息:"+post);
            JSONObject jsonObject = JSON.parseObject(post);
            String succDate = JSON.toJSONString(jsonObject.get("data"));
            if (StrUtil.isNotBlank(succDate) && !"null".equals(succDate)) {
                List listPost = JSONArray.parseArray(succDate);
                List collect = (List) listPost.stream().map(list -> list.toString()).collect(Collectors.toList());
                String joinList = String.join(",", collect);
                String saleOrgIds = "(" + joinList + ")";
                Map<String, Object> map = new HashMap<>();
                map.put("saleOrgIds", saleOrgIds);
                //修改通知成功的状态
                insuranceSalesInfoMapper.updateSalesHkNoticeLog(map);
            }


        }
        logger.info(Thread.currentThread().getName() + ":推送完毕==========");
    }

}
