package com.yeqifu.bus.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 添加或更新加盟商请求req
 */
@Data
public class AddOrUpdateFranchiseeReq {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 加盟商名字
     */
    private String name;

    /**
     * 加盟商电话
     */
    private String phone;
}
