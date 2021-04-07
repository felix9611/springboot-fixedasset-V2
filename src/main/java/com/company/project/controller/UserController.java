package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.common.aop.annotation.LogAnnotation;
import com.company.project.common.exception.code.BaseResponseCode;
import com.company.project.common.utils.DataResult;
import com.company.project.entity.SysUser;
import com.company.project.entity.SysUserRole;
import com.company.project.service.HttpSessionService;
import com.company.project.service.UserRoleService;
import com.company.project.service.UserService;
import com.company.project.vo.req.UserRoleOperationReqVO;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户管理
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@RestController
@Api(tags = "組織模塊-用戶管理")
@RequestMapping("/sys")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private HttpSessionService httpSessionService;

    @PostMapping(value = "/user/login")
    @ApiOperation(value = "用戶登入接口")
    public DataResult login(@RequestBody @Valid SysUser vo, HttpServletRequest request) {
        //判断验证码
        if (!CaptchaUtil.ver(vo.getCaptcha(), request)) {
            // 清除session中的验证码
            CaptchaUtil.clear(request);
            return DataResult.fail("驗證碼錯誤 ！");
        }
        System.out.print(vo);
        return DataResult.success(userService.login(vo));
    }

    @PostMapping(value = "/user/phoneLogin")
    @ApiOperation(value = "用戶手機登入接口")
    public DataResult phoneLogin(@RequestBody @Valid SysUser vo) {



        return DataResult.success(userService.login(vo));
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用戶註冊接口")
    public DataResult register(@RequestBody @Valid SysUser vo) {
        userService.register(vo);
        return DataResult.success();
    }

    @GetMapping("/user/unLogin")
    @ApiOperation(value = "引導客戶端進行登錄")
    public DataResult unLogin() {
        return DataResult.getResult(BaseResponseCode.TOKEN_ERROR);
    }

    @PutMapping("/user")
    @ApiOperation(value = "更新用戶資料接口")
    @LogAnnotation(title = "用户管理", action = "更新用戶資料")
    @RequiresPermissions("sys:user:update")
    public DataResult updateUserInfo(@RequestBody SysUser vo) {
        if (StringUtils.isEmpty(vo.getId())) {
            return DataResult.fail("id不能為空");
        }
        System.out.print(vo);
        userService.updateUserInfo(vo);
        return DataResult.success();
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "更新用戶資料接口")
    @LogAnnotation(title = "用戶管理", action = "更新用戶資料")
    public DataResult updateUserInfoById(@RequestBody SysUser vo) {
        System.out.print(vo);
        userService.updateUserInfoMy(vo);
        return DataResult.success();
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查詢用戶詳情接口")
    @LogAnnotation(title = "用戶管理", action = "查詢用戶詳情")
    @RequiresPermissions("sys:user:detail")
    public DataResult detailInfo(@PathVariable("id") String id) {
        System.out.print(id);
        return DataResult.success(userService.getById(id));
    }

    @GetMapping("/user")
    @ApiOperation(value = "查詢用戶詳情接口")
    @LogAnnotation(title = "用戶管理", action = "查詢用戶詳情")
    public DataResult youSelfInfo() {
        String userId = httpSessionService.getCurrentUserId();
        System.out.print(userId);
        return DataResult.success(userService.getById(userId));
    }

    @PostMapping("/users")
    @ApiOperation(value = "分頁獲取用戶列表接口")
    @RequiresPermissions("sys:user:list")
    @LogAnnotation(title = "用戶管理", action = "分頁獲取用戶列表")
    public DataResult pageInfo(@RequestBody SysUser vo) {
        System.out.print(vo);
        return DataResult.success(userService.pageInfo(vo));
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用戶接口")
    @RequiresPermissions("sys:user:add")
    @LogAnnotation(title = "用戶管理", action = "新增用戶")
    public DataResult addUser(@RequestBody @Valid SysUser vo) {
        userService.addUser(vo);
        System.out.print(vo);
        return DataResult.success();
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "登出接口")
    @LogAnnotation(title = "用戶管理", action = "登出")
    public DataResult logout() {
        httpSessionService.abortUserByToken();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return DataResult.success();
    }

    @PutMapping("/user/pwd")
    @ApiOperation(value = "更新密碼接口")
    @LogAnnotation(title = "用戶管理", action = "更新密碼")
    public DataResult updatePwd(@RequestBody SysUser vo) {
        if (StringUtils.isEmpty(vo.getOldPwd()) || StringUtils.isEmpty(vo.getNewPwd())) {
            return DataResult.fail("舊密碼與新密碼不能為空");
        }
        String userId = httpSessionService.getCurrentUserId();
        vo.setId(userId);
        userService.updatePwd(vo);
        System.out.print(vo);
        return DataResult.success();
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "刪除用戶接口")
    @LogAnnotation(title = "用戶管理", action = "刪除用戶")
    @RequiresPermissions("sys:user:deleted")
    public DataResult deletedUser(@RequestBody @ApiParam(value = "用戶id集合") List<String> userIds) {
        //删除用户， 删除redis的绑定的角色跟权限
        System.out.print(userIds);
        httpSessionService.abortUserByUserIds(userIds);
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysUser::getId, userIds);
        userService.remove(queryWrapper);
        return DataResult.success();
    }

    @GetMapping("/user/roles/{userId}")
    @ApiOperation(value = "賦予角色-獲取所有角色接口")
    @LogAnnotation(title = "用戶管理", action = "賦予角色-獲取所有角色接口")
    @RequiresPermissions("sys:user:role:detail")
    public DataResult getUserOwnRole(@PathVariable("userId") String userId) {
        System.out.print(userId);
        DataResult result = DataResult.success();
        result.setData(userService.getUserOwnRole(userId));
        return result;
    }

    @PutMapping("/user/roles/{userId}")
    @ApiOperation(value = "賦予角色-獲取所有角色接口")
    @LogAnnotation(title = "用戶管理", action = "賦予角色-獲取所有角色接口")
    @RequiresPermissions("sys:user:update:role")
    public DataResult setUserOwnRole(@PathVariable("userId") String userId, @RequestBody List<String> roleIds) {
        System.out.print(roleIds);
        LambdaQueryWrapper<SysUserRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserRole::getUserId, userId);
        userRoleService.remove(queryWrapper);
        if (!CollectionUtils.isEmpty(roleIds)) {
            UserRoleOperationReqVO reqVO = new UserRoleOperationReqVO();
            reqVO.setUserId(userId);
            reqVO.setRoleIds(roleIds);
            userRoleService.addUserRoleInfo(reqVO);
        }
        //刷新权限
        httpSessionService.refreshUerId(userId);
        return DataResult.success();
    }
}
