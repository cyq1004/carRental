package com.yeqifu.bus.req;

import lombok.Data;

/**
 * 汽车管理模块请求req
 */
@Data
public class CarReq {

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
     * 出租状态，0：已出租，1：未出租，2：审核中
     */
    private Integer isrenting;

    /**
     * 出租描述
     */
    private String description;

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    //接受多个id
    private String [] ids;

}
