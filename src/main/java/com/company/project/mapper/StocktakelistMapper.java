package com.company.project.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.company.project.entity.StocktakelistDetailEntity;
import com.company.project.entity.StocktakelistEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-25 20:33:06
 */
@Mapper
public interface StocktakelistMapper extends BaseMapper<StocktakelistEntity> {

    List<StocktakelistEntity> selectList(LambdaQueryWrapper<StocktakelistDetailEntity> eq);
}
