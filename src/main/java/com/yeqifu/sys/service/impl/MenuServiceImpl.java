package com.yeqifu.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.sys.domain.Log;
import com.yeqifu.sys.domain.Menu;
import com.yeqifu.sys.domain.User;
import com.yeqifu.sys.mapper.MenuMapper;
import com.yeqifu.sys.req.MenuReq;
import com.yeqifu.sys.service.ILogService;
import com.yeqifu.sys.service.IMenuService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ILogService iLogService;

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
        //记录操作日志 向sys_log里面插入数据
        User logUser = (User) WebUtils.getHttpSession().getAttribute("user");
        Log log = new Log();
        log.setLogname(logUser.getRealname());
        log.setLogip(WebUtils.getHttpServletRequest().getRemoteAddr());
        log.setLogtime(new Date());
        log.setLog("添加菜单"+menu.getTitle());
        iLogService.addLog(log);

        menuMapper.addMenu(menu);
    }

    /**
     * 更新菜单
     *
     * @param menu
     */
    @Override
    public void updateMenu(Menu menu) {
        //记录操作日志 向sys_log里面插入数据
        User logUser = (User) WebUtils.getHttpSession().getAttribute("user");
        Log log = new Log();
        log.setLogname(logUser.getRealname());
        log.setLogip(WebUtils.getHttpServletRequest().getRemoteAddr());
        log.setLogtime(new Date());
        log.setLog("更新菜单"+menu.getTitle());
        iLogService.addLog(log);

        menuMapper.updateMenu(menu);
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @Override
    public void deleteMenu(Integer id) {
        Menu menu = menuMapper.getMenuById(id);
        //记录操作日志 向sys_log里面插入数据
        User logUser = (User) WebUtils.getHttpSession().getAttribute("user");
        Log log = new Log();
        log.setLogname(logUser.getRealname());
        log.setLogip(WebUtils.getHttpServletRequest().getRemoteAddr());
        log.setLogtime(new Date());
        log.setLog("删除菜单"+menu.getTitle());
        iLogService.addLog(log);

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
