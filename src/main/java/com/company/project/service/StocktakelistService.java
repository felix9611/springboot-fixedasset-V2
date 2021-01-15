package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.StocktakelistEntity;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-25 20:33:06
 */
public interface StocktakelistService extends IService<StocktakelistEntity> {

    /**
     * @param vo vo
     */
    void newActivity(StocktakelistEntity vo);
}

