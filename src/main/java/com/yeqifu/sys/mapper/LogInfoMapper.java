package com.yeqifu.sys.mapper;

import com.yeqifu.sys.domain.LogInfo;
import com.yeqifu.sys.req.LogInfoReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogInfoMapper {

    /**
     * 查询日志
     *
     * @param req
     * @return
     */
    List<LogInfo> queryAllLogInfo(LogInfoReq req);


    /**
     * 根据ID删除日志
     *
     * @param id
     * @return
     */
    int deleteLogInfo(@Param("id") Long id);


    /**
     * 添加日志
     *
     * @param logInfo
     * @return
     */
    int addLogInfo(@Param("logInfo") LogInfo logInfo);




    int updateByPrimaryKeySelective(LogInfo record);

    int updateByPrimaryKey(LogInfo record);


}