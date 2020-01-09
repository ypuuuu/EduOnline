package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @program: xcEduService01
 * @description: cms模板集合的模型接口
 * @author: Mr.Yang
 * @create: 2020-01-09 12:47
 **/
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate,String> {
}
