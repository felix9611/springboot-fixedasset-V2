package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.StocktakelistDetailEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.StocktakelistMapper;
import com.company.project.entity.StocktakelistEntity;
import com.company.project.service.StocktakelistService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service("stocktakelistService")
@Slf4j
public class StocktakelistServiceImpl extends ServiceImpl<StocktakelistMapper, StocktakelistEntity> implements StocktakelistService {

    @Resource
    private StocktakelistMapper stocktakelistMapper;

    @Override
    public void newActivity(StocktakelistEntity vo){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        vo.setCreateTime(formatter.format(date));
        vo.setStartTime(formatter.format(date));
        stocktakelistMapper.insert(vo);
    }

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param listId
     * @return 参数键值
     **/
    public JSONArray getListNum(String listId){
        if (StringUtils.isEmpty(listId)) {
            return new JSONArray();
        }

        StocktakelistEntity dict = this.getOne(Wrappers.<StocktakelistEntity>lambdaQuery().eq(StocktakelistEntity::getId, listId));
        if (dict == null || dict.getId() == null) {
            return new JSONArray();
        }

        List<StocktakelistEntity> list = stocktakelistMapper.selectList(Wrappers.<StocktakelistDetailEntity>lambdaQuery().eq(StocktakelistDetailEntity::getListId, listId));
        return JSONArray.parseArray(JSON.toJSONString(list));

    }

}