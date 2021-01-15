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
 * @date 2020-11-21 10:28:41
 */
@Data
@TableName("vendor")
public class VendorEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private Integer id;

	/**
	 * 
	 */
	@TableField("vendorcode")
	private String vendorcode;

	/**
	 * 
	 */
	@TableField("vendorname")
	private String vendorname;

	/**
	 * 
	 */
	@TableField("vendortype")
	private String vendortype;

	/**
	 * 
	 */
	@TableField("vendoraddress")
	private String vendoraddress;

	/**
	 * 
	 */
	@TableField("vendorphone")
	private String vendorphone;

	/**
	 * 
	 */
	@TableField("vendorfax")
	private String vendorfax;

	/**
	 * 
	 */
	@TableField("vendorcontact")
	private String vendorcontact;

	/**
	 * 
	 */
	@TableField("active")
	private Integer active;


}
