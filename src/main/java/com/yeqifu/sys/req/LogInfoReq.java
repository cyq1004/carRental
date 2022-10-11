package com.yeqifu.sys.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 登录日志入参req
 */
@Data
public class LogInfoReq {

    /**
     * 登录名称
     */
    private String loginname;

    /**
     * 登录ip
     */
    private String loginip;

    /**
     * 转换到页面上的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date logintime;

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 扩展表单参数  将前台时间提交到后台
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 接受多个id,用于批量删除
     */
    private Long [] ids;
}
