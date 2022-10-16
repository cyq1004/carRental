package com.yeqifu.bus.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 添加或更新汽车信息请求req
 */
@Data
public class AddOrUpdateCarReq {

    /**
     * 车牌号
     */
    @NotBlank(message = "车牌号不能为空")
    private String carnumber;

    /**
     * 汽车类型
     */
    @NotBlank(message = "汽车类型不能为空")
    private String cartype;

    /**
     * 颜色
     */
    @NotBlank(message = "颜色不能为空")
    private String color;

    /**
     * 价格
     */
    @NotNull(message = "汽车价格不能为空")
    private Double price;

    /**
     * 出租价格
     */
    @NotNull(message = "出租价格不能为空")
    private Double rentprice;

    /**
     * 出租押金
     */
    @NotNull(message = "出租押金不能为空")
    private Double deposit;

    /**
     * 出租状态，0：已出租，1：未出租，2：审核中
     */
    @NotNull(message = "出租状态不能为空")
    private Integer isrenting;

    /**
     * 出租描述
     */
    @NotBlank(message = "出租描述不能为空")
    private String description;

    /**
     * 汽车照片
     */
    @NotBlank(message = "汽车照片不能为空")
    private String carimg;
}
