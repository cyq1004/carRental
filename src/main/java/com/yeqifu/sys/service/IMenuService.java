package com.yeqifu.sys.service;

import com.yeqifu.sys.domain.Menu;
import com.yeqifu.sys.req.MenuReq;
import com.yeqifu.sys.utils.DataGridView;

import java.util.List;

/**
 * 菜单管理的服务接口
 */
public interface IMenuService {

    /**
     * 查询所有菜单
     *
     * @param req
     * @return
     */
    DataGridView queryAllMenu(MenuReq req);


    /**
     * 查询所有菜单
     *
     * @param req
     * @return
     */
    List<Menu> queryAllMenuForList(MenuReq req);


    /**
     * 当前用户可用菜单列表
     *
     * @param menuVo
     * @param userId
     * @return
     */
    List<Menu> queryMenuByUserIdForList(MenuReq req, Long userId);


    /**
     * 添加菜单
     *
     * @param menu
     */
    void addMenu(Menu menu);


    /**
     * 修改菜单
     *
     * @param menu
     */
    void updateMenu(Menu menu);


    /**
     * 根据id删除菜单
     *
     * @param id
     */
    void deleteMenu(Integer id);


    /**
     * 根据pid查询菜单数量
     *
     * @param pid
     * @return
     */
    Integer queryMenuByPid(Integer pid);


}
