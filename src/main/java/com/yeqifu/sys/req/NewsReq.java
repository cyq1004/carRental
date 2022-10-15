package com.yeqifu.sys.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 公告模块入参req
 */
@Data
public class NewsReq {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;

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
