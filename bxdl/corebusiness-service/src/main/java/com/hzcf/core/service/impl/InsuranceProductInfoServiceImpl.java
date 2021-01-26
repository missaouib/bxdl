package com.hzcf.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.hzcf.core.mapper.*;
import com.hzcf.core.service.InsuranceProductInfoService;
import com.hzcf.core.util.AESUtil;
import com.hzcf.core.util.HttpRequestUtil;
import com.hzcf.core.util.PageModel;
import com.hzcf.pojo.insurance.InsuranceDept;
import com.hzcf.pojo.product.InsuranceProductDetailWithBLOBs;
import com.hzcf.pojo.product.InsuranceProductInfo;
import com.hzcf.pojo.product.ProductsCommissionRatio;
import com.hzcf.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class InsuranceProductInfoServiceImpl implements InsuranceProductInfoService {
	
    @Autowired
    private InsuranceProductInfoMapper insuranceProductInfoMapper;
    @Autowired
    private InsuranceProductDetailMapper insuranceProductDetailMapper;
    @Autowired
    private ProductsCommissionRatioMapper productsCommissionRatioMapper;
    @Autowired
    private InsuranceTypeMappingMapper insuranceTypeMappingMapper;
    @Autowired
    private InsuranceDeptMapper insuranceDeptMapper;
    
  
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public PageModel getInsuranceProductList(Map<String, Object> params) {
        PageModel model = new PageModel();
        model.setPageNo(Integer.valueOf(String.valueOf(params.get("pageNo"))));
        model.setPageSize(Integer.valueOf(String.valueOf( params.get("pageSize"))));
        params.put("startIndex", model.getStartIndex());
        params.put("endIndex", model.getEndIndex());
        params.put("pageSize", model.getPageSize());
        List<Map<String,Object>> list = insuranceProductInfoMapper.getInsuranceProductList(params);
        long size = insuranceProductInfoMapper.getInsuranceProductSize(params);
        model.setList(list);
        model.setTotalRecords(size);
        return model;
    }

 /*   @Override
    public void addInsuranceProduct(InsuranceProductInfo insuranceProductInfo) {
    	insuranceProductInfoMapper.insertSelective(insuranceProductInfo);
    }*/

  /*  @Override
    public void updateInsuranceProduct(InsuranceProductInfo insuranceProductInfo) {
    	insuranceProductInfoMapper.updateByPrimaryKeySelective(insuranceProductInfo);
    }*/

    @Override
	public void updateInsuranceProductStatus(Map<String, Object> params) {
    	insuranceProductInfoMapper.updateInsuranceProductStatus(params);
	}

	@Override
	public InsuranceProductInfo selectInsuranceProductDetail(Map<String, Object> params) {
		
		return insuranceProductInfoMapper.selectInsuranceProductDetail(params);
	}

	@Override
	public void addInsuranceProductDetail(InsuranceProductDetailWithBLOBs insuranceProductDetailWithBLOBs) {
		
		insuranceProductDetailMapper.insertSelective(insuranceProductDetailWithBLOBs);
	}

	@Override
	public void addRenewPeriod(ProductsCommissionRatio productsCommissionRatio) {
		
		productsCommissionRatioMapper.insertSelective(productsCommissionRatio);
	}

	@Override
	public InsuranceProductInfo selectInsuranceProductInfo(Map<String, Object> params) {

		return insuranceProductInfoMapper.selectInsuranceProductInfoDetail(params);
	}

	@Override
	public InsuranceProductDetailWithBLOBs selectInsuranceProductDetailWithBLOBs(Map<String, Object> params) {

		return insuranceProductDetailMapper.selectProductDetailByProductId(params);
	}

	@Override
	public List<ProductsCommissionRatio> getProductsCommissionRatioList(Map<String, Object> params) {
		return productsCommissionRatioMapper.selectProductsCommissionRatioListByProductId(params);
	}

	@Override
	public void updateInsuranceProductDetailWithBLOBs(InsuranceProductDetailWithBLOBs productDetailWithBLOBs) {
		insuranceProductDetailMapper.updateByPrimaryKeySelective(productDetailWithBLOBs);
	}

	@Override
	public void deleteInsuranceProductsCommissionRatio(Map<String, Object> params) {
		productsCommissionRatioMapper.deleteByProduct(params);
	}
	
	/**
	 * 添加推送产品数据
	 */
	// @Value("${insurance.http.url}")
	  //  private String insUrl;
	    @Override
	    public void addInsuranceProduct(InsuranceProductInfo insuranceProduct) {
	        Map<String,Object> map = new HashMap<String,Object>();
	        Map<String, Object>maps = new HashMap<String,Object>();
	        try{
	            //SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd");
	            insuranceProduct.setCreatedTime(new Date());
                insuranceProductInfoMapper.insertSelectiveReturnKey(insuranceProduct);
	            
//	            InsuranceProductInfo insuranceProductInfo=insuranceProductInfoMapper.selectByPrimaryKeyFormat(insuranceProduct.getProductId());
//	            maps = getInsuranceProductAttribute(insuranceProductInfo);
//	            map.put("insuranceProductInfo",maps);
//	            System.out.println("woshishi+"+maps.get("productId"));
//
//	            String js = JSON.toJSONString(map);
//	            String aesu = AESUtil.enCrypt(js, "1234567890123456");
//	            logger.info("新增：同步数据到保险产品系统开始"+map.toString());
//	            String s = HttpRequestUtil.sendPostForSendMsg(insUrl, aesu);
//	            addErrorData(maps);
	         }catch (Exception e){
	           logger.error("新增产品异常========",e);
	            // addErrorData(maps);
	         }
	    }
	    
	    /**
	     * 修改时推送产品数据
	     * @param insuranceDept
	     */
	    @Override
	    public void updateInsuranceProduct(InsuranceProductInfo insuranceProduct) {
	        Map<String,Object> map = new HashMap<String,Object>();
	        Map<String, Object>maps = new HashMap<String,Object>();
	        try{
	        	List<ProductsCommissionRatio> insuranceTypeMapping=insuranceTypeMappingMapper.selectByAll();
	        	
	            SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd");
	           // insuranceProduct.setCreatedTime(fmat.parse(fmat.format(new Date())));
                insuranceProductInfoMapper.updateByPrimaryKeySelective(insuranceProduct);
	            
	            /*InsuranceProductInfo insuranceProductInfo=insuranceProductInfoMapper.selectByPrimaryKeyFormat(insuranceProduct.getProductId());
	            maps = getInsuranceProductAttribute(insuranceProductInfo);
	            
	            map.put("insuranceProductInfo",maps);
	            map.put("insuranceTypeMapping", insuranceTypeMapping);
	            String js = JSON.toJSONString(map);
	            String aesu = AESUtil.enCrypt(js, "1234567890123456");
	            logger.info("修改:同步数据到保险系统开始"+map.toString());
	            String s = HttpRequestUtil.sendPostForSendMsg(insUrl, aesu);
	            addErrorData(maps);*/
	        }catch (Exception e){
	           logger.error("修改产品异常==========",e);
	        }
	    }

		public Map<String,Object> getInsuranceProductAttribute(InsuranceProductInfo insuranceProductInfo){
	        Map<String,Object> map = new HashMap<>();//封装请求参数
	       String renewalPeriodType = insuranceProductInfo.getRenewalPeriodType();
	        map.put("renewalPeriodType", renewalPeriodType);
	        String insurancePeriodType = insuranceProductInfo.getInsurancePeriodType();
	        map.put("insurancePeriodType",insurancePeriodType );
	        String specialAuthorization = insuranceProductInfo.getSpecialAuthorization();
	        map.put("specialAuthorization",specialAuthorization );
	        Date invalidDate = insuranceProductInfo.getInvalidDate();
	        map.put("invalidDate",  DateUtil.getTimeNormalString(invalidDate));
	        String updatedBy = insuranceProductInfo.getUpdatedBy();
	        map.put("updatedBy",updatedBy );
	        
	        String productStatus = insuranceProductInfo.getProductStatus();
	        map.put("productStatus",productStatus );
	        String saleType = insuranceProductInfo.getSaleType();
	        map.put("saleType",saleType );
	        
	        Date updatedTime = insuranceProductInfo.getUpdatedTime();
	        if (null!=updatedTime&&!"".equals(updatedTime)) {
				
	        	map.put("updatedTime",DateUtil.getTimeNormalString(updatedTime));
			}
	       
	        String cardProType = insuranceProductInfo.getCardProType();
	        map.put("cardProType",cardProType );
	        
	        Long insuranceCompanyId = insuranceProductInfo.getInsuranceCompanyId();
	        map.put("insuranceCompanyId",insuranceCompanyId );
	        
	        String productEnName = insuranceProductInfo.getProductEnName();
	        map.put("productEnName",productEnName );
	        
	        String circInsuranceType = insuranceProductInfo.getCircInsuranceType();
	        map.put("circInsuranceType",circInsuranceType );
	        
	        String productCode = insuranceProductInfo.getProductCode();
	        map.put("productCode",productCode );
	        
	        String productNameLess = insuranceProductInfo.getProductNameLess();
	        map.put("productNameLess",productNameLess );
	        
	        Date createdTime = insuranceProductInfo.getCreatedTime();
	        map.put("createdTime",DateUtil.getTimeNormalString(createdTime));
	        
	        String needReceipt = insuranceProductInfo.getNeedReceipt();
	        map.put("needReceipt",needReceipt );
	        
	        String companyInsuranceCode = insuranceProductInfo.getCompanyInsuranceCode();
	        map.put("companyInsuranceCode",companyInsuranceCode );
	        
	        Long productId = insuranceProductInfo.getProductId();
	        map.put("productId",productId );
	        
	        String payMode = insuranceProductInfo.getPayMode();
	        map.put("payMode",payMode );
	        
	        String circRecordCode = insuranceProductInfo.getCircRecordCode();
	        map.put("circRecordCode",circRecordCode );
	        
	        BigDecimal valueCommissionCoefficient = null;
	        map.put("valueCommissionCoefficient",valueCommissionCoefficient );
	        BigDecimal standardCommissionCoefficient = null;
	        map.put("standardCommissionCoefficient",standardCommissionCoefficient );
	        
	        String createdBy = insuranceProductInfo.getCreatedBy();
	        map.put("createdBy",createdBy );
	        
	        String mainOrAdditional = insuranceProductInfo.getMainOrAdditional();
	        map.put("mainOrAdditional",mainOrAdditional );
	        
	        String longShortRiskType = insuranceProductInfo.getLongShortRiskType();
	        map.put("longShortRiskType",longShortRiskType );
	        
	        Date takeEffectDate = insuranceProductInfo.getTakeEffectDate();
	        map.put("takeEffectDate", DateUtil.getTimeNormalString(takeEffectDate) );
	        
	        String companyInsuranceType = insuranceProductInfo.getCompanyInsuranceType();
	        map.put("companyInsuranceType",companyInsuranceType );
	        
	        String insuranceType = insuranceProductInfo.getInsuranceType();
	        map.put("insuranceType",insuranceType );
	        
	        String groupOrPersonal = insuranceProductInfo.getInsuranceType();
	        map.put("groupOrPersonal",groupOrPersonal );
	        
	        String productName = insuranceProductInfo.getProductName();
	        map.put("productName",productName );
	        
	        map.put("directorAllowanceStandard", "");
	        return map;
	    }
	    private void addErrorData(Map<String,Object> maps){
	        final String sys="bx";
	      insuranceProductInfoMapper.addErrorData(maps.get("productId").toString(),sys);
	    }

		@Override
		public void updateRenewPeriod(ProductsCommissionRatio productsCommissionRatio) {
			productsCommissionRatioMapper.updateByPrimaryKeySelective(productsCommissionRatio);
		}

		@Override
		public void updateRenewPeriodStatus(Map<String, Object> params) {

			productsCommissionRatioMapper.updateRenewPeriodStatus(params);
		}

		@Override
		public int checkRenewPeriodSize(Map<String, Object> params) {
			
			return productsCommissionRatioMapper.checkRenewPeriodSize(params);
		}

		@Override
		public InsuranceDept selectInsuranceCompanyOrg(Map<String, Object> params) {
			return insuranceDeptMapper.selectInsuranceBasicDept(params);
		}

		@Override
		public List<InsuranceProductInfo> findInsurProducts(Map<String, Object> params) {
			// TODO Auto-generated method stub
			return insuranceProductInfoMapper.findInsurProducts(params);
		}

	@Override
	public List<ProductsCommissionRatio> findAllInsurProductSub(Map<String, Object> paras) {
		return productsCommissionRatioMapper.findAllInsurProductSub(paras);
	}

    @Override
    public InsuranceProductInfo selectInsuranceBasicProductSon(Map<String, Object> paras) {
        return insuranceProductInfoMapper.selectInsuranceBasicProductSon(paras);
    }

	@Override
	public boolean findByInsuranceCompanyIdAndProductName(Long insuranceCompanyId, String productName) {
			List<InsuranceProductInfo> list = insuranceProductInfoMapper.selectByInsuranceCompanyIdAndProductName(insuranceCompanyId,productName);
	    	return !list.isEmpty();
	}

	@Override
	public boolean findByProductCode(String productCode){
		List<InsuranceProductInfo> insuranceProductInfos = insuranceProductInfoMapper.selectByProductCode(productCode);
		return !insuranceProductInfos.isEmpty();
	}
   @Override
	public boolean findRatioByInsuranceCompanyIdAndProductsName(Map<String,Object > params){
	    List<ProductsCommissionRatio> productsCommissionRatios = productsCommissionRatioMapper.selectRatioByInsuranceCompanyIdAndProductsName(params);
	    return !productsCommissionRatios.isEmpty();
	}
     @Override
	public boolean findRatioProductsCode(String productsCode){
		List<ProductsCommissionRatio> productsCommissionRatios = productsCommissionRatioMapper.selectRatioProductsCode(productsCode);
		return !productsCommissionRatios.isEmpty();
	}
    @Override
	public InsuranceProductInfo findByPrimaryKey(Long productId){
		InsuranceProductInfo insuranceProductInfo = insuranceProductInfoMapper.selectByPrimaryKey(productId);
		return insuranceProductInfo;
	}

	@Override
	public ProductsCommissionRatio findRatioProductKey(Long productsRatioId) {
		return  productsCommissionRatioMapper.selectByPrimaryKey(productsRatioId);
	}
}

