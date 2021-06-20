package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.ActionRecordEntity;
import com.company.project.mapper.ActionRecordMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.AssetType2Mapper;
import com.company.project.entity.AssetType2Entity;
import com.company.project.service.AssetType2Service;
import org.springframework.transaction.annotation.Transactional;
//import sun.invoke.util.Wrapper;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
@Service("assetType2Service")
public class AssetType2ServiceImpl extends ServiceImpl<AssetType2Mapper, AssetType2Entity> implements AssetType2Service {

    @Resource private AssetType2Mapper Atm1;

    @Resource private AssetType2Entity assetType2Entity;

    @Resource private AssetType2Mapper assetType2Mapper;

    @Resource private ActionRecordMapper actionRecordMapper;

    @Resource private ActionRecordEntity actionRecordEntity;

    @Override
    public boolean save(AssetType2Entity entity) {
        int set = assetType2Mapper.insert(entity);

        if(set == 1){
            actionRecordEntity.setActionName("INSERT");
            actionRecordEntity.setActionMethod("POST");
            actionRecordEntity.setActionFrom("種類管理");
            actionRecordEntity.setActionData("新增資料: " + entity.toString());
            actionRecordEntity.setActionSuccess("Success");
            actionRecordMapper.insert(actionRecordEntity);
        }

        return true;
    }

    @Override
    public boolean updateById(AssetType2Entity entity) {

        int set = assetType2Mapper.updateById(entity);

        if(set == 1){
            actionRecordEntity.setActionData("更新資料: " + entity.toString());
            actionRecordEntity.setActionSuccess("Success");
        } else {
            actionRecordEntity.setActionData("(失敗)更新資料: " + entity.toString());
            actionRecordEntity.setActionSuccess("Failure");
        }

        actionRecordEntity.setActionName("UPDATE");
        actionRecordEntity.setActionMethod("PUT");
        actionRecordEntity.setActionFrom("種類管理");
        actionRecordMapper.insert(actionRecordEntity);


        return true;
    }

    /**
     * @return
     **/
    public JSONArray getAssettype() {

        List<AssetType2Entity> list = Atm1.selectList(Wrappers.<AssetType2Entity>lambdaQuery().eq(AssetType2Entity::getActive, 1));
        return JSONArray.parseArray(JSON.toJSONString(list));
    }

    @Override
    public void updateActive(AssetType2Entity assetType2) {
        assetType2.setActive("0");
        int set = assetType2Mapper.updateById(assetType2);

        if(set == 1){
            actionRecordEntity.setActionName("DELETE");
            actionRecordEntity.setActionMethod("PUT");
            actionRecordEntity.setActionFrom("種類管理");
            actionRecordEntity.setActionData("已被設為無效: " + assetType2.toString());
            actionRecordEntity.setActionSuccess("Success");
            actionRecordMapper.insert(actionRecordEntity);
        }
    }

    @Override
    public List<AssetType2Entity> selectNameAndCode(AssetType2Entity assetType2){
       return assetType2Mapper.selectTypeId(assetType2);
    }

}