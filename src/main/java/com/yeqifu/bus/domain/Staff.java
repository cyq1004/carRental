package com.yeqifu.bus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工表
 */
@Data
@TableName("bus_staff")
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @TableField(value = "staff_name")
    private String staffName;

    /**
     * 性别，0：男，1女
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 身份证
     */
    @TableField(value = "identity")
    private String identity;

    /**
     * 家庭地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 职位
     */
    @TableField(value = "career")
    private String career;

    /**
     * 薪资
     */
    @TableField(value = "salary")
    private BigDecimal salary;

    /**
     * 入职时间
     */
    @TableField(value = "join_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date joinTime;

    /**
     * 离职时间
     */
    @TableField(value = "leave_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date leaveTime;

}
