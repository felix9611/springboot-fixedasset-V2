package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.ActionRecordEntity;
import com.company.project.entity.DepartmentEntity;
import com.company.project.mapper.ActionRecordMapper;
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

    @Resource private Place2Mapper place2Mapper;

    @Resource private ActionRecordMapper actionRecordMapper;

    @Resource private ActionRecordEntity actionRecordEntity;

    @Override
    public boolean save(Place2Entity entity) {
        place2Mapper.insert(entity);

        actionRecordEntity.setActionName("INSERT");
        actionRecordEntity.setActionMethod("POST");
        actionRecordEntity.setActionFrom("地點管理");
        actionRecordEntity.setActionData("新增資料: " + entity.toString());
        actionRecordEntity.setActionSuccess("Success");
        actionRecordMapper.insert(actionRecordEntity);

        return true;
    }

    @Override
    public boolean updateById(Place2Entity entity) {

        place2Mapper.updateById(entity);

        actionRecordEntity.setActionName("UPDATE");
        actionRecordEntity.setActionMethod("PUT");
        actionRecordEntity.setActionFrom("地點管理");
        actionRecordEntity.setActionData("更新資料: " + entity.toString());
        actionRecordEntity.setActionSuccess("Success");
        actionRecordMapper.insert(actionRecordEntity);

        return true;
    }

    @Override
    public void updateActive(Place2Entity entity) {
        entity.setActive("0");
        place2Mapper.updateById(entity);

        actionRecordEntity.setActionName("DELETE");
        actionRecordEntity.setActionMethod("PUT");
        actionRecordEntity.setActionFrom("地點管理");
        actionRecordEntity.setActionData("已被設為無效: " + entity.toString());
        actionRecordEntity.setActionSuccess("Success");
        actionRecordMapper.insert(actionRecordEntity);
    }

    /**
     * @return
     **/
    public JSONArray getPlacetSel(){

        List<Place2Entity> list = place2Mapper.selectList(Wrappers.<Place2Entity>lambdaQuery().eq(Place2Entity::getActive, 1));
        return JSONArray.parseArray(JSON.toJSONString(list));
    }

    /*
    @Override
    public List<AssetType2Entity> selectNameAndCode(AssetType2Entity assetType2){
       return assetType2Mapper.selectTypeId(assetType2);
    }
     */

    @Override
    public List<Place2Entity>  selectNameAndCode(Place2Entity place2Entity){
        return place2Mapper.selectTypeId(place2Entity);
    }


}