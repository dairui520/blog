<#include "layout/layout.ftl">
<#macro overrideHead>
<title>${blog.title!''}</title>
</#macro>
<#macro overrideContainer>
<!-- Page Content -->
<div class="container" style="background-color: #fff;padding-top:20px;-moz-box-shadow:2px 2px 20px #333333; -webkit-box-shadow:2px 2px 20px #333333; box-shadow:2px 2px 20px #333333;">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-12">
            <div class="blog-one">
                <h2>
                    <a href="/blog/${blog.id}" class="blog-title-new">${blog.title!''}</a>
                </h2>
                <p><span><i class="fa fa-eye" aria-hidden="true"></i> 0</span> . <span><i class="fa fa-comments-o" aria-hidden="true"></i> 0</span></p>
                <p><span class="glyphicon glyphicon-time"></span> Posted on ${(blog.createTime?string("yyyy-MM-dd"))!} </p>
                <hr>
                <div class="blog-subject">
                    <p>${blog.subject!''}</p>
                </div>
                <hr>
                <div class="blog-content" style="overflow-x: hidden;">
                ${blog.content!''}
                </div>
                <hr>
            </div>

            <nav>
                <ul class="pager">
                    <#if map.prev??>
                        <li class="previous"><a href="/blog/${map.prev.id}" style="border: 0px;border-radius: 0px;"><span aria-hidden="true">上一篇：</span> ${map.prev.title}</a></li>
                    </#if>
                    <#if map.next??>
                        <li class="next"><a href="/blog/${map.next.id}" style="border: 0px;border-radius: 0px;"><span aria-hidden="true">下一篇：</span> ${map.next.title}</a></li>
                    </#if>
                </ul>
            </nav>
        </div>

    </div>
    <!-- /.row -->
    <div class="ds-share" data-thread-key="${blog.id}" data-title="${blog.title!''}" data-images="" data-content="${blog.subject!''}" data-url="tuoluoluo.win/blog/${blog.id}">
        <div class="ds-share-inline">
            <ul  class="ds-share-icons-16">

                <li data-toggle="ds-share-icons-more"><a class="ds-more" href="javascript:void(0);">分享到：</a></li>
                <li><a class="ds-weibo" href="javascript:void(0);" data-service="weibo">微博</a></li>
                <li><a class="ds-qzone" href="javascript:void(0);" data-service="qzone">QQ空间</a></li>
                <li><a class="ds-qqt" href="javascript:void(0);" data-service="qqt">腾讯微博</a></li>
                <li><a class="ds-wechat" href="javascript:void(0);" data-service="wechat">微信</a></li>

            </ul>
            <div class="ds-share-icons-more">
            </div>
        </div>
    </div>
    <!-- 多说评论框 start -->
    <div class="ds-thread" data-thread-key="${blog.id}" data-title="${blog.title!''}" data-url="tuoluoluo.win/blog/${blog.id}"></div>
    <!-- 多说评论框 end -->
    <!-- 多说公共JS代码 start (一个网页只需插入一次) -->
    <script type="text/javascript">
        var duoshuoQuery = {short_name:"tuoluoluo"};
        (function() {
            var ds = document.createElement('script');
            ds.type = 'text/javascript';ds.async = true;
            ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
            ds.charset = 'UTF-8';
            (document.getElementsByTagName('head')[0]
            || document.getElementsByTagName('body')[0]).appendChild(ds);
        })();
    </script>
    <!-- 多说公共JS代码 end -->

    <hr>
    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; <a href="程序猿">戴瑞</a> 2016</p>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </footer>

</div>
</#macro>
<#macro overrideScript>
<script>
</script>
</#macro>
<#--引用布局文件  页面从这里开始-->
<@layout head=overrideHead container=overrideContainer script=overrideScript>
</@layout>