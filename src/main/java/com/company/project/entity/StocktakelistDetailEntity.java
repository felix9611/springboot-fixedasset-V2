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
 * @date 2020-11-25 21:02:34
 */
@Component
@Data
@TableName("stocktakelist_detail")
public class StocktakelistDetailEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private String id;

	/**
	 * 
	 */
	@TableField("list_id")
	private String listId;

	/**
	 * 
	 */
	@TableField("asset_code")
	private String assetCode;

	/**
	 * 
	 */
	@TableField("asset_place")
	private String assetPlace;

	/**
	 * 
	 */
	@TableField("status")
	private String status;

	/**
	 * 
	 */
	@TableField("status_remark")
	private String statusRemark;


	public void setListId(String listId) {
		this.listId = listId;
	}

	public String getListId() {
		return listId;
	}
}
