package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 页面查询接口
 *
 * @author yanger
 */
@Api(value = "cms页面管理接口", description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {
    /**
     * 页面查询接口
     *
     * @param page             分页参数：当前页面
     * @param size             分页参数：每页显示记录数
     * @param queryPageRequest 请求的模型类型
     * @return 响应结果类型
     */
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true,
                    paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true,
                    paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * 获取站点信息
     *
     * @return 响应结果类型
     */
    @ApiOperation("查询所有站点列表")
    QueryResponseResult getSiteList();

    /**
     * cms_page数据的唯一性，需要在cms_page集中上创建页面名称、站点Id、页面webpath为唯一索引。
     *
     * @param cmsPage 需要保存的页面
     * @return 响应结果信息
     */
    @ApiOperation("添加页面")
    public CmsPageResult add(CmsPage cmsPage);

    /**
     * 根据id查询页面信息
     *
     * @param id -- String CmsPage的Id值
     * @return Cms定义的规范响数据
     */
    @ApiOperation("根据id查询页面信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "Id值", required = true,
                    paramType = "path", dataType = "String")
    )
    CmsPageResult findById(String id);

    /**
     * 根据CmsPage的Id值修改CmsPage数据
     * @param id   String  CmsPage的Id值
     * @param cmsPage 要修改的CmsPage数据
     * @return CmsPageResult Cms定义的规范响数据
     */
    @ApiOperation("根据id修改页面")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "Id值", required = true,
                    paramType = "path", dataType = "String")
    )
    CmsPageResult edit(String id, CmsPage cmsPage);

    /**
     * 根据CmsPage的Id删除CmsPage数据
     *
     * @param id String CmsPage的Id值
     * @return ResponseResult Api定义的通用规范响数据
     */
    @ApiOperation("通过ID删除页面")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "Id值", required = true,
                    paramType = "path", dataType = "String")
    )
    public ResponseResult delete(String id);


    /**
     * 获取模板信息
     *
     * @return 响应结果类型
     */
    @ApiOperation("获取模板信息列表")
    QueryResponseResult getTemplateList();
}
