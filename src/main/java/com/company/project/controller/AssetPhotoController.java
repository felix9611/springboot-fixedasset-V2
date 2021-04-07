package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

import com.company.project.entity.AssetPhotoEntity;
import com.company.project.service.AssetPhotoService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-04-02 00:23:33
 */
@Controller
@RequestMapping("/")
public class AssetPhotoController {
    @Autowired
    private AssetPhotoService assetPhotoService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/assetPhoto")
    public String assetPhoto() {
        return "assetphoto/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("assetPhoto/add")
    @RequiresPermissions("assetPhoto:add")
    @ResponseBody
    public DataResult add(@RequestBody AssetPhotoEntity assetPhoto){
        assetPhotoService.save(assetPhoto);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("assetPhoto/delete")
    @RequiresPermissions("assetPhoto:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        assetPhotoService.removeByIds(ids);
        return DataResult.success();
    }
/*
    @ApiOperation(value = "更新")
    @PutMapping("assetPhoto/update")
    @RequiresPermissions("assetPhoto:update")
    @ResponseBody
    public DataResult update(@RequestBody AssetPhotoEntity assetPhoto){
        assetPhotoService.updateById(assetPhoto);
        return DataResult.success();
    }
*/
    @ApiOperation(value = "查询分页数据")
    @PostMapping("assetPhoto/listByPage")
    @RequiresPermissions("assetPhoto:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody AssetPhotoEntity assetPhoto){

        Page page = new Page(assetPhoto.getPage(), assetPhoto.getLimit());
        LambdaQueryWrapper<AssetPhotoEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(AssetPhotoEntity::getId, assetPhoto.getId());
        IPage<AssetPhotoEntity> iPage = assetPhotoService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
