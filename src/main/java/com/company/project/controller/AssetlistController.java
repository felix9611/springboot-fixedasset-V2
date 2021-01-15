package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

import com.company.project.entity.AssetlistEntity;
import com.company.project.service.AssetlistService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-01-10 18:28:38
 */
@Controller
@RequestMapping("/")
public class AssetlistController {
    @Autowired
    private AssetlistService assetlistService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/assetlist")
    public String assetlist() {
        return "assetlist/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("assetlist/add")
    @RequiresPermissions("assetlist:add")
    @ResponseBody
    public DataResult add(@RequestBody AssetlistEntity assetlist){
        assetlistService.save(assetlist);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("assetlist/delete")
    @RequiresPermissions("assetlist:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        assetlistService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("assetlist/update")
    @RequiresPermissions("assetlist:update")
    @ResponseBody
    public DataResult update(@RequestBody AssetlistEntity assetlist){
        assetlistService.updateById(assetlist);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("assetlist/listByPage")
    @RequiresPermissions("assetlist:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody AssetlistEntity assetlist) {
        Page page = new Page(assetlist.getPage(), assetlist.getLimit());
        LambdaQueryWrapper<AssetlistEntity> queryWrapper = Wrappers.lambdaQuery();


        if (!StringUtils.isEmpty(assetlist.getAssetName())) {
            queryWrapper.like(AssetlistEntity::getAssetName, assetlist.getAssetName());
        }
        if (!StringUtils.isEmpty(assetlist.getAssetType())) {
            queryWrapper.eq(AssetlistEntity::getAssetType, assetlist.getAssetType());
        }
        if (!StringUtils.isEmpty(assetlist.getPlace())) {
            queryWrapper.eq(AssetlistEntity::getPlace, assetlist.getPlace());
        }
        if (!StringUtils.isEmpty(assetlist.getDeptId())) {
            queryWrapper.eq(AssetlistEntity::getDeptId, assetlist.getDeptId());
        }

        queryWrapper.eq(AssetlistEntity::getActive, "1");

        IPage<AssetlistEntity> iPage = assetlistService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
