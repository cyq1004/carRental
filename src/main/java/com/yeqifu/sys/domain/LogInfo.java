package com.yeqifu.sys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登陆日志信息
 *
 * @author chenyq
 */
@Data
public class LogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

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
}