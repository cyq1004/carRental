package com.yeqifu.sys.service;

import com.yeqifu.sys.domain.Message;
import com.yeqifu.sys.req.AddOrUpdateMessageReq;
import com.yeqifu.sys.req.MessageReq;
import com.yeqifu.sys.utils.DataGridView;

public interface IMessageService {

    /**
     * 查询留言
     *
     * @param req
     * @return
     */
    DataGridView queryAllMessage(MessageReq req);

    /**
     * 添加留言
     *
     * @param req
     */
    void addMessage(AddOrUpdateMessageReq req);

    /**
     * 删除留言
     *
     * @param messageid
     */
    void deleteMessage(Long id);

    /**
     * 批量删除留言
     *
     * @param ids
     */
    void deleteBatchMessage(Long[] ids);

    /**
     * 更新留言
     *
     * @param req
     */
    void updateMessage(MessageReq req);

    /**
     * 通过id查询一条留言
     *
     * @param id
     * @return
     */
    Message loadMessageById(Long id);

}
