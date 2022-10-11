package com.yeqifu.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  @author cyq
 *  @description 菜单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父ID
     */
    private Integer pid;

    /**
     * 标题
     */
    private String title;

    /**
     * 链接
     */
    private String href;

    /**
     * 是否展开，0展开1不展开
     */
    private Integer spread;

    /**
     *
     */
    private String target;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否可用，0可用1不可用
     */
    private Integer available;
}