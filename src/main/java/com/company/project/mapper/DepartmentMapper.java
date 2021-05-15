package com.company.project.mapper;

import com.company.project.entity.DepartmentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.project.entity.Place2Entity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-20 10:15:04
 */
public interface DepartmentMapper extends BaseMapper<DepartmentEntity> {

    @Select("SELECT dept_name AS deptName , dept_code AS deptCode FROM department WHERE id = #{departmentEntity.id} ")
    List<DepartmentEntity> selectTypeId(@Param("departmentEntity") DepartmentEntity departmentEntity);
	
}
