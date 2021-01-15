package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.Place2Mapper;
import com.company.project.entity.Place2Entity;
import com.company.project.service.Place2Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
@Service("place2Service")
public class Place2ServiceImpl extends ServiceImpl<Place2Mapper, Place2Entity> implements Place2Service {

    @Resource
    private Place2Mapper place2Mapper;

    /**
     * @return
     **/
    public JSONArray getPlacetSel(){

        List<Place2Entity> list = place2Mapper.selectList(Wrappers.<Place2Entity>lambdaQuery().eq(Place2Entity::getActive, 1));
        return JSONArray.parseArray(JSON.toJSONString(list));
    }


}