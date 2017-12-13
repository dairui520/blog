<#import "head.ftl" as headTemplate><#--导入定义的变量-->
<#import "header.ftl" as headerTemplate>
<#import "footer.ftl" as footerTemplate>
<#macro defaultHead></#macro><#--定义默认的宏-->
<#macro defaultContainer></#macro>
<#macro defaultScript></#macro>
<#--定义全局的布局，设置默认值-->
<#macro layout head=defaultHead allHead=headTemplate.defaultHead header=headerTemplate.defaultHeader container=defaultContainer script=defaultScript footer=footerTemplate.defaultFooter>
<html lang="en">
<head>

    <@allHead/>
    <@head/>

</head>

<body>

<#--动画固定的div-->
<div id="canvasDIV"></div>



<div id="wrapper">
    <@header/>
                <@container/>


</div>
    <@script/>
</body>

<#--添加关闭特效的css-->
<style type="text/css">
    .closep:hover {
        font-size: 20px;
        transition: 0.8s;
        color:  rgba(34, 174, 245,1)!important; /*必须加！improtant 来提高优先级*/
        /*下面css改变属于关于我页面的*/

    }
</style>
</html>


</#macro>
