package com.yeqifu.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.bus.domain.Car;
import com.yeqifu.bus.domain.Rent;
import com.yeqifu.bus.mapper.CarMapper;
import com.yeqifu.bus.mapper.RentMapper;
import com.yeqifu.bus.req.RentReq;
import com.yeqifu.bus.service.IRentService;
import com.yeqifu.bus.vo.RentVo;
import com.yeqifu.sys.constast.SysConstast;
import com.yeqifu.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenyq
 */
@Service
public class RentServiceImpl implements IRentService {

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    /**
     * 保存出租单信息
     *
     * @param rent
     */
    @Override
    @Transactional
    public void addRent(Rent rent) {
        rentMapper.addRent(rent);
        //更改车辆的出租状态
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        //设置车辆为审核状态
        car.setIsrenting(SysConstast.RENT_CAR_CHECK);
        carMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 出租单列表查询
     *
     * @param req
     * @return
     */
    @Override
    public DataGridView queryAllRent(RentReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Rent> data = rentMapper.queryAllRent(req);
        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 修改出租单
     *
     * @param rent
     */
    @Override
    public void updateRent(Rent rent) {
        rentMapper.updateRent(rent);
    }

    /**
     * 删除出租单
     *
     * @param rentid
     */
    @Override
    public void deleteRent(String rentid) {
        //更改汽车状态，将已出租的状态转换成未出租的状态
        Rent rent = rentMapper.queryRentByRentId(rentid);
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        //转换成未出租的状态
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        carMapper.updateByPrimaryKeySelective(car);
        rentMapper.deleteRent(rentid);
    }

    /**
     * 根据出租单号查询出租单信息
     *
     * @param rentid
     * @return
     */
    @Override
    public Rent queryRentByRentId(String rentid) {
        return rentMapper.queryRentByRentId(rentid);
    }

    /**
     * 修改出租单状态
     *
     * @param rent
     */
    @Override
    public void updateRentFlag(Rent rent) {
        rentMapper.updateRentFlag(rent);
    }
}
