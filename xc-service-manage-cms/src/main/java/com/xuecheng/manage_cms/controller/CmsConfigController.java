package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsConfigControllerApi;
import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: xcEduService01
 * @description: cms配置管理接口，提供数据模型的管理和查询
 * @author: Mr.Yang
 * @create: 2020-01-10 13:04
 **/
@RestController
@RequestMapping("/cms/config")
public class CmsConfigController implements CmsConfigControllerApi {

    private final ConfigService configService;

    @Autowired
    public CmsConfigController(ConfigService configService) {
        this.configService = configService;
    }

    /**
     *
     * 根据id查询配置信息
     * @param id 数据模型的id
     * @return 页面配置信息
     */
    @Override
    @GetMapping("/getmodel/{id}")
    public CmsConfig getModel(@PathVariable("id") String id) {
        return configService.getConfigById(id);
    }
}
