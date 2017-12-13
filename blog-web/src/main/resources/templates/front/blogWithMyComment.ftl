<#include "layout/layout.ftl">
<#macro overrideHead>
<#-- 文件上传js -->
<script src="/js/jquery.ocupload-1.1.2.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<!--Bootstrop 文件上传-->
<script src="/lib/fileinput/js/fileinput.js" type="text/javascript"></script>
<!--简体中文-->
<script src="/lib/fileinput/js/locales/zh.js" type="text/javascript"></script>

<link href="/lib/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<!--  代码高亮 -->
<script src="/lib/ckeditor/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
<link rel="stylesheet" href="/lib/ckeditor/plugins/codesnippet/lib/highlight/styles/idea.css">
<script>hljs.initHighlightingOnLoad();</script>

<title>${blog.title!''}</title>
</#macro>
<#macro overrideContainer>
<!-- Page Content -->
<div class="container"
     style="background-color: #fff;padding-top:20px;margin-top: 70px;-moz-box-shadow:2px 2px 20px #333333; -webkit-box-shadow:2px 2px 20px #333333; box-shadow:2px 2px 20px #333333;border-radius: 0.3em;margin-bottom:10px">
    <div class="row">
        <!-- Blog Entries Column -->
        <div class="col-md-12">
            <div class="blog-one" style="">
                <input type="hidden" id="blogId" value="${blog.id}">
                <div class="blog-title" style="font-weight: bold;font-size: 18px">
                ${blog.title!''}
                </div>
                <div class="blog-statistics">
                    <span><i class="fa fa-eye" aria-hidden="true"></i> ${(blog.blogStatistics.viewCount)!0}</span> .
                    <span><i class="fa fa-thumbs-o-up"
                             aria-hidden="true"></i> ${(blog.blogStatistics.likeCount)!0}</span> .
                    <span><i class="fa fa-comments-o"
                             aria-hidden="true"></i> ${(blog.blogStatistics.commentCount)!0}</span> .
                    <span class="glyphicon glyphicon-time"></span> Posted
                    on ${(blog.createTime?string("yyyy-MM-dd"))!''}
                </div>
                <div class="blog-subject">
                    <span>摘要</span> ${blog.subject!''}
                </div>
                <div class="blog-content">
                ${blog.content!''}
                </div>
                <hr>
            </div>
            <nav>
                <div style="text-align: center">
                    <a class="likeCls" href="javascript:;" data-bid="${blog.id}"><span><i
                            class="fa fa-thumbs-o-up fa-lg" aria-hidden="true"></i> 喜欢 </span></a>&nbsp;&nbsp;
                    <a class='replyCls' href="javascript:;" data-id='' data-bid='${blog.id}' data-uid='' data-level='0'><span><i
                            class="fa fa-comments-o fa-lg" aria-hidden="true"></i> 评论 </span></a>
                </div>
                <ul class="pager">
                    <#if map.prev??>
                        <li class="previous"><a href="/blog/${map.prev.id}"><span
                                aria-hidden="true">上一篇：</span> ${map.prev.title}</a></li>
                    </#if>
                    <#if map.next??>
                        <li class="next"><a href="/blog/${map.next.id}"><span
                                aria-hidden="true">下一篇：</span> ${map.next.title}</a></li>
                    </#if>
                </ul>
            </nav>
        </div>
    </div>
    <h4>评论</h4>
    <div class="comment-history" style="position: relative"></div>
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
    <!-- Bootstrap跳出窗口 Modal aria-hidden="true" 触发才会显示-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="">

        </div>
    </div>
