package com.jt.blog.service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.jt.blog.common.model.Page;
import com.jt.blog.common.model.Response;
import com.jt.blog.mapper.BlogMapper;
import com.jt.blog.model.Blog;
import com.jt.blog.model.BlogStatistics;
import com.jt.blog.vo.BlogQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 戴瑞
 * @Description :博客服务层的读操作
 * @create : 2017-05-25 18:20
 **/
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class BlogReadServiceImpl extends AbstractBaseService<Blog> implements BlogReadService {

    @Autowired
    BlogStatisticsService blogStatisticsService;

    /**
     * 分页查询
     * @param page 分页通用对象
     * @param query 分页查询的条件
     * @return 查询通用结果对象 Response
     */
    public Response<Page<Blog>> page(Page<Blog> page,BlogQuery query) {
        Response<Page<Blog>> response = new Response<Page<Blog>>();
        try{
            PageHelper.startPage(page.getPageNo(),page.getPageSize(),"id desc");
            Example example = new Example(Blog.class);
            Example.Criteria criteria = example.createCriteria();
            if(null!=query){
                if(StringUtils.isNotEmpty(query.getTitle())){
                    criteria.andLike("title","%"+query.getTitle()+"%");
                }
                if(query.getCategoryId()!=null){
                    criteria.andEqualTo("categoryId",query.getCategoryId());
                }
                if(query.getBeginTime()!=null){
                    criteria.andBetween("createTime",query.getBeginTime(),query.getEndTime());
                }
                if(query.getPersonal()!=null){
                    criteria.andEqualTo("personal",query.getPersonal());
                }
            }
            List<Blog> blogs = mapper.selectByExample(example);
            // 获取博客的统计数据
            wrapBlogStatistics(blogs);
            // 创建分页信息对象
            PageInfo<Blog> temp = new PageInfo<Blog>(blogs);
            page.setData(temp.getList());
            page.setTotal(temp.getTotal());
            page.setPageMax(temp.getLastPage());
            response.setResult(page);
        }catch (Exception e){
            logger.error("blog page fail, params = {}, query = {}", query, Throwables.getStackTraceAsString(e));
            response.setError("blog.page.fail");
        }
        return response;
    }

    /**
     * 根据ID获取博客信息
     * @param id
     * @return
     */
    public Response<Blog> get(Long id) {
        Response<Blog> response = new Response<Blog>();
        try {
            Example example = new Example(Blog.class);
            Example.Criteria criteria = example.createCriteria();
            Blog blog = mapper.selectByPrimaryKey(id);
            // 查询完blog后在查询改博客的统计表
            BlogStatistics blogStatistics = blogStatisticsService.get(id);
            blog.setBlogStatistics(blogStatistics);
            response.setResult(blog);
        } catch (Exception e){
            logger.error("blog get fail, id = {}, error = {}", id, Throwables.getStackTraceAsString(e));
            response.setError("blog.get.fail");
        }
        return response;
    }

    /**
     * 根据用户ID和博客ID获取博客
     * @param id
     * @param userId
     * @return
     */
    @Override
    public Response<Blog> get(Long id, Long userId) {
        Response<Blog> response = new Response<Blog>();
        try {
            Example example = new Example(Blog.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id",id);
            criteria.andEqualTo("userId",userId);
            List<Blog> blogs =  mapper.selectByExample(example);
            if(blogs!=null&&blogs.size()>0){
                Blog blog = blogs.get(0);
                BlogStatistics blogStatistics = blogStatisticsService.get(id);
                blog.setBlogStatistics(blogStatistics);
                response.setResult(blog);
            }else{
                throw new Exception("没有该博文");
            }
        } catch (Exception e){
            logger.error("blog get fail, id = {}, error = {}", id, Throwables.getStackTraceAsString(e));
            response.setError("blog.get.fail");
        }
        return response;
    }

    /**
     * 根据博客地址URL 判断数据库中是否存在此博客
     * 如果存在就不存入数据库中去
     * @param url
     * @return
     */
    @Override
    public boolean isExist(String url) {
        if(StringUtils.isNotEmpty(url)){
            Example example = new Example(Blog.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("sourceUrl",url);
            List<Blog> blogs =  mapper.selectByExample(example);
            if(blogs!=null&&blogs.size()>0){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取当前博客的上一篇和下一篇
     * @param id
     * @return
     */
    public Response<Map<String, Blog>> getPrevAndNextBlog(Long id) {
        Response<Map<String, Blog>> response = new Response<Map<String, Blog>>();
        Map<String, Blog> map = new HashMap<String, Blog>();
        try {
            BlogMapper blogMapper = (BlogMapper) mapper;
            List<Blog> blogs = blogMapper.getPrevAndNextBlog(id);
            if(blogs!=null&&blogs.size()>0){
                for(Blog temp : blogs){
                    Long tempId = temp.getId();
                    if(tempId>id){
                        map.put("next",temp);
                    }else{
                        map.put("prev",temp);
                    }
                }
            }
            response.setResult(map);
        } catch (Exception e){
            logger.error("blog get fail, id = {}, error = {}", id, Throwables.getStackTraceAsString(e));
            response.setError("blog.get.fail");
        }
        return response;
    }

    /**
     * 私有方法
     * 查询blog的统计表并赋值到
     * @param blogs
     */
    private void wrapBlogStatistics(List<Blog> blogs){
        try {
            for(Blog blog : blogs){
                Long blogId = blog.getId();
                BlogStatistics blogStatistics = blogStatisticsService.get(blogId);
                blog.setBlogStatistics(blogStatistics);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
