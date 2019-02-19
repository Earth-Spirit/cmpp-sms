<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

      <!-- sidebar: style can be found in sidebar.less -->
      <section class="sidebar">

          <#--<!-- Sidebar user panel (optional) &ndash;&gt;-->
          <#--<div class="user-panel">-->
              <#--<div class="pull-left image">-->
                  <#--<img src="${ctx.contextPath}/plugins/adminlte/img/user2-160x160.jpg" class="img-circle" alt="User Image">-->
              <#--</div>-->
              <#--<div class="pull-left info">-->
                  <#--<p>Alexander Pierce</p>-->
                  <#--<!-- Status &ndash;&gt;-->
                  <#--<a href="#"><i class="fa fa-circle text-success"></i> Online</a>-->
              <#--</div>-->
          <#--</div>-->

          <#--<!-- search form (Optional) &ndash;&gt;-->
          <#--<form action="#" method="get" class="sidebar-form">-->
              <#--<div class="input-group">-->
                  <#--<input type="text" name="q" class="form-control" placeholder="Search...">-->
                  <#--<span class="input-group-btn">-->
              <#--<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>-->
              <#--</button>-->
            <#--</span>-->
              <#--</div>-->
          <#--</form>-->
          <#--<!-- /.search form &ndash;&gt;-->

          <!-- Sidebar Menu -->
          <ul class="sidebar-menu" data-widget="tree">
              <li class="header">系统菜单</li>
              <!-- Optionally, you can add icons to the links -->
              <#--<li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>-->
              <#--<li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>-->
              <li class="active treeview">
                  <a href="/index"><i class="fa fa-dashboard"></i> <span>短信管理</span>
                      <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
                  </a>
                  <ul class="treeview-menu">

                      <li><a href="${ctx.contextPath}/index"><i class="fa fa-circle-o"></i>仪表盘</a></li>
                      <li><a href="${ctx.contextPath}/sms/list"><i class="fa fa-circle-o"></i>短信记录查询</a></li>
                      <li><a href="${ctx.contextPath}/client"><i class="fa fa-circle-o"></i>网关状态查询</a></li>
                      <li><a href="${ctx.contextPath}/sms"><i class="fa fa-circle-o"></i>短信发送</a></li>

                  </ul>
              </li>
          </ul>
          <!-- /.sidebar-menu -->
      </section>
      <!-- /.sidebar -->
  </aside>