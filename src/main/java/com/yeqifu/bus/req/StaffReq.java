package com.yeqifu.bus.req;

import lombok.Data;

/**
 * @Author: cyq
 * @Date: 2023/01/31 15:42
 * @Description:
 */
@Data
public class StaffReq {

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 性别，0：男，1女
     */
    private Integer sex;

    /**
     * 身份证
     */
    private String identity;

    /**
     * 家庭地址
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
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    //接受多个id
    private String [] ids;

}
