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
    @Autowired
    private AssetPlaceService assetPlaceService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/assetPlace")
    public String assetPlace() {
        return "assetplace/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("assetPlace/add")
    @RequiresPermissions("assetPlace:add")
    @ResponseBody
    public DataResult add(@RequestBody AssetPlaceEntity assetPlace){
        assetPlaceService.save(assetPlace);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("assetPlace/delete")
    @RequiresPermissions("assetPlace:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        assetPlaceService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("assetPlace/update")
    @RequiresPermissions("assetPlace:update")
    @ResponseBody
    public DataResult update(@RequestBody AssetPlaceEntity assetPlace){
        assetPlaceService.updateById(assetPlace);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("assetPlace/listByPage")
    @RequiresPermissions("assetPlace:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody AssetPlaceEntity assetPlace){
        Page page = new Page(assetPlace.getPage(), assetPlace.getLimit());
        LambdaQueryWrapper<AssetPlaceEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(AssetPlaceEntity::getId, assetPlace.getId());
        IPage<AssetPlaceEntity> iPage = assetPlaceService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
