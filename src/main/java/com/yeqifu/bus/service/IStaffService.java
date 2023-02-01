package com.yeqifu.bus.service;

import com.yeqifu.bus.domain.Staff;
import com.yeqifu.bus.req.StaffReq;
import com.yeqifu.sys.utils.DataGridView;

/**
 * @Author: cyq
 * @Date: 2023/01/31 15:36
 * @Description:
 */
public interface IStaffService {

    /**
     * 加载员工列表返回DataGridView
     * @param req
     * @return
     */
    DataGridView loadAllStaff(StaffReq req);

    /**
     * 添加
     * @param staff
     */
    void add(Staff staff);

    /**
     * 修改
     * @param staff
     */
    void update(Staff staff);

    /**
     * 删除
     * @param identity
     */
    void delete(String identity);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(String[] ids);

    /**
     * 离职
     * @param identity
     */
    void updateLeave(String identity);
}
