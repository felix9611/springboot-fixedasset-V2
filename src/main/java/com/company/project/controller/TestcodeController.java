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

import com.company.project.entity.TestcodeEntity;
import com.company.project.service.TestcodeService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-01-17 17:06:23
 */
@Controller
@RequestMapping("/")
public class TestcodeController {
    @Autowired
    private TestcodeService testcodeService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/testcode")
    public String testcode() {
        return "testcode/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("testcode/add")
    @RequiresPermissions("testcode:add")
    @ResponseBody
    public DataResult add(@RequestBody TestcodeEntity testcode){
        testcodeService.saveTest(testcode);
        System.out.print(testcode);
        return DataResult.success(testcode);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("testcode/delete")
    @RequiresPermissions("testcode:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        System.out.print(ids);
        testcodeService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("testcode/update")
    @RequiresPermissions("testcode:update")
    @ResponseBody
    public DataResult update(@RequestBody TestcodeEntity testcode){
        System.out.print(testcode);
        testcodeService.updateById(testcode);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("testcode/listByPage")
    @RequiresPermissions("testcode:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody TestcodeEntity testcode){
        Page page = new Page(testcode.getPage(), testcode.getLimit());
        LambdaQueryWrapper<TestcodeEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(TestcodeEntity::getId, testcode.getId());
        IPage<TestcodeEntity> iPage = testcodeService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
