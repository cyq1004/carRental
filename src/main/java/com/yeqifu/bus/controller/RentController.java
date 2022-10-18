package com.yeqifu.bus.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yeqifu.bus.domain.Car;
import com.yeqifu.bus.domain.Customer;
import com.yeqifu.bus.domain.Rent;
import com.yeqifu.bus.req.RentReq;
import com.yeqifu.bus.service.ICarService;
import com.yeqifu.bus.service.ICustomerService;
import com.yeqifu.bus.service.IRentService;
import com.yeqifu.bus.vo.RentVo;
import com.yeqifu.sys.constast.SysConstast;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.RandomUtils;
import com.yeqifu.sys.utils.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 汽车出租管理的控制器
 */
@Slf4j
@RestController
@RequestMapping("rent")
public class RentController {

    @Autowired
    private IRentService rentService;

    @Autowired
    private ICarService carService;

    @Autowired
    private ICustomerService customerService;

    /**
     * 检查身份证号是否存在
     *
     * @param req
     * @return
     */
    @PostMapping("checkCustomerExist")
    public ResultObj checkCustomerExist(RentReq req) {
        log.info("检查身份证号是否存在:{}", req);
        Customer customer = customerService.queryCustomerByIdentity(req.getIdentity());
        if (null != customer) {
            return ResultObj.STATUS_TRUE;
        } else {
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 初始化添加出租单的表单的数据
     *
     * @param req
     * @return
     */
    @PostMapping("initRentFrom")
    public Rent initRentFrom(RentReq req) {
        log.info("初始化添加出租单的表单的数据:{}", req);
        Rent rent = new Rent();
        BeanUtil.copyProperties(req, rent);
        //生成出租单号
        rent.setRentid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_CZ));
        //设置起租时间
        rent.setBegindate(new Date());
        //设置客户名称
        Customer customer = customerService.queryCustomerByIdentity(req.getIdentity());
        rent.setOpername(customer.getCustname());
        return rent;
    }

    /**
     * 保存出租单信息
     *
     * @param req
     * @return
     */
    @PostMapping("saveRent")
    public ResultObj saveRent(RentReq req) {
        try {
            Rent rent = new Rent();
            BeanUtil.copyProperties(req, rent);
            //设置创建时间
            rent.setCreatetime(new Date());
            //设置归还状态  默认为审核中
            rent.setRentflag(SysConstast.RENT_CHECK);
            //保存
            rentService.addRent(rent);
            return ResultObj.ADD_SUCCESS_RENT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR_RENT;
        }
    }

    /**
     * 出租单列表查询
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllRent")
    public DataGridView loadAllRent(RentReq req) {
        return rentService.queryAllRent(req);
    }

    /**
     * 审核出租单信息
     *
     * @param req
     * @return
     */
    @PostMapping("checkRent")
    public ResultObj checkRent(RentReq req) {
        try {
            Rent rent = new Rent();
            BeanUtil.copyProperties(req, rent);
            //修改出租单的状态
            rent.setRentflag(SysConstast.RENT_BACK_FALSE);
            rentService.updateRent(rent);
            //修改汽车的状态
            Car car = carService.queryCarByCarNumber(rent.getCarnumber());
            car.setIsrenting(SysConstast.RENT_CAR_TRUE);
            carService.updateCarCheck(car);
            return ResultObj.CHECK_SUCCESS_RENT;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.CHECK_ERROR_RENT;
        }
    }

    /**
     * 删除出租单信息
     *
     * @param rentVo
     * @return
     */
    @RequestMapping("deleteRent")
    public ResultObj deleteRent(RentVo rentVo) {
        try {
            //删除
            this.rentService.deleteRent(rentVo.getRentid());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 修改出租单信息
     *
     * @param rentVo
     * @return
     */
    @RequestMapping("updateRent")
    public ResultObj updateRent(RentVo rentVo) {
        try {
            //修改
            this.rentService.updateRent(rentVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }





}
