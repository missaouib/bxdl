package com.hzcf.core.calculation;

import com.hzcf.core.constants.Constants;
import com.hzcf.pojo.cal.CalNurtureDirector;
import java.math.BigDecimal;
import java.util.List;

/**
 * 处育成奖金计算
 * seniorityType  区分直接还是间接
 * yearType 区分第一年还是第二年以后
 */

public class CalNurtureDirectorFormula {
    public static BigDecimal nurtureDirectorFormula(BigDecimal cycfyc,String seniorityType, String yearType, List<CalNurtureDirector> calNurtureDirectorList) {
        BigDecimal cycjt = BigDecimal.valueOf(0.00);
        for (CalNurtureDirector calNurtureDirector : calNurtureDirectorList) {
            if (calNurtureDirector.getGenerativeAlgebra().equals(seniorityType)) {
                if (Constants.YC_YEAR_1.equals(yearType)) {
                    cycjt = cycfyc.multiply(calNurtureDirector.getFastYear());
                } else if (Constants.YC_YEAR_2.equals(yearType)) {
                    cycjt = cycfyc.multiply(calNurtureDirector.getFollowingYearAndBeyond());

                }
                break;
            }

        }
        return cycjt;
    }

}
