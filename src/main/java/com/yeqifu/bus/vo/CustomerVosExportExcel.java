package com.yeqifu.bus.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cyq
 * @Date: 2023/01/17 16:12
 * @Description:
 */
@Data
public class CustomerVosExportExcel {

    @ExcelProperty("身份证")
    @ColumnWidth(value = 15)
    private String identity;

    @ExcelProperty("姓名")
    @ColumnWidth(value = 15)
    private String custname;

    @ExcelProperty("性别，0：男，1女")
    @ColumnWidth(value = 15)
    private Integer sex;

    @ExcelProperty("地址")
    @ColumnWidth(value = 15)
    private String address;

    @ExcelProperty("电话")
    @ColumnWidth(value = 15)
    private String phone;

    @ExcelProperty("职位")
    @ColumnWidth(value = 15)
    private String career;

    @ExcelProperty("创建时间")
    @ColumnWidth(value = 15)
    private Date createtime;
}
