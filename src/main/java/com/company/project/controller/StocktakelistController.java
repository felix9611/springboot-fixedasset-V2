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

import com.company.project.entity.StocktakelistEntity;
import com.company.project.service.StocktakelistService;

import javax.annotation.Resource;


/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-25 20:33:06
 */
@Api(tags = "盤點")
@Controller
@RequestMapping("/")
public class StocktakelistController {

    @Resource private StocktakelistService stocktakelistService;

    @ApiOperation(value = "新增")
    @PostMapping("stocktakelist/add")
  //  @RequiresPermissions("stocktakelist:add")
    @ResponseBody
    public DataResult add(@RequestBody StocktakelistEntity stocktakelist){
        stocktakelistService.newActivity(stocktakelist);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("stocktakelist/delete")
  //  @RequiresPermissions("stocktakelist:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        stocktakelistService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "查詢分頁數據")
    @PostMapping("stocktakelist/listByPage")
  //  @RequiresPermissions("stocktakelist:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody StocktakelistEntity stocktakelist){
        Page page = new Page(stocktakelist.getPage(), stocktakelist.getLimit());
        LambdaQueryWrapper<StocktakelistEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(StocktakelistEntity::getId, stocktakelist.getId());

        if (!StringUtils.isEmpty(stocktakelist.getActivityName())) {
            queryWrapper.like(StocktakelistEntity::getActivityName, stocktakelist.getActivityName());
        }
        //queryWrapper.orderByAsc(StocktakelistEntity::getId);

        IPage<StocktakelistEntity> iPage = stocktakelistService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
