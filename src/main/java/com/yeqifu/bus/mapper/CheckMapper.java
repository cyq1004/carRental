package com.yeqifu.bus.mapper;

import com.yeqifu.bus.domain.Check;
import com.yeqifu.bus.req.CheckReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckMapper {

    /**
     * 保存检查单数据
     *
     * @param check
     * @return
     */
    int addCheck(@Param("check") Check check);

    /**
     * 查询所有检查单
     *
     * @param req
     * @return
     */
    List<Check> loadAllCheck(@Param("req") CheckReq req);

    /**
     * 删除检查单
     *
     * @param checkid
     * @return
     */
    int deleteCheck(@Param("checkid") String checkid);

    /**
     * 修改检查单
     *
     * @param check
     * @return
     */
    int updateCheck(@Param("check") Check check);
}