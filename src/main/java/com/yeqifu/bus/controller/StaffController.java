package com.yeqifu.bus.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReUtil;
import com.yeqifu.bus.domain.Customer;
import com.yeqifu.bus.domain.Staff;
import com.yeqifu.bus.req.AddOrUpdateCustomerReq;
import com.yeqifu.bus.req.AddOrUpdateStaffReq;
import com.yeqifu.bus.req.CustomerReq;
import com.yeqifu.bus.req.StaffReq;
import com.yeqifu.bus.service.IStaffService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * @Author: cyq
 * @Date: 2023/01/31 15:18
 * @Description:
 */

@Slf4j
@RestController
@RequestMapping("staff")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    /**
     * 加载员工列表返回DataGridView
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllStaff")
    public DataGridView loadAllStaff(StaffReq req) {
        return staffService.loadAllStaff(req);
    }

    /**
     * 添加
     * @param req
     * @return
     */
    @PostMapping("add")
    public ResultObj add(@Validated AddOrUpdateStaffReq req) {
        if (!ReUtil.isMatch(Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$").pattern(), req.getIdentity())) {
            return ResultObj.IDENTITY_ERROR;
        }
        try {
            Staff staff = new Staff();
            BeanUtil.copyProperties(req, staff);
            staff.setJoinTime(new Date());
            staffService.add(staff);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改
     * @param req
     * @return
     */
    @PostMapping("update")
    public ResultObj update(@Validated AddOrUpdateStaffReq req) {
        try {
            Staff staff = new Staff();
            BeanUtil.copyProperties(req, staff);
            staffService.update(staff);

            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     * @param identity
     * @return
     */
    @PostMapping("delete")
    public ResultObj delete(String identity) {
        try {
            staffService.delete(identity);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     * @param req
     * @return
     */
    @PostMapping("deleteBatch")
    public ResultObj deleteBatch(StaffReq req) {
        try {
            staffService.deleteBatch(req.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 离职
     * @param identity
     * @return
     */
    @PostMapping("updateLeave")
    public ResultObj updateLeave(String identity) {
        try {
            staffService.updateLeave(identity);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}
