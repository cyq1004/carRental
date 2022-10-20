package com.yeqifu.bus.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 车辆表
 */
@Data
@TableName("bus_car")
public class Car {
    /**
     * 车牌号
     */
    private String carnumber;

    /**
     * 汽车类型
     */
    private String cartype;

    /**
     * 颜色
     */
    private String color;

    /**
     * 价格
     */
    private Double price;

    /**
     * 出租价格
     */
    private Double rentprice;

    /**
     * 出租押金
     */
    private Double deposit;

    /**
     * 出租状态，0：已出租，1：未出租，2：审核中
     */
    private Integer isrenting;

    /**
     * 出租描述
     */
    private String description;

    /**
     * 汽车照片
     */
    private String carimg;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss",timezone = "GMT+8")
    private Date createtime;
}