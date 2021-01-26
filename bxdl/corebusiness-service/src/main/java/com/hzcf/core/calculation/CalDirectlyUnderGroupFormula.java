package com.hzcf.core.calculation;

import com.hzcf.Enum.CalParamRuleEnum;
import com.hzcf.core.constants.Constants;
import com.hzcf.pojo.cal.CalDirectlyUnderGroup;

import java.math.BigDecimal;
import java.util.List;

/**
 * 直辖组管理津贴
 * Created by qin lina on 2020/10/14.
 */
public class CalDirectlyUnderGroupFormula {

    /**
     *
     * @param zxzFyc
     * @param manpower 组活动人力数
     * @param calDirectlyUnderGroupList
     * @return
     */
    public static BigDecimal directlyUnderGroupFormula(BigDecimal zxcFyc,int manpower, List<CalDirectlyUnderGroup> calDirectlyUnderGroupList){
        BigDecimal zxcgljt = BigDecimal.valueOf(0.00);
        for(CalDirectlyUnderGroup calDirectlyUnderGroup: calDirectlyUnderGroupList){
            boolean formula_min = false; //FYC是否满足上限
            boolean formula_max = false; //FYC是否满足下限
            boolean manpower_flag = false; //组活动人力是否满足
            boolean fyc_flag = false; //fyc是否满足条件
            //FYC是否满足参数下限
            if ("0".equals(calDirectlyUnderGroup.getMinSign())){
                formula_min = zxcFyc.compareTo(calDirectlyUnderGroup.getMinValue()) > 0;
            }else if ("1".equals(calDirectlyUnderGroup.getMinSign())){
                formula_min = zxcFyc.compareTo(calDirectlyUnderGroup.getMinValue()) >=0;
            }
            //Fyc是否满足参数上限
            if ("0".equals(calDirectlyUnderGroup.getMaxSign())){
                formula_max = zxcFyc.compareTo(calDirectlyUnderGroup.getMaxValue()) <0;
            }else if ("1".equals(calDirectlyUnderGroup.getMaxSign())){
                formula_max = zxcFyc.compareTo(calDirectlyUnderGroup.getMaxValue()) <=0 ;
            }
            fyc_flag = formula_max && formula_min;
            //如果规则和组互动人力有关 组互动人力是否满足
            if (CalParamRuleEnum.RULE_D_GROUP_2.getCode().equals(calDirectlyUnderGroup.getRuleType())){
                //0；小于；1：大于；2小于等于；3：大于等于
                if ("0".equals(calDirectlyUnderGroup.getActiveSalerSign())){
                     manpower_flag = manpower < calDirectlyUnderGroup.getActiveSalerNum();
                }else if ("1".equals(calDirectlyUnderGroup.getActiveSalerSign())){
                    manpower_flag = manpower > calDirectlyUnderGroup.getActiveSalerNum();
                }else if ("2".equals(calDirectlyUnderGroup.getActiveSalerSign())){
                    manpower_flag = manpower <= calDirectlyUnderGroup.getActiveSalerNum();
                } else if ("3".equals(calDirectlyUnderGroup.getActiveSalerSign())){
                    manpower_flag = manpower >= calDirectlyUnderGroup.getActiveSalerNum();
                }
                //如果 或的关系
               if (Constants.RELATION_TYPE_0.equals(calDirectlyUnderGroup.getRelationType())){
                    if (manpower_flag || fyc_flag){
                        zxcgljt =zxcFyc.multiply(calDirectlyUnderGroup.getAllowanceRatio());
                        break;
                    }
               }else {
                   if (manpower_flag && fyc_flag){
                       zxcgljt = zxcFyc.multiply(calDirectlyUnderGroup.getAllowanceRatio());
                       break;
                   }

               }


            }else {
                //如果不涉及组活动人力
                if (formula_min && formula_max){
                    zxcgljt = zxcFyc.multiply(calDirectlyUnderGroup.getAllowanceRatio());
                    break;
                }
            }

        }
        return zxcgljt;
    }


}
