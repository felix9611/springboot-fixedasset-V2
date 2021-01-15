package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.AssetTypeMapper;
import com.company.project.entity.AssetTypeEntity;
import com.company.project.service.AssetTypeService;


@Service("assetTypeService")
public class AssetTypeServiceImpl extends ServiceImpl<AssetTypeMapper, AssetTypeEntity> implements AssetTypeService {


}