package com.yeqifu.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.sys.domain.Menu;
import com.yeqifu.sys.mapper.MenuMapper;
import com.yeqifu.sys.req.MenuReq;
import com.yeqifu.sys.service.IMenuService;
import com.yeqifu.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有菜单
     *
     * @param req
     * @return
     */
    @Override
    public DataGridView queryAllMenu(MenuReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Menu> data = menuMapper.queryAllMenu(req);
        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 查询所有菜单
     *
     * @param req
     * @return
     */
    @Override
    public List<Menu> queryAllMenuForList(MenuReq req) {
        return menuMapper.queryAllMenu(req);
    }

    /**
     * 当前用户可用菜单列表
     *
     * @param req
     * @param userId
     * @return
     */
    @Override
    public List<Menu> queryMenuByUserIdForList(MenuReq req, Long userId) {
        return menuMapper.queryMenuByUid(req.getAvailable(), userId);
    }


    /**
     * 添加菜单
     *
     * @param menu
     */
    @Override
    public void addMenu(Menu menu) {
        menuMapper.addMenu(menu);
    }

    /**
     * 更新菜单
     *
     * @param menu
     */
    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateMenu(menu);
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @Override
    public void deleteMenu(Integer id) {
        //删除菜单表的数据
        menuMapper.deleteMenu(id);
        //根据菜单id删除sys_role_menu里面的数据
        menuMapper.deleteRoleMenuByMid(id);

    }

    /**
     * 根据pid查询菜单数量
     *
     * @param pid
     * @return
     */
    @Override
    public Integer queryMenuByPid(Integer pid) {
        return menuMapper.queryMenuByPid(pid);
    }


}
