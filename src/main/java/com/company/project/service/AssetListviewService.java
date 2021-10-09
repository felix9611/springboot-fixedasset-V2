package com.company.project.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.dto.AssetListviewDTO;
import com.company.project.entity.AssetListviewEntity;

import java.util.List;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-01-17 17:53:32
 */
public interface AssetListviewService extends IService<AssetListviewEntity> {

    Page<AssetListviewDTO> newPage(Page page, LambdaQueryWrapper<AssetListviewEntity> queryWrapper);

    void newAsset(AssetListviewEntity vo);

    void updateActive(AssetListviewEntity assetListview);

    void updateAsset(AssetListviewEntity assetListview);

    List<AssetListviewDTO> assetDetailInfo(AssetListviewEntity assetListviewEntity);
}

