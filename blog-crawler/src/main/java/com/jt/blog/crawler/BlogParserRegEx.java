package com.jt.blog.crawler;

/**
 * @author : 戴瑞
 * @Description: 设置爬虫正则规则的前台pojo
 * @create 2016-12-09 21
 **/
public class BlogParserRegEx {

    private Long userId;

    private String seedUrl;

    private String urlRegEx;

    private String titleRegEx;

    private String abstractRegEx;

    private String contentRegEx;

    private String keywordsRegEx;

    private String authorRegEx;

    public BlogParserRegEx() {
    }

    public BlogParserRegEx(String seedUrl, String urlRegEx, String titleRegEx, String abstractRegEx, String contentRegEx, String keywordsRegEx, String authorRegEx) {
        this.seedUrl = seedUrl;
        this.urlRegEx = urlRegEx;
        this.titleRegEx = titleRegEx;
        this.abstractRegEx = abstractRegEx;
        this.contentRegEx = contentRegEx;
        this.keywordsRegEx = keywordsRegEx;
        this.authorRegEx = authorRegEx;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSeedUrl() {
        return seedUrl;
    }

    public void setSeedUrl(String seedUrl) {
        this.seedUrl = seedUrl;
    }

    public String getUrlRegEx() {
        return urlRegEx;
    }

    public void setUrlRegEx(String urlRegEx) {
        this.urlRegEx = urlRegEx;
    }

    public String getTitleRegEx() {
        return titleRegEx;
    }

    public void setTitleRegEx(String titleRegEx) {
        this.titleRegEx = titleRegEx;
    }

    public String getAbstractRegEx() {
        return abstractRegEx;
    }

    public void setAbstractRegEx(String abstractRegEx) {
        this.abstractRegEx = abstractRegEx;
    }

    public String getContentRegEx() {
        return contentRegEx;
    }

    public void setContentRegEx(String contentRegEx) {
        this.contentRegEx = contentRegEx;
    }

    public String getKeywordsRegEx() {
        return keywordsRegEx;
    }

    public void setKeywordsRegEx(String keywordsRegEx) {
        this.keywordsRegEx = keywordsRegEx;
    }

    public String getAuthorRegEx() {
        return authorRegEx;
    }

    public void setAuthorRegEx(String authorRegEx) {
        this.authorRegEx = authorRegEx;
    }
}
