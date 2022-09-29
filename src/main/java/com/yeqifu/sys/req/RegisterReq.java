package com.yeqifu.sys.req;

import lombok.Data;

/**
 * 用户注册请求
 */
@Data
public class RegisterReq {

    /**
     * 用户名
     */
    private String loginname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 确认密码
     */
    private String confirmPwd;

    /**
     * 验证码
     */
    private String code;
}
