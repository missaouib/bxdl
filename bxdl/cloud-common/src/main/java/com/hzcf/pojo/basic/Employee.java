package com.hzcf.pojo.basic;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Employee {
	private Integer employeeId;

    private String cardNo;

    private String name;

    private String sex;

    private String employeeNo;

    private String email;

    private String password;

    private String salt;

    private String mobile;

    private String telephone;

    private Integer deptId;

    private String deptNo;

    private Integer deptIdOne;

    private Integer deptIdTwo;

    private Integer deptIdThree;

    private Integer deptIdFour;

    private Integer deptIdFive;

    private Integer deptIdSix;

    private String activatedState;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date entryTime;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Integer operator;

    
    //是否重置密码
    private String isResetPwd;
    private String departmentname;//部门名称

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getActivatedState() {
        return activatedState;
    }

    public void setActivatedState(String activatedState) {
        this.activatedState = activatedState == null ? null : activatedState.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

	public String getIsResetPwd() {
		return isResetPwd;
	}

	public void setIsResetPwd(String isResetPwd) {
		this.isResetPwd = isResetPwd;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public Integer getDeptIdOne() {
		return deptIdOne;
	}

	public void setDeptIdOne(Integer deptIdOne) {
		this.deptIdOne = deptIdOne;
	}

	public Integer getDeptIdTwo() {
		return deptIdTwo;
	}

	public void setDeptIdTwo(Integer deptIdTwo) {
		this.deptIdTwo = deptIdTwo;
	}

	public Integer getDeptIdThree() {
		return deptIdThree;
	}

	public void setDeptIdThree(Integer deptIdThree) {
		this.deptIdThree = deptIdThree;
	}

	public Integer getDeptIdFour() {
		return deptIdFour;
	}

	public void setDeptIdFour(Integer deptIdFour) {
		this.deptIdFour = deptIdFour;
	}

	public Integer getDeptIdFive() {
		return deptIdFive;
	}

	public void setDeptIdFive(Integer deptIdFive) {
		this.deptIdFive = deptIdFive;
	}

	public Integer getDeptIdSix() {
		return deptIdSix;
	}

	public void setDeptIdSix(Integer deptIdSix) {
		this.deptIdSix = deptIdSix;
	}
	
	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
    
}