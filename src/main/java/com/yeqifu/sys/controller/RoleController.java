package com.yeqifu.sys.controller;

import com.yeqifu.sys.req.AddOrUpdateRoleReq;
import com.yeqifu.sys.req.RoleReq;
import com.yeqifu.sys.service.IRoleService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.ResultObj;
import com.yeqifu.sys.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理控制器
 *
 * @author cyq
 */
@Slf4j
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 角色列表
     * 加载角色列表返回DataGridView
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllRole")
    public DataGridView loadAllRole(RoleReq req) {
        log.info("角色列表:{}", req);
        return roleService.queryAllRole(req);
    }

    /**
     * 添加角色
     *
     * @param req
     * @return
     */
    @PostMapping("addRole")
    public ResultObj addRole(@Validated AddOrUpdateRoleReq req) {
        log.info("添加角色:{}", req);
        try {
            roleService.addRole(req);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改角色
     *
     * @param req
     * @return
     */
    @PostMapping("updateRole")
    public ResultObj updateRole(@Validated AddOrUpdateRoleReq req) {
        log.info("修改角色:{}", req);
        try {
            roleService.updateRole(req);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除角色
     *
     * @param roleid
     * @return
     */
    @GetMapping("deleteRole")
    public ResultObj deleteRole(@RequestParam("roleid") Integer roleid) {
        log.info("删除角色:{}", roleid);
        try {
            roleService.deleteRole(roleid);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     *
     * @param req
     * @return
     */
    @PostMapping("deleteBatchRole")
    public ResultObj deleteBatchRole(RoleReq req) {
        log.info("批量删除角色:{}", req);
        try {
            roleService.deleteBatchRole(req.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载角色管理分配菜单的json
     *
     * @param roleid
     * @return
     */
    @GetMapping("initRoleMenuTreeJson")
    public DataGridView initRoleMenuJson(@RequestParam("roleid") Integer roleid) {
        return roleService.initRoleMenuTreeJson(roleid);
    }

    /**
     * 保存角色和菜单的关系
     *
     * @param roleVo
     * @return
     */
    @RequestMapping("saveRoleMenu")
    public ResultObj saveRoleMenu(RoleVo roleVo) {
        try {
            roleService.saveRoleMenu(roleVo);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }


}
