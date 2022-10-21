package com.yeqifu.bus.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 汽车出租表
 */
@Data
@TableName("bus_rent")
public class Rent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出租单号
     */
    @TableId
    private String rentid;

    /**
     * 出租价格
     */
    @TableField("price")
    private Double price;

    /**
     * 起租时间
     */
    @TableField("begindate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //前台获取的时间进行格式化插入到数据库中
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //后台数据库查询出来的时间转换到前台进行显示
    private Date begindate;

    /**
     * 还车时间
     */
    @TableField("returndate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date returndate;

    /**
     * 归还状态
     */
    @TableField("rentflag")
    private Integer rentflag;

    /**
     * 身份证号
     */
    @TableField("identity")
    private String identity;

    /**
     * 车牌号
     */
    @TableField("carnumber")
    private String carnumber;

    /**
     * 客户名称
     */
    @TableField("opername")
    private String opername;

    /**
     * 创建时间
     */
    @TableField("createtime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //后台数据库查询出来的时间转换到前台进行显示
    private Date createtime;
}