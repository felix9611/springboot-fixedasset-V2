package com.company.project.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * UserInfoRespVO
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Data
public class UserInfoRespVO {
    @ApiModelProperty(value = "用戶id")
    private String id;
    @ApiModelProperty(value = "賬號")
    private String username;
    @ApiModelProperty(value = "手機號")
    private String phone;
    @ApiModelProperty(value = "暱稱")
    private String nickName;
    @ApiModelProperty(value = "真實姓名")
    private String realName;
    @ApiModelProperty(value = "所屬機構id")
    private String deptId;
    @ApiModelProperty(value = "所屬機構名稱")
    private String deptName;

}
