<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-language" content="zh-CN" />
    <title>文件上传</title>
    <!-- Bootstrap Core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
    <!--Bootstrop 文件上传-->
    <script src="/lib/fileinput/js/fileinput.js" type="text/javascript"></script>
    <!--简体中文-->
    <script src="/lib/fileinput/js/locales/zh.js" type="text/javascript"></script>

    <link href="/lib/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />

</head>
<body style="text-align: center">
<div style="margin: 10px;text-align: center">
    <input id="image1" class="file" type="file" name="file">
    <script>
        $("#image1").fileinput({
            language: 'zh', //设置语言
            showUpload: true,
            allowedFileExtensions : ['jpg', 'png','gif'],
            uploadUrl: '/file/upload',
            minFileCount:1,
            validateInitialCount:true,
            dropZoneEnabled: false
        });
    </script>
</div>
</body>
</html>