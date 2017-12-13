<#include "../layout/layout.ftl">
<#macro overrideHead>
<title>爬虫</title>
</#macro>
<#macro overrideContainer>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <ol class="breadcrumb">
                    <li class="active">
                        爬虫
                    </li>
                </ol>
                <hr>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6" style="position: relative;">
                <div class="load">
                    <i class="fa fa-spinner fa-pulse fa-5x fa-fw"></i>
                    <span class="sr-only">Loading...</span>
                </div>
                <div>
                    <form id="editForm">
                        <div class="form-group">
                            <label>目标路径</label>
                            <input class="form-control" name="seedUrl" value="https://my.oschina.net/taoluoluo/blog">
                        </div>
                        <div class="form-group">
                            <label>路径规则</label>
                            <input class="form-control" name="urlRegEx" value="https://my.oschina.net/taoluoluo/blog/[0-9]+">
                        </div>
                        <div class="form-group">
                            <label>标题规则</label>
                            <input class="form-control" name="titleRegEx" value="div[class='title']">
                        </div>
                        <div class="form-group">
                            <label>摘要规则</label>
                            <input class="form-control" name="abstractRegEx" value="div[class='blog-abstract']">
                        </div>
                        <div class="form-group">
                            <label>正文规则</label>
                            <input class="form-control" name="contentRegEx" value="div[class='BlogContent']">
                        </div>
                        <div class="form-group">
                            <label>作者规则</label>
                            <input class="form-control" name="authorRegEx">
                        </div>
                        <button type="button" class="btn btn-primary" id="crawlerBtn">爬取</button>
                    </form>
                </div>
            </div>
            <div class="col-lg-6">

            </div>
        </div>
    </div>
</div>
</#macro>
<#macro overrideScript>
<script>
    $(function(){
        var int;
        $(".side-nav").children("li").eq(2).addClass("active");
        $("#crawlerBtn").click(function () {
            var params = $("#editForm").serializeArray();
            $.post("/back/crawler/start",params,function (result) {
                if(result){
                    $(".load").show();
                    timer();
                }else{
                    alert("爬取失败");
                }
            });
        });
        function timer() {
            int = setInterval(status,1000);
        }
        function status() {
            $.get("/back/crawler/status",function (result) {
                if(result==null||result==""){
                    return;
                }
                if(result == 1){
                    alert("爬取成功");
                    window.clearInterval(int);
                    $(".load").hide();
                }
                if(result == 0){
                    alert("爬取失败");
                    window.clearInterval(int);
                    $(".load").hide();
                }
            })
        }
    });
</script>
</#macro>
<@layout head=overrideHead container=overrideContainer script=overrideScript>
</@layout>