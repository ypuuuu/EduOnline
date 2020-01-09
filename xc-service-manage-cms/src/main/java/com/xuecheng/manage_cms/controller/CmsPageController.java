package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResultCode;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: xcEduService01
 * @description: CmsPageController的实现类
 * @author: Mr.Yang
 * @create: 2020-01-07 11:13
 **/
@Controller
@ResponseBody //将相应的结果集转为JSON
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private PageService pageService;
    /**
     * 页面查询接口
     *GetMapping相当于RequestMapping请求格式指定为GET
     * @param page  分页参数：当前页面
     * @param size  分页参数：每页显示记录数
     * @param queryPageRequest 请求的模型类型
     * @return 响应结果类型
     */
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size")  int size, QueryPageRequest queryPageRequest) {

        return pageService.findList(page,size,queryPageRequest);
    }

    /**
     * 获取站点信息
     *
     * @return 响应结果类型
     */
    @Override
    @GetMapping("/site/list")
    public QueryResponseResult getSiteList() {
        return pageService.getSiteList();
    }

    /**
     * cms_page数据的唯一性，需要在cms_page集中上创建页面名称、站点Id、页面webpath为唯一索引。
     *
     * @param cmsPage 需要保存的页面
     * @return 响应结果信息
     */
    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return pageService.add(cmsPage);
    }


}
