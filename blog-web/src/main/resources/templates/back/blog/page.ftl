<#include "../layout/layout.ftl">
<#macro overrideHead>
    <title>博客管理</title>
</#macro>
<#macro overrideContainer>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <ol class="breadcrumb">
                    <li class="active">
                        博文
                    </li>
                </ol>
                <hr>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-10">
                <#list page.data as blog>
                    <div class="blog-one">
                        <div class="blog-title">
                            <a href="javascript:;" class="blog-title">${blog.title!''}</a>
                        </div>
                        <div class="blog-statistics">
                            <span><i class="fa fa-eye" aria-hidden="true"></i> ${(blog.blogStatistics.viewCount)!0}</span> .
                            <span><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> ${(blog.blogStatistics.likeCount)!0}</span> .
                            <span><i class="fa fa-comments-o" aria-hidden="true"></i> ${(blog.blogStatistics.commentCount)!0}</span> .
                            (<a href="/back/blog/edit?id=${blog.id}">编辑</a> | <a class="blogDelete" data="${blog.id}" href="javascript:;">删除</a>)
                            <span class="glyphicon glyphicon-time"></span> 发表于 ${(blog.createTime?string("yyyy-MM-dd"))!''}
                        </div>
                        <hr>
                        <div class="blog-subject well">
                            <p>${blog.subject!''}</p>
                        </div>
                        <div class="blog-content well" style="display: none;overflow-x: hidden;">
                        ${blog.content!''}
                        </div>
                        <hr>
                    </div>
                </#list>
                <nav>
                    <ul class="pager">
                        <#if page.pageNo &gt; 1>
                            <li class="previous" id="preBtn"><a href="javascript:;"><span aria-hidden="true">&larr;</span> 上一页</a></li>
                        </#if>
                        <#if page.pageNo &lt; page.pageMax>
                            <li class="next" id="nextBtn"><a href="javascript:;">下一页 <span aria-hidden="true">&rarr;</span></a></li>
                        </#if>
                    </ul>
                </nav>
                <form id="editForm" method="post" action="/back/blog">
                    <input type="hidden" name="categoryId" id="categoryId">
                    <input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}">
                    <input type="hidden" id="pageMax" value="${page.pageMax}">
                </form>
            </div>
            <div class="col-lg-2">
                <div class="r-menu-item">
                    <button type="button" class="btn btn-primary" id="newBlog">新建博文</button>
                </div>
                <div class="r-menu-item">
                    <h4>博客分类</h4>
                    <div class="row">
                        <div class="col-lg-12">
                            <ul class="list-unstyled">
                                <li><a data-value="0" class="categoryCls" href="javascript:;">默认</a></li>
                                <#list categories as category>
                                    <li><a data-value="${category.id}" class="categoryCls" href="javascript:;">${category.name}</a><span style="margin-left: 10px">(${category.blogNum})</span></li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="r-menu-item">
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
<#macro overrideScript>
<script>
    $(function(){
        $(".side-nav").children("li").eq(0).addClass("active");
        $(".categoryCls").on("click",function (e) {
            e.stopPropagation();
            var categoryId = $(this).attr("data-value");
            $("#categoryId").val(categoryId);
            $("#editForm").submit();
        });
        $(".blog-title").on("click",function (e) {
            e.stopPropagation();
            $(this).parent().siblings(".blog-subject").hide();
            $(this).parent().siblings(".blog-content").show();
        });
        $(".blog-content").on("click",function (e) {
            e.stopPropagation();
        });
        $("#newBlog").on("click",function (e) {
            e.stopPropagation();
            window.location.href = "/back/blog/edit";
        });
        $(".blogDelete").on("click",function (e) {
            e.stopPropagation();
            var id = $(this).attr("data");
            $.get("/back/blog/delete?id="+id,function (flag) {
                if(flag){
                    $("#editForm").submit();
                }
            })
        });
        var pageNo = parseInt($("#pageNo").val());
        var pageMax = parseInt($("#pageMax").val());
        $("#preBtn").on("click",function (e) {
            e.stopPropagation();
            if(!$(this).hasClass("disabled")){
                $("#pageNo").val(pageNo-1);
                $("#editForm").submit();
            }
        });
        $("#nextBtn").on("click",function (e) {
            e.stopPropagation();
            if(!$(this).hasClass("disabled")){
                $("#pageNo").val(pageNo+1);
                $("#editForm").submit();
            }
        });
        $(document).on("click",function(e){
            var target = e.target;
            var className = target.className;
            if(className!="blog-content"&&className!="blog-title"){
                $(".blog-subject").show();
                $(".blog-content").hide();
            }
        })
    })
</script>
</#macro>
<@layout head=overrideHead container=overrideContainer script=overrideScript>
</@layout>