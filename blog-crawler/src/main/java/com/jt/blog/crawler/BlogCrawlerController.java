package com.jt.blog.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 戴瑞
 * @create 2016-12-10 11
 **/
public class BlogCrawlerController {

    private static Map<Long,Integer> statusMap = Collections.synchronizedMap(new HashMap<Long,Integer>());

    public static void crawler(BlogParserRegEx regEx) throws Exception{
        statusMap.remove(regEx.getUserId());
        BlogParser.getInstance().init(regEx);

        //定义爬虫数据存储位置
        String crawlStorageFolder = "/data/crawl/root";
        // 定义1个爬虫，也就是1个线程
        int numberOfCrawlers = 1;

        // 创建爬虫配置类
        CrawlConfig config = new CrawlConfig();

        // 定义爬虫数据存储位置
        config.setCrawlStorageFolder(crawlStorageFolder);
        // 设置断开重连
        config.setResumableCrawling(true);
        // 实例化页面获取器
        PageFetcher pageFetcher = new PageFetcher(config);

        // 实例化爬虫机器人配置 比如可以设置 user-agent
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setUserAgentName("tim-spider");

        // 实例化爬虫机器人对目标服务器的配置，每个网站都有一个robots.txt文件
        // 规定了该网站哪些页面可以爬，哪些页面禁止爬，该类是对robots.txt规范的实现
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        // 实例化爬虫控制器
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /**
         * 配置爬虫种子页面，就是规定的从哪里开始爬，可以配置多个种子页面
         */
        controller.addSeed(regEx.getSeedUrl());

        /**
         * 启动爬虫，爬虫从此刻开始执行爬虫任务，根据以上配置
         */
        controller.start(BlogCrawler.class, numberOfCrawlers);
        statusMap.put(regEx.getUserId(),1);
    }

    public static Integer status(Long userId){
        return statusMap.get(userId);
    }

    public static void setStatus(Long userId,Integer status){
        statusMap.put(userId,status);
    }
}
