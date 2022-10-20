package com.yeqifu.bus.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 汽车出租管理请求req
 */
@Data
public class RentReq {

    /**
     * 出租单号
     */
    private String rentid;

    /**
     * 出租价格
     */
    private Double price;

    /**
     * 起租时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //前台获取的时间进行格式化插入到数据库中
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //后台数据库查询出来的时间转换到前台进行显示
    private Date begindate;

    /**
     * 还车时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date returndate;

    /**
     * 归还状态
     */
    private Integer rentflag;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 车牌号
     */
    private String carnumber;

    /**
     * 客户名称
     */
    private String opername;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //后台数据库查询出来的时间转换到前台进行显示
    private Date createtime;


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
}
