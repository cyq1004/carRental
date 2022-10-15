package com.yeqifu.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.sys.domain.Log;
import com.yeqifu.sys.domain.LogInfo;
import com.yeqifu.sys.mapper.LogMapper;
import com.yeqifu.sys.req.LogReq;
import com.yeqifu.sys.service.ILogService;
import com.yeqifu.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志管理服务接口
 *
 * @author chenyq
 */
@Service
public class ILogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 日志管理列表
     *
     * @param req
     * @return
     */
    @Override
    public DataGridView queryAllLogInfo(LogReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Log> data = logMapper.queryAllLogInfo(req);
        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 删除一条日志
     *
     * @param id
     */
    @Override
    public void deleteLogInfo(Long id) {
        logMapper.deleteLogInfo(id);
    }


    /**
     * 批量删除日志
     *
     * @param ids
     */
    @Override
    public void deleteBatchLogInfo(Long[] ids) {
        for (Long id : ids) {
            deleteLogInfo(id);
        }
    }


    /**
     * 添加日志
     *
     * @param req
     */
    @Override
    public void addLog(Log log) {
        logMapper.addLog(log);
    }
}
