package com.yeqifu.sys.controller;

import com.yeqifu.sys.constast.SysConstast;
import com.yeqifu.sys.domain.Menu;
import com.yeqifu.sys.domain.User;
import com.yeqifu.sys.req.MenuReq;
import com.yeqifu.sys.service.IMenuService;
import com.yeqifu.sys.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理控制器
 *
 * @author cheyq
 */
@Slf4j
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * 加载菜单列表返回DataGridView
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllMenu")
    public DataGridView loadAllMenu(MenuReq req) {
        log.info("加载菜单列表:{}", req);
        return this.menuService.queryAllMenu(req);
    }


    /**
     * 加载菜单左边的菜单树
     *
     * @param req
     * @return
     */
    @PostMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(MenuReq req) {
        log.info("加载菜单左边的菜单树:{}", req);
        req.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        List<Menu> menuList = menuService.queryAllMenuForList(req);
        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据放到nodes
        for (Menu menu : menuList) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return new DataGridView(nodes);
    }


    /**
     * 当前用户可用菜单列表
     *
     * @param req
     * @return
     */
    @RequestMapping("loadIndexleftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(MenuReq req) {
        log.info("当前用户可用菜单列表:{}", req);
        //得到当前登陆的用户对象
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        List<Menu> list = null;
        //只查询可用的菜单
        req.setAvailable(SysConstast.AVAILABLE_TRUE);
        if (user.getType() == SysConstast.USER_TYPE_SUPER) {
            list = menuService.queryAllMenuForList(req);
        } else {
            list = menuService.queryMenuByUserIdForList(req, user.getUserid());
        }
        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据放到nodes中
        for (Menu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return TreeNodeBuilder.builder(nodes, 1);
    }


    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("addMenu")
    public ResultObj addMenu(Menu menu) {
        log.info("添加菜单:{}", menu);
        try {
            menuService.addMenu(menu);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("updateMenu")
    public ResultObj updateMenu(Menu menu) {
        try {
            menuService.updateMenu(menu);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @GetMapping("deleteMenu")
    public ResultObj deleteMenu(@RequestParam("id") Integer id) {
        try {
            menuService.deleteMenu(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据id判断当前菜单有没有子节点
     * 有 返回code>=0
     * 没有 返回code<0
     *
     * @param id
     * @return
     */
    @GetMapping("checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(@RequestParam("id") Integer id) {
        //根据pid查询菜单数量
        Integer count = this.menuService.queryMenuByPid(id);
        System.out.println(count);
        if (count > 0) {
            return ResultObj.STATUS_TRUE;
        } else {
            return ResultObj.STATUS_FALSE;
        }
    }


}
