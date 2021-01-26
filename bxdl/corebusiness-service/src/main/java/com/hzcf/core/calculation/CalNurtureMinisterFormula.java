package com.hzcf.core.calculation;

import com.hzcf.core.constants.Constants;
import com.hzcf.pojo.cal.CalNurtureDirector;
import com.hzcf.pojo.cal.CalNurtureMinister;

import java.math.BigDecimal;
import java.util.List;

/**
 * 部育成津贴计算
 * Created by qin lina on 2020/10/15.
 */
public class CalNurtureMinisterFormula {
    public static BigDecimal nurtureMinisterFormula(BigDecimal bycFyc, String seniorityType, String yearType, List<CalNurtureMinister> calNurtureMinisterList){
        BigDecimal bycjt = BigDecimal.valueOf(0.00);
        for (CalNurtureMinister calNurtureMinister:calNurtureMinisterList){
            if (calNurtureMinister.getGenerativeAlgebra().equals(seniorityType)){
                if (Constants.YC_YEAR_1.equals(yearType)){
                    bycjt = bycFyc.multiply(calNurtureMinister.getFastYear());
                }else if (Constants.YC_YEAR_2.equals(yearType)){
                    bycjt = bycFyc.multiply(calNurtureMinister.getFollowingYearAndBeyond());
                }
                break;
            }

        }
        return bycjt;

    }
}
