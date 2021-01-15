package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.SysUserRole;
import com.company.project.vo.req.UserRoleOperationReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色 服务类
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Repository
public interface UserRoleService  extends IService<SysUserRole> {

    /**
     * 根据userId获取绑定的角色id
     * @param userId userId
     * @return List
     */
    List<String> getRoleIdsByUserId(String userId);

    /**
     * 用户绑定角色
     * @param vo vo
     */
    void addUserRoleInfo(UserRoleOperationReqVO vo);
}
