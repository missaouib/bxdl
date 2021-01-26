package com.hzcf.util;

import com.alibaba.fastjson.JSON;
import com.hzcf.pojo.cal.CalParamVersion;

import java.util.Arrays;

public class CalVersionUtil {

    public static final Integer INTI_MAJORVERSION = 1; //初始主版本号
    public static final Integer INTI_MINORVERSION = 0; //初始次级版本
    public static final Integer INIT_CRITICALVERSION = 0; //临界版本


    public static void buildVersion(CalParamVersion calParamVersion){
        Integer major = calParamVersion.getMajorVersion() == null ? INTI_MAJORVERSION :
                calParamVersion.getMajorVersion();
        Integer minor = calParamVersion.getMinorVersion() == null ? INTI_MINORVERSION :
                calParamVersion.getMinorVersion();
        Integer critical = calParamVersion.getCriticalVersion() == null ? INIT_CRITICALVERSION :
                calParamVersion.getCriticalVersion();

        critical += 1;

        if(critical == 100){
            critical = 0;
            minor +=1;
        }
        if(minor == 100){
            minor  = 0;
            major += 1;
        }
        calParamVersion.setMajorVersion(major);
        calParamVersion.setMinorVersion(minor);
        calParamVersion.setCriticalVersion(critical);
    }


    public static void main(String[] args) {
        CalParamVersion calParamVersion =new CalParamVersion();
        calParamVersion.setMajorVersion(1);
        calParamVersion.setMinorVersion(2);
        calParamVersion.setCriticalVersion(98);
        buildVersion(calParamVersion);
        System.out.println("args = " + JSON.toJSONString(calParamVersion));
    }
}
