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

import com.company.project.entity.VendorEntity;
import com.company.project.service.VendorService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-21 10:28:41
 */
@Controller
@RequestMapping("/")
public class VendorController {
    @Autowired
    private VendorService vendorService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/vendor")
    public String vendor() {
        return "vendor/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("vendor/add")
    @RequiresPermissions("vendor:add")
    @ResponseBody
    public DataResult add(@RequestBody VendorEntity vendor){
        vendorService.save(vendor);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("vendor/delete")
    @RequiresPermissions("vendor:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        vendorService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("vendor/update")
    @RequiresPermissions("vendor:update")
    @ResponseBody
    public DataResult update(@RequestBody VendorEntity vendor){
        vendorService.updateById(vendor);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("vendor/listByPage")
    @RequiresPermissions("vendor:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody VendorEntity vendor){
        Page page = new Page(vendor.getPage(), vendor.getLimit());
        LambdaQueryWrapper<VendorEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(VendorEntity::getId, vendor.getId());
        IPage<VendorEntity> iPage = vendorService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
