package com.yeqifu.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.sys.constast.SysConstast;
import com.yeqifu.sys.domain.Role;
import com.yeqifu.sys.domain.User;
import com.yeqifu.sys.mapper.RoleMapper;
import com.yeqifu.sys.mapper.UserMapper;
import com.yeqifu.sys.req.AddOrUpdateUserReq;
import com.yeqifu.sys.req.UserReq;
import com.yeqifu.sys.req.UserRoleReq;
import com.yeqifu.sys.service.IUserService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.vo.UserVo;
import com.yeqifu.sys.vo.UserVos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

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
        return this.userMapper.updateUser(req);
    }

    /**
     * 删除用户
     *
     * @param userid
     */
    @Override
    public void deleteUser(Long userid) {
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
        Role role = new Role();
        role.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Role> allRole = this.roleMapper.queryAllRole(role);
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
}
