package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-25 20:33:06
 */
@Component
@Data
@TableName("stocktakelist")
public class StocktakelistEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private String id;

	/**
	 * 
	 */
	@TableField("activity_name")
	private String activityName;

	/**
	 * 
	 */
	@TableField("start_time")
	private String startTime;

	/**
	 * 
	 */
	@TableField("create_time")
	private String createTime;

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


}
