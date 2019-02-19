<#macro header>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Starter</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/Ionicons/css/ionicons.min.css">
    <!-- bootstrap-table -->
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/bootstrap-table/bootstrap-table.css">
    <!-- icheck -->
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/iCheck/all.css">
    <!-- select2 -->
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/select2/css/select2.css">
    <!-- ztree -->
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/ztree/css/metroStyle/metroStyle.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/adminlte/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/adminlte/css/skins/skin-blue.min.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <#nested>
</head>
</#macro>
<!-- /header -->
<#macro body>
<body>
    <#nested>
</#macro>
<!-- /body -->
<#macro footer>
    <!-- REQUIRED JS SCRIPTS -->

    <!-- jQuery 3 -->
    <script src="${ctx.contextPath}/plugins/jquery/jquery.min.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="${ctx.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
    <!-- bootstarp-table -->
    <script src="${ctx.contextPath}/plugins/bootstrap-table/bootstrap-table.js"></script>
    <script src="${ctx.contextPath}/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <!-- iCheck -->
    <script src="${ctx.contextPath}/plugins/iCheck/icheck.js"></script>
    <!-- select2 -->
    <script src="${ctx.contextPath}/plugins/select2/js/select2.full.js"></script>
    <!-- ztree -->
    <script src="${ctx.contextPath}/plugins/ztree/js/jquery.ztree.all.js"></script>
    <!-- AdminLTE App -->
    <script src="${ctx.contextPath}/plugins/adminlte/js/adminlte.min.js"></script>
    <!-- app -->
    <script src="${ctx.contextPath}/js/app.js"></script>
    <#nested>
</body>
</html>
</#macro>