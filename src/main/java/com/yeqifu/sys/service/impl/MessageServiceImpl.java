package com.yeqifu.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.sys.domain.Message;
import com.yeqifu.sys.mapper.MessageMapper;
import com.yeqifu.sys.req.AddOrUpdateMessageReq;
import com.yeqifu.sys.req.MessageReq;
import com.yeqifu.sys.service.IMessageService;
import com.yeqifu.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 查询留言
     *
     * @param req
     * @return
     */
    @Override
    public DataGridView queryAllMessage(MessageReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Message> data = messageMapper.queryAllMessage(req);
        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 添加留言
     *
     * @param req
     */
    @Override
    public void addMessage(AddOrUpdateMessageReq req) {
        messageMapper.addMessage(req);
    }

    /**
     * 删除一条留言
     *
     * @param id
     */
    @Override
    public void deleteMessage(Long id) {
        messageMapper.deleteMessage(id);
    }

    /**
     * 批量删除留言
     *
     * @param ids
     */
    @Override
    public void deleteBatchMessage(Long[] ids) {
        for (Long id : ids) {
            deleteMessage(id);
        }
    }

    /**
     * 更新留言
     *
     * @param req
     */
    @Override
    public void updateMessage(AddOrUpdateMessageReq req) {
        messageMapper.updateMessage(req);
    }

    /**
     * 通过id查询一条留言
     *
     * @param id
     * @return
     */
    @Override
    public Message loadMessageById(Long id) {
        return this.messageMapper.loadMessageById(id);
    }

}
