package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.AssetListviewEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.AssetPlaceEntity;
import com.company.project.service.AssetPlaceService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-03-14 17:03:14
 */
@Controller
@RequestMapping("/")
public class AssetPlaceController {

    @Autowired private AssetPlaceService assetPlaceService;

    @ApiOperation(value = "查詢分頁數據")
    @PostMapping("assetPlace/listByPage")
 //   @RequiresPermissions("assetPlace:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody AssetPlaceEntity assetPlace){
        Page page = new Page(assetPlace.getPage(), assetPlace.getLimit());
        LambdaQueryWrapper<AssetPlaceEntity> queryWrapper = Wrappers.lambdaQuery();

        if(!StringUtils.isEmpty(assetPlace.getPlaceId())){
            queryWrapper.eq(AssetPlaceEntity::getPlaceId, assetPlace.getPlaceId());
        }
        System.out.print(assetPlace);
        IPage<AssetPlaceEntity> iPage = assetPlaceService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
