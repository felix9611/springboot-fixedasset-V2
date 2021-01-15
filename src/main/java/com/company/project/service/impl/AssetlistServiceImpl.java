package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.AssetlistMapper;
import com.company.project.entity.AssetlistEntity;
import com.company.project.service.AssetlistService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


@Transactional
//@MapperScan("com.company.project.entity")
@Service("assetlistService")
@Slf4j
public class AssetlistServiceImpl extends ServiceImpl<AssetlistMapper, AssetlistEntity> implements AssetlistService {

    @Resource
    private AssetlistMapper assetlistMapper;

    @Resource
    private AssetlistEntity ate1;

    @Override
    public boolean delByID(AssetlistEntity vo){
        vo.setActive("0");
        assetlistMapper.updateById(vo);
        return true;
    }

    @Override
    public boolean updateById(AssetlistEntity vo){
        vo.setActive("1");
        assetlistMapper.updateById(vo);
        return true;
        //return false;
    }

    @Override
    public void addAsset(AssetlistEntity vo) {
        //String newCode;
        //String Code = this.getNewAssetCode();
        //vo.setAssetCode(Code);
        vo.setActive("1");
        assetlistMapper.insert(vo);

    }

    public String getNewAssetCode() {
        LambdaQueryWrapper<AssetlistEntity> lqw = Wrappers.lambdaQuery();
        //lqw.select(AssetlistEntity::getAssetCode);

        List<Object> assetcodes = assetlistMapper.selectObjs(lqw);
        AtomicReference<Integer> maxAssetcodes = new AtomicReference<>(0);

        assetcodes.forEach(o -> {
            String code = String.valueOf(o);
            if (code.length() >= 6) {
                Integer one = Integer.parseInt(code.substring(code.length() - 5));
                if (one > maxAssetcodes.get()) {
                    maxAssetcodes.set(one);
                }
            }

        });
        return padRight(maxAssetcodes.get() + 1, 6, "0");
    }

    /**
     *
     * @param len
     * @param alexi
     * @param oriStr
     * @return
     */
    public static String padRight(int oriStr, int len, String alexi) {
        StringBuilder str = new StringBuilder();
        int strlen = String.valueOf(oriStr).length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str.append(alexi);
            }
        }
        str.append(oriStr);
        return str.toString();
    }




}