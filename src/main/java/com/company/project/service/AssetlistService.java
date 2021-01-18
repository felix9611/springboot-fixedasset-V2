package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.AssetlistEntity;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-01-17 17:42:02
 */
public interface AssetlistService extends IService<AssetlistEntity> {

    void addAsset(AssetlistEntity vo);
}

