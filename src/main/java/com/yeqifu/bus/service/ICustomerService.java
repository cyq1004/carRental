package com.yeqifu.bus.service;

import com.yeqifu.bus.domain.Customer;
import com.yeqifu.bus.req.CustomerReq;
import com.yeqifu.bus.vo.CustomerVo;
import com.yeqifu.sys.utils.DataGridView;

import java.util.List;

public interface ICustomerService {

    /**
     * 查询所有客户
     *
     * @param req
     * @return
     */
    DataGridView loadAllCustomer(CustomerReq req);

    /**
     * 添加客户
     *
     * @param customer
     */
     void addCustomer(Customer customer);

    /**
     * 修改客户信息
     *
     * @param customer
     */
    void updateCustomer(Customer customer);

    /**
     * 删除客户
     *
     * @param identity
     */
    void deleteCustomer(String identity);

    /**
     * 批量删除客户
     *
     * @param identitys
     */
    void deleteBatchCustomer(String[] identitys);

    /**
     * 根据身份号查询客户信息
     *
     * @param identity
     * @return
     */
    Customer queryCustomerByIdentity(String identity);

    /**
     * 查询客户数据返回集合
     *
     * @param req
     * @return
     */
    List<Customer> queryAllCustomerForList(CustomerReq req);
}
