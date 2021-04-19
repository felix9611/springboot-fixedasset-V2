package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.company.project.entity.BaseEntity;


import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Component;
import lombok.Data;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-04-19 16:23:42
 */
@Component
@Data
@TableName("action_record")
public class ActionRecordEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("id")
	private int id;

	/**
	 * 活動名稱
	 */
	@TableField("action_name")
	private String actionName;

	/**
	 * 活動類型
	 */
	@TableField("action_method")
	private String actionMethod;

	/**
	 * 活動單元
	 */
	@TableField("action_from")
	private String actionFrom;

	/**
	 * 執行數據
	 */
	@TableField("action_data")
	private String actionData;

	/**
	 * 成功執行?
	 */
	@TableField("action_success")
	private String actionSuccess;

	/**
	 * 活動時間
	 */
	@TableField("create_time")
	private Date createTime;


}
