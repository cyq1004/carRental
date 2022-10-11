package com.yeqifu.sys.mapper;

import com.yeqifu.sys.domain.Message;
import com.yeqifu.sys.req.AddOrUpdateMessageReq;
import com.yeqifu.sys.req.MessageReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {

    /**
     * 查询留言
     *
     * @param req
     * @return
     */
    List<Message> queryAllMessage(@Param("req") MessageReq req);


    /**
     * 添加留言
     *
     * @param req
     * @return
     */
    int addMessage(@Param("req") AddOrUpdateMessageReq req);

    /**
     * 删除留言
     *
     * @param id
     * @return
     */
    int deleteMessage(@Param("id") Integer id);

    /**
     * 更新留言
     *
     * @param req
     * @return
     */
    int updateMessage(@Param("req") MessageReq req);


    /**
     * 通过id查询一条留言
     *
     * @param id
     * @return
     */
    Message loadMessageById(@Param("id") Long id);

}