package com.yeqifu.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.bus.domain.Car;
import com.yeqifu.bus.mapper.CarMapper;
import com.yeqifu.bus.req.AddOrUpdateCarReq;
import com.yeqifu.bus.req.CarReq;
import com.yeqifu.bus.service.ICarService;
import com.yeqifu.bus.vo.CarVo;
import com.yeqifu.sys.constast.SysConstast;
import com.yeqifu.sys.utils.AppFileUtils;
import com.yeqifu.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarMapper carMapper;

    /**
     * 查询所有车辆
     *
     * @param req
     * @return
     */
    @Override
    public DataGridView loadAllCar(CarReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Car> data = carMapper.loadAllCar(req);
        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 添加一个车辆
     *
     * @param car
     */
    @Override
    public void addCar(Car car) {
        carMapper.addCar(car);
    }

    /**
     * 更新一个车辆
     *
     * @param req
     */
    @Override
    public void updateCar(AddOrUpdateCarReq req) {
        carMapper.updateCar(req);
    }

    /**
     * 删除一个车辆
     *
     * @param carnumber
     */
    @Override
    public void deleteCar(String carnumber) {
        //先删除图片
        Car car = carMapper.getByCarnumber(carnumber);
        //如果不是默认图片就删除
        if (!car.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)) {
            AppFileUtils.deleteFileUsePath(car.getCarimg());
        }
        //删除数据库的数据
        carMapper.deleteCar(carnumber);
    }

    /**
     * 批量删除车辆
     *
     * @param carnumbers
     */
    @Override
    public void deleteBatchCar(String[] carnumbers) {
        for (String carnumber : carnumbers) {
            this.deleteCar(carnumber);
        }
    }

    /**
     * 根据车牌号查询
     *
     * @param carnumber
     * @return
     */
    @Override
    public Car queryCarByCarNumber(String carnumber) {
        return carMapper.getByCarnumber(carnumber);
    }

    /**
     * 出租单审核通过后修改汽车状态
     *
     * @param car
     */
    @Override
    public void updateCarCheck(Car car) {
        carMapper.updateByPrimaryKeySelective(car);
    }
}
