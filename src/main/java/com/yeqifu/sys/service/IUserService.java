package com.yeqifu.sys.service;

import com.yeqifu.sys.domain.User;
import com.yeqifu.sys.req.AddOrUpdateUserReq;
import com.yeqifu.sys.req.UserReq;
import com.yeqifu.sys.req.UserRoleReq;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.vo.UserVo;

/**
 * 用户服务接口
 */
public interface IUserService {

    /**
     * 用户登陆
     *
     * @param userVo
     * @return
     */
    User login(UserVo userVo);

    /**
     * 查询所有用户
     *
     * @param req
     * @return
     */
    DataGridView queryAllUser(UserReq req);

    /**
     * 添加用户
     *
     * @param req
     */
    void addUser(AddOrUpdateUserReq req);

    /**
     * 修改用户
     *
     * @param req
     */
    Boolean updateUser(AddOrUpdateUserReq req);

    /**
     * 根据id删除用户
     *
     * @param userid
     */
    void deleteUser(Long userid);

    /**
     * 批量删除用户
     *
     * @param ids
     */
    void deleteBatchUser(Long[] ids);

    /**
     * 重置密码
     *
     * @param userid
     */
    void resetUserPwd(Long userid);

    /**
     * 保存用户和角色的关系
     *
     * @param req
     */
    void saveUserRole(UserRoleReq req);

    /**
     * 加载用户管理分配角色的数据
     *
     * @param userid
     * @return
     */
    DataGridView queryUserRole(Long userid);


    /**
     * 更新密码
     *
     * @param user
     */
    Boolean changePassword(User user);

    /**
     * 根据手机号获取用户信息
     *
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     *
     * 根据注册名获取用户信息
     * @param loginname
     * @return
     */
    User getUserByLoginName(String loginname);

    /**
     * 保存注册用户
     *
     * @param user
     */
    Boolean saveUser(User user);

}
