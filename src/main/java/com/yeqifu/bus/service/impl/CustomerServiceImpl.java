package com.yeqifu.bus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.IdcardUtil;
import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.bus.domain.Customer;
import com.yeqifu.bus.mapper.CustomerMapper;
import com.yeqifu.bus.req.CustomerReq;
import com.yeqifu.bus.service.ICustomerService;
import com.yeqifu.bus.vo.CustomerVo;
import com.yeqifu.bus.vo.CustomerVosExportExcel;
import com.yeqifu.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询所有客户信息 分页
     *
     * @param req
     * @return
     */
    @Override
    public DataGridView loadAllCustomer(CustomerReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Customer> data = customerMapper.loadAllCustomer(req);
        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 添加一个客户
     *
     * @param customer
     */
    @Override
    public void addCustomer(Customer customer) {
        customerMapper.addCustomer(customer);
    }

    /**
     * 更新客户信息
     *
     * @param customer
     */
    @Override
    public void updateCustomer(Customer customer) {
        customerMapper.updateCustomer(customer);
    }

    /**
     * 删除一个客户
     *
     * @param identity
     */
    @Override
    public void deleteCustomer(String identity) {
        customerMapper.deleteCustomer(identity);
    }

    /**
     * 批量删除客户
     *
     * @param identitys
     */
    @Override
    public void deleteBatchCustomer(String[] identitys) {
        for (String identity : identitys) {
            this.deleteCustomer(identity);
        }
    }

    /**
     * 通过身份证号查询客户
     *
     * @param identity
     * @return
     */
    @Override
    public Customer queryCustomerByIdentity(String identity) {
        return customerMapper.queryCustomerByIdentity(identity);
    }

    /**
     * 查询所有客户数据不分页
     *
     * @param req
     * @return
     */
    @Override
    public List<Customer> queryAllCustomerForList(CustomerReq req) {
        return customerMapper.loadAllCustomer(req);
    }

    @Override
    public void exportExcel(CustomerReq req) {
        List<Customer> data = customerMapper.loadAllCustomer(req);
        List<CustomerVosExportExcel> list = new ArrayList<>();
        for (Customer c : data) {
            CustomerVosExportExcel customerVosExportExcel = new CustomerVosExportExcel();
            BeanUtil.copyProperties(c, customerVosExportExcel);
            customerVosExportExcel.setIdentity(DesensitizedUtil.idCardNum(customerVosExportExcel.getIdentity(), 1, 2));
            customerVosExportExcel.setPhone(DesensitizedUtil.mobilePhone(customerVosExportExcel.getPhone()));
            list.add(customerVosExportExcel);
        }

        String fileName = "C:\\Users\\aa\\Desktop\\customer.xlsx";
        EasyExcel.write(fileName, CustomerVosExportExcel.class)
                .sheet("customer")
                .doWrite(list);
    }
}
