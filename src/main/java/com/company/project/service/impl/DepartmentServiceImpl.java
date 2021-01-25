package com.company.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

    @Resource
    private DepartmentEntity departmentEntity;

    @Resource
    private DepartmentMapper departmentMapper;

    /*
    /**
     * @return
     **/
    public JSONArray getDeptId() {

        List<DepartmentEntity> list = departmentMapper.selectList(Wrappers.<DepartmentEntity>lambdaQuery().eq(DepartmentEntity::getActive, 1));
        return JSONArray.parseArray(JSON.toJSONString(list));
    }

    @Override
    public void updateActive(DepartmentEntity department) {
        department.setActive("0");
        departmentMapper.updateById(department);
    }
}