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

    @Select("SELECT place_id FROM asset_place WHERE asset_id = #{assetId}")
    String selectAssetPlace(@Param("assetId") String assetId);

    @Select("SELECT asset_id FROM asset_place WHERE asset_id = #{assetId}")
    String selectAssetId(@Param("assetId") String assetId);

    @Delete("DELETE FROM asset_place WHERE asset_id = #{assetId}")
    String removeRecord(@Param("assetId") String assetId);

    @Update("UPDATE asset_place SET place_id = #{assetPlaceEntity.placeId} WHERE asset_id = #{assetPlaceEntity.assetId}")
    void updateAssetAtPlace(@Param("assetPlaceEntity") AssetPlaceEntity assetPlaceEntity);
}
