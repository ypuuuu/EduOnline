package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 *
 * MongoRepository<CmsPage,String> 指定实体类型和主键类型
 * @program: xcEduService01
 * @description: 集合CmsPage的模型类
 * @author: Mr.Yang
 * @create: 2020-01-07 12:28
 **/
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
    /**
     * 根据页面名称、站点Id、页面访问路径三个值查询
     * @param siteId  站点Id
     * @param pageName 页面名称
     * @param dataUrl 页面访问路径
     * @return 查询到的页面列表
     */
    List<CmsPage> findBySiteIdAndPageNameAndDataUrl(String siteId,String pageName,String dataUrl);
}
