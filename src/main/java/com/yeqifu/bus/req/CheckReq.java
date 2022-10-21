package com.yeqifu.bus.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 检查单管理模块请求req
 */
@Data
public class CheckReq {

    /**
     * 检查单id
     */
    private String checkid;

    /**
     * 检查时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date checkdate;

    /**
     * 问题描述
     */
    private String checkdesc;

    /**
     * 存在问题
     */
    private String problem;

    /**
     * 赔付金额
     */
    private Double paymoney;

    /**
     * 客户名称
     */
    private String opername;

    /**
     * 出租单id
     */
    private String rentid;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    //接受多个id
    private String [] ids;

    /**
     * 扩展表单参数  将前台时间提交到后台
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
