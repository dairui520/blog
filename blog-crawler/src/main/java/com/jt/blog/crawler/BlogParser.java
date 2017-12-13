package com.jt.blog.crawler;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.regex.Pattern;

/**
 * @author : 戴瑞
 * @Description :爬虫数据匹配,采用单例模式
 * @create 2016-12-09 21
 **/
public class BlogParser {

    public final static Object LOCK = new Object();

    private static BlogParser parser;

    private BlogParserRegEx regEx;// 前台博客匹配pojo

    private String source;

    private BlogParser(){}

    public static BlogParser getInstance(){
        if(parser==null){
            synchronized (LOCK){
                if(parser==null){
                    parser = new BlogParser();
                }
            }
        }
        return parser;
    }


    public void init(BlogParserRegEx regEx){
        this.regEx = regEx;
    }

    public void init(String source){
        this.source = source;
    }

    /**
     * 匹配
     * @param url
     * @return
     * @throws NoRegExException
     */
    public boolean isMatch(String url) throws NoRegExException{
        String temp = regEx.getUrlRegEx();
        if(StringUtils.isNotEmpty(temp)){
            Pattern BLOG_REG = Pattern.compile(temp);
            return BLOG_REG.matcher(url).matches();
        }
        throw new NoRegExException("没有路径解析规则");
    }
    public Long getUserId(){
        return regEx.getUserId();
    }

    public String getTitle() throws NoRegExException,ParserException{
        String temp = regEx.getTitleRegEx();
        if(StringUtils.isNotEmpty(temp)){
            String title = getFilterNodesHtml(temp);
            return title.substring(title.lastIndexOf(">")+1).trim();
        }
        throw new NoRegExException("没有标题解析规则");
    }

    public String getAbstract() throws NoRegExException,ParserException{
        String temp = regEx.getAbstractRegEx();
        if(StringUtils.isNotEmpty(temp)){
            return getFilterNodesHtml(temp);
        }
        throw new NoRegExException("没有摘要解析规则");
    }

    public String getContent() throws NoRegExException,ParserException{
        String temp = regEx.getContentRegEx();
        if(StringUtils.isNotEmpty(temp)){
            return getFilterNodesHtml(temp);
        }
        throw new NoRegExException("没有内容解析规则");
    }

    /**
     * 提取爬取页面所需要的信息
     * @param regEx
     * @return
     * @throws ParserException
     */
    private String getFilterNodesHtml(String regEx) throws ParserException{
        // 实例化解析对象
        Parser parser = new Parser(source);
        // css选择器进行过滤
        NodeFilter contentFilter = new CssSelectorNodeFilter(regEx);
        // extract 提取过滤后的所有树结点
        NodeList contentList = parser.extractAllNodesThatMatch(contentFilter);
        if(contentList!=null&&contentList.size()>0){
            return contentList.elementAt(0).getChildren().toHtml().replaceAll("\n","").trim();
        }
        return null;
    }
}
