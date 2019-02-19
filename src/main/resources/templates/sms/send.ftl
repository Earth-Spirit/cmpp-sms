<#include "${ctx.contextPath}/common/layout.ftl">
<@header title="发送短信">

</@header>
<@body>
    <div class="content-wrapper">


        <section class="content-header">
            <h1>
                Dashboard
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${ctx.contextPath}/index"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">发送短信</li>
            </ol>
        </section>

        <section class="content">
            <div class="box box-body">

                <form action="${ctx.contextPath}/sms/send" method="post" role="form">
                    <#--<legend>发送短信</legend>-->

                    <div class="form-group">
                        <label for="phones">发送号码</label>
                        <textarea class="form-control" rows="3" name="phones" id="phones" placeholder="输入号码,多个号码逗号分隔。一次不要超过100个"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="msg">短信内容</label>
                        <textarea class="form-control"  rows="10" name="msg" id="msg" placeholder="输入短信内容"></textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">提交</button>
                </form>
            </div>
        </section>
    </div>
</@body>
<@footer>

</@footer>