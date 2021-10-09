package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.dto.AssetPlaceDto;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.AssetPlaceMapper;
import com.company.project.entity.AssetPlaceEntity;
import com.company.project.service.AssetPlaceService;

import javax.annotation.Resource;


@Service("assetPlaceService")
public class AssetPlaceServiceImpl extends ServiceImpl<AssetPlaceMapper, AssetPlaceEntity> implements AssetPlaceService {

    @Resource private AssetPlaceMapper assetPlaceMapper;

    @Override
    public Page<AssetPlaceDto> newPage(Page page, LambdaQueryWrapper<AssetPlaceEntity> queryWrapper) {
        return this.assetPlaceMapper.newPage(page, queryWrapper);
    }
}