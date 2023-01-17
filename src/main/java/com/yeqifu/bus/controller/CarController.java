package com.yeqifu.bus.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yeqifu.bus.domain.Car;
import com.yeqifu.bus.req.AddOrUpdateCarReq;
import com.yeqifu.bus.req.CarReq;
import com.yeqifu.bus.service.ICarService;
import com.yeqifu.bus.vo.CarVo;
import com.yeqifu.sys.constast.SysConstast;
import com.yeqifu.sys.utils.AppFileUtils;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 车辆管理控制器
 */
@Slf4j
@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private ICarService carService;

    /**
     * 加载车辆列表返回DataGridView
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllCar")
    public DataGridView loadAllCar(CarReq req) {
        log.info("加载车辆列表:{}", req);
        return carService.loadAllCar(req);
    }

    /**
     * 添加车辆
     *
     * @param req
     * @return
     */
    @PostMapping("addCar")
    public ResultObj addCar(@Validated AddOrUpdateCarReq req) {
        log.info("添加车辆:{}", req);
        try {
            if (carService.queryCarByCarNumber(req.getCarnumber()) != null) {
                return new ResultObj(-1, "该车牌号的车辆已存在");
            }
            Car car = new Car();
            BeanUtil.copyProperties(req, car);
            car.setCreatetime(new Date());
            //如果不是默认图片就去掉图片的_temp的后缀
            if (!car.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)) {
                String filePath = AppFileUtils.updateFileName(car.getCarimg(), SysConstast.FILE_UPLOAD_TEMP);
                car.setCarimg(filePath);
            }
            carService.addCar(car);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改车辆
     *
     * @param req
     * @return
     */
    @PostMapping("updateCar")
    public ResultObj updateCar(@Validated AddOrUpdateCarReq req) {
        log.info("修改车辆:{}", req);
        try {
            String carimg = req.getCarimg();
            if (carimg.endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
                String filePath = AppFileUtils.updateFileName(req.getCarimg(), SysConstast.FILE_UPLOAD_TEMP);
                req.setCarimg(filePath);
                //把原来图片的删除
                Car car = carService.queryCarByCarNumber(req.getCarnumber());
                AppFileUtils.removeFileByPath(car.getCarimg());
            }
            carService.updateCar(req);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除一个车辆
     *
     * @param carnumber
     * @return
     */
    @GetMapping("deleteCar")
    public ResultObj deleteCar(@RequestParam("carnumber") String carnumber) {
        log.info("删除一个车辆:{}", carnumber);
        try {
            carService.deleteCar(carnumber);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除车辆
     *
     * @param req
     * @return
     */
    @PostMapping("deleteBatchCar")
    public ResultObj deleteBatchCar(CarReq req) {
        log.info("批量删除车辆:{}", req);
        try {
            carService.deleteBatchCar(req.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @PostMapping("exportExcel")
    public ResultObj exportExcel(CarReq req) {
        log.info("导出车辆信息:{}", req);
        carService.exportExcel(req);
        return new ResultObj(200, "导出成功");
    }
}
