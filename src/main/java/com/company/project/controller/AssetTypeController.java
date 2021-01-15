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

import com.company.project.entity.AssetTypeEntity;
import com.company.project.service.AssetTypeService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-21 10:28:30
 */
@Controller
@RequestMapping("/")
public class AssetTypeController {
    @Autowired
    private AssetTypeService assetTypeService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/assetType")
    public String assetType() {
        return "assettype/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("assetType/add")
    @RequiresPermissions("assetType:add")
    @ResponseBody
    public DataResult add(@RequestBody AssetTypeEntity assetType){
        assetTypeService.save(assetType);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("assetType/delete")
    @RequiresPermissions("assetType:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        assetTypeService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("assetType/update")
    @RequiresPermissions("assetType:update")
    @ResponseBody
    public DataResult update(@RequestBody AssetTypeEntity assetType){
        assetTypeService.updateById(assetType);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("assetType/listByPage")
    @RequiresPermissions("assetType:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody AssetTypeEntity assetType){
        Page page = new Page(assetType.getPage(), assetType.getLimit());
        LambdaQueryWrapper<AssetTypeEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(AssetTypeEntity::getId, assetType.getId());
        IPage<AssetTypeEntity> iPage = assetTypeService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
