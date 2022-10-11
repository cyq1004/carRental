package com.yeqifu.sys.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 登陆日志信息
 *
 * @author chenyq
 */
@Data
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;


    /**
     * 操作名称
     */
    private String logname;


    /**
     * 操作IP
     */
    private String logip;


    /**
     * 操作事件
     */
    private String log;


    /**
     * 操作时间
     */
    private String logtime;
}
