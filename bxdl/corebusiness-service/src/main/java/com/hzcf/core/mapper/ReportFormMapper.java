package com.hzcf.core.mapper;

import com.hzcf.core.util.PageModel;

import java.util.List;
import java.util.Map;

public interface ReportFormMapper {

    List<Map<String,Object>> getSettleRequireIndexData(Map<String, Object> params);

    long getSettleRequireIndexDataSize(Map<String, Object> params);
}