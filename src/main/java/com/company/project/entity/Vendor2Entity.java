package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-20 09:32:24
 */
@Data
@TableName("vendor2")
public class Vendor2Entity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private String id;

	/**
	 * 
	 */
	@TableField("vendor_code")
	private String vendorCode;

	/**
	 * 
	 */
	@TableField("vendor_name")
	private String vendorName;

	/**
	 * 
	 */
	@TableField("vandor_type")
	private String vandorType;

	/**
	 * 
	 */
	@TableField("vendor_address")
	private String vendorAddress;

	/**
	 * 
	 */
	@TableField("vendor_phone")
	private String vendorPhone;

	/**
	 * 
	 */
	@TableField("vendor_fax")
	private String vendorFax;

	/**
	 * 
	 */
	@TableField("vendor_contacter")
	private String vendorContacter;

	/**
	 * 
	 */
	@TableField("active")
	private String active;


}
