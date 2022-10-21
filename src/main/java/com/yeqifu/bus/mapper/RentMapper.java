package com.yeqifu.bus.mapper;

import com.yeqifu.bus.domain.Rent;
import com.yeqifu.bus.req.RentReq;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RentMapper {

    /**
     * 保存出租单信息
     *
     * @param rent
     * @return
     */
    int addRent(@Param("rent") Rent rent);

    /**
     * 出租单列表查询
     *
     * @param req
     * @return
     */
    List<Rent> queryAllRent(@Param("req") RentReq req);

    /**
     * 修改出租单
     *
     * @param rent
     * @return
     */
    int updateRent(@Param("rent") Rent rent);

    /**
     * 删除出租单
     *
     * @param rentid
     * @return
     */
    int deleteRent(@Param("rentid") String rentid);

    /**
     * 根据id获取出租单信息
     *
     * @param rentid
     * @return
     */
    Rent queryRentByRentId(@Param("rentid") String rentid);

    /**
     * 修改出租单状态
     *
     * @param rent
     */
    @Update("update bus_rent set rentflag = #{rent.rentflag} where rentid = #{rent.rentid}")
    void updateRentFlag(@Param("rent") Rent rent);
}