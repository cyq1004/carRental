package com.yeqifu.sys.mapper;

import com.yeqifu.sys.domain.News;
import com.yeqifu.sys.req.AddOrUpdateNewsReq;
import com.yeqifu.sys.req.NewsReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    /**
     * 查询所有公告
     *
     * @param req
     * @return
     */
    List<News> loadAllNews(@Param("req") NewsReq req);

    /**
     * 添加公告
     *
     * @param news
     * @return
     */
    int addNews(@Param("news") News news);

    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    int deleteNews(@Param("id") Long id);

    /**
     * 更新公告
     *
     * @param req
     * @return
     */
    int updateNews(@Param("req") AddOrUpdateNewsReq req);

    /**
     * 查询公告
     * @param id
     * @return
     */
    News loadNewsById(@Param("id") Long id);
}