package com.yeqifu.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.bus.domain.Car;
import com.yeqifu.bus.domain.Check;
import com.yeqifu.bus.domain.Customer;
import com.yeqifu.bus.domain.Rent;
import com.yeqifu.bus.mapper.CarMapper;
import com.yeqifu.bus.mapper.CheckMapper;
import com.yeqifu.bus.mapper.CustomerMapper;
import com.yeqifu.bus.mapper.RentMapper;
import com.yeqifu.bus.req.CheckReq;
import com.yeqifu.bus.service.ICheckService;
import com.yeqifu.bus.vo.CheckVo;
import com.yeqifu.sys.constast.SysConstast;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements ICheckService {

    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    /**
     * 根据出租单号加载检测单的表单数据
     *
     * @param rentid
     * @return
     */
    @Override
    public Map<String, Object> initCheckFormData(String rentid) {
        //查询出租单
        Rent rent = rentMapper.queryRentByRentId(rentid);
        //查询客户
        Customer customer = customerMapper.queryCustomerByIdentity(rent.getIdentity());
        //查询车辆
        Car car = carMapper.getByCarnumber(rent.getCarnumber());
        //组装check
        Check check = new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        check.setOpername(rent.getOpername());

        Map<String, Object> map = new HashMap<>();
        map.put("rent", rent);
        map.put("customer", customer);
        map.put("car", car);
        map.put("check", check);
        return map;
    }

    /**
     * 保存检查单数据
     *
     * @param check
     */
    @Override
    public void addCheck(Check check) {
        checkMapper.addCheck(check);
        //更改出租单的状态
        Rent rent = rentMapper.queryRentByRentId(check.getRentid());
        //更改为已归还
        rent.setRentflag(SysConstast.RENT_BACK_TRUE);
        rentMapper.updateRentFlag(rent);
        //更改汽车的状态
        Car car = carMapper.getByCarnumber(rent.getCarnumber());
        //更改汽车状态为未出租
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        carMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 查询所有检查单
     *
     * @param req
     * @return
     */
    @Override
    public DataGridView loadAllCheck(CheckReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Check> data = checkMapper.loadAllCheck(req);
        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 批量删除检查单
     *
     * @param ids
     */
    @Override
    public void deleteBatchCheck(String[] ids) {
        for (String id : ids) {
            checkMapper.deleteCheck(id);
        }
    }

    /**
     * 删除检查单
     *
     * @param checkid
     */
    @Override
    public void deleteCheck(String checkid) {
        checkMapper.deleteCheck(checkid);
    }

    /**
     * 更新检查单
     *
     * @param check
     */
    @Override
    public void updateCheck(Check check) {
        checkMapper.updateCheck(check);
    }
}