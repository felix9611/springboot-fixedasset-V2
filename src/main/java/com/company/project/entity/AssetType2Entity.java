package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;


import java.io.Serializable;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-15 17:48:59
 */
@Component
@Data
@TableName("asset_type2")
public class AssetType2Entity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private String id;

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
	private String active;

	public String getId() {
		return id;
	}


	
}
