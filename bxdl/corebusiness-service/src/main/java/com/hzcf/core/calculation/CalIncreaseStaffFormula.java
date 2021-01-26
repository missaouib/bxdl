package com.hzcf.core.calculation;

import com.hzcf.pojo.cal.CalIncreaseStaff;

import java.math.BigDecimal;
import java.util.List;

/**
 * 增员津贴计算
 *  2020/10/14.
 */
public class CalIncreaseStaffFormula {
    public static BigDecimal increaseStaffFormula(BigDecimal zyFYC, List<CalIncreaseStaff> calIncreaseStaffList){
        BigDecimal zyjt = BigDecimal.valueOf(0.00);
        for (CalIncreaseStaff cis :calIncreaseStaffList){
             boolean formula_min = false;
             boolean formula_max = false;
              //上限符号 0大于 1大于等于于
             if ("0".equals(cis.getMinSign())){
                 formula_min = zyFYC.compareTo(cis.getMinValue()) > 0;
             }else if ("1".equals(cis.getMinSign())){
                 formula_min = zyFYC.compareTo(cis.getMinValue()) >= 0;
             }
             //下限符号 0小于 1小于等于
            if ("0".equals(cis.getMaxSign())){
                 formula_max = zyFYC.compareTo(cis.getMaxValue()) < 0;
            }else if ("1".equals(cis.getMaxSign())){
                formula_max = zyFYC.compareTo(cis.getMaxValue()) <= 0;
            }
            if (formula_min && formula_max){
                zyjt = zyFYC.multiply(cis.getAllowanceRatio());
                break;
            }
        }
        return zyjt;

    }
}
