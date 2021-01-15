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

import com.company.project.entity.Vendor2Entity;
import com.company.project.service.Vendor2Service;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-20 09:32:24
 */
@Controller
@RequestMapping("/")
public class Vendor2Controller {
    @Autowired
    private Vendor2Service vendor2Service;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/vendor2")
    public String vendor2() {
        return "vendor2/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("vendor2/add")
    @RequiresPermissions("vendor2:add")
    @ResponseBody
    public DataResult add(@RequestBody Vendor2Entity vendor2){
        vendor2Service.save(vendor2);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("vendor2/delete")
    @RequiresPermissions("vendor2:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        vendor2Service.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("vendor2/update")
    @RequiresPermissions("vendor2:update")
    @ResponseBody
    public DataResult update(@RequestBody Vendor2Entity vendor2){
        vendor2Service.updateById(vendor2);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("vendor2/listByPage")
    @RequiresPermissions("vendor2:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody Vendor2Entity vendor2){
        Page page = new Page(vendor2.getPage(), vendor2.getLimit());
        LambdaQueryWrapper<Vendor2Entity> queryWrapper = Wrappers.lambdaQuery();


        if(!StringUtils.isEmpty(vendor2.getVendorName())){
            queryWrapper.like(Vendor2Entity::getVendorName, vendor2.getVendorName());
        }

        queryWrapper.eq(Vendor2Entity::getActive, "1");

        IPage<Vendor2Entity> iPage = vendor2Service.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
