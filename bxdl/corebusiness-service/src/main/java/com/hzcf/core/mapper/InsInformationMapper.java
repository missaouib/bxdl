package com.hzcf.core.mapper;

import com.hzcf.pojo.insurancePolicy.InsInformationEntity;

import java.util.List;
import java.util.Map;

public interface InsInformationMapper {


    void addInsInformation(List<Map<String, Object>> ins_information);

    List<Map<String, Object>> selectInsInformationEntityByKey(Map<String, Object> params);

    void updateInsInformation(List<Map<String, Object>>  ins_information);

    void deleteInformation(Map<String, Object> ins_policy_insured_person);

}
