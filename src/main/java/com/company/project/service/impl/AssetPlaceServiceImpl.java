package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.AssetPlaceMapper;
import com.company.project.entity.AssetPlaceEntity;
import com.company.project.service.AssetPlaceService;


@Service("assetPlaceService")
public class AssetPlaceServiceImpl extends ServiceImpl<AssetPlaceMapper, AssetPlaceEntity> implements AssetPlaceService {


}