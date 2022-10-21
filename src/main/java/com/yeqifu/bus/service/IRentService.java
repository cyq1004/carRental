package com.yeqifu.bus.service;

import com.yeqifu.bus.domain.Rent;
import com.yeqifu.bus.req.RentReq;
import com.yeqifu.bus.vo.RentVo;
import com.yeqifu.sys.utils.DataGridView;

public interface IRentService {

    /**
     * 保存出租单信息
     *
     * @param rent
     */
    void addRent(Rent rent);

    /**
     * 出租单列表查询
     *
     * @param req
     */
    DataGridView queryAllRent(RentReq req);

    /**
     * 修改出租单
     *
     * @param rent
     */
    void updateRent(Rent rent);

    /**
     * 删除出租单
     *
     * @param rentid
     */
    void deleteRent(String rentid);

    /**
     * 根据出租单号查询出租单信息
     *
     * @param rentid
     * @return
     */
    Rent queryRentByRentId(String rentid);

    /**
     * 修改出租单状态
     *
     * @param rent
     */
    void updateRentFlag(Rent rent);
}
