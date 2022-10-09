package com.yeqifu.sys.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * 用户列表导出excelVo
 */
@Data
public class UserVosExportExcel {

    @ExcelProperty("用户id")
    @ColumnWidth(value = 15)
    private Integer userid;

    @ExcelProperty("用户登录账号")
    @ColumnWidth(value = 15)
    private String loginname;

    @ExcelProperty("身份证")
    @ColumnWidth(value = 15)
    private String identity;

    @ExcelProperty("真实姓名")
    @ColumnWidth(value = 15)
    private String realname;

    @ExcelProperty("性别：0女1男")
    @ColumnWidth(value = 15)
    private Integer sex;

    @ExcelProperty("地址")
    @ColumnWidth(value = 15)
    private String address;

    @ExcelProperty("手机号")
    @ColumnWidth(value = 15)
    private String phone;

    @ExcelProperty("地位")
    @ColumnWidth(value = 15)
    private String position;

    @ExcelProperty("用户类型；1超级管理员2系统用户")
    @ColumnWidth(value = 15)
    private Integer type;

    @ExcelProperty("是否可用 0:可用，1:不可用")
    @ColumnWidth(value = 15)
    private Integer available;

    @ExcelProperty("角色")
    @ColumnWidth(value = 15)
    private String rolename;

    @ExcelProperty("最后一次登录时间")
    @ColumnWidth(value = 15)
    private String currentTime;

}
