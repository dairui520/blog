package com.jt.blog.crawler;

import com.jt.blog.model.Blog;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.htmlparser.util.ParserException;

import java.util.Date;

/**
 * 爬虫实现类
 * @author : 戴瑞
 * @create 2016-08-30 10
 **/
public class BlogCrawler extends WebCrawler {

    /**
     * 这个方法主要是决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
     * 第一个参数referringPage封装了当前爬取的页面信息
     * 第二个参数url封装了当前爬取的页面url信息
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        boolean flag=false;
        try {
            flag =  BlogParser.getInstance().isMatch(href);
        } catch (NoRegExException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 当我们爬到我们需要的页面，这个方法会被调用，我们可以尽情的处理这个页面
     * page参数封装了所有爬取页面信息
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL().toLowerCase();

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            BlogParser parser = BlogParser.getInstance();
            try {
                boolean flag = parser.isMatch(url);
                if (flag){
                    boolean existFlag = BlogOperationUtil.blogReadService.isExist(url);
                    if(!existFlag){
                        String html = htmlParseData.getHtml();
                        parser.init(html);
                        Blog blog = new Blog();
                        blog.setUserId(parser.getUserId());
                        blog.setTitle("转:"+parser.getTitle());
                        blog.setSubject(parser.getAbstract());
                        String sourceUrl = "<p>来源：<a href='"+url+"'>"+url+"</a></p>";
                        blog.setContent(sourceUrl+parser.getContent());
                        blog.setSourceUrl(url);
                        blog.setPersonal(1);
                        blog.setCategoryId(0L);
                        blog.setCreateTime(new Date());
                        blog.setLastModifyTime(new Date());
                        BlogOperationUtil.blogWriteService.create(blog);
                    }
                }
            }catch (ParserException e) {
                e.printStackTrace();
            } catch (NoRegExException e) {
                e.printStackTrace();
            }

        }
    }

}
