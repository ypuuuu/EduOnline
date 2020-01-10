package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.dao.CmsSiteRepository;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 * @program: xcEduService01
 * @description: 页面服务
 * @author: Mr.Yang
 * @create: 2020-01-07 13:34
 **/
@Service
public class PageService {
    private static Logger LOGGER = LoggerFactory.getLogger(PageService.class);

    private final CmsPageRepository cmsPageRepository;

    private final CmsSiteRepository cmsSiteRepository;

    private final CmsTemplateRepository cmsTemplateRepository;

    @Autowired
    public PageService(CmsPageRepository cmsPageRepository, CmsSiteRepository cmsSiteRepository, CmsTemplateRepository cmsTemplateRepository) {
        this.cmsPageRepository = cmsPageRepository;
        this.cmsSiteRepository = cmsSiteRepository;
        this.cmsTemplateRepository = cmsTemplateRepository;
    }

    /**
     * 页面查询方法
     *
     * @param page             页码，用户输入从1开始
     * @param size             每页记录数
     * @param queryPageRequest 查询条件
     * @return 页面列表
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        LOGGER.debug("queryPageRequest" + queryPageRequest);
        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        if (size <= 0) {
            size = 20;
        }
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //查询数据库
        //构造匹配器
        ExampleMatcher matching = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //模糊查询站点别名

        //条件值
        CmsPage cmsPage = new CmsPage();
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        //创建条件实例
        Example<CmsPage> cmsPageExample = Example.of(cmsPage, matching);

        //分页 条件 查询
        Page<CmsPage> all = cmsPageRepository.findAll(cmsPageExample, pageable);
        if (ObjectUtils.isEmpty(all)) {
            return new QueryResponseResult(CommonCode.FAIL, null);
        } else {
            QueryResult<CmsPage> queryResult = new QueryResult<>();
            //数据列表
            queryResult.setList(all.getContent());
            //数据总记录数
            queryResult.setTotal(all.getTotalElements());
            return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        }
    }

    /**
     * 获取站点信息
     *
     * @return 响应结果类型
     */
    public QueryResponseResult getSiteList() {
        List<CmsSite> all = cmsSiteRepository.findAll();
        if (ObjectUtils.isEmpty(all)) {
            return new QueryResponseResult(CommonCode.FAIL, null);
        } else {
            QueryResult<CmsSite> queryResult = new QueryResult<>();
            //数据列表
            queryResult.setList(all);
            //数据总记录数
            queryResult.setTotal(all.size());
            return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        }
    }

    /**
     * cmsPage添加方法
     *
     * @param cmsPage 需要添加的cmsPage数据
     * @return CmsPageResult Api接口规范的响应结果对象
     */
    public CmsPageResult add(CmsPage cmsPage) {
        //1.如果传来的cmsPage数据为空，返回失败结果
        if (ObjectUtils.isEmpty(cmsPage)) {
            return new CmsPageResult(CommonCode.FAIL, null);
        }
        //2.根据页面名称、站点Id、页面访问路径三个值的唯一性判断cmsPage是否存在
        List<CmsPage> bySiteIdAndPageNameAndDataUrl = cmsPageRepository.findBySiteIdAndPageNameAndDataUrl(cmsPage.getSiteId(), cmsPage.getPageName(), cmsPage.getDataUrl());
        if (ObjectUtils.isEmpty(bySiteIdAndPageNameAndDataUrl)) {
            //3.如果mongodb中没有对应的数据，再保存cmsPage数据
            //为了确保数据的新增，可以设置cmsPage的Id为空
            cmsPage.setPageId(null);
            cmsPageRepository.save(cmsPage);
            //4.保存完毕，返回成果的结果
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
        } else {
            return new CmsPageResult(CommonCode.FAIL, null);
        }
    }

    /**
     * @param id cmspage 的ID
     * @return CmsPageResult Api接口规范的响应结果对象
     */
    public CmsPageResult getById(String id) {
        LOGGER.debug("id: " + id);
        if (StringUtils.isNotBlank(id)) {
            Optional<CmsPage> optionalCmsPage = cmsPageRepository.findById(id);
            if (optionalCmsPage.isPresent()) {
                LOGGER.info("optionalCmsPage is present.");
                CmsPage cmsPage = optionalCmsPage.get();
                return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
            } else {
                return new CmsPageResult(CommonCode.FAIL, null);
            }
        } else {
            return new CmsPageResult(CommonCode.FAIL, null);
        }
    }

    /**
     * @param id      cmspage 的ID
     * @param cmsPage 具体修改的信息
     * @return CmsPageResult Api接口规范的响应结果对象
     */
    public CmsPageResult update(String id, CmsPage cmsPage) {
        LOGGER.debug("id: " + id);
        LOGGER.debug("cmsPage: " + cmsPage);
        if (StringUtils.isBlank(id) || ObjectUtils.isEmpty(cmsPage)) {
            return new CmsPageResult(CommonCode.FAIL, cmsPage);
        }
        //根据id查询
        CmsPageResult cmsPageResult = this.getById(id);
        LOGGER.debug("cmsPageResult: " + cmsPageResult);
        if (cmsPageResult.isSuccess()) {
            CmsPage cmsPageFindById = cmsPageResult.getCmsPage();
            //更新模板id
            cmsPageFindById.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            cmsPageFindById.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            cmsPageFindById.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            cmsPageFindById.setPageName(cmsPage.getPageName());
            //更新访问路径
            cmsPageFindById.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            cmsPageFindById.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //更新dataUrl
            cmsPageFindById.setDataUrl(cmsPage.getDataUrl());
            //4.执行更新
            CmsPage save = cmsPageRepository.save(cmsPageFindById);
            LOGGER.debug("save: " + save);
            if (ObjectUtils.isEmpty(save)) {
                //7.返回修改失败的信息
                return new CmsPageResult(CommonCode.FAIL, cmsPage);
            } else {
                //6.返回成功结果
                return new CmsPageResult(CommonCode.SUCCESS, save);
            }
        } else {
            //6.返回成功结果
            return new CmsPageResult(CommonCode.FAIL, cmsPage);
        }
    }
    /**
     * 根据CmsPage的Id删除CmsPage数据
     *
     * @param id String CmsPage的Id值
     * @return ResponseResult Api定义的通用规范响数据
     */
    public ResponseResult delete(String id) {
        if (StringUtils.isNotBlank(id)){
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        } else {
            return new ResponseResult(CommonCode.FAIL);
        }
    }

    /**
     * 查询 CmsTemplate集合中该有模板
     * @return 响应结果类型
     */
    public QueryResponseResult getTemplateList() {
        List<CmsTemplate> all = cmsTemplateRepository.findAll();
        QueryResult<CmsTemplate> cmsTemplateQueryResult = new QueryResult<>();
        cmsTemplateQueryResult.setList(all);
        cmsTemplateQueryResult.setTotal(all.size());
        return new QueryResponseResult(CommonCode.SUCCESS,cmsTemplateQueryResult);
    }
}
