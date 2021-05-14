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

import com.company.project.entity.ActionRecordEntity;
import com.company.project.service.ActionRecordService;



/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-04-19 16:23:42
 */
@Controller
@RequestMapping("/")
public class ActionRecordController {
    @Autowired
    private ActionRecordService actionRecordService;

    @GetMapping("/index/actionRecord")
    public String actionRecord() {
        return "actionrecord/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("actionRecord/add")
    @RequiresPermissions("actionRecord:add")
    @ResponseBody
    public DataResult add(@RequestBody ActionRecordEntity actionRecord){
        actionRecordService.save(actionRecord);
        return DataResult.success();
    }

    @ApiOperation(value = "刪除")
    @PutMapping("actionRecord/delete")
    @RequiresPermissions("actionRecord:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        actionRecordService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("actionRecord/update")
    @RequiresPermissions("actionRecord:update")
    @ResponseBody
    public DataResult update(@RequestBody ActionRecordEntity actionRecord){
        actionRecordService.updateById(actionRecord);
        return DataResult.success();
    }

    @ApiOperation(value = "查詢分頁數據")
    @PostMapping("actionRecord/listByPage")
    @RequiresPermissions("actionRecord:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody ActionRecordEntity actionRecord){
        Page page = new Page(actionRecord.getPage(), actionRecord.getLimit());
        LambdaQueryWrapper<ActionRecordEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(ActionRecordEntity::getId, actionRecord.getId());
        IPage<ActionRecordEntity> iPage = actionRecordService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
