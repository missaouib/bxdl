package com.hzcf.core.calculation;

import cn.hutool.core.util.StrUtil;
import com.hzcf.core.constants.Constants;
import com.hzcf.pojo.cal.CalDirectlyUnderDept;

import java.math.BigDecimal;
import java.util.List;

/**
 * 直辖部管理津贴
 * Created by qin lina on 2020/10/15.
 */
public class CalDirectlyUnderDeptFormula {

    public static BigDecimal directlyUnderDeptFormula(BigDecimal zxbFyc,String extraType, List<CalDirectlyUnderDept> calDirectlyUnderDeptList){
        BigDecimal zxbgljt = BigDecimal.valueOf(0.00);
        //说明是江苏或者山西的直辖组管理津贴  部长直辖处的FYC系数 取固定值
       if (StrUtil.isNotBlank(extraType) && Constants.INCLUDE_DIRECTLY_GROUP_FLAG_1.equals(extraType)){
           zxbgljt = zxbFyc.multiply(calDirectlyUnderDeptList.get(0).getExtraRatio());
       }else {
           for (CalDirectlyUnderDept calDirectlyUnderDept:calDirectlyUnderDeptList){
                boolean formula_min = false;
                boolean formula_max = false;
                //0：大于，1：大于等于
                if ("0".equals(calDirectlyUnderDept.getMinSign())){
                    formula_min = zxbFyc.compareTo(calDirectlyUnderDept.getMinValue()) > 0;
                }else if("1".equals(calDirectlyUnderDept.getMinSign())){
                    formula_min = zxbFyc.compareTo(calDirectlyUnderDept.getMinValue()) >= 0;
               }

               //0：小于，1：小于等于
               if ("0".equals(calDirectlyUnderDept.getMaxSign())){
                    formula_max = zxbFyc.compareTo(calDirectlyUnderDept.getMaxValue()) < 0;
               }else  if ("1".equals(calDirectlyUnderDept.getMaxSign())){
                   formula_max = zxbFyc.compareTo(calDirectlyUnderDept.getMaxValue()) <=0;
               }

               if (formula_min &formula_max){
                   zxbgljt = zxbFyc.multiply(calDirectlyUnderDept.getAllowanceRatio());
                   break;
               }
           }


       }
        return zxbgljt;
    }
}
