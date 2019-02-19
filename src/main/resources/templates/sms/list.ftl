<#include "${ctx.contextPath}/common/layout.ftl">
<@header title="短信记录查询">
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/bootstrap-table/bootstrap-table.min.css">
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/bootstrap-daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="${ctx.contextPath}/plugins/select2/css/select2.min.css">

</@header>
<@body>
    <div class="content-wrapper">


        <section class="content-header">
            <h1>
                Dashboard
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">短信记录查询</li>
            </ol>
        </section>

        <section class="content">
            <div class="col-xs-12">

                <div id="condition" class="box box-default">
                    <div class="box-header with-border">
                        <h3 class="box-title">查询条件</h3>

                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                            </button>
                        </div>
                        <!-- /.box-tools -->
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">


                        <form class="form-horizontal">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="phone" class="col-xs-3 control-label">号码</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" id="phone" name="phone">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label for="createDate" class="col-xs-2 control-label">时间</label>
                                    <label id="startTime" style="display: none"></label>
                                    <label id="endTime" style="display: none"></label>

                                    <div class="col-xs-10">
                                        <input id="createDate" name="createDate" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="state" class="col-xs-3 control-label">状态</label>
                                    <div class="col-xs-9">
                                        <select class="form-control" id="state" name="state">
                                            <option value="-1">全部</option>
                                            <option value="0">待发送</option>
                                            <option value="1">发送成功</option>
                                            <option value="2">发送失败</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <div class="form-group">
                                    <button id="btnQuery"  type="button" class="btn btn-primary"><span class="fa fa-search"></span> 查询</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- /.box-body -->
                </div>

                <div class="box">
                    <!-- /.box-header -->
                    <div class="box-body">
                        <div class="form-inline">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div id="toolbar">
                                        <#--<button id="btn-query-display" class="btn btn-sm btn-default"><span class="fa fa-search-plus"></span>查询条件</button>-->
                                        <button id="btnReSend" class="btn btn-sm btn-primary" disabled><span class="fa fa-pencil"></span>重发</button>
                                        <button id="btnRemove" class="btn btn-sm btn-danger" disabled><span class="fa fa-remove"></span>删除</button>
                                    </div>
                                    <table id="detail-table" class="table table-bordered table-hover table-striped bootstrap-table" role="table"></table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                </div>

            </div>
        </section>
    </div>
