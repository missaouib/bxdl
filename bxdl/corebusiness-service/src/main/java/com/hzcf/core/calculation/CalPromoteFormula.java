package com.hzcf.core.calculation;

import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.pojo.cal.CalPromote;

import java.math.BigDecimal;
import java.util.List;

/**
 * 展业津贴 模板计算
 * Created by qin lina on 2020/10/10.
 */
public class CalPromoteFormula {

    /**
     * 展业津贴计算
     * @return
     */
    public static BigDecimal promoteFormula(BigDecimal selfFyc, List<CalPromote> promoteParas) {
        //展业津贴
        BigDecimal zhyjt = BigDecimal.valueOf(0.00);
        for (CalPromote cp : promoteParas) {
            boolean formula_min = false;
            boolean formula_max = false;
            //上限符号 0大于 1大于等于于
            if (cp.getMinSign().equals("0")) {
                formula_min = selfFyc.compareTo(cp.getMinValue()) > 0;
            } else if (cp.getMinSign().equals("1")) {
                formula_min = selfFyc.compareTo(cp.getMinValue()) >= 0;
            }
            //下限符号 0小于 1小于等于
            if (cp.getMaxSign().equals("0")) {
                formula_max = selfFyc.compareTo(cp.getMaxValue()) < 0;
            } else if (cp.getMaxSign().equals("1")) {
                formula_max = selfFyc.compareTo(cp.getMaxValue()) <= 0;
            }
            if (formula_min && formula_max) {
                //取固定值
                if (cp.getRuleType().equals(CalParamRuleEnum.RULE_ZY_0.getCode())) {
                    zhyjt = cp.getAllowance();
                } else if (cp.getRuleType().equals(CalParamRuleEnum.RULE_ZY_1.getCode())) {
                    //FYC百位取整
                    zhyjt = selfFyc.setScale(0, BigDecimal.ROUND_DOWN).divideAndRemainder(BigDecimal.valueOf(100))[0].multiply(BigDecimal.valueOf(100));
                } else if (cp.getRuleType().equals(CalParamRuleEnum.RULE_ZY_2.getCode())) {
                    //FYC 乘以比例
                    zhyjt = selfFyc.multiply(cp.getAllowance());
                } else if (cp.getRuleType().equals(CalParamRuleEnum.RULE_ZY_3.getCode())) {
                    //FYC取整并有步长
                    zhyjt = cp.getAllowance();
                    //增加的部分 =（展业津贴FYC-系数上限）/ 步长* 额外增加津贴
                    BigDecimal addPart = selfFyc.subtract(cp.getMinValue()).setScale(0, BigDecimal.ROUND_DOWN).divideAndRemainder(cp.getStep())[0].multiply(cp.getExtraAllowance());
                    // BigDecimal addPart = new BigDecimal((int) Math.floor(((selfFyc.subtract(cp.getMaxValue())).divide(cp.getStep())).doubleValue())).multiply(cp.getExtraAllowance());
                    zhyjt = zhyjt.add(addPart);
                }

                break;
            }
        }

        return zhyjt;

    }

     public static void main(String[] args) {
       BigDecimal selfFyc= new BigDecimal("2000.98");
       BigDecimal MaxValue= new BigDecimal("2000.98");
       BigDecimal step= new BigDecimal(500);
       BigDecimal ExtraAllowance= new BigDecimal(200);
         BigDecimal bigDecimals = selfFyc.setScale(0, BigDecimal.ROUND_DOWN).divideAndRemainder(BigDecimal.valueOf(100))[0].multiply(BigDecimal.valueOf(100));
         System.out.println(bigDecimals);
            BigDecimal addPart1 = selfFyc.subtract(MaxValue).setScale(0,BigDecimal.ROUND_DOWN).divideAndRemainder(step)[0].multiply(ExtraAllowance);
           BigDecimal addPart2 = new BigDecimal((int) Math.floor(((selfFyc.subtract(MaxValue)).divide(step)).doubleValue())).multiply(ExtraAllowance);
         System.out.println(addPart1+","+addPart2);

         System.out.println(selfFyc.compareTo(MaxValue) >= 0);

    }


}
