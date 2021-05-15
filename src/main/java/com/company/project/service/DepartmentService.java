package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.DepartmentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-20 10:15:04
 */
@Service
public interface DepartmentService extends IService<DepartmentEntity> {

    void updateActive(DepartmentEntity department);

    List<DepartmentEntity> selectNameAndCode(DepartmentEntity departmentEntity);
}

