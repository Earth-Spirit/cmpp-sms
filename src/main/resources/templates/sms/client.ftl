<#include "${ctx.contextPath}/common/layout.ftl">
<@header title="客户端状态">

</@header>
<@body>
    <div class="content-wrapper">




        <section class="content">
            <div class="box box-body">
                <form class="form-horizontal">
                    <legend>客户端信息</legend>

                    <div class="box-body">
                        <div class="form-group">
                            <label for="ip" class="col-sm-2 control-label">网关ip:</label>

                            <div class="col-sm-10">
                                <input id="ip" readonly="readonly" class="form-control" value="${config.ip}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="port" class="col-sm-2 control-label">端口:</label>

                            <div class="col-sm-10">
                                <input  class="form-control" readonly="readonly" id="port" value="${config.port?c}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="port" class="col-sm-2 control-label">企业代码:</label>

                            <div class="col-sm-10">
                                <input  class="form-control" readonly="readonly" id="port" value="${config.spId}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="port" class="col-sm-2 control-label">密码:</label>

                            <div class="col-sm-10">
                                <input  class="form-control" readonly="readonly" id="port" value="${config.sharedSecret}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="port" class="col-sm-2 control-label">服务代码:</label>

                            <div class="col-sm-10">
                                <input  class="form-control" id="port" readonly="readonly" value="${config.serviceId}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="port" class="col-sm-2 control-label">客户端状态:</label>

                            <div class="col-sm-10">
                                <#if status == false>
                                    <div class="badge bg-red" style="font-size: 14px; margin-top: 6px"> 停止状态</div>
                                <#else >
                                    <div class="badge bg-green" style="font-size: 14px; margin-top: 6px"> 运行状态</div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                </form>
            </div>
        </section>
    </div>
</@body>
<@footer>
</@footer>