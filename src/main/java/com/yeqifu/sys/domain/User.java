package com.yeqifu.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *  @author cyq
 *  @description 用户表
 */
@Data
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "userid", type = IdType.AUTO)
    private Long userid;

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
     * 密码
     */
    private String pwd;

    /**
     * 地位
     */
    private String position;

    /**
     * 用户类型；1超级管理员2系统用户
     */
    private Integer type;

    /**
     * 是否可用，0可用1不可用
     */
    private Integer available;

}