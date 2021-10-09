package com.company.project.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.dto.AssetPlaceDto;
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

    String querySql = "SELECT ap.* ," +
            "ale.asset_name AS assetName, ale.asset_code AS assetCode, " +
            "p.place_name AS placeName, p.place_code AS placeCode "+
            "FROM asset_place AS ap " +
            "LEFT JOIN asset_listview AS ale ON ap.asset_id = ale.id " +
            "LEFT JOIN place2 AS p ON ap.place_id = p.id ";
    String wrapperSql = "SELECT * from ( " + querySql + " ) AS q ${ew.customSqlSegment}";

    @Select(wrapperSql)
    Page<AssetPlaceDto> newPage(Page page, @Param("ew") Wrapper queryWrapper);

    @Select("SELECT place_id FROM asset_place WHERE asset_id = #{assetId}")
    String selectAssetPlace(@Param("assetId") String assetId);

    @Select("SELECT asset_id FROM asset_place WHERE asset_id = #{assetId}")
    String selectAssetId(@Param("assetId") String assetId);

    @Delete("DELETE FROM asset_place WHERE asset_id = #{assetId}")
    String removeRecord(@Param("assetId") String assetId);

    @Update("UPDATE asset_place SET place_id = #{assetPlaceEntity.placeId} WHERE asset_id = #{assetPlaceEntity.assetId}")
    void updateAssetAtPlace(@Param("assetPlaceEntity") AssetPlaceEntity assetPlaceEntity);
}
