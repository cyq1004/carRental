package com.yeqifu.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.sys.domain.Log;
import com.yeqifu.sys.domain.News;
import com.yeqifu.sys.domain.User;
import com.yeqifu.sys.mapper.NewsMapper;
import com.yeqifu.sys.req.AddOrUpdateNewsReq;
import com.yeqifu.sys.req.NewsReq;
import com.yeqifu.sys.service.ILogService;
import com.yeqifu.sys.service.INewsService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.utils.WebUtils;
import com.yeqifu.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private ILogService iLogService;

    /**
     * 查询所有
     *
     * @param req
     * @return
     */
    @Override
    public DataGridView loadAllNews(NewsReq req) {
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<News> data = newsMapper.loadAllNews(req);
        return new DataGridView(page.getTotal(), data);
    }

    /**
     * 添加公告
     *
     * @param news
     */
    @Override
    public void addNews(News news) {
        //记录操作日志 向sys_log里面插入数据
        User logUser = (User) WebUtils.getHttpSession().getAttribute("user");
        Log log = new Log();
        log.setLogname(logUser.getRealname());
        log.setLogip(WebUtils.getHttpServletRequest().getRemoteAddr());
        log.setLogtime(new Date());
        log.setLog("添加公告"+news.getTitle());
        iLogService.addLog(log);

        newsMapper.addNews(news);
    }

    /**
     * 删除一条公告
     *
     * @param id
     */
    @Override
    public void deleteNews(Long id) {
        //记录操作日志 向sys_log里面插入数据
        User logUser = (User) WebUtils.getHttpSession().getAttribute("user");
        Log log = new Log();
        log.setLogname(logUser.getRealname());
        log.setLogip(WebUtils.getHttpServletRequest().getRemoteAddr());
        log.setLogtime(new Date());
        log.setLog("删除一条公告");
        iLogService.addLog(log);

        newsMapper.deleteNews(id);
    }

    /**
     * 批量删除公告
     *
     * @param ids
     */
    @Override
    public void deleteBatchNews(Long[] ids) {
        for (Long id : ids) {
            deleteNews(id);
        }
    }

    /**
     * 更新公告
     *
     * @param req
     */
    @Override
    public void updateNews(AddOrUpdateNewsReq req) {
        //记录操作日志 向sys_log里面插入数据
        User logUser = (User) WebUtils.getHttpSession().getAttribute("user");
        Log log = new Log();
        log.setLogname(logUser.getRealname());
        log.setLogip(WebUtils.getHttpServletRequest().getRemoteAddr());
        log.setLogtime(new Date());
        log.setLog("更新公告"+req.getTitle());
        iLogService.addLog(log);

        newsMapper.updateNews(req);
    }

    /**
     * 根据id查询公告
     *
     * @param id
     * @return
     */
    @Override
    public News loadNewsById(Long id) {
        return newsMapper.loadNewsById(id);
    }

}
