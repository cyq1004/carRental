package com.yeqifu.sys.req;

import lombok.Data;

/**
 * 保存用户和角色关系请求req
 */
@Data
public class UserRoleReq {

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 角色id集合
     */
    private Integer roleIds[];
}
