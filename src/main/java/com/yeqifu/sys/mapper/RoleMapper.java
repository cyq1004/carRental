package com.yeqifu.sys.mapper;

import com.yeqifu.sys.domain.Role;
import com.yeqifu.sys.req.AddOrUpdateRoleReq;
import com.yeqifu.sys.req.RoleReq;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RoleMapper {

    /**
     * 角色列表
     *
     * @param req
     * @return
     */
    List<Role> queryAllRole(@Param("req") RoleReq req);

    /**
     * 添加角色
     *
     * @param req
     */
    void addRole(@Param("req") AddOrUpdateRoleReq req);


    /**
     * 更新角色
     *
     * @param req
     */
    void updateRole(@Param("req") AddOrUpdateRoleReq req);


    /**
     * 删除角色表的数据
     *
     * @param roleid
     */
    @Delete("delete from sys_role where roleid = #{roleid}")
    void deleteRole(@Param("roleid") Integer roleid);


    /**
     * 删除角色对应的sys_role_menu里面的数据
     *
     * @param roleid
     */
    @Delete("delete from sys_role_menu where rid=#{roleid}")
    void deleteRoleMenu(@Param("roleid") Integer roleid);


    /**
     * 删除角色对应的sys_role_user里面的数据
     *
     * @param roleid
     */
    @Delete("delete from sys_role_user where rid=#{roleid}")
    void deleteRoleUser(@Param("roleid") Integer roleid);


    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    /**
     * 保存角色和菜单的关系sys_role_menu
     *
     * @param rid
     * @param mid
     */
    void insertRoleMenu(@Param("rid") Integer rid, @Param("mid") Integer mid);

    /**
     * 根据用户id删除sys_role_user里面的数据
     *
     * @param userid
     */
    @Delete("delete from sys_role_user where uid = #{userid}")
    void deleteRoleUserByUid(Long userid);

    /**
     * 根据用户id查询角色
     *
     * @param available
     * @param userid
     * @return
     */
    List<Role> queryRoleByUid(@Param("available") Integer available, @Param("uid") Long userid);


}