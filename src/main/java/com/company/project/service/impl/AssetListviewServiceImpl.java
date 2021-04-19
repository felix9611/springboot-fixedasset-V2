package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.common.config.FileUploadProperties;
import com.company.project.common.exception.BusinessException;
import com.company.project.common.utils.DataResult;
import com.company.project.common.utils.DateUtils;
import com.company.project.entity.*;
import com.company.project.mapper.ActionRecordMapper;
import com.company.project.mapper.AssetPhotoMapper;
import com.company.project.mapper.AssetPlaceMapper;
import com.company.project.service.AssetPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.AssetListviewMapper;
import com.company.project.service.AssetListviewService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
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

    @Resource private FileUploadProperties fileUploadProperties;

    @Resource private AssetPhotoService assetPhotoService;

    @Resource private AssetPhotoMapper assetPhotoMapper;

    @Resource private ActionRecordMapper actionRecordMapper;

    @Resource private ActionRecordEntity actionRecordEntity;

    @Override
    public void newAsset(AssetListviewEntity vo){
        String newCode = this.getNewAssetCode();
        vo.setAssetCode(newCode);
        vo.setActive("1");
        System.out.print(vo);
        assetListviewMapper.insert(vo);

        actionRecordEntity.setActionName("INSERT");
        actionRecordEntity.setActionMethod("POST");
        actionRecordEntity.setActionFrom("資產管理");
        actionRecordEntity.setActionData("新增資料: " + vo.toString());
        actionRecordEntity.setActionSuccess("Success");
        actionRecordMapper.insert(actionRecordEntity);

        if(vo.getPlace()!= null){
            setAssetToPlace(vo.getId(), vo.getPlace());
        }
        //uploadSaveHelper(file, vo.getId(), vo.getAssetCode());
    }

    @Override
    public void updateActive(AssetListviewEntity assetListview) {
        assetListview.setActive("0");
        System.out.print(assetListview);
        this.removeAssetToPlace(assetListview.getId());
        assetListviewMapper.updateById(assetListview);

        actionRecordEntity.setActionName("DELETE");
        actionRecordEntity.setActionMethod("PUT");
        actionRecordEntity.setActionFrom("資產管理");
        actionRecordEntity.setActionData("已被設為無效: " + assetListview.toString());
        actionRecordEntity.setActionSuccess("Success");
        actionRecordMapper.insert(actionRecordEntity);
    }

    @Override
    public void updateAsset(AssetListviewEntity assetListview) {
        this.setAssetToPlace(assetListview.getId(), assetListview.getPlace());

        actionRecordEntity.setActionName("UPDATE");
        actionRecordEntity.setActionMethod("PUT");
        actionRecordEntity.setActionFrom("資產管理");
        actionRecordEntity.setActionData("更新資料: " + assetListview.toString());
        actionRecordEntity.setActionSuccess("Success");
        actionRecordMapper.insert(actionRecordEntity);

        System.out.print(assetListview);
        assetListviewMapper.updateById(assetListview);
    }

    private void setAssetToPlace(final String assetId, String placeId){
        if(placeId != null && assetId != null) {
            assetPlaceEntity.setAssetId(assetId);
            assetPlaceEntity.setPlaceId(placeId);
            System.out.print(assetPlaceEntity);
            String selectData = assetPlaceMapper.selectAssetId(assetPlaceEntity);
            if (selectData == null) {
                assetPlaceMapper.insert(assetPlaceEntity);

                actionRecordEntity.setActionName("INSERT");
                actionRecordEntity.setActionMethod("POST");
                actionRecordEntity.setActionFrom("被分配地點");
                actionRecordEntity.setActionData("新增資料: " + assetPlaceEntity.toString());
                actionRecordEntity.setActionSuccess("Success");
                actionRecordMapper.insert(actionRecordEntity);

            } else {
                assetPlaceMapper.updateAssetAtPlace(assetPlaceEntity);

                actionRecordEntity.setActionName("UPDATE");
                actionRecordEntity.setActionMethod("PUT");
                actionRecordEntity.setActionFrom("被分配地點");
                actionRecordEntity.setActionData("更新資料: " + assetPlaceEntity.toString());
                actionRecordEntity.setActionSuccess("Success");
                actionRecordMapper.insert(actionRecordEntity);
            }
        }
    }

    private void removeAssetToPlace(final String assetId){
        assetPlaceEntity.setAssetId(assetId);
        System.out.print(assetPlaceEntity);
        String selectData = assetPlaceMapper.selectAssetId(assetPlaceEntity);
        if(selectData != null) {
            assetPlaceMapper.removeRecord(assetPlaceEntity);
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

    private void uploadSaveHelper(MultipartFile file, AssetListviewEntity assetListviewEntity){
        String assetIDStr = this.assetPhotoMapper.selectAssetCode(assetListviewEntity.getAssetCode());
        if(assetIDStr == null){
            saveFile(file, assetListviewEntity);
        }
    }

    //updoad File
    private DataResult saveFile(MultipartFile file, AssetListviewEntity assetListviewEntity) {
        String createTime = DateUtils.format(new Date(), DateUtils.DATEPATTERN);
        String newPath = fileUploadProperties.getPath() + createTime + File.separator;
        File uploadDirectory = new File(newPath);
        if (uploadDirectory.exists()) {
            if (!uploadDirectory.isDirectory()) {
               // uploadDirectory.delete();
                throw new BusinessException("上傳文件失敗");
            }
        } else {
            uploadDirectory.mkdir();
        }
        try {
            String fileName = file.getOriginalFilename();
            //id与filename保持一直，删除文件
            String fileNameNew = UUID.randomUUID().toString().replace("-", "") + getFileType(fileName);
            String newFilePathName = newPath + fileNameNew;
            String url = fileUploadProperties.getUrl() + "/AssetPhoto/" + assetListviewEntity.getAssetCode() + "/" + fileNameNew;

            File outFile = new File(newFilePathName);

            FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);

            AssetPhotoEntity assetPhotoEntity = new AssetPhotoEntity();
            assetPhotoEntity.setAssetCode(assetListviewEntity.getAssetCode());
            assetPhotoEntity.setAssetId(assetListviewEntity.getId());
            assetPhotoEntity.setUrl(url);
            this.assetPhotoService.save(assetPhotoEntity);

            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("src", url);
            return DataResult.success(resultMap);
        } catch (Exception e) {
            throw new BusinessException("上傳文件失敗");
        }
    }

    private String getFileType(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }
}