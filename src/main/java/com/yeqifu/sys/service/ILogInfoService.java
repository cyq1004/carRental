package com.yeqifu.sys.service;


import com.yeqifu.sys.domain.LogInfo;
import com.yeqifu.sys.req.LogInfoReq;
import com.yeqifu.sys.utils.DataGridView;

/**
 * 日志管理的服务接口
 *
 * @author yeqifu
 */
public interface ILogInfoService {

    /**
     * 查询所有日志
     *
     * @param req
     * @return
     */
    DataGridView queryAllLogInfo(LogInfoReq req);


    /**
     * 删除一条日志
     *
     * @param logInfoId
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
     * @param logInfo
     */
    void addLogInfo(LogInfo logInfo);


}
