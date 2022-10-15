package com.yeqifu.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yeqifu.sys.domain.News;
import com.yeqifu.sys.domain.User;
import com.yeqifu.sys.req.AddOrUpdateNewsReq;
import com.yeqifu.sys.req.NewsReq;
import com.yeqifu.sys.service.INewsService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.ResultObj;
import com.yeqifu.sys.utils.WebUtils;
import com.yeqifu.sys.vo.NewsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 系统公告控制器
 */
@Slf4j
@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private INewsService newsService;

    /**
     * 加载公告列表返回DataGridView
     *
     * @param req
     * @return
     */
    @PostMapping("loadAllNews")
    public DataGridView loadAllNews(NewsReq req) {
        return newsService.loadAllNews(req);
    }

    /**
     * 添加公告
     *
     * @param req
     * @return
     */
    @RequestMapping("addNews")
    public ResultObj addNews(AddOrUpdateNewsReq req) {
        try {
            News news = new News();
            BeanUtil.copyProperties(req, news);
            news.setCreatetime(new Date());
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            news.setOpername(user.getRealname());
            newsService.addNews(news);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    @GetMapping("deleteNews")
    public ResultObj deleteNews(@RequestParam("id") Long id) {
        try {
            newsService.deleteNews(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     *
     * @param req
     * @return
     */
    @PostMapping("deleteBatchNews")
    public ResultObj deleteBatchNews(NewsReq req) {
        try {
            newsService.deleteBatchNews(req.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 更新公告
     *
     * @param req
     * @return
     */
    @RequestMapping("updateNews")
    public ResultObj updateNews(AddOrUpdateNewsReq req) {
        try {
            newsService.updateNews(req);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id查询公告
     */
    @GetMapping("loadNewsById")
    public News loadNewsById(@RequestParam("id") Long id) {
        return newsService.loadNewsById(id);
    }

}
