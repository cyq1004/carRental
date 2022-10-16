package com.yeqifu.bus.mapper;

import com.yeqifu.bus.domain.Car;
import com.yeqifu.bus.req.AddOrUpdateCarReq;
import com.yeqifu.bus.req.CarReq;
import com.yeqifu.bus.vo.CarVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {
    /**
     * 查询所有车辆
     *
     * @param req
     * @return
     */
    List<Car> loadAllCar(@Param("req") CarReq req);

    /**
     * 添加车辆
     *
     * @param car
     * @return
     */
    int addCar(@Param("car") Car car);

    /**
     * 更新车辆信息
     *
     * @param req
     * @return
     */
    int updateCar(@Param("req") AddOrUpdateCarReq req);


    /**
     * 根据车牌号获取信息
     *
     * @param carnumber
     * @return
     */
    Car getByCarnumber(@Param("carnumber") String carnumber);

    /**
     * 根据车牌号删除车辆信息
     *
     * @param carnumber
     * @return
     */
    int deleteCar(@Param("carnumber") String carnumber);


    /**
     * 出租单审核通过后修改汽车状态
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Car record);


}