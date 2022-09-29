package com.yeqifu.sys.req;

import lombok.Data;

/**
 * 用户模块入参req
 */
@Data
public class UserReq {

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
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    private String code;

    /**
     * 接手多个角色的id
     */
    private Long [] ids;
}
