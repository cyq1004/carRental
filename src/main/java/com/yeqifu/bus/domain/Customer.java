package com.yeqifu.bus.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户表
 */
@Data
@TableName("bus_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 身份证
     */
    private String identity;

    /**
     * 姓名
     */
    private String custname;

    /**
     * 性别，0：男，1女
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 职位
     */
    private String career;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
}