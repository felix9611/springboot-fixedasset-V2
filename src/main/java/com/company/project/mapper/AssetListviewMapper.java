package com.company.project.mapper;

import com.company.project.dto.AssetListviewDTO;
import com.company.project.entity.AssetListviewEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("SELECT ale.* ," +
            "at2.type_name AS typeName, at2.type_code AS typeCode, " +
            "d.dept_name AS deptName, d.dept_code AS deptCode "+
            "FROM asset_listview AS ale " +
            "LEFT JOIN asset_type2 AS at2 ON ale.asset_type = at2.id " +
            "LEFT JOIN department AS d ON ale.dept_id = d.id " +
            "WHERE ale.asset_code = #{assetListviewEntity.assetCode}")
    List<AssetListviewDTO> selectAssetInfo(@Param("assetListviewEntity") AssetListviewEntity assetListviewEntity);


	
}
