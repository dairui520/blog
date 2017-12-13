<#--页面的头部-->
<#macro defaultHeader>
    <!-- Navigation 响应式Bootstrap导航栏-->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" id="navigation" style="background: rgba(26, 51, 71, 0.83);border-color: transparent">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">程序猿生活</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/back/login">登陆</a></li><li class="divider"></li>
                    <li><a href="/back/register">注册</a></li><li class="divider"></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/about/me">关于我</a>

                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
<style>

</style>
</#macro>