package com.company.project.mapper;

import com.company.project.entity.AssetPlaceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * 
 * 
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-03-14 17:03:14
 */
public interface AssetPlaceMapper extends BaseMapper<AssetPlaceEntity> {

    /**
     * @param assetPlaceEntity
     */
    @Update("UPDATE asset_place SET place_id = #{assetPlaceEntity.placeId} WHERE asset_id = #{assetPlaceEntity.assetId}")
    void updateAssetAtPlace(@Param("assetPlaceEntity") AssetPlaceEntity assetPlaceEntity);

    /**
     * @param assetPlaceEntity
     */
    @Select("SELECT asset_id FROM asset_place WHERE asset_id = #{assetPlaceEntity.assetId}")
    String selectAssetId(@Param("assetPlaceEntity") AssetPlaceEntity assetPlaceEntity);

    /**
     * @param assetPlaceEntity
     */
    @Delete("DELETE FROM asset_place WHERE asset_id = #{assetPlaceEntity.assetId}")
    void removeRecord(@Param("assetPlaceEntity") AssetPlaceEntity assetPlaceEntity);
}
