package com.yeqifu.bus.service;

import com.yeqifu.bus.domain.Franchisee;
import com.yeqifu.bus.req.AddOrUpdateFranchiseeReq;
import com.yeqifu.bus.req.FranchiseeReq;
import com.yeqifu.bus.vo.FranchiseeVo;
import com.yeqifu.sys.utils.DataGridView;

/**
 * @author chenyq
 * @Date: 2022/5/22 13:50
 */
public interface FranchiseeService {

    /**
     * 查询所有加盟商
     *
     * @param req
     * @return
     */
    DataGridView loadAllFranchisee(FranchiseeReq req);

    /**
     * 添加加盟商
     *
     * @param franchisee
     */
    void addFranchisee(Franchisee franchisee);

    /**
     * 修改加盟商
     *
     * @param req
     */
    void updateFranchisee(AddOrUpdateFranchiseeReq req);

    /**
     * 删除加盟商
     *
     * @param id
     */
    void deleteFranchisee(Integer id);
}
