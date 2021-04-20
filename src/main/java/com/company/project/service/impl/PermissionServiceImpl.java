package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.common.exception.BusinessException;
import com.company.project.common.exception.code.BaseResponseCode;
import com.company.project.entity.ActionRecordEntity;
import com.company.project.entity.SysPermission;
import com.company.project.entity.SysRolePermission;
import com.company.project.entity.SysUserRole;
import com.company.project.mapper.ActionRecordMapper;
import com.company.project.mapper.SysPermissionMapper;
import com.company.project.service.HttpSessionService;
import com.company.project.service.PermissionService;
import com.company.project.service.RolePermissionService;
import com.company.project.service.UserRoleService;
import com.company.project.vo.resp.PermissionRespNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单权限
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
@Slf4j
public class PermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements PermissionService {

    @Resource private UserRoleService userRoleService;

    @Resource private RolePermissionService rolePermissionService;

    @Resource private SysPermissionMapper sysPermissionMapper;

    @Resource private HttpSessionService httpSessionService;

    @Resource private ActionRecordMapper actionRecordMapper;

    @Resource private ActionRecordEntity actionRecordEntity;

    @Override
    public List<SysPermission> getPermission(String userId) {
        List<String> roleIds = userRoleService.getRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }
        List<Object> permissionIds = rolePermissionService.listObjs(Wrappers.<SysRolePermission>lambdaQuery().select(SysRolePermission::getPermissionId).in(SysRolePermission::getRoleId, roleIds));
        if (CollectionUtils.isEmpty(permissionIds)) {
            return null;
        }

        LambdaQueryWrapper<SysPermission> queryWrapper = Wrappers.<SysPermission>lambdaQuery().in(SysPermission::getId, permissionIds).orderByAsc(SysPermission::getOrderNum);
        return sysPermissionMapper.selectList(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleted(String permissionId) {
        //获取关联userId
        List<String> userIds = getUserIdsById(permissionId);
        SysPermission sysPermission = sysPermissionMapper.selectById(permissionId);
        if (null == sysPermission) {
            log.error("傳入 的 id:{}不合法", permissionId);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        //获取下一级
        List<SysPermission> childs = sysPermissionMapper.selectList(Wrappers.<SysPermission>lambdaQuery().eq(SysPermission::getPid, permissionId));
        if (!CollectionUtils.isEmpty(userIds)) {
            throw new BusinessException(BaseResponseCode.ROLE_PERMISSION_RELATION);
        }
        sysPermissionMapper.deleteById(permissionId);
        //删除和角色关联
        rolePermissionService.remove(Wrappers.<SysRolePermission>lambdaQuery().eq(SysRolePermission::getPermissionId, permissionId));

        if (!CollectionUtils.isEmpty(userIds)) {
            //刷新权限
            userIds.parallelStream().forEach(httpSessionService::refreshUerId);
        }

        actionRecordEntity.setActionName("DELETE");
        actionRecordEntity.setActionMethod("DELETE");
        actionRecordEntity.setActionFrom("權限管理");
        actionRecordEntity.setActionData("刪除: " + permissionId);
        actionRecordEntity.setActionSuccess("Success");
        actionRecordMapper.insert(actionRecordEntity);

    }

    @Override
    public void updatePermission(SysPermission vo) {
        sysPermissionMapper.updateById(vo);
        httpSessionService.refreshPermission(vo.getId());

        actionRecordEntity.setActionName("UPDATE");
        actionRecordEntity.setActionMethod("PUT");
        actionRecordEntity.setActionFrom("權限管理");
        actionRecordEntity.setActionData("更新權限: " + vo.toString());
        actionRecordEntity.setActionSuccess("Success");
        actionRecordMapper.insert(actionRecordEntity);
    }

    @Override
    public List<SysPermission> selectAll() {
        List<SysPermission> result = sysPermissionMapper.selectList(Wrappers.<SysPermission>lambdaQuery().orderByAsc(SysPermission::getOrderNum));
        if (!CollectionUtils.isEmpty(result)) {
            for (SysPermission sysPermission : result) {
                SysPermission parent = sysPermissionMapper.selectById(sysPermission.getPid());
                if (parent != null) {
                    sysPermission.setPidName(parent.getName());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getPermissionsByUserId(String userId) {

        List<SysPermission> list = getPermission(userId);
        Set<String> permissions = new HashSet<>();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        for (SysPermission sysPermission : list) {
            if (!StringUtils.isEmpty(sysPermission.getPerms())) {
                permissions.add(sysPermission.getPerms());
            }

        }
        return permissions;
    }

    @Override
    public List<PermissionRespNode> permissionTreeList(String userId) {
        List<SysPermission> list = getPermission(userId);
        return getTree(list, true);
    }

    private List<PermissionRespNode> getTree(List<SysPermission> all, boolean type) {

        List<PermissionRespNode> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(all)) {
            return list;
        }
        for (SysPermission sysPermission : all) {
            if ("0".equals(sysPermission.getPid())) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());

                if (type) {
                    permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(), all));
                } else {
                    permissionRespNode.setChildren(getChildAll(sysPermission.getId(), all));
                }
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    private List<PermissionRespNode> getChildAll(String id, List<SysPermission> all) {

        List<PermissionRespNode> list = new ArrayList<>();
        for (SysPermission sysPermission : all) {
            if (sysPermission.getPid().equals(id)) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                permissionRespNode.setChildren(getChildAll(sysPermission.getId(), all));
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    private List<PermissionRespNode> getChildExcBtn(String id, List<SysPermission> all) {

        List<PermissionRespNode> list = new ArrayList<>();
        for (SysPermission sysPermission : all) {
            if (sysPermission.getPid().equals(id) && sysPermission.getType() != 3) {
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(), all));
                list.add(permissionRespNode);
            }
        }
        return list;
    }

    @Override
    public List<PermissionRespNode> selectAllByTree() {

        List<SysPermission> list = selectAll();
        return getTree(list, false);
    }

    @Override
    public List<PermissionRespNode> selectAllMenuByTree(String permissionId) {

        List<SysPermission> list = selectAll();
        if (!CollectionUtils.isEmpty(list) && !StringUtils.isEmpty(permissionId)) {
            for (SysPermission sysPermission : list) {
                if (sysPermission.getId().equals(permissionId)) {
                    list.remove(sysPermission);
                    break;
                }
            }
        }
        List<PermissionRespNode> result = new ArrayList<>();
        //新增顶级目录是为了方便添加一级目录
        PermissionRespNode respNode = new PermissionRespNode();
        respNode.setId("0");
        respNode.setTitle("默认顶级菜单");
        respNode.setSpread(true);
        respNode.setChildren(getTree(list, true));
        result.add(respNode);
        return result;
    }

    @Override
    public List getUserIdsById(String id) {
        //根据权限id，获取所有角色id
        //根据权限id，获取所有角色id
        List<Object> roleIds = rolePermissionService.listObjs(Wrappers.<SysRolePermission>lambdaQuery().select(SysRolePermission::getRoleId).eq(SysRolePermission::getPermissionId, id));
        if (!CollectionUtils.isEmpty(roleIds)) {
            //根据角色id， 获取关联用户
            return userRoleService.listObjs(Wrappers.<SysUserRole>lambdaQuery().select(SysUserRole::getUserId).in(SysUserRole::getRoleId, roleIds));
        }
        return null;
    }


}
