package com.yeqifu.bus.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 添加或更新客户请求req
 */
@Data
public class AddOrUpdateCustomerReq {

    /**
     * 身份证
     */
    @NotBlank(message = "身份证不能为空")
    private String identity;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String custname;

    /**
     * 性别，0：男，1女
     */
    @NotNull(message = "性别不能为空")
    private Integer sex;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空")
    private String phone;

    /**
     * 职位
     */
    @NotBlank(message = "职位不能为空")
    private String career;
}
