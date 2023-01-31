package com.yeqifu.stat.mapper;

import com.yeqifu.stat.domain.BaseEntity;

import java.util.List;

public interface StatMapper {

    /**
     * 查询客户地区数据
     * @return
     */
    List<BaseEntity> loadCustomerAreaStatList();

    /**
     * 查询客户地区性别数据
     * @return
     * @param area
     */
    List<BaseEntity> loadCustomerAreaSexStatList(String area);

    /**
     * 查询业务员年度业绩
     * @param year
     * @return
     */
    List<BaseEntity> loadOpernameYearGradeStatList(String year);

    /**
     * 查询公司年度业务销售额数据
     * @param year
     * @return
     */
    List<Double> loadCompanyYearGradeStatList(String year);

}
