package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
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

import com.company.project.entity.Place2Entity;
import com.company.project.service.Place2Service;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-15 18:53:25
 */
@Api(tags = "地點管理")
@Controller
@RequestMapping("/")
public class Place2Controller {
    @Autowired
    private Place2Service place2Service;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/place2")
    public String place2() {
        return "place2/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("place2/add")
    @RequiresPermissions("place2:add")
    @ResponseBody
    public DataResult add(@RequestBody Place2Entity place2){
        place2Service.save(place2);
        System.out.print(place2);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("place2/delete")
    @RequiresPermissions("place2:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        place2Service.removeByIds(ids);
        System.out.print(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("place2/update")
    @RequiresPermissions("place2:update")
    @ResponseBody
    public DataResult update(@RequestBody Place2Entity place2){
        place2Service.updateById(place2);
        System.out.print(place2);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("place2/listByPage")
    @RequiresPermissions("place2:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody Place2Entity place2){
        Page page = new Page(place2.getPage(), place2.getLimit());
        LambdaQueryWrapper<Place2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(Place2Entity::getId, place2.getId());

        if(!StringUtils.isEmpty(place2.getPlaceName())){
            queryWrapper.like(Place2Entity::getPlaceName, place2.getPlaceName());
        }

        queryWrapper.eq(Place2Entity::getActive, "1");

        System.out.print(place2);

        IPage<Place2Entity> iPage = place2Service.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
