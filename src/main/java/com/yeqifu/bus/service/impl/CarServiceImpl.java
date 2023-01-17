package com.yeqifu.bus.service.impl;

import cn.afterturn.easypoi.entity.ImageEntity;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.bus.domain.Car;
import com.yeqifu.bus.mapper.CarMapper;
import com.yeqifu.bus.req.AddOrUpdateCarReq;
import com.yeqifu.bus.req.CarReq;
import com.yeqifu.bus.service.ICarService;
import com.yeqifu.bus.vo.CarVo;
import com.yeqifu.bus.vo.CarVosExportExcel;
import com.yeqifu.sys.constast.SysConstast;
import com.yeqifu.sys.utils.AppFileUtils;
import com.yeqifu.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.tools.Tool;
import java.awt.*;
import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
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

    @Override
    public void exportExcel(CarReq req) {
        try {
            byte[] data = null;

            List<Car> cars = carMapper.loadAllCar(req);
            List<CarVosExportExcel> list = new ArrayList<>();
            for (Car car : cars) {
                CarVosExportExcel carVosExportExcel = new CarVosExportExcel();
                list.add(carVosExportExcel);
                String carimg = car.getCarimg();
                carimg = carimg.replace("/", "\\");
                String fileName = "E:\\MyIDEAproject\\car\\public\\upload\\" + carimg;
                byte[] bytes = imagebyte(fileName);
                ImageEntity image = new ImageEntity();
                image.setHeight(40);
                image.setWidth(150);
                image.setData(bytes);
                image.setType(ImageEntity.Data);

                carVosExportExcel.setCarimg(image);

                list.add(carVosExportExcel);
            }

            String fileName = "C:\\Users\\aa\\Desktop\\car.xlsx";
            EasyExcel.write(fileName, CarVosExportExcel.class)
                    .sheet("car")
                    .doWrite(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] imagebyte(String path) {
        byte[] data = null;

        InputStream inputStream = null;
        try {
            // 获取图片流
            inputStream = new FileInputStream(path);

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = inputStream.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }

            data = output.toByteArray();
            output.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
