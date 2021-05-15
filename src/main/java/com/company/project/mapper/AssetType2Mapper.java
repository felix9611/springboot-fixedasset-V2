package com.company.project.mapper;

import com.company.project.entity.AssetType2Entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * 
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-15 17:48:59
 */
@Mapper
@Repository
public interface AssetType2Mapper extends BaseMapper<AssetType2Entity> {

    @Select("SELECT type_name AS typeName , type_code AS typeCode FROM asset_type2 WHERE id = #{assetType2Entity.id} ")
    List<AssetType2Entity> selectTypeId(@Param("assetType2Entity") AssetType2Entity assetType2Entity);

}
