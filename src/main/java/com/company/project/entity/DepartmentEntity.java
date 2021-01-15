package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-20 10:15:04
 */
@Component
@Data
@TableName("department")
public class DepartmentEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private String id;

	/**
	 * 
	 */
	@TableField("dept_code")
	private String deptCode;

	/**
	 * 
	 */
	@TableField("dept_name")
	private String deptName;

	/**
	 * 
	 */
	@TableField("dept_remark")
	private String deptRemark;

	/**
	 * 
	 */
	@TableField("active")
	private String active;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptRemark() {
		return deptRemark;
	}

	public void setDeptRemark(String deptRemark) {
		this.deptRemark = deptRemark;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
}