</@body>
<@footer>
    <script src="${ctx.contextPath}/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctx.contextPath}/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- date-range-picker -->
    <script src="${ctx.contextPath}/plugins/moment/min/moment.min.js"></script>
    <script src="${ctx.contextPath}/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>

    <script src="${ctx.contextPath}/plugins/select2/js/select2.min.js"></script>

    <script type="text/javascript">

        function initTable(){
            $("#detail-table").bootstrapTable({
                url: "${ctx.contextPath}/sms/page", // 服务器数据的加载地址
                method:"POST",
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                contentType: "application/json",   // 编码类型
                pagination: true, // 设置为true会在底部显示分页条
                sortable: true,                     //是否启用排序
                pageList: [20,50,100],
                pageSize: 20, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                toolbar: "#toolbar",
                search: false,
                showColumns: true,
                showRefresh: true,
                showToggle: true,
                queryParams: function (params) {

                    var p = {
                        "pageNumber": params.offset / params.limit + 1,
                        "pageSize": params.limit,
                        "conditions":{
                            "startTime":$('#startTime').val(),
                            "endTime": $('#endTime').val(),
                            "state": $('#state').val(),
                            "phone": $('#phone').val()
                        }
                    };

                    // var conditions = new Object();
                    // conditions.startTime = conditions
                    // conditions.endTime = $('#endTime').val();
                    // conditions.state = $('#state').val();
                    // conditions.phone = $('#phone').val();
                    //
                    // p.conditions = conditions;
                    return JSON.stringify(p);

                },
                // responseHandler: function(result){
                //     if(result.code == 0){
                //         return {rows: result.data.rows,total:result.data.total }
                //     }else{
                //         alert('处理失败'+ result.msg)
                //     }
                // },
                columns:[
                    {
                        checkbox: true // 列首单选框
                    },
                    {
                        field: "id",
                        title: "ID"
                    },{
                        field: "phone",
                        title: "电话号码"
                    },
                    {
                        field: "msg",
                        title: "短信内容"
                    },{
                        field: "createDate",
                        title: "创建时间"
                    },{
                        field: "state",
                        title: "状态",
                        formatter:function (value , row ,index) {
                            //<span class="badge bg-green">90%</span>
                            if (value == '0'){
                                return '<span class="badge bg-blue">待发送</span>';

                            }else if(value == '1'){
                                return '<span class="badge bg-green">发送成功</span>';
                            }else if(value == '2'){
                                return '<span class="badge bg-red">发送失败</span>';
                            }else{
                                return value;
                            }

                        }
                    },{
                        field: "stateDate",
                        title: "状态时间"
                    },{
                        field: "remark",
                        title: "备注"
                    },{
                        field: "operate",
                        title: "操作",
                        formatter: function operateFormatter(value , row ,index) {
                            var actions = [];
                            actions.push('<button id="edit" class="btn btn-primary btn-xs" onclick=updateSms(\''+ row.id+'\')> <i class="fa fa-pencil"></i>重发 </button> ')
                            actions.push('<button id="remove" class="btn btn-danger btn-xs" onclick=deleteSms(\''+ row.id +'\')> <i class="fa fa-remove"></i>删除 </button> ')
                            return actions.join('');
                        }
                    }
                ],
                onCheckAll:function(rows){

                   if(rows.length >0){
                       $("#btnReSend").removeAttr("disabled");
                       $("#btnRemove").removeAttr("disabled");

                   }
                },
                onCheck:function(row){

                    $("#btnReSend").removeAttr("disabled");
                    $("#btnRemove").removeAttr("disabled");

                },
                onUncheck:function(row){

                    if(rows.length >0){
                        $("#btnReSend").removeAttr("disabled");
                        $("#btnRemove").removeAttr("disabled");

                    }
                },
                onUncheckAll:function(rows){
                    $("#btnReSend").attr("disabled","disabled");
                    $("#btnRemove").attr("disabled","disabled");
                },
                onLoadSuccess: function(data) {
                    console.log('load');
                }
            });
        }

        function initTableQueryBtn(tableId) {

            var toolbarRight = document.getElementById(tableId).parentElement.parentElement.getElementsByClassName('columns')[0];
            var innerHTML = '<button id="btn-query-display" class="btn btn-default" type="button" name="query" aria-label="query" title="查询"><i class="glyphicon glyphicon-search icon-search"></i></button>';
            toolbarRight.innerHTML += innerHTML ;

        }
        function deletebyIds(ids){


            if(confirm("确定删除选中的"+ ids.length + "行数据？")){

                $.ajax({
                    type: 'POST',
                    url: '${ctx.contextPath}/sms/delete',
                    //contentType : 'application/json',
                    data: {
                        'ids': ids
                    },
                    success:function () {
                        $("#detail-table").bootstrapTable('refresh');
                    },
                    error:function (data) {
                        alert('error:' + data.toString());
                    }
                });

            }


        }

        function updateStatebyIds(ids){

            if(confirm("确定重发选定"+ ids.length + "行数据？")) {

                $.ajax({
                    type: 'POST',
                    //contentType: 'application/json', //不使用contentType: “application/json”则data可以是对象,使用contentType: “application/json”则data只能是json字符串
                    url: '${ctx.contextPath}/sms/updateState',
                    data: {
                        'ids': ids
                    },
                    success:function(){
                        $("#detail-table").bootstrapTable('refresh');
                    },
                    error: function (error) {
                        alert(error);
                    }
                });
            }
        }

        function deleteSms(id){

            var ids = new Array();
            ids.push(id);
            deletebyIds(ids);
        }

        function updateSms(id){
            var ids = new Array();
            ids.push(id);
            updateStatebyIds(ids);
        }
        $(function () {



            var state = ${RequestParameters["state"]!-1};
            $("#state option[value="+ state+"]").attr("selected",true);
            console.log("state=" + state);

            $("#createDate").daterangepicker(
                {
                    timePicker: true,
                    timePickerIncrement: 30,
                    format: 'YYYY-MM-DD HH:mm:ss',
                    // autoApply: true,
                    autoUpdateInput: false,
                    // alwaysShowCalendars: true,
                    ranges: {
                        '今天': [moment().startOf('day'),moment().endOf('day')],
                        '本周': [moment().startOf('week'),moment().endOf('week')],
                        '本月': [moment().startOf('month'),moment().endOf('month')],
                        '上月': [moment().add(-1, 'months').startOf("month"), moment().add(-1, 'months').endOf('month')],
                        '最近三个月':[moment().add(-3,'months').startOf('day'),moment().endOf('day')]
                    },
                    locale: {
                        format: "YYYY-MM-DD HH:mm:ss",
                        separator: " - ",
                        applyLabel: "确认",
                        cancelLabel: "清空",
                        fromLabel: "开始时间",
                        toLabel: "结束时间",
                        customRangeLabel: "自定义",
                        daysOfWeek: ["日","一","二","三","四","五","六"],
                        monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
                    }
                }
            ).on('cancel.daterangepicker', function(ev, picker) {
                $("#createDate").val("请选择日期范围");
                $("#startTime").val("");
                $("#endTime").val("");
            }).on('apply.daterangepicker', function(ev, picker) {
                $("#startTime").val(picker.startDate.format('YYYY-MM-DD HH:mm:ss'));
                $("#endTime").val(picker.endDate.format('YYYY-MM-DD HH:mm:ss'));
                $("#createDate").val(picker.startDate.format('YYYY-MM-DD HH:mm:ss')+" 至 "+picker.endDate.format('YYYY-MM-DD HH:mm:ss'));
            });
            initTable();
            //initTableQueryBtn('toolbar');

            $('#btnQuery').on('click',function () {

                $("#detail-table").bootstrapTable('refresh');

            });

            $('#btnRemove').click(function () {

                var ids = $.map($('#detail-table').bootstrapTable('getSelections'), function (row) {
                    return row.id;
                });
                deletebyIds(ids);
            });

            $('#btnReSend').on('click',function () {

                var ids = $.map($('#detail-table').bootstrapTable('getSelections'), function (row) {
                    return row.id;
                });
                updateStatebyIds(ids);
            });

            $('#btn-query-display').click(function () {

                /*
                1.$("#id").show()表示为display：block，
                $("#id").hide()表示为display:none;
                2.$("#id").toggle()切换元素的可见状态。如果元素是可见的，切换为隐藏的；如果元素是隐藏的，则切换为可见的。
                3.$("#id").css('display','none');//隐藏
                    $("#id").css('display','block');//显示
                    或者$("#id")[0].style.display='none';
                 */
                $('#condition').toggle();
            });


        });
    </script>
</@footer>