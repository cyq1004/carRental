package com.yeqifu.bus.mapper;

import com.yeqifu.bus.domain.Franchisee;

import java.util.List;

/**
 * @author chenyq
 * @Date: 2022/5/22 13:57
 */
public interface FranchiseeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Franchisee franchisee);

    int insertSelective(Franchisee franchisee);

    Franchisee selectByPrimaryKey(String identity);

    int updateByPrimaryKey(Franchisee record);

    int updateByPrimaryKeySelective(Franchisee franchisee);


    List<Franchisee> queryAllFranchisee(Franchisee franchisee);

}
