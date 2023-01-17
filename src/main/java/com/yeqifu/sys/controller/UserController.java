package com.yeqifu.sys.controller;

import cn.hutool.core.util.ReUtil;
import com.yeqifu.sys.domain.User;
import com.yeqifu.sys.req.AddOrUpdateUserReq;
import com.yeqifu.sys.req.UpdatePasswordReq;
import com.yeqifu.sys.req.UserReq;
import com.yeqifu.sys.req.UserRoleReq;
import com.yeqifu.sys.service.IUserService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.ResultObj;
import com.yeqifu.sys.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 * 用户管理控制器
 *
 * @author cyq
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户列表
     * 加载用户列表返回DataGridView
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllUser")
    public DataGridView loadAllUser(UserReq req) {
        log.info("用户列表:{}", req);
        return userService.queryAllUser(req);
    }

    /**
     * 添加用户
     *
     * @param req
     * @return
     */
    @PostMapping("addUser")
    public ResultObj addUser(@Validated AddOrUpdateUserReq req) {
        log.info("添加用户:{}", req);
        try {
            if (!ReUtil.isMatch(Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$").pattern(), req.getIdentity())) {
                return ResultObj.IDENTITY_ERROR;
            }
            if (userService.getUserByPhone(req.getPhone()) != null) {
                return ResultObj.ADD_PHONE_ERROR;
            }
            if (userService.getUserByLoginName(req.getLoginname()) != null) {
                return ResultObj.ADD_LoginName_ERROR;
            }
            userService.addUser(req);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改用户
     *
     * @param req
     * @return
     */
    @PostMapping("updateUser")
    public ResultObj updateUser(@Validated AddOrUpdateUserReq req) {
        log.info("修改用户:{}", req);
        try {
            userService.updateUser(req);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户
     *
     * @param userid
     * @return
     */
    @GetMapping("deleteUser")
    public ResultObj deleteUser(@RequestParam("userid") Long userid) {
        log.info("删除用户:{}", userid);
        try {
            userService.deleteUser(userid);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除用户
     *
     * @param req
     * @return
     */
    @RequestMapping("deleteBatchUser")
    public ResultObj deleteBatchUser(UserReq req) {
        log.info("批量删除用户:{}", req);
        try {
            userService.deleteBatchUser(req.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 重置用户密码
     *
     * @param userid
     * @return
     */
    @GetMapping("resetUserPwd")
    public ResultObj resetUserPwd(Long userid) {
        log.info("重置用户密码:{}", userid);
        try {
            userService.resetUserPwd(userid);
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 保存用户和角色的关系
     *
     * @param req
     * @return
     */
    @PostMapping("saveUserRole")
    public ResultObj saveUserRole(UserRoleReq req) {
        log.info("保存用户和角色的关系:{}", req);
        try {
            userService.saveUserRole(req);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

    /**
     * 加载用户的分配角色
     *
     * @param userid
     * @return
     */
    @GetMapping("initUserRole")
    public DataGridView initUserRole(Long userid) {
        log.info("加载用户的分配角色:{}", userid);
        return this.userService.queryUserRole(userid);
    }


    /**
     * 修改用户的密码
     *
     * @param req
     * @return
     */
    @PostMapping("changePassword")
    public ResultObj changePassword(UpdatePasswordReq req) {
        log.info("修改用户的密码:{}", req);
        String oldPassword = req.getOldPassword();
        String newPassword = req.getNewPassword();
        String confirmPassword = req.getConfirmPassword();
        if (newPassword.equals(confirmPassword)) {
            String oldPasswordEncryption = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
            //1.先通过session获得当前用户
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            if (oldPasswordEncryption.equals(user.getPwd())) {
                // 对用户输入的新密码进行加密
                String newPasswordEncryption = DigestUtils.md5DigestAsHex(newPassword.getBytes());
                User userAdd = new User();
                userAdd.setUserid(user.getUserid());
                userAdd.setPwd(newPasswordEncryption);
                userService.changePassword(userAdd);
                return new ResultObj(200, "修改密码成功，请重新登陆！");
            } else {
                return new ResultObj(500, "您输入的原密码错误！");
            }
        } else {
            return new ResultObj(500, "您输入的两次新密码不一致！");
        }
    }

    /**
     * 导出用户管理列表
     * @param req
     */
    @PostMapping("exportExcel")
    public ResultObj exportExcel(UserReq req) {
        log.info("导出用户管理列表：{}", req);
        userService.exportExcel(req);
        return new ResultObj(200, "导出成功");
    }
}
