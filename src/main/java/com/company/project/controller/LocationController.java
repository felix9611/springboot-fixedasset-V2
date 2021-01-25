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

import com.company.project.entity.LocationEntity;
import com.company.project.service.LocationService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-21 10:28:41
 */
@Controller
@RequestMapping("/")
public class LocationController {
    @Autowired
    private LocationService locationService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/location")
    public String location() {
        return "location/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("location/add")
    @RequiresPermissions("location:add")
    @ResponseBody
    public DataResult add(@RequestBody LocationEntity location){
        locationService.save(location);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("location/delete")
    @RequiresPermissions("location:delete")
    @ResponseBody
    public DataResult delete(@RequestBody LocationEntity location){
        locationService.updateActive(location);
        return DataResult.success();
    }
   /* public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        locationService.removeByIds(ids);
        return DataResult.success();
    }*/

    @ApiOperation(value = "更新")
    @PutMapping("location/update")
    @RequiresPermissions("location:update")
    @ResponseBody
    public DataResult update(@RequestBody LocationEntity location){
        locationService.updateById(location);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("location/listByPage")
    @RequiresPermissions("location:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody LocationEntity location){
        Page page = new Page(location.getPage(), location.getLimit());
        LambdaQueryWrapper<LocationEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(LocationEntity::getId, location.getId());
        IPage<LocationEntity> iPage = locationService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
