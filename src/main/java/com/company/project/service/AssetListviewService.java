package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.AssetListviewEntity;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-01-17 17:53:32
 */
public interface AssetListviewService extends IService<AssetListviewEntity> {

    void newAsset(AssetListviewEntity vo);

    void updateActive(AssetListviewEntity assetListview);
}

