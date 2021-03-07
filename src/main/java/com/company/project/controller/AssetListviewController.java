package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.company.project.entity.Place2Entity;
import io.swagger.annotations.Api;
import org.springframework.util.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.AssetListviewEntity;
import com.company.project.service.AssetListviewService;

import javax.annotation.Resource;


/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-01-17 17:53:32
 */
@Api(tags = "資產清單")
@Controller
@RequestMapping("/")
public class AssetListviewController {
    @Autowired
    private AssetListviewService assetListviewService;




    /**
    * 跳转到页面
    */
    @GetMapping("/index/assetListview")
    public String assetListview() {
        return "assetlistview/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("assetListview/add")
    @RequiresPermissions("assetListview:add")
    @ResponseBody
    public DataResult add(@RequestBody AssetListviewEntity assetListview){
        assetListviewService.newAsset(assetListview);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("assetListview/delete")
    @RequiresPermissions("assetListview:delete")
    @ResponseBody
    public DataResult delete(@RequestBody AssetListviewEntity assetListview){
        assetListviewService.updateActive(assetListview);
        return DataResult.success();
    }
    /*
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        assetListviewService.removeByIds(ids);
        return DataResult.success();
    }
    */
    @ApiOperation(value = "更新")
    @PutMapping("assetListview/update")
    @RequiresPermissions("assetListview:update")
    @ResponseBody
    public DataResult update(@RequestBody AssetListviewEntity assetListview){
        assetListviewService.updateById(assetListview);
        return DataResult.success();
    }

    @ApiOperation(value = "查詢分頁數據(新)")
    @PostMapping("assetListview/listPage")
    @RequiresPermissions("assetListview:list")
    @ResponseBody
    public DataResult findListPage(@RequestBody AssetListviewEntity assetListview){
        Page page = new Page(assetListview.getPage(), assetListview.getLimit());
        LambdaQueryWrapper<AssetListviewEntity> queryWrapper = Wrappers.lambdaQuery();

        if(!StringUtils.isEmpty(assetListview.getAssetCode())){
            queryWrapper.like(AssetListviewEntity::getAssetCode, assetListview.getAssetCode());
        }
        if(!StringUtils.isEmpty(assetListview.getAssetName())){
            queryWrapper.like(AssetListviewEntity::getAssetName, assetListview.getAssetName());
        }
        if(!StringUtils.isEmpty(assetListview.getAssetType())){
            queryWrapper.eq(AssetListviewEntity::getAssetType, assetListview.getAssetType());
        }
        if(!StringUtils.isEmpty(assetListview.getPlace())){
            queryWrapper.eq(AssetListviewEntity::getPlace, assetListview.getPlace());
        }
        if(!StringUtils.isEmpty(assetListview.getDeptId())){
            queryWrapper.eq(AssetListviewEntity::getDeptId, assetListview.getDeptId());

        }
        queryWrapper.orderByDesc(true, AssetListviewEntity::getAssetCode);
        queryWrapper.eq(AssetListviewEntity::getActive, "1");




        //查询条件示例
        //queryWrapper.eq(AssetListviewEntity::getId, assetListview.getId());
        IPage<AssetListviewEntity> iPage = assetListviewService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "查詢分頁數據")
    @PostMapping("assetListview/listByPage")
    @RequiresPermissions("assetListview:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody AssetListviewEntity assetListview){
        Page page = new Page(assetListview.getPage(), assetListview.getLimit());
        LambdaQueryWrapper<AssetListviewEntity> queryWrapper = Wrappers.lambdaQuery();

        if(!StringUtils.isEmpty(assetListview.getAssetCode())){
            queryWrapper.like(AssetListviewEntity::getAssetCode, assetListview.getAssetCode());
        }
        if(!StringUtils.isEmpty(assetListview.getAssetName())){
            queryWrapper.like(AssetListviewEntity::getAssetName, assetListview.getAssetName());
        }
        if(!StringUtils.isEmpty(assetListview.getAssetType())){
            queryWrapper.eq(AssetListviewEntity::getAssetType, assetListview.getAssetType());
        }
        if(!StringUtils.isEmpty(assetListview.getPlace())){
            queryWrapper.eq(AssetListviewEntity::getPlace, assetListview.getPlace());
        }
        if(!StringUtils.isEmpty(assetListview.getDeptId())){
            queryWrapper.eq(AssetListviewEntity::getDeptId, assetListview.getDeptId());
        }

        queryWrapper.orderByDesc(true, AssetListviewEntity::getAssetCode);
        queryWrapper.eq(AssetListviewEntity::getActive, "1");


        //查询条件示例
        //queryWrapper.eq(AssetListviewEntity::getId, assetListview.getId());
        IPage<AssetListviewEntity> iPage = assetListviewService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
