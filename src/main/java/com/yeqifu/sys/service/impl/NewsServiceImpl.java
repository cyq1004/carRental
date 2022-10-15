package com.yeqifu.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yeqifu.sys.domain.News;
import com.yeqifu.sys.mapper.NewsMapper;
import com.yeqifu.sys.req.AddOrUpdateNewsReq;
import com.yeqifu.sys.req.NewsReq;
import com.yeqifu.sys.service.INewsService;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查询所有
     *
     * @param newsVo
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
        newsMapper.addNews(news);
    }

    /**
     * 删除一条公告
     *
     * @param id
     */
    @Override
    public void deleteNews(Long id) {
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
