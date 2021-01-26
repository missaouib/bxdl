package com.hzcf.pojo;

import java.util.List;
import java.util.Map;

public class Params {
    private Map<String,Object> ins_policy_insured_person;

    private Map<String,Object> ins_policy_holder;

    private Map<String,Object> ins_policy_proposal;

    private List<Map<String,Object>> ins_information;

    private Map<String,Object> ins_policy_insured_examine;

    private List<Map<String,Object>> ins_policy_profits_person_list;

    private List<Map<String,Object>> ins_policy_att_list;
    private List<String> list;
    private Map<String,Object> map;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Map<String, Object> getIns_policy_insured_person() {
        return ins_policy_insured_person;
    }

    public void setIns_policy_insured_person(Map<String, Object> ins_policy_insured_person) {
        this.ins_policy_insured_person = ins_policy_insured_person;
    }

    public Map<String, Object> getIns_policy_holder() {
        return ins_policy_holder;
    }

    public void setIns_policy_holder(Map<String, Object> ins_policy_holder) {
        this.ins_policy_holder = ins_policy_holder;
    }

    public Map<String, Object> getIns_policy_proposal() {
        return ins_policy_proposal;
    }

    public void setIns_policy_proposal(Map<String, Object> ins_policy_proposal) {
        this.ins_policy_proposal = ins_policy_proposal;
    }

    public List<Map<String, Object>> getIns_information() {
        return ins_information;
    }

    public void setIns_information(List<Map<String, Object>> ins_information) {
        this.ins_information = ins_information;
    }

    public List<Map<String, Object>> getIns_policy_profits_person_list() {
        return ins_policy_profits_person_list;
    }

    public void setIns_policy_profits_person_list(List<Map<String, Object>> ins_policy_profits_person_list) {
        this.ins_policy_profits_person_list = ins_policy_profits_person_list;
    }

    public List<Map<String, Object>> getIns_policy_att_list() {
        return ins_policy_att_list;
    }

    public void setIns_policy_att_list(List<Map<String, Object>> ins_policy_att_list) {
        this.ins_policy_att_list = ins_policy_att_list;
    }

    public Map<String, Object> getIns_policy_insured_examine() {
        return ins_policy_insured_examine;
    }

    public void setIns_policy_insured_examine(Map<String, Object> ins_policy_insured_examine) {
        this.ins_policy_insured_examine = ins_policy_insured_examine;
    }
}
