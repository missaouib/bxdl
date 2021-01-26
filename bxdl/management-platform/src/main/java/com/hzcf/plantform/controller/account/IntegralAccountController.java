package com.hzcf.plantform.controller.account;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.plantform.feign.account.IntegralAccountFeignClient;
import com.hzcf.plantform.util.DataMsg;
import com.hzcf.plantform.util.DateTimeUtil;
import com.hzcf.plantform.util.ExportExcel;
import com.hzcf.plantform.util.PageModel;
import com.hzcf.util.StringUtil;

/**
 * @Description: 积分账户
 * @Author: zxr
 * @Date: 2018/11/5 11:47
 */
@Controller
@RequestMapping("/integralAccount")
public class IntegralAccountController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IntegralAccountFeignClient integralAccountFeignClient;


    /**
     * 跳转到积分账户管理列表页面
     *
     * @return
     */
    //@RequiresPermissions("accountManager:list")//权限管理;
    @RequestMapping("/goIntegralAccountPage")
    public String goIntegralAccountPage() {
        return "account/integralAccount/integralAccountList";
    }

    /**
     * 跳转到积分账户明细列表页面
     *
     * @return
     */
    //@RequiresPermissions("accountManager:list")//权限管理;
    @RequestMapping("/goIntegralAccountDetailPage")
    public String goIntegralAccountDetailPage(Model model, String customerInfoId, String realNameInfoId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerInfoId", customerInfoId);
        map.put("realNameInfoId", realNameInfoId);
        model.addAttribute("map", map);
        return "account/integralAccount/integralAccountDetailList";
    }

    /**
     * 获取积分账户列表
     *
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getIntegralAccountList")
    @ResponseBody
    public DataMsg getIntegralAccountList(HttpServletRequest request, DataMsg dataMsg) {

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
            map.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            setIntegralAccountListParams(request, map);
            PageModel pageModel = integralAccountFeignClient.getIntegralAccountList(map);
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
        } catch (Exception e) {
            logger.error("查询积分账户异常", e);
        }

        return dataMsg;
    }

    /**
     * 积分账户列表参数
     *
     * @param request
     * @param map
     */
    private void setIntegralAccountListParams(HttpServletRequest request, Map<String, Object> map) {
        String customerName = StringUtil.trim(request.getParameter("customerName"));
        if (StringUtil.isNotBlank(customerName)) {
            map.put("customerName", customerName);
        }
        String cardNo = StringUtil.trim(request.getParameter("cardNo"));
        if (StringUtil.isNotBlank(cardNo)) {
            map.put("cardNo", cardNo);
        }
        String accountStatus = StringUtil.trim(request.getParameter("accountStatus"));
        if (StringUtil.isNotBlank(accountStatus)) {
            map.put("accountStatus", accountStatus);
        }
        String channel = StringUtil.trim(request.getParameter("channel"));
        if (StringUtil.isNotBlank(channel)) {
            map.put("channel", channel);
        }
        String minRegisterTime = request.getParameter("minRegisterTime");
        if (StringUtil.isNotBlank(minRegisterTime)) {
            map.put("minRegisterTime", minRegisterTime);
        }
        String maxRegisterTime = request.getParameter("maxRegisterTime");
        if (StringUtil.isNotBlank(maxRegisterTime)) {
            map.put("maxRegisterTime", maxRegisterTime);
        }
    }


    /**
     * 导出积分账户列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/exportIntegralAccountList")
    @ResponseBody
    public void exportIntegralAccountList(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> map1 = new HashMap<String, Object> ();
            setIntegralAccountListParams(request, map1);
            List<Map<String, Object>> result = integralAccountFeignClient.exportIntegralAccountList(map1);
            List<Object[]> dataList = new ArrayList<Object[]>();
            //月数据导出
            String title = "积分账户列表导出-" + DateTimeUtil.getNowDateChinaString();
            Object[] objs = null;
            Map<String, Object> map = null;
            String[] rowsName = new String[]{"序号", "用户名", "客户姓名", "身份账号", "通用积分数量（分）", "可用通用积分数量（分）",
                    "已消耗通用积分数量（分）", "自有积分总数（分）", "可用自有积分数量（分）", "已消耗自有积分数量（分）",
                    "账户状态", "账户创建时间", "渠道"};
            for (int i = 0; i < result.size(); i++) {
                map = result.get(i);
                objs = new Object[rowsName.length];
                objs[0] = i + 1;//序号
                objs[1] = map.get("userName");//用户名
                objs[2] = map.get("customerName");//客户姓名
                objs[3] = map.get("cardNo");//身份账号
                objs[4] = map.get("totalCurrencyIntegral");//通用积分数量
                objs[5] = map.get("availableCurrencyIntegral");//可用通用积分数量
                objs[6] = map.get("usedCurrencyIntegral");//已消耗通用积分数量
                objs[7] = map.get("totalOwnIntegral");//自有积分总数
                objs[8] = map.get("availableOwnIntegral");//可用自有积分数量
                objs[9] = map.get("usedOwnIntegral");//已消耗自有积分数量
                objs[10] = map.get("accountStatus");//账户状态
                objs[11] = map.get("createTime");//账户创建时间
                objs[12] = map.get("channel");//渠道
                dataList.add(objs);
            }
            ExportExcel ex = new ExportExcel(title, rowsName, dataList);
            ex.export(response);

        } catch (Exception e) {
            logger.error("导出积分账户列表异常", e);
        }
    }

    /**
     * 积分账户明细
     *
     * @param request
     * @param dataMsg
     * @return
     */
    @RequestMapping("/getIntegralAccountDetailList")
    @ResponseBody
    public DataMsg getIntegralAccountDetailList(HttpServletRequest request, DataMsg dataMsg) {

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pageNo", Integer.valueOf(request.getParameter("pageNumber")));
            map.put("pageSize", Integer.valueOf(request.getParameter("pageSize")));
            setIntegralAccountDetailListParams(request, map);
            PageModel pageModel = integralAccountFeignClient.getIntegralAccountDetailList(map);
            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());
        } catch (Exception e) {
            logger.error("查询积分账户异常", e);
        }
        return dataMsg;
    }

    /**
     * 积分账户明细动态字段
     *
     * @param request
     * @param map
     */
    private void setIntegralAccountDetailListParams(HttpServletRequest request, Map<String, Object> map) {
        String customerInfoId = StringUtil.trim(request.getParameter("customerInfoId"));
        if (StringUtil.isNotBlank(customerInfoId)) {
            map.put("customerInfoId", customerInfoId);
        }
        String realNameInfoId = StringUtil.trim(request.getParameter("realNameInfoId"));
        if (StringUtil.isNotBlank(realNameInfoId)) {
            map.put("realNameInfoId", realNameInfoId);
        }
        String detailOddNo = StringUtil.trim(request.getParameter("detailOddNo"));
        if (StringUtil.isNotBlank(detailOddNo)) {
            map.put("detailOddNo", detailOddNo);
        }
        String detailType = StringUtil.trim(request.getParameter("detailType"));
        if (StringUtil.isNotBlank(detailType)) {
            map.put("detailType", detailType);
        }
        String integralType = StringUtil.trim(request.getParameter("integralType"));
        if (StringUtil.isNotBlank(integralType)) {
            map.put("integralType", integralType);
        }
        String status = StringUtil.trim(request.getParameter("status"));
        if (StringUtil.isNotBlank(status)) {
            map.put("status", status);
        }
        String minCreateTime = request.getParameter("minCreateTime");
        if (StringUtil.isNotBlank(minCreateTime)) {
            map.put("minCreateTime", minCreateTime);
        }
        String maxCreateTime = request.getParameter("maxCreateTime");
        if (StringUtil.isNotBlank(maxCreateTime)) {
            map.put("maxCreateTime", maxCreateTime);
        }
    }

    /**
     * 导出积分账户明细
     *
     * @param request
     * @param response
     */
    @RequestMapping("/exportIntegralAccountDetailList")
    @ResponseBody
    public void exportIntegralAccountDetailList(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            setIntegralAccountDetailListParams(request, map);
            List<Map<String, Object>> result = integralAccountFeignClient.exportIntegralAccountDetailList(map);
            List<Object[]> dataList = new ArrayList<Object[]>();
            //月数据导出
            String title = "积分账户明细列表导出-" + DateTimeUtil.getNowDateChinaString();
            Object[] objs = null;
            Map<String, Object> map1 = null;
            String[] rowsName = new String[]{"序号", "明细单号", "生成时间", "积分类别", "明细类别",
                    "积分数量（分）", "状态"};
            for (int i = 0; i < result.size(); i++) {
                map1 = result.get(i);
                objs = new Object[rowsName.length];
                objs[0] = i + 1;//序号
                objs[1] = map1.get("detailOddNo");//明细单号
                objs[2] = map1.get("buildTime");//生成时间
                objs[3] = map1.get("integralType");//积分类别
                objs[4] = map1.get("detailType");//明细类别
                objs[5] = map1.get("integralNum");//积分数量
                objs[6] = map1.get("status");//状态
                dataList.add(objs);
            }
            ExportExcel ex = new ExportExcel(title, rowsName, dataList);
            ex.export(response);

        } catch (Exception e) {
            logger.error("导出积分账户列表异常", e);
        }


    }
}
