package com.yeqifu.bus.req;

import lombok.Data;

/**
 * 加盟商模块请求req
 */
@Data
public class FranchiseeReq {

    /**
     * 加盟商名字
     */
    private String name;

    /**
     * 分页参数，当前是第一页，每页10条数据
     */
    private Integer page=1;
    private Integer limit=10;

    /**
     * 批量删除客户，存放客户ID的数组
     */
    private Integer[] ids;
}
