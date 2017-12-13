/**
 * Created by 戴瑞Mic on 2017/6/14.
 * 判断用户浏览的类型
 */

// cookie 的名字
var COOKIE_NAME = 'IsShowAnimation';
var count=1;
// 如果是PC端就返回True
$(function () {
    var flag= IsPC();
    /*如果是手机端就不显示改动画*/
    if (!flag){
        $('#pageContent').css("margin-top","65px");
        //$('#navigation').css("background","#3566a4");
        //$('html').css("background","#b0d5f2");
        //$('body').css("background","#b0d5f2");
    }else {


        $("#canvasDIV").append(" <canvas id='canv' style='position: absolute;z-index: -2px'></canvas>");
        /* 加载动画js*/
        jQuery.getScript("/js/canvas.js", function(data, status, jqxhr) {
            setTimeout(function () {
                /*如果body的高度小于HTML*/
                if (document.body.offsetHeight-window.innerHeight<-50){
                    $('body').css("overflow-y","hidden");
                    $('html').css("background-image","-webkit-radial-gradient(ellipse farthest-corner at center top, #000d4d 0%, #000105 100%)");
                    $('html').css("background","transparent");
                }
            },100);

        });

        /* 添加关闭特效功能*/
        $("body").after("<div id='close_animation' style='bottom: 0;right: 10px;position: fixed;font-size: 15px;margin-bottom: 5px;margin-right: 5px;'>"+
            "<p class='closep' id='closep' style='color: rgba(34, 174, 245,0.42);background: rgba(240, 248, 255, 0.05);cursor:pointer;'>关闭特效</p></div>")

        //TODO 使用cookie来切换主题，效果不好
        /* 获取cookie 值来控制动画的开启   */
        if ($.cookie(COOKIE_NAME)=='true'){
            // addDarkTheme();
            openAnimation();
        }else {
            // $('#theme-css-dark').remove();
            closeAnimation();
        }


        /*关闭特效功能js*/
        $("#closep").click(function () {
            if (count==1){
                closeAnimation();
            }else {
                openAnimation();
            }
        })
    }
});
/**
 * @return {boolean}
 */
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}

/**
 *  关闭动画
 */
function closeAnimation() {
    $.backstretch([
        "/img/backgrounds/8.jpg"
    ], {fade: 0});
    $("#canvasDIV").hide();
    $("body").css({'background':'transparent'});
    $("#closep").text("开启特效");
    $("#navigation").css({"background":'rgba(41, 103, 141,0.83)'});
    $(".navbar.navbar-inverse.navbar-fixed-top a").css({
        "link":"darkgray"
    });
    /*以下js是关于我 about.ftl 页面的*/
    $("#mainbody").css('background-color','#fff');
    $(".touchMe h1").css("color",'black');
    $(".touchMe p").css("color",'black');

    /* 设置cookie 过期时间为7天*/
    $.cookie(COOKIE_NAME,false,{  expires: 7, path: '/' });
    count=0;
}

/**
 * 开启动画
 */
function openAnimation(){
    $.backstretch('destroy',false);
    $("#navigation").css({"background":'rgba(26, 51, 71,0.83)'});
    $("body").css({'background':''});
    $("#canvasDIV").show();
    $("#closep").text("关闭特效");

    /*以下js是关于我页面的*/
    $("#mainbody").css('background-color','rgba(80, 63, 63, 0.78)');
    $(".touchMe h1").css("color",'white');
    $(".touchMe p").css("color",'white');
    /* 设置cookie 过期时间为7天*/
    $.cookie(COOKIE_NAME,true,{  expires: 7, path: '/' });
    count=1;
}

// 添加暗色主题
function addDarkTheme() {
    var link = document.createElement('link');
    link.type = 'text/css';
    link.id = "theme-css-dark";  // 加上id方便后面好查找到进行删除
    link.rel = 'stylesheet';
    link.href = '/css/canvas.css';
    document.getElementsByTagName("head")[0].appendChild(link);
}


var flag = IsPC(); //true为PC端，false为手机端
