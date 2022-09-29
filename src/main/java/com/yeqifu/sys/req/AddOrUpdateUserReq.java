package com.yeqifu.sys.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 增加用户入参req
 */
@Data
public class AddOrUpdateUserReq {

    public static final String reg = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 用户登录账号
     */
    @NotBlank(message = "登录账号不能为空")
    private String loginname;

    /**
     * 身份证
     */
    @NotBlank(message = "身份证不能为空")
    //@Pattern(regexp = AddOrUpdateUserReq.reg, message = "身份证格式有误")
    private String identity;

    /**
     * 真实姓名
     */
    @NotBlank(message = "真实姓名不能为空")
    private String realname;

    /**
     * 性别：0女1男
     */
    @NotNull(message = "性别不能为空")
    private Integer sex;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 地位
     */
    @NotBlank(message = "地位不能为空")
    private String position;

    /**
     * 1，超级管理员,2，系统用户
     */
    private Integer type;

    /**
     * 是否可用 0:可用，1:不可用
     */
    @NotNull(message = "可用性不能为空")
    private Integer available;
}
