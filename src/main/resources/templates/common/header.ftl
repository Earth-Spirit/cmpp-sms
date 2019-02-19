<header class="main-header">

    <!-- Logo -->
    <a href="${ctx.contextPath}/index" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>A</b>LT</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>Admin</b>LTE</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <!-- Navbar Right Menu -->
        <#--<div class="navbar-custom-menu">-->
            <#--<ul class="nav navbar-nav">-->
                <#--<!-- Messages: style can be found in dropdown.less&ndash;&gt;-->
                <#--<li class="dropdown messages-menu">-->
                    <#--<!-- Menu toggle button &ndash;&gt;-->
                    <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                        <#--<i class="fa fa-envelope-o"></i>-->
                        <#--<span class="label label-success">4</span>-->
                    <#--</a>-->
                    <#--<ul class="dropdown-menu">-->
                        <#--<li class="header">You have 4 messages</li>-->
                        <#--<li>-->
                            <#--<!-- inner menu: contains the messages &ndash;&gt;-->
                            <#--<ul class="menu">-->
                                <#--<li><!-- start message &ndash;&gt;-->
                                    <#--<a href="#">-->
                                        <#--<div class="pull-left">-->
                                            <#--<!-- Client Image &ndash;&gt;-->
                                            <#--<img src="${ctx.contextPath}/plugins/adminlte/img/user2-160x160.jpg" class="img-circle" alt="User Image">-->
                                        <#--</div>-->
                                        <#--<!-- Message title and timestamp &ndash;&gt;-->
                                        <#--<h4>-->
                                            <#--Support Team-->
                                            <#--<small><i class="fa fa-clock-o"></i> 5 mins</small>-->
                                        <#--</h4>-->
                                        <#--<!-- The message &ndash;&gt;-->
                                        <#--<p>Why not buy a new awesome theme?</p>-->
                                    <#--</a>-->
                                <#--</li>-->
                                <#--<!-- end message &ndash;&gt;-->
                            <#--</ul>-->
                            <#--<!-- /.menu &ndash;&gt;-->
                        <#--</li>-->
                        <#--<li class="footer"><a href="#">See All Messages</a></li>-->
                    <#--</ul>-->
                <#--</li>-->
                <#--<!-- /.messages-menu &ndash;&gt;-->

                <#--<!-- Notifications Menu &ndash;&gt;-->
                <#--<li class="dropdown notifications-menu">-->
                    <#--<!-- Menu toggle button &ndash;&gt;-->
                    <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                        <#--<i class="fa fa-bell-o"></i>-->
                        <#--<span class="label label-warning">10</span>-->
                    <#--</a>-->
                    <#--<ul class="dropdown-menu">-->
                        <#--<li class="header">You have 10 notifications</li>-->
                        <#--<li>-->
                            <#--<!-- Inner Menu: contains the notifications &ndash;&gt;-->
                            <#--<ul class="menu">-->
                                <#--<li><!-- start notification &ndash;&gt;-->
                                    <#--<a href="#">-->
                                        <#--<i class="fa fa-users text-aqua"></i> 5 new members joined today-->
                                    <#--</a>-->
                                <#--</li>-->
                                <#--<!-- end notification &ndash;&gt;-->
                            <#--</ul>-->
                        <#--</li>-->
                        <#--<li class="footer"><a href="#">View all</a></li>-->
                    <#--</ul>-->
                <#--</li>-->
                <#--<!-- Tasks Menu &ndash;&gt;-->
                <#--<li class="dropdown tasks-menu">-->
                    <#--<!-- Menu Toggle Button &ndash;&gt;-->
                    <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                        <#--<i class="fa fa-flag-o"></i>-->
                        <#--<span class="label label-danger">9</span>-->
                    <#--</a>-->
                    <#--<ul class="dropdown-menu">-->
                        <#--<li class="header">You have 9 tasks</li>-->
                        <#--<li>-->
                            <#--<!-- Inner menu: contains the tasks &ndash;&gt;-->
                            <#--<ul class="menu">-->
                                <#--<li><!-- Task item &ndash;&gt;-->
                                    <#--<a href="#">-->
                                        <#--<!-- Task title and progress text &ndash;&gt;-->
                                        <#--<h3>-->
                                            <#--Design some buttons-->
                                            <#--<small class="pull-right">20%</small>-->
                                        <#--</h3>-->
                                        <#--<!-- The progress bar &ndash;&gt;-->
                                        <#--<div class="progress xs">-->
                                            <#--<!-- Change the css width attribute to simulate progress &ndash;&gt;-->
                                            <#--<div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar"-->
                                                 <#--aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">-->
                                                <#--<span class="sr-only">20% Complete</span>-->
                                            <#--</div>-->
                                        <#--</div>-->
                                    <#--</a>-->
                                <#--</li>-->
                                <#--<!-- end task item &ndash;&gt;-->
                            <#--</ul>-->
                        <#--</li>-->
                        <#--<li class="footer">-->
                            <#--<a href="#">View all tasks</a>-->
                        <#--</li>-->
                    <#--</ul>-->
                <#--</li>-->
                <#--<!-- Client Account Menu &ndash;&gt;-->
                <#--<li class="dropdown user user-menu">-->
                    <#--<!-- Menu Toggle Button &ndash;&gt;-->
                    <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                        <#--<!-- The user image in the navbar&ndash;&gt;-->
                        <#--<img src="${ctx.contextPath}/plugins/adminlte/img/user2-160x160.jpg" class="user-image" alt="User Image">-->
                        <#--<!-- hidden-xs hides the username on small devices so only the image appears. &ndash;&gt;-->
                        <#--<span class="hidden-xs">Alexander Pierce</span>-->
                    <#--</a>-->
                    <#--<ul class="dropdown-menu">-->
                        <#--<!-- The user image in the menu &ndash;&gt;-->
                        <#--<li class="user-header">-->
                            <#--<img src="${ctx.contextPath}/plugins/adminlte/img/user2-160x160.jpg" class="img-circle" alt="User Image">-->

                            <#--<p>-->
                                <#--Alexander Pierce - Web Developer-->
                                <#--<small>Member since Nov. 2012</small>-->
                            <#--</p>-->
                        <#--</li>-->
                        <#--<!-- Menu Body &ndash;&gt;-->
                        <#--<li class="user-body">-->
                            <#--<div class="row">-->
                                <#--<div class="col-xs-4 text-center">-->
                                    <#--<a href="#">Followers</a>-->
                                <#--</div>-->
                                <#--<div class="col-xs-4 text-center">-->
                                    <#--<a href="#">Sales</a>-->
                                <#--</div>-->
                                <#--<div class="col-xs-4 text-center">-->
                                    <#--<a href="#">Friends</a>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<!-- /.row &ndash;&gt;-->
                        <#--</li>-->
                        <#--<!-- Menu Footer&ndash;&gt;-->
                        <#--<li class="user-footer">-->
                            <#--<div class="pull-left">-->
                                <#--<a href="#" class="btn btn-default btn-flat">Profile</a>-->
                            <#--</div>-->
                            <#--<div class="pull-right">-->
                                <#--<a href="#" class="btn btn-default btn-flat">Sign out</a>-->
                            <#--</div>-->
                        <#--</li>-->
                    <#--</ul>-->
                <#--</li>-->
                <#--<!-- Control Sidebar Toggle Button &ndash;&gt;-->
                <#--<li>-->
                    <#--<a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>-->
                <#--</li>-->
            <#--</ul>-->
        <#--</div>-->
    </nav>
</header>