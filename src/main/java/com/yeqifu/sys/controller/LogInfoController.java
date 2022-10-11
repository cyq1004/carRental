package com.yeqifu.sys.controller;

import com.yeqifu.sys.req.LogInfoReq;
import com.yeqifu.sys.service.ILogInfoService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 日志管理控制器
 *
 * @author chenyq
 */
@Slf4j
@RestController
@RequestMapping("logInfo")
public class LogInfoController {

    @Autowired
    private ILogInfoService logInfoService;

    /**
     * 加载日志列表返回DataGridView
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllLogInfo")
    public DataGridView loadAllLogInfo(LogInfoReq req) {
        log.info("加载日志列表:{}", req);
        return logInfoService.queryAllLogInfo(req);
    }

    /**
     * 删除一条日志
     *
     * @param id
     * @return
     */
    @GetMapping("deleteLogInfo")
    public ResultObj deleteLogInfo(@RequestParam("id") Long id) {
        log.info("删除一条日志:{}", id);
        try {
            this.logInfoService.deleteLogInfo(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除日志
     *
     * @param req
     * @return
     */
    @PostMapping("deleteBatchLogInfo")
    public ResultObj deleteBatchLogInfo(LogInfoReq req) {
        log.info("批量删除日志:{}", req);
        try {
            logInfoService.deleteBatchLogInfo(req.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
