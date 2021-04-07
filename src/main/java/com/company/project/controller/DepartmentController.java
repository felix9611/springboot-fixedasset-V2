package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.AssetType2Entity;
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

import com.company.project.entity.DepartmentEntity;
import com.company.project.service.DepartmentService;

import javax.annotation.Resource;


/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-20 10:15:04
 */
@Api(tags = "部門管理")
@Controller
@RequestMapping("/")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/department")
    public String department() {
        return "department/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("department/add")
    @RequiresPermissions("department:add")
    @ResponseBody
    public DataResult add(@RequestBody DepartmentEntity department){
        departmentService.save(department);
        System.out.print(department);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("department/delete")
    @RequiresPermissions("department:delete")
    @ResponseBody
    public DataResult delete(@RequestBody DepartmentEntity department){
        departmentService.updateActive(department);
        System.out.print(department);
        return DataResult.success();
    }
    /*
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        departmentService.removeByIds(ids);
        return DataResult.success();
    }
    */

    @ApiOperation(value = "更新")
    @PutMapping("department/update")
    @RequiresPermissions("department:update")
    @ResponseBody
    public DataResult update(@RequestBody DepartmentEntity department){
        departmentService.updateById(department);
        System.out.print(department);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("department/listByPage")
    @RequiresPermissions("department:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody DepartmentEntity department){
        Page page = new Page(department.getPage(), department.getLimit());
        LambdaQueryWrapper<DepartmentEntity> queryWrapper = Wrappers.lambdaQuery();

        if(!StringUtils.isEmpty(department.getDeptName())){
            queryWrapper.like(DepartmentEntity::getDeptName, department.getDeptName());
        }

        queryWrapper.eq(DepartmentEntity::getActive, "1");

        System.out.print(department);
        //查询条件示例
        //queryWrapper.eq(DepartmentEntity::getId, department.getId());
        IPage<DepartmentEntity> iPage = departmentService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
