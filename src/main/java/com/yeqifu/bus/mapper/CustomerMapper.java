package com.yeqifu.bus.mapper;

import com.yeqifu.bus.domain.Customer;
import com.yeqifu.bus.req.CustomerReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {

    /**
     * 查询所有客户信息 分页
     *
     * @param req
     * @return
     */
    List<Customer> loadAllCustomer(@Param("req") CustomerReq req);

    /**
     * 添加客户
     *
     * @param customer
     * @return
     */
    int addCustomer(@Param("customer") Customer customer);

    /**
     * 更新客户信息
     *
     * @param customer
     * @return
     */
    int updateCustomer(@Param("customer") Customer customer);

    /**
     * 删除客户
     *
     * @param identity
     * @return
     */
    int deleteCustomer(@Param("identity") String identity);

    /**
     * 通过身份证号查询客户
     *
     * @param identity
     * @return
     */
    Customer queryCustomerByIdentity(String identity);







    int insert(Customer record);







    int updateByPrimaryKey(Customer record);


}