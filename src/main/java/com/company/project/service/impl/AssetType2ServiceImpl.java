package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.injector.methods.Update;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.common.exception.BusinessException;
import com.company.project.common.exception.code.BaseResponseCode;
import com.company.project.entity.AssetlistEntity;
import com.company.project.mapper.AssetlistMapper;
import com.company.project.service.AssetlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.AssetType2Mapper;
import com.company.project.entity.AssetType2Entity;
import com.company.project.service.AssetType2Service;
import org.springframework.transaction.annotation.Transactional;
//import sun.invoke.util.Wrapper;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

@Component
@Transactional
@Service("assetType2Service")
public class AssetType2ServiceImpl extends ServiceImpl<AssetType2Mapper, AssetType2Entity> implements AssetType2Service {

    @Resource
    private AssetType2Mapper Atm1;

    @Resource
    private AssetType2Entity assetType2Entity;

    @Resource
    private AssetType2Mapper assetType2Mapper;
    /**
     * @return
     **/
    public JSONArray getAssettype() {

        List<AssetType2Entity> list = Atm1.selectList(Wrappers.<AssetType2Entity>lambdaQuery().eq(AssetType2Entity::getActive, 1));
        return JSONArray.parseArray(JSON.toJSONString(list));
    }


}