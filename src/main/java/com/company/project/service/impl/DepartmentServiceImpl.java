package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.entity.ActionRecordEntity;
import com.company.project.mapper.ActionRecordMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.DepartmentMapper;
import com.company.project.entity.DepartmentEntity;
import com.company.project.service.DepartmentService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
@Service("departmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, DepartmentEntity> implements DepartmentService {

    @Resource private DepartmentEntity departmentEntity;

    @Resource private DepartmentMapper departmentMapper;

    @Resource private ActionRecordMapper actionRecordMapper;

    @Resource private ActionRecordEntity actionRecordEntity;

    /**
     * @return
     **/
    public JSONArray getDeptId() {

        List<DepartmentEntity> list = departmentMapper.selectList(Wrappers.<DepartmentEntity>lambdaQuery().eq(DepartmentEntity::getActive, 1));
        return JSONArray.parseArray(JSON.toJSONString(list));
    }

    @Override
    public boolean save(DepartmentEntity entity) {

        int set = departmentMapper.insert(entity);

        if(set == 1){
            actionRecordEntity.setActionData("新增資料: " + entity.toString());
            actionRecordEntity.setActionSuccess("Success");
        } else {
            actionRecordEntity.setActionData("(失敗)新增資料: " + entity.toString());
            actionRecordEntity.setActionSuccess("Failure");
        }

        actionRecordEntity.setActionName("INSERT");
        actionRecordEntity.setActionMethod("POST");
        actionRecordEntity.setActionFrom("部門管理");
        actionRecordMapper.insert(actionRecordEntity);

        return true;
    }

    @Override
    public boolean updateById(DepartmentEntity entity) {

        int set = departmentMapper.updateById(entity);


        if(set == 1){
            actionRecordEntity.setActionData("更新資料: " + entity.toString());
            actionRecordEntity.setActionSuccess("Success");
        } else {
            actionRecordEntity.setActionData("(失敗)更新資料: " + entity.toString());
            actionRecordEntity.setActionSuccess("Failure");
        }

        actionRecordEntity.setActionName("UPDATE");
        actionRecordEntity.setActionMethod("PUT");
        actionRecordEntity.setActionFrom("部門管理");
        actionRecordMapper.insert(actionRecordEntity);
        return true;
    }

    @Override
    public void updateActive(DepartmentEntity department) {
        department.setActive("0");
        int set = departmentMapper.updateById(department);

        if(set == 1){
            actionRecordEntity.setActionData("已被設為無效: " + department.toString());
            actionRecordEntity.setActionSuccess("Success");
        } else {
            actionRecordEntity.setActionData("(失敗)已被設為無效: " + department.toString());
            actionRecordEntity.setActionSuccess("Failure");
        }

        actionRecordEntity.setActionName("DELETE");
        actionRecordEntity.setActionMethod("PUT");
        actionRecordEntity.setActionFrom("部門管理");
        actionRecordMapper.insert(actionRecordEntity);
    }

    @Override
    public  List<DepartmentEntity> selectNameAndCode(DepartmentEntity departmentEntity){
        return departmentMapper.selectTypeId(departmentEntity);
    }
}