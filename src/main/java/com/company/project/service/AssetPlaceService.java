package com.company.project.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.dto.AssetPlaceDto;
import com.company.project.entity.AssetPlaceEntity;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-03-14 17:03:14
 */
public interface AssetPlaceService extends IService<AssetPlaceEntity> {


    /*
        @Override
        public Page<AssetListviewDTO> newPage(Page page, LambdaQueryWrapper<AssetListviewEntity> queryWrapper){
            return this.assetListviewMapper.page(page, queryWrapper);
        }
         */
    Page<AssetPlaceDto> newPage(Page page, LambdaQueryWrapper<AssetPlaceEntity> queryWrapper);
}

