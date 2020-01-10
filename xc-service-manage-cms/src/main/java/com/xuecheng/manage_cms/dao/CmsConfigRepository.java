package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @program: xcEduService01
 * @description: cmsConfig集合的模型类
 * @author: Mr.Yang
 * @create: 2020-01-10 13:06
 **/
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {

}
