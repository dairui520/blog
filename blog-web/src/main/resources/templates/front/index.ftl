<#include "layout/layout.ftl">
<#macro overrideHead>
<title>程序猿生活</title>
</#macro>
<#macro overrideContainer>
<!-- Page Content -->
<div class="container" id="pageContent"
     style="background-color: #fff;padding-top:20px;margin-top: 70px;-moz-box-shadow:2px 2px 20px #333333; -webkit-box-shadow:2px 2px 20px #333333; box-shadow:2px 2px 20px #333333;border-radius: 0.3em;margin-bottom:10px">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">
            <#if (page.data?size > 0)>
                <#list page.data as blog>
                    <div class="blog-one">
                        <div class="blog-title">
                            <a href="/blog/${blog.id}" class="blog-title-new" target="_blank">${blog.title!''}</a>
                        </div>
                        <div class="blog-statistics">
                            <span><i class="fa fa-eye"
                                     aria-hidden="true"></i> ${(blog.blogStatistics.viewCount)!0}</span> .
                            <span><i class="fa fa-thumbs-o-up"
                                     aria-hidden="true"></i> ${(blog.blogStatistics.likeCount)!0}</span> .
                            <span><i class="fa fa-comments-o"
                                     aria-hidden="true"></i> ${(blog.blogStatistics.commentCount)!0}</span> .
                            <span class="glyphicon glyphicon-time"></span>
                            发表于 ${(blog.createTime?string("yyyy-MM-dd"))!''}
                        </div>
                        <hr>
                        <div class="blog-subject well">
                            <span>摘要</span> ${blog.subject!''}
                        </div>
                        <hr>
                    </div>
                </#list>
            <#else>
            <#--<h2>-->
            <#--该分类没有博客-->
            <#--</h2>-->
                <h2>
                    咦！没有了。
                </h2>
            </#if>

            <nav>
                <ul class="pager">
                    <#if page.pageNo &gt; 1>
                        <li class="previous" id="preBtn"><a href="javascript:;"><span aria-hidden="true">&larr;</span>
                            上一页</a></li>
                    </#if>
                    <#if page.pageNo &lt; page.pageMax>
                        <li class="next" id="nextBtn"><a href="javascript:;">下一页 <span aria-hidden="true">&rarr;</span></a>
                        </li>
                    </#if>
                </ul>
            </nav>
            <form id="editForm" method="post" action="/">
                <input type="hidden" name="title" class="form-control">
                <input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}">
                <input type="hidden" id="pageMax" value="${page.pageMax}">
            </form>

        </div>

        <!-- Blog Sidebar Widgets Column -->
        <div class="col-md-4">

            <!-- Blog Search Well -->
            <form action="/" method="post">
                <div class="well">
                    <h4>博客搜索</h4>
                    <div class="input-group">
                    <#--默认为文章标题搜索-->
                        <input type="text" name="title" class="form-control" value="${(query.title)!''}">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="submit" style="height: 34px;margin-left: 10px;border-radius: 0.2em">
                                <span class="glyphicon glyphicon-search"></span>
                        </button>
                    </span>
                    </div>
                    <!-- /.input-group -->
                </div>
            </form>


            <!-- Blog Categories Well -->
            <div class="well">
                <h4>博客分类</h4>
                <div class="row">
                    <div class="col-lg-12">
                        <ul class="list-unstyled">
                            <li style="margin-bottom: 5px"><a href="/category/0">默认</a></li>
                            <#list categories as category>
                                <li style="margin-bottom: 5px"><a href="/category/${category.id}">${category.name}</a><span style="margin-left: 10px">(${category.blogNum})</span></li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>

            <!-- Side Widget Well -->
            <div class="well">
                <h4>公告墙</h4>
                <p>欢迎关注</p>
            </div>

        </div>

    </div>
    <!-- /.row -->
    <hr>


    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12" style="text-align: center">
                <p>Copyright &copy; <a href="#">戴瑞</a> 2017<span style="margin-left: 8px">鄂ICP备17017200号</span></p>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
    </footer>
</div>
</#macro>
<#macro overrideScript>
<script>
    $(function () {
        $(".blog-title").on("click", function (e) {
            e.stopPropagation();
            $(this).parent().siblings(".blog-subject").hide();
            $(this).parent().siblings(".blog-content").show();

        })
        $(".blog-content").on("click", function (e) {
            e.stopPropagation();
        })
        var pageNo = parseInt($("#pageNo").val());
        var pageMax = parseInt($("#pageMax").val());
        $("#preBtn").on("click", function (e) {
            e.stopPropagation();
            if (!$(this).hasClass("disabled")) {
                $("#pageNo").val(pageNo - 1);
                $("#editForm").submit();
            }
        })
        $("#nextBtn").on("click", function (e) {
            e.stopPropagation();
            if (!$(this).hasClass("disabled")) {
                $("#pageNo").val(pageNo + 1);
                $("#editForm").submit();
            }
        })
        $(document).on("click", function (e) {
            var target = e.target;
            var className = target.className;
            if (className != "blog-content" && className != "blog-title") {
                $(".blog-subject").show();
                $(".blog-content").hide();
            }
        })
    })
</script>
</#macro>
<@layout head=overrideHead container=overrideContainer script=overrideScript>
</@layout>