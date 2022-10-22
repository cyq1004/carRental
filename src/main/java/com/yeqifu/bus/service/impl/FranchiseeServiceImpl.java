package com.yeqifu.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.bus.domain.Franchisee;
import com.yeqifu.bus.mapper.FranchiseeMapper;
import com.yeqifu.bus.req.AddOrUpdateFranchiseeReq;
import com.yeqifu.bus.req.FranchiseeReq;
import com.yeqifu.bus.service.FranchiseeService;
import com.yeqifu.bus.vo.FranchiseeVo;
import com.yeqifu.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenyq
 * @Date: 2022/5/22 13:56
 */
@Service
public class FranchiseeServiceImpl implements FranchiseeService {

    @Autowired
    private FranchiseeMapper franchiseeMapper;

    /**
     * 查询所有加盟商信息 分页
     *
     * @param req
     * @return
     */
    @Override
    public DataGridView loadAllFranchisee(FranchiseeReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Franchisee> data = franchiseeMapper.loadAllFranchisee(req);
        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 添加一个加盟商
     *
     * @param franchisee
     */
    @Override
    public void addFranchisee(Franchisee franchisee) {
        franchiseeMapper.addFranchisee(franchisee);
    }

    /**
     * 更新一个加盟商
     *
     * @param req
     */
    @Override
    public void updateFranchisee(AddOrUpdateFranchiseeReq req) {
        franchiseeMapper.updateFranchisee(req);
    }

    @Override
    public void deleteFranchisee(Integer id) {
        franchiseeMapper.deleteFranchisee(id);
    }

}
