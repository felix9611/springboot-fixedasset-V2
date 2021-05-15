package com.company.project.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.AssetType2Entity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-15 17:48:59
 */
@Component
@Service
public interface AssetType2Service extends IService<AssetType2Entity> {


    void updateActive(AssetType2Entity assetType2);

    List<AssetType2Entity> selectNameAndCode(AssetType2Entity assetType2);
}

