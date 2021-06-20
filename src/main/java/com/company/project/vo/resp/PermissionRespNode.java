package com.company.project.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * PermissionRespNode
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Data
public class PermissionRespNode {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "菜單權限名稱")
    private String title;

    @ApiModelProperty(value = "菜單權限標識，shiro 適配restful")
    private String perms;

    @ApiModelProperty(value = "接口地址")
    private String url;

    @ApiModelProperty(value = "icon")
    private String icon;

    private String target;

    @ApiModelProperty(value = "父級id")
    private String pid;

    @ApiModelProperty(value = "父級名稱")
    private String pidName;

    @ApiModelProperty(value = "菜單權限類型(1:目錄;2:菜單;3:按鈕)")
    private Integer type;

    @ApiModelProperty(value = "排序碼")
    private Integer orderNum;

    @ApiModelProperty(value = "是否展開 默認不展開(false)")
    private boolean spread = true;

    @ApiModelProperty(value = "是否選中 默認false")
    private boolean checked;
    private List<?> children;


}
