package com.company.project.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * HomeRespVO
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Data
public class HomeRespVO {
    @ApiModelProperty(value = "用戶信息")
    private UserInfoRespVO userInfo;
    @ApiModelProperty(value = "目錄菜單")
    private List<PermissionRespNode> menus;

}