package com.yeqifu.sys.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 日志管理入参req
 */
@Data
public class LogReq {

    /**
     * 操作名称
     */
    private String logname;

    /**
     * 操作ip
     */
    private String logip;

    /**
     * 操作事件
     */
    private String log;

    /**
     * 转换到页面上的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date logtime;

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
