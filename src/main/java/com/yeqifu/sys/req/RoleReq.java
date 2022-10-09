package com.yeqifu.sys.req;

import lombok.Data;

/**
 * 角色模块入参req
 */
@Data
public class RoleReq {

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 角色备注
     */
    private String roledesc;

    /**
     * 是否可用
     */
    private Integer available;

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
