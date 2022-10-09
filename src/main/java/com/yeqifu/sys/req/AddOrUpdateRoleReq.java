package com.yeqifu.sys.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 增加角色入参req
 */
@Data
public class AddOrUpdateRoleReq {

    /**
     * 用户id
     */
    private Long roleid;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String rolename;

    /**
     * 角色备注
     */
    @NotBlank(message = "角色备注不能为空")
    private String roledesc;

    /**
     * 是否可用
     */
    @NotBlank(message = "可用性不能为空")
    private Integer available;
}
