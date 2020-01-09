package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @program: xcEduService01
 * @description: 集合cmsSite的模型接口
 * @author: Mr.Yang
 * @create: 2020-01-09 10:04
 **/
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {
}
