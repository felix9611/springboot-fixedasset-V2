package com.company.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.AssetlistEntity;
import com.company.project.entity.StocktakelistDetailEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-25 21:02:34
 */
public interface StocktakelistDetailService extends IService<StocktakelistDetailEntity> {

    /**
     * @param page page
     * @param listId listId
     * @return IPage
     */
    IPage<StocktakelistDetailEntity> listByPage(Page<StocktakelistDetailEntity> page, String listId);

}

