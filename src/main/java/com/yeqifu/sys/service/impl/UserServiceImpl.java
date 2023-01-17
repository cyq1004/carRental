package com.yeqifu.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.sys.constast.SysConstast;
import com.yeqifu.sys.domain.Log;
import com.yeqifu.sys.domain.Role;
import com.yeqifu.sys.domain.User;
import com.yeqifu.sys.mapper.RoleMapper;
import com.yeqifu.sys.mapper.UserMapper;
import com.yeqifu.sys.req.AddOrUpdateUserReq;
import com.yeqifu.sys.req.RoleReq;
import com.yeqifu.sys.req.UserReq;
import com.yeqifu.sys.req.UserRoleReq;
import com.yeqifu.sys.service.ILogService;
import com.yeqifu.sys.service.IUserService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.WebUtils;
import com.yeqifu.sys.vo.LogInfoVo;
import com.yeqifu.sys.vo.UserVo;
import com.yeqifu.sys.vo.UserVos;
import com.yeqifu.sys.vo.UserVosExportExcel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.hutool.core.date.DateTime.now;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ILogService iLogService;

    /**
     * 用户登陆
     *
     * @param userVo
     * @return
     */
    @Override
    public User login(UserVo userVo) {
        //明文123456
        //生成密文
        String pwd = DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes());
        System.out.println(pwd);
        userVo.setPwd(pwd);
        return userMapper.login(userVo);
    }

    /**
     * 用户列表
     *
     * @param req
     * @return
     */
    @Override
    public DataGridView queryAllUser(UserReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<UserVos> data = this.userMapper.queryAllUser(req);
        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 添加用户
     *
     * @param req
     */
    @Override
    public void addUser(AddOrUpdateUserReq req) {
        //记录操作日志 向sys_log里面插入数据
        User logUser = (User) WebUtils.getHttpSession().getAttribute("user");
        Log log = new Log();
        log.setLogname(logUser.getRealname());
        log.setLogip(WebUtils.getHttpServletRequest().getRemoteAddr());
        log.setLogtime(new Date());
        log.setLog("添加用户"+req.getRealname());
        iLogService.addLog(log);

        User user = new User();
        BeanUtil.copyProperties(req, user);
        //设置默认密码
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        //设置用户类型 都是普通用户 type=2
        user.setType(SysConstast.USER_TYPE_NORMAL);
        this.userMapper.addUser(user);
    }

    /**
     * 更新用户
     *
     * @param req
     */
    @Override
    public Boolean updateUser(AddOrUpdateUserReq req) {
        //记录操作日志 向sys_log里面插入数据
        User logUser = (User) WebUtils.getHttpSession().getAttribute("user");
        Log log = new Log();
        log.setLogname(logUser.getRealname());
        log.setLogip(WebUtils.getHttpServletRequest().getRemoteAddr());
        log.setLogtime(new Date());
        log.setLog("更新用户"+req.getRealname());
        iLogService.addLog(log);

        return this.userMapper.updateUser(req);
    }

    /**
     * 删除用户
     *
     * @param userid
     */
    @Override
    public void deleteUser(Long userid) {
        User user = userMapper.getUserByUserId(userid);
        //记录操作日志 向sys_log里面插入数据
        User logUser = (User) WebUtils.getHttpSession().getAttribute("user");
        Log log = new Log();
        log.setLogname(logUser.getRealname());
        log.setLogip(WebUtils.getHttpServletRequest().getRemoteAddr());
        log.setLogtime(new Date());
        log.setLog("删除用户"+user.getRealname());
        iLogService.addLog(log);

        this.userMapper.deleteUser(userid);
        //根据用户id删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByUid(userid);
    }

    /**
     * 批量删除用户
     *
     * @param ids
     */
    @Override
    public void deleteBatchUser(Long[] ids) {
        for (Long uid : ids) {
            this.deleteUser(uid);
        }
    }

    /**
     * 重置用户的密码
     *
     * @param userid
     */
    @Override
    public void resetUserPwd(Long userid) {
        User userInfo = userMapper.getUserByUserId(userid);
        //记录操作日志 向sys_log里面插入数据
        User logUser = (User) WebUtils.getHttpSession().getAttribute("user");
        Log log = new Log();
        log.setLogname(logUser.getRealname());
        log.setLogip(WebUtils.getHttpServletRequest().getRemoteAddr());
        log.setLogtime(new Date());
        log.setLog("重置用户的密码"+userInfo.getRealname());
        iLogService.addLog(log);

        User user = new User();
        user.setUserid(userid);
        //设置默认密码
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        //更新
        this.userMapper.resetUserPwd(user);
    }


    /**
     * 保存用户和角色的关系
     *
     * @param req
     */
    @Override
    public void saveUserRole(UserRoleReq req) {
        Long userid = req.getUserid();
        Integer[] roleIds = req.getRoleIds();
        //根据用户id删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByUid(userid);
        //保存关系
        if (roleIds != null && roleIds.length > 0) {
            for (Integer rid : roleIds) {
                this.userMapper.saveUserRole(userid, rid);
            }
        }
    }

    /**
     * 加载用户管理的分配角色的数据
     *
     * @param userid
     * @return
     */
    @Override
    public DataGridView queryUserRole(Long userid) {
        //1.查询所有可用的角色
//        Role role = new Role();
//        role.setAvailable(SysConstast.AVAILABLE_TRUE);
        RoleReq roleReq = new RoleReq();
        roleReq.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Role> allRole = this.roleMapper.queryAllRole(roleReq);
        //2.根据用户ID查询已拥有的角色
        List<Role> userRole = this.roleMapper.queryRoleByUid(SysConstast.AVAILABLE_TRUE, userid);

        List<Map<String, Object>> data = new ArrayList<>();

        for (Role r1 : allRole) {
            Boolean LAY_CHECKED = false;
            for (Role r2 : userRole) {
                if (r1.getRoleid() == r2.getRoleid()) {
                    LAY_CHECKED = true;
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("roleid", r1.getRoleid());
            map.put("rolename", r1.getRolename());
            map.put("roledesc", r1.getRoledesc());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);

        }

        return new DataGridView(data);
    }

    /**
     * 更新密码
     *
     * @param user
     * @return
     */
    @Override
    public Boolean changePassword(User user) {
        //记录操作日志 向sys_log里面插入数据
        User logUser = (User) WebUtils.getHttpSession().getAttribute("user");
        Log log = new Log();
        log.setLogname(logUser.getRealname());
        log.setLogip(WebUtils.getHttpServletRequest().getRemoteAddr());
        log.setLogtime(new Date());
        log.setLog("更新用户密码"+user.getRealname());
        iLogService.addLog(log);

        return userMapper.changePassword(user);
    }


    /**
     * 根据手机号获取用户信息
     *
     * @param phone
     * @return
     */
    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public User getUserByLoginName(String loginname) {
        return userMapper.getUserByLoginName(loginname);
    }

    /**
     * 保存注册用户
     *
     * @param user
     * @return
     */
    @Override
    public Boolean saveUser(User user) {
        return userMapper.saveUser(user);
    }

    /**
     * 导出用户管理列表
     *
     * @param req
     */
    @Override
    public void exportExcel(UserReq req) {
        List<UserVos> data = userMapper.queryAllUser(req);
        List<UserVosExportExcel> list = new ArrayList<>();
        for (UserVos userVo : data) {
            UserVosExportExcel userVosExportExcel = new UserVosExportExcel();
            BeanUtil.copyProperties(userVo, userVosExportExcel);
            list.add(userVosExportExcel);
        }

        String fileName = "C:\\Users\\aa\\Desktop\\user.xlsx";

        EasyExcel.write(fileName, UserVosExportExcel.class)
                .sheet("user")
                .doWrite(list);
    }
}