</div>
</#macro>
<#macro overrideScript>
<script>
    /*递归*/
    function cycleHandler(node) {
        var dom = "";
        var comment = node.node;
        if (comment != null) {
            /* dom += "<div class='media'>" +
                     "<a class='pull-left' href='" + comment.replyerHome + "'>" +
                     "<img id='head' src='" + comment.replyerHead + "' alt='User Avatar' style='width: 40px; height: 40px;'>" +
                     "</a>" +
                     "<div class='media-body'>" +
                     "<div class='media-heading'>" +
                     "<span href='" + comment.replyerHome + "' style='font-size: 16px;'>" + comment.replyerName + "</span>" +
                     "<small style='display: block'>" + getFormatDateByLong(comment.createTime)
                     + " / <a class='replyCls' data-id='" + comment.id + "' data-bid='" + comment.blogId + "' data-uid='" + comment.replyerId + "' data-level='" + comment.level + "' >Reply</a></small>" +
                     "</div>" +
                     comment.comment;*/
            dom += "<div class='media'>" +
                    "<a class='pull-left' id='head1' href='javascript:void(0)' value='"+comment.replyerHead+"' onclick=openl(this)>" +
                    "<img id='head' src='"+ comment.replyerHead + "' alt='User Avatar' style='width: 40px; height: 40px;'>" +
                    "</a>" +
                    "<div class='media-body'>" +
                    "<div class='media-heading'>" +
                    "<span href='" + comment.replyerHome + "' style='font-size: 16px;'>" + comment.replyerName + "</span>" +
                    "<small style='display: block'>" + getFormatDateByLong(comment.createTime)
                    + " / <a class='replyCls' data-id='" + comment.id + "' data-bid='" + comment.blogId + "' data-uid='" + comment.replyerId + "' data-level='" + comment.level + "' >Reply</a></small>" +
                    "</div>" +
                    comment.comment;

        }
        var children = node.children;
        if (children != null && children.length > 0) {
            for (var i = 0; i < children.length; i++) {
                dom += cycleHandler(children[i]);
            }
        }
        if (comment != null) {
            dom += "</div></div>";
        }
        return dom;
    }

    function initComments() {
        var blogId = $("#blogId").val();
        $.post("/comments/" + blogId, function (data) {
            if (data == null || data.children == null) {
                $(".comment-history").html("no comments");
            } else {
                var ul = cycleHandler(data);
                if (ul != "") {
                    ul = "<div class='media-list m-top-lg'>" + ul + "</div>";
                }
                $(".comment-history").html(ul);
            }
        });
    }

    $(function () {
        /*初始化评论*/
        initComments();
        /*为评论添加绑定事件*/
        $(document).on("click", ".replyCls", function () {
            var pid = $(this).attr("data-id");
            var blogId = $(this).attr("data-bid");
            var receiverId = $(this).attr("data-uid");
            var level = Number($(this).attr("data-level")) + 1;
            var params = [];
            params.push({name: 'pid', value: pid});
            params.push({name: 'blogId', value: blogId});
            params.push({name: 'receiverId', value: receiverId});
            params.push({name: 'level', value: level});
            $.post("/comment/reply", params, function (data) {
                $('#myModal').html(data);
            });
            $('#myModal').modal({backdrop: 'static', keyboard: false, show: true});
        });
        $(document).on("click", ".likeCls", function () {
            var blogId = $(this).attr("data-bid");
            $.post("/blog/like/" + blogId, function (data) {
                if (data) {
                    layer.msg('谢谢点赞o(￣ε￣*)', {time: 2000, icon: 6, anim: 4, offset: '200px'});
                } else {
                    layer.msg('你已经点过了<(￣ˇ￣)/', {time: 2000, icon: 2, anim: 6, offset: '150px'});
                }
            });
        });

        /*setTimeout(function () {
            /!*再次判断body的大小 是否决定隐藏滑动条*!/
            if (document.body.offsetHeight-window.innerHeight>-50){
                $('body').css("overflow-y","auto");
            }
        },500);*/

    });

    function openl(Dom) {
        var url="/image\\2017\\6\\4\\34900990-6ba0-48b8-823f-6af017d00ed0.jpg".replace(/\\/g, "/");
        var imgurl=$(Dom).attr("value");
        layer.open({
            type: 1,
            title: '游客头像',
            shadeClose: true,
            shade: 0.8,
            area: ['80%','70%'],
            content: '<img src='+imgurl+' style="width:100%;height:100%">'
        })
    }

    $(".button.kv-file-zoom.btn.btn-xs.btn-default").click(function () {
        console.log("dsad");
        alert("dsd");
    });

    //    $(".pull-left").mouseover(function () {
    //        alert("ds");
    //        layer.tips('点击可以上传头像哦。', $("#head"), {
    //            tips: [1, '#3595CC'],
    //            time: 2000
    //        });
    //    });

</script>

<#-- 给Bootstrap model添加垂直滑动条-->
<style>
    .modal-dialog {
        position: absolute;
        top: 0;
        bottom: 40%;
        left: 0;
        right: 0;
    }

    .modal-content {
        /*overflow-y: scroll;*/
        position: absolute;
        top: 0;
        bottom: 0;
        width: 100%;
    }

    .modal-body {
        overflow-y: scroll;
        position: absolute;
        top: 55px;
        bottom: 65px;
        width: 100%;
    }

    .modal-header .close {margin-right: 15px;}

    .modal-footer {
        position: absolute;
        width: 100%;
        bottom: 0;
    }
</style>

<#-- 解决Bootstrap code样式与编辑器 Consolas字体 冲突 和 代码块冲突  -->
<style>

     code{
        padding: 0;
        font-size: 100%;
        color: black;
        background-color: transparent;
        border-radius: 0;

    }

    /* BootStrap  */
    pre{
        display: block;
        padding: 9.5px;
        margin: 0 0 10px;
        line-height: 1.42857143;
        color: #333;
        font-size: 100%;
        word-break:normal!important;
        word-wrap: normal!important;
        background-color: #F0F0F0;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

     pre code {
         /* 对span 标签的内容中的空格进行切割 来换行 bootStrap中属性，这里设置为不切割 */
         white-space: unset;
     }
    /* 设置idea主题的背景 */
    .hljs{
        background:#F0F0F0;
    }
</style>



</#macro>
<@layout head=overrideHead container=overrideContainer script=overrideScript>
</@layout>