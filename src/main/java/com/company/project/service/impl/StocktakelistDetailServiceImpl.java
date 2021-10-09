package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.common.exception.BusinessException;
import com.company.project.dto.StockDetailDto;
import com.company.project.entity.ActionRecordEntity;
import com.company.project.entity.StocktakelistEntity;
import com.company.project.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.entity.StocktakelistDetailEntity;
import com.company.project.service.StocktakelistDetailService;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

@Transactional
@Service("stocktakelistDetailService")
@Slf4j
public class StocktakelistDetailServiceImpl extends ServiceImpl<StocktakelistDetailMapper, StocktakelistDetailEntity> implements StocktakelistDetailService {

    @Resource private StocktakelistDetailMapper stocktakelistDetailMapper;

    @Resource private StocktakelistMapper stocktakelistMapper;

    @Resource private ActionRecordMapper actionRecordMapper;

    @Resource private ActionRecordEntity actionRecordEntity;

    @Resource private StocktakelistDetailEntity stocktakelistDetailEntity;

    @Resource private AssetListviewMapper assetListviewMapper;

    @Resource private AssetPlaceMapper assetPlaceMapper;

    @Override
    public Page<StockDetailDto> listByPage(Page<StocktakelistDetailEntity> page, String listId){

        StocktakelistEntity stocktakelistEntity = stocktakelistMapper.selectById(listId);
        if (stocktakelistEntity == null) {
            throw new BusinessException("Get data failed!");
        }

        LambdaQueryWrapper<StocktakelistDetailEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StocktakelistDetailEntity::getListId, listId);

        Page<StockDetailDto> result = stocktakelistDetailMapper.newPage(page, wrapper);
        if (!CollectionUtils.isEmpty(result.getRecords())) {
            result.getRecords().parallelStream().forEach(entity -> entity.setListId(stocktakelistEntity.getId()));
        }
        return result;
    }

    @Override
    public void saveDetail(StocktakelistDetailEntity entity) {

         int set = stocktakelistDetailMapper.insert(entity);
            if(set == 1){
              actionRecordEntity.setActionName("INSERT");
              actionRecordEntity.setActionMethod("POST");
              actionRecordEntity.setActionFrom("盤點管理");
              actionRecordEntity.setActionData("盤點: " + entity.getAssetCode());
              actionRecordEntity.setActionSuccess("Success");
              actionRecordMapper.insert(actionRecordEntity);
            } else {
                actionRecordEntity.setActionName("INSERT");
                actionRecordEntity.setActionMethod("POST");
                actionRecordEntity.setActionFrom("盤點管理");
                actionRecordEntity.setActionData("盤點: " + entity.getAssetCode());
                actionRecordEntity.setActionSuccess("Failure");
                actionRecordMapper.insert(actionRecordEntity);
            }
    }


}
