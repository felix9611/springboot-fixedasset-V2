package com.company.project.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * LoginRespVO
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Data
public class LoginRespVO {
    @ApiModelProperty(value = "token")
    private String accessToken;
    @ApiModelProperty(value = "用戶名")
    private String username;
    @ApiModelProperty(value = "用戶id")
    private String id;
    @ApiModelProperty(value = "電話")
    private String phone;
    @ApiModelProperty(value = "用戶所擁有的菜單權限(前後端分離返回給前端控制菜單和按鈕的顯示和隱藏)")
    private List<PermissionRespNode> list;
}
