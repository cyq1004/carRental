package com.yeqifu.bus.mapper;

import com.yeqifu.bus.domain.Franchisee;
import com.yeqifu.bus.req.AddOrUpdateFranchiseeReq;
import com.yeqifu.bus.req.FranchiseeReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenyq
 * @Date: 2022/5/22 13:57
 */
public interface FranchiseeMapper {

    /**
     * 查询所有加盟商信息
     *
     * @param req
     * @return
     */
    List<Franchisee> loadAllFranchisee(@Param("req") FranchiseeReq req);

    /**
     * 添加一个加盟商
     *
     * @param franchisee
     * @return
     */
    int addFranchisee(@Param("franchisee") Franchisee franchisee);

    /**
     * 更新一个加盟商
     *
     * @param req
     * @return
     */
    int updateFranchisee(AddOrUpdateFranchiseeReq req);

    /**
     * 删除加盟商
     *
     * @param id
     * @return
     */
    int deleteFranchisee(Integer id);

    /**
     * 根据id查询加盟商
     *
     * @param id
     * @return
     */
    Franchisee selectByPrimaryKey(@Param("id") Integer id);

}
