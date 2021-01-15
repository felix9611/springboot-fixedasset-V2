package com.company.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.project.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户角色 Mapper
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Mapper
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}