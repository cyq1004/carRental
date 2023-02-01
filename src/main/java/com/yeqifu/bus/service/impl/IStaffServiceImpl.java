package com.yeqifu.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.bus.domain.Customer;
import com.yeqifu.bus.domain.Staff;
import com.yeqifu.bus.mapper.StaffMapper;
import com.yeqifu.bus.req.StaffReq;
import com.yeqifu.bus.service.IStaffService;
import com.yeqifu.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: cyq
 * @Date: 2023/01/31 15:37
 * @Description:
 */
@Service
public class IStaffServiceImpl implements IStaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public DataGridView loadAllStaff(StaffReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Staff> data = staffMapper.loadAllStaff(req);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void add(Staff staff) {
        staffMapper.add(staff);
    }

    @Override
    public void update(Staff staff) {
        staffMapper.update(staff);
    }

    @Override
    public void delete(String identity) {
        staffMapper.delete(identity);
    }

    @Override
    public void deleteBatch(String[] ids) {
        for (String identity : ids) {
            delete(identity);
        }
    }

    @Override
    public void updateLeave(String identity) {
        staffMapper.updateLeave(identity);
    }
}
