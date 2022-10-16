package com.yeqifu.bus.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReUtil;
import com.yeqifu.bus.domain.Customer;
import com.yeqifu.bus.req.AddOrUpdateCustomerReq;
import com.yeqifu.bus.req.CustomerReq;
import com.yeqifu.bus.service.ICustomerService;
import com.yeqifu.bus.vo.CustomerVo;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * 客户管理的控制器
 */
@Slf4j
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    /**
     * 加载客户列表返回DataGridView
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllCustomer")
    public DataGridView loadAllCustomer(CustomerReq req) {
        return customerService.loadAllCustomer(req);
    }

    /**
     * 添加客户
     *
     * @param req
     * @return
     */
    @PostMapping("addCustomer")
    public ResultObj addCustomer(@Validated AddOrUpdateCustomerReq req) {
        if (!ReUtil.isMatch(Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$").pattern(), req.getIdentity())) {
            return ResultObj.IDENTITY_ERROR;
        }
        if (customerService.queryCustomerByIdentity(req.getIdentity()) != null) {
            return new ResultObj(-1, "拥有当前身份证的客户已被注册");
        }
        try {
            Customer customer = new Customer();
            BeanUtil.copyProperties(req, customer);
            customer.setCreatetime(new Date());
            customerService.addCustomer(customer);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改客户信息
     *
     * @param req
     * @return
     */
    @PostMapping("updateCustomer")
    public ResultObj updateCustomer(@Validated AddOrUpdateCustomerReq req) {
        try {
            Customer customer = new Customer();
            BeanUtil.copyProperties(req, customer);
            customerService.updateCustomer(customer);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除客户
     *
     * @param identity
     * @return
     */
    @PostMapping("deleteCustomer")
    public ResultObj deleteCustomer(String identity) {
        try {
            customerService.deleteCustomer(identity);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除客户
     *
     * @param req
     * @return
     */
    @PostMapping("deleteBatchCustomer")
    public ResultObj deleteBatchCustomer(CustomerReq req) {
        try {
            customerService.deleteBatchCustomer(req.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}
