package com.company.project.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * DeptRespNodeVO
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Data
public class DeptRespNodeVO {
    @ApiModelProperty(value = "組織id")
    private String id;

    @ApiModelProperty(value = "組織編碼")
    private String deptNo;

    @ApiModelProperty(value = "組織名稱")
    private String title;

    @ApiModelProperty(value = "組織名稱")
    private String label;

    @ApiModelProperty(value = "組織父級id")
    private String pid;

    @ApiModelProperty(value = "組織狀態")
    private Integer status;

    @ApiModelProperty(value = "組織關係id")
    private String relationCode;

    @ApiModelProperty(value = "是否展開 默認不展開(false)")
    private boolean spread = true;

    @ApiModelProperty(value = "是否選中")
    private boolean checked = false;

    private boolean disabled = false;

    @ApiModelProperty(value = "子集")
    private List<?> children;

    public String getLabel() {
        return title;
    }

}
