package com.yeqifu.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yeqifu.sys.constast.SysConstast;
import com.yeqifu.sys.domain.User;
import com.yeqifu.sys.req.RegisterReq;
import com.yeqifu.sys.service.IUserService;
import com.yeqifu.sys.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户注册控制器
 *
 * @author cyq
 */
@Slf4j
@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private IUserService userService;

    /**
     * 注册方法
     */
    @RequestMapping("register")
    public String register(RegisterReq req, Model model) {
        log.info("前端用户注册:{}", req);
        String code = WebUtils.getHttpSession().getAttribute("code").toString();
        if (req.getCode().equals(code)) {
            if (req.getPwd().equals(req.getConfirmPwd())) {
                User existPhoneUser = userService.getUserByPhone(req.getPhone());
                User existLoginNameUser = userService.getUserByLoginName(req.getLoginname());
                if (existPhoneUser == null && existLoginNameUser == null) {
                    User user = new User();
                    BeanUtil.copyProperties(req, user);
                    String newPasswordEncryption = DigestUtils.md5DigestAsHex(user.getPwd().getBytes());
                    user.setPwd(newPasswordEncryption);
                    userService.saveUser(user);
                    model.addAttribute("success", SysConstast.REGISTER_SUCCESS);
                } else {
                    model.addAttribute("error", "该手机号或者账号已被注册");
                }
            } else {
                model.addAttribute("error", "两次密码输入不一致");
            }
        } else {
            model.addAttribute("error", SysConstast.USER_LOGIN_CODE_ERROR_MSG);
        }
        return "system/main/login";
    }

}
