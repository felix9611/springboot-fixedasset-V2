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
 * @date 2021-03-14 17:03:14
 */

@Component
@Data
@TableName("asset_place")
public class AssetPlaceEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("asset_id")
	private String assetId;

	/**
	 * 
	 */
	@TableField("place_id")
	private String placeId;


}
