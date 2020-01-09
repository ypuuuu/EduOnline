package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 接收请求的查询条件
 * @author yanger
 */
@Data
@ToString
public class QueryPageRequest {
    /**
     * 站点id
     */
    @ApiModelProperty("站点id")
    private String siteId;
    /**
     * 页面id
     */
    @ApiModelProperty("页面id")
    private String pageId;
    /**
     * 页面名称
     */
    @ApiModelProperty("页面名称")
    private String pageName;
    /**
     * 别名  
     */
    @ApiModelProperty("页面别名")
    private String pageAliase;
    /**
     * 模版id
     */
    @ApiModelProperty("模板id")
    private String templateId;



}
