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
 * @date 2020-11-15 18:53:25
 */
@Component
@Data
@TableName("place2")
public class Place2Entity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private String id;

	/**
	 * 
	 */
	@TableField("place_code")
	private String placeCode;

	/**
	 * 
	 */
	@TableField("place_name")
	private String placeName;

	/**
	 * 
	 */
	@TableField("active")
	private String active;


}
