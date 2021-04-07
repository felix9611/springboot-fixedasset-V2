package com.company.project.mapper;

import com.company.project.entity.AssetPhotoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.project.entity.AssetPlaceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * 
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-04-02 00:23:33
 */
public interface AssetPhotoMapper extends BaseMapper<AssetPhotoEntity> {

    @Select("SELECT asset_code FROM asset_phone WHERE asset_code = #{assetPlaceEntity.assetCode}")
    String selectAssetCode(@Param("assetPlaceEntity") String assetPlaceEntity);

	
}
