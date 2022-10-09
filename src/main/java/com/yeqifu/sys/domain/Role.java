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
@TableName("sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "roleid", type = IdType.AUTO)
    private Integer roleid;

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 角色备注
     */
    private String roledesc;

    /**
     * 是否可用，0可用1不可用
     */
    private Integer available;
}