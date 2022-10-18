package com.yeqifu.bus.mapper;

import com.yeqifu.bus.domain.Rent;
import com.yeqifu.bus.req.RentReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RentMapper {

    /**
     * 保存出租单信息
     *
     * @param record
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




    int deleteByPrimaryKey(String rentid);

    int insert(Rent record);



    Rent selectByPrimaryKey(String rentid);



    int updateByPrimaryKey(Rent record);



}