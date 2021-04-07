package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.AssetPhotoMapper;
import com.company.project.entity.AssetPhotoEntity;
import com.company.project.service.AssetPhotoService;


@Service("assetPhotoService")
public class AssetPhotoServiceImpl extends ServiceImpl<AssetPhotoMapper, AssetPhotoEntity> implements AssetPhotoService {


}