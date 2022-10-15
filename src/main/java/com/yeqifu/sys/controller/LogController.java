package com.yeqifu.sys.controller;

import com.yeqifu.sys.req.LogInfoReq;
import com.yeqifu.sys.req.LogReq;
import com.yeqifu.sys.service.ILogInfoService;
import com.yeqifu.sys.service.ILogService;
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
@RequestMapping("log")
public class LogController {

    @Autowired
    private ILogService logService;

    /**
     * 加载日志列表返回DataGridView
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllLogInfo")
    public DataGridView loadAllLogInfo(LogReq req) {
        log.info("加载日志列表:{}", req);
            return logService.queryAllLogInfo(req);
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
            logService.deleteLogInfo(id);
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
    public ResultObj deleteBatchLogInfo(LogReq req) {
        log.info("批量删除日志:{}", req);
        try {
            logService.deleteBatchLogInfo(req.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
