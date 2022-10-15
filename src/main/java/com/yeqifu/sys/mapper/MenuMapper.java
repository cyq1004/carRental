package com.yeqifu.sys.mapper;

import com.yeqifu.sys.domain.Menu;
import com.yeqifu.sys.req.MenuReq;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MenuMapper {

    /**
     * 查询所有菜单
     */
    List<Menu> queryAllMenu(@Param("req") MenuReq req);

    /**
     * 根据用户id查询菜单
     *
     * @param available
     * @param userId
     * @return
     */
    List<Menu> queryMenuByUid(@Param("available") Integer available, @Param("uid") Long userId);

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    int addMenu(@Param("menu") Menu menu);


    /**
     * 更新菜单
     *
     * @param menu
     */
    @Update("update sys_menu set pid = #{pid},title = #{title},href = #{href},spread = #{spread},target = #{target},icon = #{icon},available = #{available} where id = #{id}")
    void updateMenu(Menu menu);


    /**
     * 删除菜单表数据
     *
     * @param id
     */
    @Delete("delete from sys_menu where id = #{id}")
    void deleteMenu(@Param("id") Integer id);


    /**
     * 根据菜单id删除sys_role_menu里面的数据
     *
     * @param mid
     */
    void deleteRoleMenuByMid(@Param("mid") Integer mid);


    /**
     * 根据pid查询菜单数量
     *
     * @param pid
     * @return
     */
    Integer queryMenuByPid(@Param("pid") Integer pid);



    /**
     * 根据角色ID查询菜单
     *
     * @param available
     * @param roleid
     * @return
     */
    List<Menu> queryMenuByRoleId(@Param("available") Integer available, @Param("rid") Integer roleid);


    /**
     * 根据id获取菜单信息
     *
     * @param id
     * @return
     */
    @Select({"select * from sys_menu where id = #{id}"})
    Menu getMenuById(@Param("id") Integer id);
}