package com.yeqifu.sys.req;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 消息模块请求req
 */
@Data
public class MessageReq {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布人
     */
    private String opername;

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 将前台页面的时间转换到后端
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 接受多个id
     */
    private Long [] ids;
}
