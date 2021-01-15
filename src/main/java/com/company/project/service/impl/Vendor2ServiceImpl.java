package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.Vendor2Mapper;
import com.company.project.entity.Vendor2Entity;
import com.company.project.service.Vendor2Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("vendor2Service")
public class Vendor2ServiceImpl extends ServiceImpl<Vendor2Mapper, Vendor2Entity> implements Vendor2Service {

    @Resource
    private Vendor2Mapper vendor2Mapper;

    /**
     * @return
     **/
    public JSONArray getVendorSel(){
        List<Vendor2Entity> list = vendor2Mapper.selectList(Wrappers.<Vendor2Entity>lambdaQuery().eq(Vendor2Entity::getActive, 1));
        return JSONArray.parseArray(JSON.toJSONString(list));
    }


}