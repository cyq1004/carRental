package com.yeqifu.bus.mapper;

import com.yeqifu.bus.domain.Staff;
import com.yeqifu.bus.req.StaffReq;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @Author: cyq
 * @Date: 2023/01/31 15:38
 * @Description:
 */
public interface StaffMapper {

    /**
     * 加载员工列表返回DataGridView
     * @param req
     * @return
     */
    List<Staff> loadAllStaff(@Param("req") StaffReq req);

    /**
     * 添加
     * @param staff
     */
    int add(@Param("staff") Staff staff);

    /**
     * 修改
     * @param staff
     */
    void update(@Param("staff") Staff staff);

    /**
     * 删除
     * @param identity
     */
    void delete(String identity);

    /**
     * 离职
     * @param identity
     */
    void updateLeave(@Param("identity") String identity);
}
