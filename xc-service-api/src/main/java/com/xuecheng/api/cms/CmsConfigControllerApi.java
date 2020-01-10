package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @program: xcEduService01
 * @description: 页面数据模型集合
 * @author: Mr.Yang
 * @create: 2020-01-10 12:57
 **/
@Api(value = "cms配置管理接口", description = "cms配置管理接口，提供数据模型的管理和查询")
public interface CmsConfigControllerApi {

    /**
     * @param id 数据模型的id
     * @return 页面配置信息
     */
    @ApiOperation("根据id查询CMS配置信息")
    @ApiImplicitParam(name = "id", value = "配置Id值", required = true,
            paramType = "path", dataType = "String")
    public CmsConfig getModel(String id);



}
