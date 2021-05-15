package com.company.project.dto;

import com.company.project.entity.AssetListviewEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;

@EqualsAndHashCode(callSuper = true)
@Data
public class AssetListviewDTO extends AssetListviewEntity {

    @Transient private String typeName;

    @Transient private String typeCode;

    @Transient private String deptName;

    @Transient private String deptCode;

    @Transient private String placeName;

    @Transient private String placeCode;
}
