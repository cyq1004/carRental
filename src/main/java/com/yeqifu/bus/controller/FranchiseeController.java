package com.yeqifu.bus.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yeqifu.bus.domain.Franchisee;
import com.yeqifu.bus.req.AddOrUpdateFranchiseeReq;
import com.yeqifu.bus.req.FranchiseeReq;
import com.yeqifu.bus.service.FranchiseeService;
import com.yeqifu.bus.vo.FranchiseeVo;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

/**
 * 加盟商控制器
 *
 * @author chenyq
 * @Date: 2022/5/22 9:45
 */
@Slf4j
@RestController
@RequestMapping("/franchisee")
public class FranchiseeController {

    @Autowired
    private FranchiseeService franchiseeService;

    /**
     * 加载加盟商列表返回DataGridView
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllFranchisee")
    public DataGridView loadAllFranchisee(FranchiseeReq req) {
        log.info("加载加盟商列表返回DataGridView:{}", req);
        return franchiseeService.loadAllFranchisee(req);
    }

    /**
     * 添加一个加盟商
     *
     * @param req
     * @return
     */
    @PostMapping("addFranchisee")
    public ResultObj addFranchisee(AddOrUpdateFranchiseeReq req) {
        log.info("添加一个加盟商:{}", req);
        try {
            Franchisee franchisee = new Franchisee();
            BeanUtil.copyProperties(req, franchisee);
            franchiseeService.addFranchisee(franchisee);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改一个加盟商
     *
     * @param req
     * @return
     */
    @PostMapping("updateFranchisee")
    public ResultObj updateFranchisee(AddOrUpdateFranchiseeReq req) {
        log.info("修改一个加盟商:{}", req);
        try {
            franchiseeService.updateFranchisee(req);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除一个加盟商
     *
     * @param id
     * @return
     */
    @GetMapping("deleteFranchisee")
    public ResultObj deleteFranchisee(@RequestParam("id") Integer id) {
        log.info("删除一个加盟商:{}", id);
        try {
            franchiseeService.deleteFranchisee(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

