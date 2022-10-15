package com.yeqifu.sys.mapper;


import com.yeqifu.sys.domain.Log;
import com.yeqifu.sys.domain.LogInfo;
import com.yeqifu.sys.req.LogReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {

    /**
     * 日志管理列表
     *
     * @param req
     * @return
     */
    List<Log> queryAllLogInfo(@Param("req") LogReq req);


    /**
     * 删除一条日志
     *
     * @param id
     */
    void deleteLogInfo(@Param("id") Long id);


    /**
     * 添加日志
     *
     * @param req
     */
    int addLog(@Param("log") Log log);
}
