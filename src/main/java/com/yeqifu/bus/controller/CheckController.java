package com.yeqifu.bus.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yeqifu.bus.domain.Check;
import com.yeqifu.bus.domain.Rent;
import com.yeqifu.bus.req.CheckReq;
import com.yeqifu.bus.service.ICheckService;
import com.yeqifu.bus.service.IRentService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * 检查单管理的控制器
 */
@Slf4j
@RestController
@RequestMapping("check")
public class CheckController {

    @Autowired
    private IRentService rentService;

    @Autowired
    private ICheckService checkService;

    /**
     * 根据出租单号查询出租单信息
     *
     * @param rentid
     * @return
     */
    @GetMapping("checkRentExist")
    public Rent checkRentExist(@RequestParam("rentid") String rentid) {
        Rent rent = rentService.queryRentByRentId(rentid);
        return rent;
    }

    /**
     * 根据出租单号加载检查单的表单数据
     *
     * @param rentid
     * @return
     */
    @GetMapping("initCheckFormData")
    public Map<String, Object> initCheckFormData(@RequestParam("rentid") String rentid) {
        return checkService.initCheckFormData(rentid);
    }

    /**
     * 保存检查单数据
     *
     * @param req
     * @return
     */
    @PostMapping("saveCheck")
    public ResultObj saveCheck(CheckReq req) {
        try {
            Check check = new Check();
            BeanUtil.copyProperties(req, check);
            check.setCreatetime(new Date());
            checkService.addCheck(check);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询所有检查单
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllCheck")
    public DataGridView loadAllCheck(CheckReq req) {
        return checkService.loadAllCheck(req);
    }

    /**
     * 删除一个检查单
     *
     * @param checkid
     * @return
     */
    @GetMapping("deleteCheck")
    public ResultObj deleteCheck(@RequestParam("checkid") String checkid) {
        try {
            checkService.deleteCheck(checkid);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除检查单
     *
     * @param req
     * @return
     */
    @PostMapping("deleteBatchCheck")
    public ResultObj deleteBatchCheck(CheckReq req) {
        try {
            checkService.deleteBatchCheck(req.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 更新检查单
     *
     * @param req
     * @return
     */
    @PostMapping("updateCheck")
    public ResultObj updateCheck(CheckReq req) {
        try {
            Check check = new Check();
            BeanUtil.copyProperties(req, check);
            checkService.updateCheck(check);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
}
