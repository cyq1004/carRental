package com.yeqifu.sys.service;


import com.yeqifu.sys.domain.Log;
import com.yeqifu.sys.req.LogReq;
import com.yeqifu.sys.utils.DataGridView;

/**
 * 日志管理的服务接口
 *
 * @author chenyq
 */
public interface ILogService {

    /**
     * 日志管理列表
     *
     * @param req
     * @return
     */
    DataGridView queryAllLogInfo(LogReq req);


    /**
     * 删除一条日志
     *
     * @param id
     */
    void deleteLogInfo(Long id);

    /**
     * 批量删除日志
     *
     * @param ids
     */
    void deleteBatchLogInfo(Long[] ids);


    /**
     * 添加日志
     *
     * @param req
     */
    void addLog(Log log);
}
