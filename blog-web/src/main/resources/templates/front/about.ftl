<#include "layout/layout.ftl">
<#macro overrideHead>
<title>关于我</title>
</#macro>
<#macro overrideContainer>
<!-- Page Content -->
<div class="container" id="mainbody"
     style="background-color: rgba(80, 63, 63, 0.78);padding-top:10px;margin-top: 70px;-moz-box-shadow:2px 2px 20px #333333; -webkit-box-shadow:2px 2px 20px #333333; box-shadow:2px 2px 20px #333333; border-radius: 2em;">
    <div class="row" style="text-align: center">
        <div class="wrap" style="margin-left: 10%;width: 80%;text-align: center;">
            <div class="touchMe" style="margin-top: 10px">
                <div style="margin: 0 auto;width: 150px;height: 150px;"><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=695554967&site=qq&menu=yes">
                    <img id="qq" src="/img/my.jpg" style="border-radius: 50%;width: 50%;height: 50%;"></a>
                </div>
                <h1 style="margin-top: -50px;color: white">戴瑞</h1>
                <div><p style="margin-left: 10%;font-size: 14px;color:white;width: 80%;">调过前台，写过后台。<br>敲过代码，做过项目。<br>找过女友，谈着恋爱。<br>╰(￣▽￣)╭
                    <br>我就是大三狗!</p></div>
                <div id="github" style="margin-top: 50px;">
                    <a id="friendlink" target="_blank" class="fa fa-github" href="https://github.com/dairui520"
                       style="cursor: pointer;font-size: 40px;padding:10px;"></a>
                    <a class="fa fa-git" target="_blank" href="https://git.oschina.net/dairui520"
                       style="cursor: pointer;font-size: 40px;padding:10px;"></a>
                    <a class="fa fa-envelope-o" id="myemail" target="#" onclick="copyToClipboard('q1996716@vip.qq.com')"
                       style="cursor: pointer;font-size: 40px;padding:10px;"></a>
                    <a class="fa fa-weibo" target="_blank" href="http://weibo.com/p/1005052189641942/home"
                       style="cursor: pointer;font-size: 40px;padding:10px;"></a>

                </div>
                <br>
            </div>


        </div>
    </div>
    <br>
</div>


<style>
    #github a {
        color: gray;
    }

    #github a:hover {
        color: black;
    }

    #github a:visited {
        color: black
    }

    /*动画css*/
    .wrap {
        /*position: relative;*/
        /*width: 33.33%;*/
        /*height: 350px;*/
        /*超出的部分隐藏起来*/
        overflow: hidden;
        transform: all 2s;
        /*opacity: 1;*/
    }

    .touchMe {
        transition: all 2.5s;
        left: 80px;
        bottom: 0px;
        transform: translateY(280px);
        opacity: 0;
    }
</style>

</#macro>
<#macro overrideScript>
<script>
    $("#qq").mouseover(function(){
        //layer.tips('Hi，我是tips', '#qq');
        layer.tips('点击头像，用QQ与我交流。', '#qq', {
            tips: [2, '#3595CC'],
            time: 4000
        });
    });
    $("#qq").mouseleave(function(){
        layer.closeAll('tips');
    });
</script>
<script>
    /*动画js*/
    $(function () {
        setTimeout('tran()', 100);

    })
    function tran() {

        $(".touchMe").css({
            /*animation-fill-mode 设定保存动画最后的状态 */
            "animation-fill-mode": "forwards",
            "transform": "translateY(0px)",
            "-ms-transform": "translateY(0px)", /* IE 9 */
            "-moz-transform": "translateY(0px)", /* Firefox */
            "-webkit-transform": "translateY(0px)", /* Safari 和 Chrome */
            "-o-transform": "translateY(0px)",
            "opacity": "1"
        });
    }
    /*判断是否是手机端*/
    var ispc = IsPC();
    if (!ispc) {
        $("#mainbody").css('background-color', '#fff');
        $(".touchMe h1").css("color", 'black');
        $(".touchMe p").css("color", 'black');
    }
</script>
<script>
    /*邮箱复制到剪切板*/
    var i = 0;
    function copyToClipboard(maintext) {
        if (window.clipboardData) {
            window.clipboardData.setData("Text", maintext);
        } else if (window.netscape) {
            try {
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            } catch (e) {
                alert("该浏览器不支持一键复制！n请手工复制文本框链接地址～");
            }

            var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
            if (!clip) return;
            var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
            if (!trans) return;
            trans.addDataFlavor('text/unicode');
            var str = new Object();
            var len = new Object();
            var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
            var copytext = maintext;
            str.data = copytext;
            trans.setTransferData("text/unicode", str, copytext.length * 2);
            var clipid = Components.interfaces.nsIClipboard;
            if (!clip) return false;
            clip.setData(trans, null, clipid.kGlobalClipboard);
        }
        layer.msg('邮箱已复制');
    }
</script>
</#macro>
<#--引用布局文件  页面从这里开始-->
<@layout head=overrideHead container=overrideContainer script=overrideScript>
</@layout>