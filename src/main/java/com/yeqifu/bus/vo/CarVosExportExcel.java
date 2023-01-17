package com.yeqifu.bus.vo;

import cn.afterturn.easypoi.entity.ImageEntity;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cyq
 * @Date: 2023/01/17 16:51
 * @Description:
 */
@Data
public class CarVosExportExcel {

    @ExcelProperty("车牌号")
    @ColumnWidth(value = 15)
    private String carnumber;

    @ExcelProperty("汽车类型")
    @ColumnWidth(value = 15)
    private String cartype;

    @ExcelProperty("颜色")
    @ColumnWidth(value = 15)
    private String color;

    @ExcelProperty("价格")
    @ColumnWidth(value = 15)
    private Double price;

    @ExcelProperty("出租价格")
    @ColumnWidth(value = 15)
    private Double rentprice;

    @ExcelProperty("出租押金")
    @ColumnWidth(value = 15)
    private Double deposit;

    @ExcelProperty("出租状态，0：已出租，1：未出租，2：审核中")
    @ColumnWidth(value = 15)
    private Integer isrenting;

    @ExcelProperty("出租描述")
    @ColumnWidth(value = 15)
    private String description;

    @ExcelProperty("汽车照片")
    @ColumnWidth(value = 15)
    private ImageEntity carimg;

    @ExcelProperty("创建时间")
    @ColumnWidth(value = 15)
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss",timezone = "GMT+8")
    private Date createtime;
}
