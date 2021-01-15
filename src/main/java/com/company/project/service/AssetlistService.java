package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.AssetlistEntity;
import com.company.project.entity.SysDept;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-10 20:30:00
 */
@Service
public interface AssetlistService extends IService<AssetlistEntity> {

    /**
     * @param vo vo
     */
    void addAsset(AssetlistEntity vo);

    /**
     * @param vo vo
     */
    boolean delByID(AssetlistEntity vo);

    /**
     * @param vo vo
     */
    boolean updateById(AssetlistEntity vo);


}

