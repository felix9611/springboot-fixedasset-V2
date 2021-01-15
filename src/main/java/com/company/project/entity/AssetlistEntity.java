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
 * @date 2021-01-10 18:28:38
 */
@Component
@Data
@TableName("assetlist")
public class AssetlistEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private int id;

	/**
	 * 
	 */
	//@TableField("asset_code")
	//private String assetCode;

	/**
	 * 
	 */
	@TableField("asset_name")
	private String assetName;

	/**
	 * 
	 */
	@TableField("asset_type")
	private String assetType;

	/**
	 * 
	 */
	@TableField("unit")
	private String unit;

	/**
	 * 
	 */
	@TableField("buydate")
	private Date buydate;

	/**
	 * 
	 */
	@TableField("description")
	private String description;

	/**
	 * 
	 */
	@TableField("cost")
	private String cost;

	/**
	 * 
	 */
	@TableField("serialnum")
	private String serialnum;

	/**
	 * 
	 */
	@TableField("invoiceno")
	private String invoiceno;

	/**
	 * 
	 */
	@TableField("invoicenodate")
	private Date invoicenodate;

	/**
	 * 
	 */
	@TableField("place")
	private String place;

	/**
	 * 
	 */
	@TableField("dept_id")
	private String deptId;

	/**
	 * 
	 */
	@TableField("respon_staff")
	private String responStaff;

	/**
	 * 
	 */
	@TableField("remark")
	private String remark;

	/**
	 * 
	 */
	@TableField("active")
	private String active;


}
