package com.yeqifu.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.sys.domain.LogInfo;
import com.yeqifu.sys.mapper.LogInfoMapper;
import com.yeqifu.sys.req.LogInfoReq;
import com.yeqifu.sys.service.ILogInfoService;
import com.yeqifu.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志管理
 *
 * @author yeqifu
 */
@Service
public class LogInfoServiceImpl implements ILogInfoService {

    @Autowired
    private LogInfoMapper logInfoMapper;

    /**
     * 查询日志
     *
     * @param req
     * @return
     */
    @Override
    public DataGridView queryAllLogInfo(LogInfoReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<LogInfo> data = logInfoMapper.queryAllLogInfo(req);
        return new DataGridView(page.getTotal(), data);
    }


    /**
     * 删除一条日志
     *
     * @param id
     */
    @Override
    public void deleteLogInfo(Long id) {
        logInfoMapper.deleteLogInfo(id);
    }


    /**
     * 批量删除日志
     *
     * @param ids
     */
    @Override
    public void deleteBatchLogInfo(Long[] ids) {
        for (Long id : ids) {
            this.deleteLogInfo(id);
        }
    }


    /**
     * 添加日志
     *
     * @param logInfo
     */
    @Override
    public void addLogInfo(LogInfo logInfo) {
        logInfoMapper.addLogInfo(logInfo);
    }
}
