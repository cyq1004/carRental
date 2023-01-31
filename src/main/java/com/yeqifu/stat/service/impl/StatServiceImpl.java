package com.yeqifu.stat.service.impl;

import com.yeqifu.stat.domain.BaseEntity;
import com.yeqifu.stat.mapper.StatMapper;
import com.yeqifu.stat.service.IStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatServiceImpl implements IStatService {

    @Autowired
    private StatMapper statMapper;

    /**
     * 查询客户地区数据
     * @return
     */
    @Override
    public List<BaseEntity> loadCustomerAreaStatList() {
        return statMapper.loadCustomerAreaStatList();
    }

    /**
     * 查询客户地区性别数据
     * @param area
     * @return
     */
    @Override
    public List<BaseEntity> loadCustomerAreaSexStatList(String area) {
        return statMapper.loadCustomerAreaSexStatList(area);
    }

    /**
     * 查询业务员年度销售额统计数据
     * @param year
     * @return
     */
    @Override
    public List<BaseEntity> loadOpernameYearGradeStatList(String year) {
        return statMapper.loadOpernameYearGradeStatList(year);
    }

    /**
     * 查询公司年度月份销售额数据
     * @param year
     * @return
     */
    @Override
    public List<Double> loadCompanyYearGradeStatList(String year) {
        return statMapper.loadCompanyYearGradeStatList(year);
    }
}
