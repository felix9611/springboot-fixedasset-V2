package com.company.project.dto;

import com.company.project.entity.AssetPlaceEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Transient;

@EqualsAndHashCode(callSuper = true)
@Data
public class AssetPlaceDto extends AssetPlaceEntity {

    @Transient private String placeName;

    @Transient private String placeCode;

    @Transient private String assetName;

    @Transient private String assetCode;

}
