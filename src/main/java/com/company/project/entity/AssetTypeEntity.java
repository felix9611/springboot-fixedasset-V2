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
 * @date 2020-11-21 10:28:30
 */
@Data
@TableName("asset_type")
public class AssetTypeEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private Integer id;

	/**
	 * 
	 */
	@TableField("type_code")
	private String typeCode;

	/**
	 * 
	 */
	@TableField("type_name")
	private String typeName;

	/**
	 * 
	 */
	@TableField("active")
	private Integer active;


}
