package com.yeqifu.sys.req;

import lombok.Data;

/**
 * 菜单模块入参req
 */
@Data
public class MenuReq {

    private Integer id;

    private String title;

    private Integer available;

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
}
