package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.AssetPlaceEntity;
import com.company.project.mapper.AssetPlaceMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.AssetListviewMapper;
import com.company.project.entity.AssetListviewEntity;
import com.company.project.service.AssetListviewService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Transactional
@Service("assetListviewService")
@Slf4j
public class AssetListviewServiceImpl extends ServiceImpl<AssetListviewMapper, AssetListviewEntity> implements AssetListviewService {

    @Resource
    private AssetListviewEntity assetListviewEntity;

    @Resource
    private AssetListviewMapper assetListviewMapper;

    @Resource private AssetPlaceEntity assetPlaceEntity;

    @Resource private AssetPlaceMapper assetPlaceMapper;


    @Override
    public void newAsset(AssetListviewEntity vo){
        String newCode = this.getNewAssetCode();
        vo.setAssetCode(newCode);
        vo.setActive("1");
        assetListviewMapper.insert(vo);
        //return true;
    }

    @Override
    public void updateActive(AssetListviewEntity assetListview) {
        assetListview.setActive("0");
        assetListviewMapper.updateById(assetListview);
    }

    @Override
    public void updateAsset(AssetListviewEntity assetListview) {
        this.setAssetToPlace(assetListview.getId(), assetListview.getPlace());
        assetListviewMapper.updateById(assetListview);
    }

    private void setAssetToPlace(final String assetId, String placeId){
        assetPlaceEntity.setAssetId(assetId);
        assetPlaceEntity.setPlaceId(placeId);
        String selectData = assetPlaceMapper.selectAssetId(assetPlaceEntity);
        if(selectData == null){
            assetPlaceMapper.insert(assetPlaceEntity);
        }else{
            assetPlaceMapper.updateAssetAtPlace(assetPlaceEntity);
        }

    }

    public String getNewAssetCode() {
        LambdaQueryWrapper<AssetListviewEntity> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.select(AssetListviewEntity::getAssetCode);

        List<Object> assetcodes = assetListviewMapper.selectObjs(lambdaQueryWrapper);
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


    /**
     * @return
     **/
    public JSONArray getAssetCodeName() {

        List<AssetListviewEntity> list = assetListviewMapper.selectList(Wrappers.<AssetListviewEntity>lambdaQuery().eq(AssetListviewEntity::getActive, "1"));
        return JSONArray.parseArray(JSON.toJSONString(list));
    }


}