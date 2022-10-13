package com.yeqifu.sys.controller;

import com.yeqifu.sys.domain.Message;
import com.yeqifu.sys.domain.User;
import com.yeqifu.sys.req.AddOrUpdateMessageReq;
import com.yeqifu.sys.req.MessageReq;
import com.yeqifu.sys.service.IMessageService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.ResultObj;
import com.yeqifu.sys.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 系统留言控制器
 */
@Slf4j
@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    /**
     * 加载留言列表返回DataGridView
     *
     * @param req
     * @return
     */
    @RequestMapping("loadAllMessage")
    public DataGridView loadAllMessage(MessageReq req) {
        log.info("留言列表:{}", req);
        return messageService.queryAllMessage(req);
    }

    /**
     * 添加留言
     *
     * @param req
     * @return
     */
    @RequestMapping("addMessage")
    public ResultObj addMessage(@Validated AddOrUpdateMessageReq req) {
        log.info("添加留言:{}", req);
        try {
            req.setCreatetime(new Date());
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            req.setOpername(user.getRealname());
            messageService.addMessage(req);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除留言
     *
     * @param id
     * @return
     */
    @GetMapping("deleteMessage")
    public ResultObj deleteMessage(@RequestParam("id") Long id) {
        log.info("删除留言:{}", id);
        try {
            messageService.deleteMessage(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除留言
     *
     * @param req
     * @return
     */
    @PostMapping("deleteBatchMessage")
    public ResultObj deleteBatchMessage(MessageReq req) {
        log.info("批量删除留言:{}", req);
        try {
            messageService.deleteBatchMessage(req.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 更新留言
     *
     * @param req
     * @return
     */
    @PostMapping("updateMessage")
    public ResultObj updateMessage(@Validated AddOrUpdateMessageReq req) {
        log.info("更新留言:{}", req);
        try {
            messageService.updateMessage(req);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id查询留言
     *
     * @param id
     * @return
     */
    @GetMapping("loadMessageById")
    public Message loadMessageById(@RequestParam("id") Long id) {
        log.info("根据id查询留言:{}", id);
        return messageService.loadMessageById(id);
    }

}
