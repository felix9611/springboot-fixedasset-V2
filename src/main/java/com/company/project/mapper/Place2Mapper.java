package com.company.project.mapper;

import com.company.project.entity.AssetType2Entity;
import com.company.project.entity.Place2Entity;
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
 * @date 2020-11-15 18:53:25
 */
public interface Place2Mapper extends BaseMapper<Place2Entity> {

    @Select("SELECT place_name AS placeName , place_code AS placeCode FROM place2 WHERE id = #{place2Entity.id} ")
    List<Place2Entity> selectTypeId(@Param("place2Entity") Place2Entity place2Entity);
}
