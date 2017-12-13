基于SpringBoot的博客系统，目前版本是1.5.4.RELEASE，欢迎大家Mark学习。码云地址：https://gitee.com/dairui520/blog
# blog(博客系统)
  基于spring-boot搭建的博客系统，集成了redis 对 session的管理（可以删除配置，改成web容器管理），使用了Mybatis、CommonMapper、PageHelper对数据进行   操作。博客图片可以通过nginx进行静态资源代理
  
## blog-web
  主要包括 模板、拦截器、控制器等，前端方面简单使用了bootstrap和jquery，博文编辑主要使用了ckeditor  
  
  
## blog-api
   该模块主要提供了接口和对外实体对象
   
## blog-service
服务层，以及包含了数据持久操作、缓存数据操作
## blog-util
通用工具
## blog-crawler
oschina博客爬虫
## 2016-11-30新增
增加了管理员注册功能（只允许服务器本地注册）
/ 访问博客首页
/back 登陆博客编辑页
/back/register 管理员注册
## 2016-12-15新增
/back/crawler oschina博客爬取

## 说明
基于TIM前辈的blog开源项目：https://gitee.com/catshen/blog.git 的二次开发<br/>
1.项目所需jdk8，数据库mysql<br/>
2.数据库脚本在项目根目录中，请提前创建数据库blog，执行blog.sql<br/>
3.由于之前开启了@EnableRedisHttpSession ，没有正确配置redis的会启动报错，因此把这个关闭了，有需要可以在blog-common下的conf包下修改<br/>
4.直接运行application，即可执行项目
