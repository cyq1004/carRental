package com.yeqifu.bus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 加盟商表
 */
@Data
@TableName("bus_franchisee")
public class Franchisee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 加盟商名字
     */
    private String name;

    /**
     * 加盟商电话
     */
    private String phone;
}
