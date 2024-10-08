package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import com.company.project.common.utils.DataResult;

import com.company.project.entity.StocktakelistDetailEntity;
import com.company.project.service.StocktakelistDetailService;

import javax.annotation.Resource;


/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-25 21:02:34
 */
@Api(tags = "盤點詳細")
@Controller
@RequestMapping("/")
public class StocktakelistDetailController {

    @Resource
    private StocktakelistDetailService stocktakelistDetailService;




    /**
    * 跳转到页面
    */
    @GetMapping("/index/stocktakelistDetail")
    public String stocktakelistDetail() {
        return "stocktakelistdetail/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("stocktakelistDetail/add")
    @RequiresPermissions("stocktakelistDetail:add")
    @ResponseBody
    public DataResult add(@RequestBody StocktakelistDetailEntity stocktakelistDetail){

        /*LambdaQueryWrapper<AssetlistEntity> queryWrapper2 = Wrappers.lambdaQuery();
        LambdaQueryWrapper<AssetlistEntity> test = queryWrapper2.eq(AssetlistEntity::getAssetCode, stocktakelistDetail.getAssetCode());
        boolean test1 = Boolean.parseBoolean(String.valueOf(test.eq(AssetlistEntity::getPlace, stocktakelistDetail.getAssetPlace())));

        if(test1 == true){*/
            System.out.print(stocktakelistDetail);
            stocktakelistDetailService.saveDetail(stocktakelistDetail);
        //}
        return DataResult.success();
    }

 /*   @ApiOperation(value = "删除")
    @DeleteMapping("stocktakelistDetail/delete")
    @RequiresPermissions("stocktakelistDetail:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        stocktakelistDetailService.removeByIds(ids);
        return DataResult.success();
    }*/


    @ApiOperation(value = "查詢分頁數據")
    @PostMapping("stocktakelistDetail/listByPage")
    @RequiresPermissions("stocktakelistDetail:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody StocktakelistDetailEntity stocktakelistDetail){
        Page page = new Page(stocktakelistDetail.getPage(), stocktakelistDetail.getLimit());
        if (StringUtils.isEmpty(stocktakelistDetail.getListId())) {
            return DataResult.success();
        }
        Page<StocktakelistDetailEntity> iPage = stocktakelistDetailService.listByPage(page, stocktakelistDetail.getListId());
        return DataResult.success(iPage);
    }

}
