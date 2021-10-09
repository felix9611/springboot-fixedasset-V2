package com.company.project.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.dto.AssetPlaceDto;
import com.company.project.dto.StockDetailDto;
import com.company.project.entity.StocktakelistDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * 
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-25 21:02:34
 */
public interface StocktakelistDetailMapper extends BaseMapper<StocktakelistDetailEntity> {

    String querySql = "SELECT sd.* ," +
            "ale.asset_name AS assetName, ale.asset_code AS assetCode, " +
            "p.place_name AS placeName, p.place_code AS placeCode "+
            "FROM stocktakelist_detail AS sd " +
            "LEFT JOIN asset_listview AS ale ON sd.asset_code = ale.asset_code " +
            "LEFT JOIN place2 AS p ON sd.asset_place = p.id ";
    String wrapperSql = "SELECT * from ( " + querySql + " ) AS q ${ew.customSqlSegment}";

    @Select(wrapperSql)
    Page<StockDetailDto> newPage(Page page, @Param("ew") Wrapper queryWrapper);
	
}
