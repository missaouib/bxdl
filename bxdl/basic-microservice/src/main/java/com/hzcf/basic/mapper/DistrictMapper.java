package com.hzcf.basic.mapper;

import com.hzcf.pojo.basic.District;
import com.hzcf.pojo.basic.DistrictExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DistrictMapper {
    long countByExample(DistrictExample example);

    int deleteByExample(DistrictExample example);

    int deleteByPrimaryKey(Long districtid);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Long districtid);

    int updateByExampleSelective(@Param("record") District record, @Param("example") DistrictExample example);

    int updateByExample(@Param("record") District record, @Param("example") DistrictExample example);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    /**
     * 查询所有的地区
     * @return
     */
	List<District> findAllDistrict();

	List<District> findDistrictByParentId(String parentId);

	List<District> findDistrictByDistrict(String district);

	List<District> findDistrictByDistrictAndParentId(@Param("district")String district,@Param("parentId")String parentId);



}