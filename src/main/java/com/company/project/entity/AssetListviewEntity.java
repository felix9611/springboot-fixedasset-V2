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
 * @date 2021-01-17 17:53:32
 */
@Component
@Data
@TableName("asset_listview")
public class AssetListviewEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private String id;

	/**
	 * 資產編號
	 */
	@TableField("asset_code")
	private String assetCode;

	/**
	 * 資產名稱
	 */
	@TableField("asset_name")
	private String assetName;

	/**
	 * 資產種類
	 */
	@TableField("asset_type")
	private String assetType;

	/**
	 * 單位
	 */
	@TableField("unit")
	private String unit;

	/**
	 * 購買日期
	 */
	@TableField("buydate")
	private Date buydate;

	/**
	 * 內容
	 */
	@TableField("description")
	private String description;

	/**
	 * 購買成本
	 */
	@TableField("cost")
	private String cost;

	/**
	 * Serial Number
	 */
	@TableField("serialnum")
	private String serialnum;

	/**
	 * 發票編號
	 */
	@TableField("invoiceno")
	private String invoiceno;

	/**
	 * 發票日期
	 */
	@TableField("invoicenodate")
	private Date invoicenodate;

	/**
	 * 地點
	 */
	@TableField("place")
	private String place;

	/**
	 * 所屬部門
	 */
	@TableField("dept_id")
	private String deptId;

	/**
	 * 負責職員
	 */
	@TableField("respon_staff")
	private String responStaff;

	/**
	 * 備註
	 */
	@TableField("remark")
	private String remark;

	/**
	 * 
	 */
	@TableField("active")
	private String active;


}
