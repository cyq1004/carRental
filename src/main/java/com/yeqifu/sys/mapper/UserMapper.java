package com.yeqifu.sys.mapper;

import com.yeqifu.sys.domain.User;
import com.yeqifu.sys.req.AddOrUpdateUserReq;
import com.yeqifu.sys.req.UserReq;
import com.yeqifu.sys.vo.UserVos;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 用户登陆
     */
    User login(User user);

    /**
     * 用户列表
     *
     * @param req
     * @return
     */
    List<UserVos> queryAllUser(UserReq req);

    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    int addUser(@Param("user") User user);

    /**
     * 更新用户
     *
     * @param req
     * @return
     */
    Boolean updateUser(@Param("req") AddOrUpdateUserReq req);

    /**
     * 删除用户
     *
     * @param userid
     * @return
     */
    @Delete("delete from sys_user where userid = #{userid}")
    Integer deleteUser(@Param("userid") Long userid);

    /**
     * 重置密码123456
     *
     * @param user
     */
    Boolean resetUserPwd(@Param("user") User user);

    /**
     * 保存用户和角色的关系
     *
     * @param userid
     * @param rid
     */
    void saveUserRole(@Param("userid") Long userid, @Param("rid") Integer rid);

    /**
     * 更新密码
     *
     * @param user
     * @return
     */
    Boolean changePassword(@Param("user") User user);

    /**
     * 根据手机号获取用户信息
     *
     * @param phone
     * @return
     */
    @Select({"select * from sys_user where phone = #{phone}"})
    User getUserByPhone(@Param("phone") String phone);


    @Select({"select * from sys_user where loginname = #{loginname}"})
    User getUserByLoginName(@Param("loginname") String loginname);

    /**
     * 保存注册用户
     *
     * @param user
     * @return
     */
    @Insert({"insert into sys_user(loginname,phone,pwd) values (#{user.loginname},#{user.phone},#{user.pwd})"})
    Boolean saveUser(@Param("user") User user);


}