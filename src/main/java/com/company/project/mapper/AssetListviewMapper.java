package com.company.project.mapper;

import com.company.project.entity.AssetListviewEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * 
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-01-17 17:53:32
 */
public interface AssetListviewMapper extends BaseMapper<AssetListviewEntity> {

    @Select("SELECT asset_code FROM asset_listview WHERE asset_code = #{assetCode}")
    String selectAssetCode(@Param("assetCode") String assetCode);


	
}
