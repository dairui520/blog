<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                    class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel"><a href="javascript:;" id="toLogin">登录</a>|<a href="javascript:;"
                                                                                                    id="toRegister">注册</a>
            </h4>
        </div>
        <div class="modal-body">
            <form role="form" id="modalForm">
            </form>
            <div class="form-group" id="fileupload" style="display: none">
                <label for="name" class="control-label">头像(不上传，则使用默认头像)</label>
                <input id="image1" class="file" type="file" name="image">
                <script>
                    $("#image1").fileinput({
                        language: 'zh', //设置语言
                        showUpload: true,
                        allowedFileExtensions: ['jpg', 'png', 'gif'],
                        uploadUrl: '/file/upload',
                        maxFileSize: 5000,
                        overwriteInitial: false,
                        validateInitialCount: true,
                        maxFileCount: 1,
                        msgSizeTooLarge: "文件过大（小于5M），请重新上传",
                        msgInvalidFileType: "不支持此图片类型，支持jpg,png,git类型",
                        msgUploadEmpty: "不支持此图片类型，请上传 jpg,png,git 类型图片",
                        dropZoneEnabled: false
                    });
                    //异步上传返回结果处理
                    $("#image1").on("fileuploaded", function (event, data, previewId, index) {
                        // 返回的数据在data.response里面
                        if (data.response.flag) {
                            layer.msg("上传成功", {icon: 6, time: 1500});
                            $("#imageUrl").val(data.response.url);
                        }
                    });
                    // 选择图片后直接上传
                    $("#image1").on("filebatchselected", function (event, files) {
                        $(this).fileinput("upload");
                    })
                </script>

            </div>
        </div>

        <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="submitBtn">登录</button>
        </div>
    </div>
</div>
<div id="loginDiv" style="display: none;">
    <div class="form-group">
        <label for="account" class="control-label">用户名</label>
        <input type="text" class="form-control" id="account" name="account">
    </div>
    <div class="form-group">
        <label for="password" class="control-label">密码</label>
        <input type="password" class="form-control" id="password" name="password">
    </div>
</div>
<div id="registerDiv" style="display: none;">
    <div class="form-group">
        <label for="account" class="control-label">用户名</label>
        <input type="text" class="form-control" id="account" name="account">
    </div>
    <div class="form-group">
        <label for="password" class="control-label">密码</label>
        <input type="password" class="form-control" id="password" name="password">
    </div>
    <div class="form-group">
        <label for="name" class="control-label">昵称</label>
        <input type="text" class="form-control" id="name" name="name">
    <#--头像默认值-->
        <input type="hidden" name="head" id="imageUrl" value="/img/head-404.jpg">
    </div>
<#-- 游客目前还不需要 -->
<#--<div class="form-group">
    <label for="homeUrl" class="control-label">主页</label>
    <input type="text" class="form-control" id="homeUrl" name="homeUrl">
</div>-->

</div>
<script type="text/javascript">
    $(function () {
        $("#modalForm").html($("#loginDiv").html());
        $("#toLogin").click(function () {
            $("#submitBtn").text("登录");
            $("#fileupload").css("display", "none");// 设置上传图片按钮可见
            $(".modal-dialog").css("bottom", "40%");// 设置文件上传高度
            $("#modalForm").html($("#loginDiv").html());
        });

        $("#toRegister").click(function () {
            $("#submitBtn").text("注册");
            $("#fileupload").css("display", "block");// 设置上传图片按钮不可见
            $(".modal-dialog").css("bottom", "0");   // 设置文件上传高度
            $("#modalForm").html($("#registerDiv").html());
        });

        $("#submitBtn").click(function () {
            var params = $("#modalForm").serializeArray();
            var text = $("#submitBtn").text();
            var url;
            if (text == "登录") {
                url = "/visitor/login";
                $.post(url, params, function (data) {
                    if (data.flag) {
                        layer.msg('游客：' + data.tip + '登录成功', {icon: 6, time: 2000});
                        $(".replyCls").click();
                    } else {
                        layer.confirm(data.errMsg, {
                            icon: 2,
                            btn: ['确定'] //按钮
                        }, function (index) {
                            layer.close(index);
                        });
                    }
                })
            } else {
                url = "/visitor/register";
                $.post(url, params, function (data) {
                    if (data.flag) {
                        $(".replyCls").click();
                    } else {
                        layer.confirm(data.errMsg + "(＞﹏＜)", {
                            icon: 5,
                            btn: ['确定'] //按钮
                        }, function (index) {
                            layer.close(index);
                        });
                    }
                })
            }
        });
        $("[class='button.kv-file-zoom btn btn-xs btn-default']").click(function () {
            console.log("dsad");
            alert("dsd");
        })
    });

    $(".button.kv-file-zoom.btn.btn-xs.btn-default").click(function () {
        console.log("dsad");
        alert("dsd");
    });

    $(".button.kv-file-zoom.btn.btn-xs.btn-default").bind("click", function () {
        console.log("dsad");
        alert("dsd");
    })
</script>
