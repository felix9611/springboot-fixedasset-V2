package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.common.aop.annotation.LogAnnotation;
import com.company.project.mapper.AssetType2Mapper;
import io.netty.util.internal.StringUtil;
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

import com.company.project.entity.AssetType2Entity;
import com.company.project.service.AssetType2Service;

import javax.annotation.Resource;


/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-15 17:48:59
 */
@Controller
@RequestMapping("/")
public class AssetType2Controller {
    @Resource
    private AssetType2Service assetType2Service;

    @Resource
    private AssetType2Entity assetType2Entity;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/assetType2")
    public String assetType2() {
        return "assettype2/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("assetType2/add")
    @RequiresPermissions("assetType2:add")
    @ResponseBody
    public DataResult add(@RequestBody AssetType2Entity assetType2){
        assetType2Service.save(assetType2);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/assetType2/delete")
    @RequiresPermissions("assetType2:delete")
    @ResponseBody
    public DataResult delete(@RequestBody AssetType2Entity assetType2){
        assetType2Service.updateActive(assetType2);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("assetType2/update")
    @RequiresPermissions("assetType2:update")
    @ResponseBody
    public DataResult update(@RequestBody AssetType2Entity assetType2){
        assetType2Service.updateById(assetType2);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("assetType2/listByPage")
    @RequiresPermissions("assetType2:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody AssetType2Entity assetType2){
        Page page = new Page(assetType2.getPage(), assetType2.getLimit());
        LambdaQueryWrapper<AssetType2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(AssetType2Entity::getId, assetType2.getId());

        if(!StringUtils.isEmpty(assetType2.getTypeName())){
            queryWrapper.like(AssetType2Entity::getTypeName, assetType2.getTypeName());
        }
        /*

        if(!StringUtils.isEmpty(assetlist.getBuydate())){
            queryWrapper.like(AssetlistEntity::getBuydate, assetlist.getBuydate());
        }
         */
        queryWrapper.eq(AssetType2Entity::getActive, "1");
        IPage<AssetType2Entity> iPage = assetType2Service.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
