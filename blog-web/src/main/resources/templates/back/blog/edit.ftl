<#include "../layout/layout.ftl">
<#macro overrideHead>
    <title>博客编辑</title>
</#macro>
<#macro overrideContainer>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <ol class="breadcrumb">
                    <li>
                        <a href="/back/blog">博文</a>
                    </li>
                    <li class="active">
                        编辑
                    </li>
                </ol>
                <hr>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-10">
                <div>
                    <form id="editForm">
                        <input id="id" name="id" value="${blog.id!}" type="hidden">
                        <div class="form-group">
                            <label for="title">标题</label>
                            <input id="title" name="title" class="form-control" value="${blog.title!}">
                        </div>
                        <div class="form-group">
                            <label for="subject">摘要</label>
                            <textarea id="subject" name="subject" class="form-control">${blog.subject!}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="blogContent">正文</label>
                            <textarea id="blogContent" class="form-control" >${blog.content!}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="keywords">关键词</label>
                            <input id="keywords" name="keywords" class="form-control" value="${blog.keywords!}">
                        </div>
                        <div class="form-group">
                            <label>相关设置</label>
                            <div>
                                <p>
                                    <select class="form-control" name="categoryId" style="width:150px;">
                                        <option value="0">默认</option>
                                        <#local categoryId=blog.categoryId!0>
                                        <#list categories as category>
                                            <option value="${category.id}" <#if category.id == categoryId>selected</#if>>${category.name}</option>
                                        </#list>
                                    </select>
                                </p>
                                <p>
                                    <#local personal=blog.personal!0>
                                    <label style="margin-right: 20px;"><input type="radio" name="personal" value="0" <#if personal==0>checked</#if>> 所有人可见</label>
                                    <label><input type="radio" name="personal" value="1" <#if personal==1>checked</#if>> 仅自己可见</label>
                                </p>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary" id="submitBtn">提交</button>
                    </form>
                </div>
            </div>
            <div class="col-lg-2">

            </div>
        </div>
    </div>
</div>
</#macro>
<#macro overrideScript>
<script type="text/javascript" src="/lib/ckeditor/ckeditor.js"></script>
<script>
    $(function(){
        $(".side-nav").children("li").eq(0).addClass("active");
        CKEDITOR.replace('blogContent');
        $("#submitBtn").click(function(){
            var params = $("#editForm").serializeArray();
            var blogData = CKEDITOR.instances.blogContent.getData();
            params.push({"name":"content","value":blogData});
            $.post("/back/blog/save",params,function(result){
                if(result){
                    window.location.href="/back/blog";
                }
            });
        });
    });
</script>
</#macro>
<@layout head=overrideHead container=overrideContainer script=overrideScript>
</@layout>