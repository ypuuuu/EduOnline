package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.exception.ExceptionCatch;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.manage_cms.dao.CmsConfigRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @program: xcEduService01
 * @description: 页面配置服务
 * @author: Mr.Yang
 * @create: 2020-01-10 13:08
 **/
@Service
public class ConfigService {
    private static Logger LOGGER = LoggerFactory.getLogger(ConfigService.class);

    private final CmsConfigRepository cmsConfigRepository;

    @Autowired
    public ConfigService(CmsConfigRepository cmsConfigRepository) {
        this.cmsConfigRepository = cmsConfigRepository;
    }

    /**
     * 根据主键id 获取配置信息
     * @param id 配置文档id
     * @return 配置文档
     */
    public CmsConfig getConfigById(String id){
        LOGGER.debug("id: " + id);
        Optional<CmsConfig> byId = cmsConfigRepository.findById(id);
        return byId.orElse(null);
    }

}
