package com.yeqifu.bus.service;

import com.yeqifu.bus.domain.Car;
import com.yeqifu.bus.req.AddOrUpdateCarReq;
import com.yeqifu.bus.req.CarReq;
import com.yeqifu.bus.vo.CarVo;
import com.yeqifu.sys.utils.DataGridView;

public interface ICarService {

    /**
     * 查询所有车辆
     *
     * @param req
     * @return
     */
    DataGridView loadAllCar(CarReq req);

    /**
     * 添加车辆
     *
     * @param req
     */
    void addCar(Car car);

    /**
     * 修改车辆
     *
     * @param req
     */
    void updateCar(AddOrUpdateCarReq req);

    /**
     * 根据车牌号删除车辆
     *
     * @param carnumber
     */
    void deleteCar(String carnumber);

    /**
     * 批量删除车辆
     *
     * @param carnumbers
     */
    void deleteBatchCar(String[] carnumbers);

    /**
     * 根据车牌号查询
     *
     * @param carnumber
     * @return
     */
    Car queryCarByCarNumber(String carnumber);

    /**
     * 出租单审核通过后修改汽车状态
     *
     * @param car
     */
    void updateCarCheck(Car car);

    /**
     * 导出车辆信息
     *
     * @param req
     */
    void exportExcel(CarReq req);
}
