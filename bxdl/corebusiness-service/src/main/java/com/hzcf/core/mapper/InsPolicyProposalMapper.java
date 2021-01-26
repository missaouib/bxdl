package com.hzcf.core.mapper;

import java.util.List;
import java.util.Map;

public interface InsPolicyProposalMapper {
    void addInsPolicyProposal(Map<String, Object> ins_policy_proposal);

    Map<String,Object> selectInsPolicyProposalEntityByKey(Map<String, Object> params);

    void updateInsPolicyProposal(Map<String, Object> ins_policy_proposal);



}
