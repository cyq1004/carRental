package com.yeqifu.sys.vo;

import lombok.Data;

/**
 *  @author cyq
 *  @description 分页查询所有用户vo
 */
@Data
public class UserVos {
    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 用户登录账号
     */
    private String loginname;

    /**
     * 身份证
     */
    private String identity;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 性别：0女1男
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 地位
     */
    private String position;

    /**
     * 用户类型；1超级管理员2系统用户
     */
    private Integer type;

    /**
     * 是否可用 0:可用，1:不可用
     */
    private Integer available;

    /**
     * 角色
     */
    private String rolename;

    /**
     * 最后一次登录时间
     */
    private String currentTime;


}

