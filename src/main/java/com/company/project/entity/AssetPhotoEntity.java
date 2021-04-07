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
 * @date 2021-04-02 00:23:33
 */
@Component
@Data
@TableName("asset_photo")
public class AssetPhotoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private String id;

	/**
	 * 資產ID
	 */
	@TableField("asset_id")
	private String assetId;

	/**
	 * 資產編號
	 */
	@TableField("asset_code")
	private String assetCode;

	/**
	 * 圖片URL
	 */
	@TableField("url")
	private String url;

	/**
	 * 上傳時間
	 */
	@TableField("create_time")
	private Date createTime;


}
