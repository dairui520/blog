<#macro defaultHeader>

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/back">博客管理</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <#if Session.user??>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${Session.user.name!"Anonymous"} <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#"><i class="fa fa-fw fa-envelope"></i> 邮箱</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-gear"></i> 设置</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a id="logout" href="javascript:;"><i class="fa fa-fw fa-power-off"></i> 注销</a>
                            </li>
                        </ul>
                    <#else>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> 匿名<b class="caret"></b></a>
                    </#if>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="/back/blog"><i class="fa fa-fw fa-pencil"></i> 博文</a>
                    </li>
                    <li>
                        <a href="/back/category"><i class="fa fa-fw fa-folder"></i> 分录</a>
                    </li>
                    <li>
                        <a href="/back/crawler"><i class="fa fa-fw fa-bug"></i> 爬虫</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <!-- /#page-wrapper -->

</#macro>