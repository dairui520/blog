jQuery(document).ready(function () {

    //document.getElementById('cloud').style.backgroundImage = 'url("' + context.canvas.toDataURL() + '")';
    /*
     Fullscreen background
     */
   /* $(".container").css({opacity: .8});  */ //设置透明度
    /*$.backstretch([
        "/img/backgrounds/1.jpg",
        "/img/backgrounds/2.jpg",
        "/img/backgrounds/3.jpg",
        "/img/backgrounds/4.jpg",
        "/img/backgrounds/5.jpg",
        "/img/backgrounds/6.jpg",
        "/img/backgrounds/7.jpg"
    ], {duration: 3000, fade: 750});*/
/*$.backstretch("/img/backgrounds/1.jpg");*/

/*
 Form validation
 */
$('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function () {
    $(this).removeClass('input-error');
});

$('.login-form').on('submit', function (e) {

    $(this).find('input[type="text"], input[type="password"], textarea').each(function () {
        if ($(this).val() == "") {
            e.preventDefault();
            $(this).addClass('input-error');
        }
        else {
            $(this).removeClass('input-error');
        }
    });

});


})
;
