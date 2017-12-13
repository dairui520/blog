<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理员注册</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/form-elements.css">
    <link rel="stylesheet" href="/css/style.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you get the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="/js/jquery.js"></script>
    <!--引用星空动画-->
    <script src="/js/IsPC.js"></script>
    <link src="/css/canvas.css" rel="stylesheet" type="text/css">
</head>

<!--这个页面需要再次引用-->
<style>
    html {
        height: 100%;
        background-image: -webkit-radial-gradient(ellipse farthest-corner at center top, #000d4d 0%, #000105 100%);
        background-image: radial-gradient(ellipse farthest-corner at center top, #000d4d 0%, #000105 100%);
        cursor: auto;
        overflow-y: auto;
        overflow-x: hidden;
    }

    body {
        background-image: -webkit-radial-gradient(ellipse farthest-corner at center top, #000d4d 0%, #000105 100%);
        background-image: radial-gradient(ellipse farthest-corner at center top, #000d4d 0%, #000105 100%);
        width: 100%;
        height: 100%;
        margin: 0;
        text-align: start;
    }
</style>
<!--与动画一起-->
<div id="canvasDIV"></div>
<body>
<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container" style="margin-top: -50px">
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>输入信息注册</h3>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-lock"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="" method="post" class="register-form">
                            <div class="form-group">
                                <label class="sr-only" for="account">Account</label>
                                <input type="text" name="account" placeholder="账号..." class="form-control" id="account">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="password">Password</label>
                                <input type="password" name="password" placeholder="密码..." class="form-control" id="password">
                            </div>
                            <div class="form-group">
                                <label for="name" class="sr-only">nickName</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="昵称..." >
                            </div>
                            <div class="form-group">
                                <label for="homeUrl" class="sr-only">home</label>
                                <input type="text" class="form-control" id="homeUrl" name="homeUrl" placeholder="主页..." >
                            </div>
                            <div class="form-group">
                                <label for="head" class="sr-only">head</label>
                                <input type="text" class="form-control" id="head" name="head" placeholder="头像...">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="verification">Verification</label>
                                <input type="text" name="verification" placeholder="验证码..." class="form-control" id="verification">
                                <img id="vpic" src="/validate/code/pic">
                                <div style="clear: both"></div>
                            </div>
                            <div id="message" class="alert alert-danger alert-dismissible" role="alert" style="background-color:transparent;width: 100%;">
                                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <label id="message-content"></label>
                            </div>
                            <button id="signBtn" type="button" class="btn">注册</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- Javascript -->
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.backstretch.min.js"></script>
<script src="/js/scripts.js"></script>
<script>
    function msgHide(){
        $("#message").hide();
    }
    function failShow(message){
        $("#message-content").html(message);
        $("#message").show();
        window.setTimeout("msgHide()",5000);
    }
    $(function() {
        $("#vpic").click(function () {
            $("#vpic").attr("src","/validate/code/pic?tt="+Math.random());
        });
        $("#message").hide();
        $(document).keyup(function(event){
            if(event.keyCode ==13){
                $("#signBtn").trigger("click");
            }
        });
        $('#signBtn').click(function(){
            var account = $('#account').val();
            if(account == undefined || account.trim() ==''){
                alert('请输入账号');
                return;
            }

            var password = $('#password').val();
            if(password == undefined || password==''){
                alert('请输入密码');
                return;
            }

            var verification = $('#verification').val();
            if(verification == undefined || verification==''){
                alert('请输入图片验证码');
                return;
            }
            var params = $(".register-form").serializeArray();
            $.ajax({
                url: "/back/register",
                type: "POST",
                data: params,
                success: function (data) {
                    if(data.flag){
                        var target = $("#target").val();
                        if(target==null||target == ''){
                            target ="/back";
                        }else{
                            target = decodeURI(target);
                        }
                        window.location.href=target;
                    }else{
                        $("#vpic").click();
                        failShow(data.errMsg);
                    }
                }
            });
        });
    })
</script>
</body>
</html>