package com.yeqifu.sys.service;

import com.yeqifu.sys.domain.News;
import com.yeqifu.sys.req.AddOrUpdateNewsReq;
import com.yeqifu.sys.req.NewsReq;
import com.yeqifu.sys.utils.DataGridView;
import com.yeqifu.sys.vo.NewsVo;

public interface INewsService {

    /**
     * 查询所有公告
     *
     * @param req
     * @return
     */
    DataGridView loadAllNews(NewsReq req);

    /**
     * 添加公告
     *
     * @param news
     */
    void addNews(News news);

    /**
     * 删除公告
     *
     * @param id
     */
    void deleteNews(Long id);

    /**
     * 批量删除公告
     *
     * @param ids
     */
    void deleteBatchNews(Long[] ids);

    /**
     * 更新公告
     *
     * @param req
     */
     void updateNews(AddOrUpdateNewsReq req);

    /**
     * 根据id查询公告
     *
     * @param id
     * @return
     */
    News loadNewsById(Long id);

}
