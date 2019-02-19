<#include "${ctx.contextPath}/common/layout.ftl">
<@header title="仪表盘">

</@header>
<@body>
    <div class="content-wrapper">


        <section class="content-header">
            <h1>
                Dashboard
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Dashboard</li>
            </ol>
        </section>

        <section class="content">
            <!-- Small boxes (Stat box) -->
            <div class="row">
                <div class="col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-red"><i class="fa fa-star-o"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">处理失败记录数</span>
                            <span class="info-box-number"><a href="${ctx.contextPath}/sms/list?state=2">${failCount}</a></span>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->
                <div class="col-sm-6 col-xs-12">
                    <div class="info-box">
                        <span class="info-box-icon bg-aqua"><i class="fa fa-star-o"></i></span>

                        <div class="info-box-content">
                            <span class="info-box-text">待处理记录数</span>
                            <span class="info-box-number"><a href="${ctx.contextPath}/sms/list?state=0">${pendingCount}</a></span>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

            <div class="row">
                <div class="col-md-12">
                    <!-- AREA CHART -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">近30天短信发送情况</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                </button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <div class="box-body">
                            <div id="areaChart" class="chart" style="height: 350px; width: 100%;"></div>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->


                </div>
                <!-- /.clol -->

            </div>
        </section>
    </div>
</@body>
<@footer>
    <script src="${ctx.contextPath}/plugins/echarts.min.js"></script>
    <script type="text/javascript">


        function loadStatistics(){


            var myChart = echarts.init(document.getElementById('areaChart'));
            $.get("${ctx.contextPath}/sms/getstatistics").done(function (data) {

                var option = {
                    legend: {},
                    tooltip: {},
                    dataset: {
                        dimensions: ['CREATE_DAY', 'SMS_COUNT'],
                        source: data
                    },
                    // 声明一个 X 轴，类目轴（category）。默认情况下，类目轴对应到 dataset 第一列。
                    xAxis: {type: 'category'},
                    // 声明一个 Y 轴，数值轴。
                    yAxis: {},
                    // 声明多个 bar 系列，默认情况下，每个系列会自动对应到 dataset 的每一列。
                    series: [
                        {type: 'line'}
                    ]
                }
                myChart.setOption(option);
            });
            // 使用刚指定的配置项和数据显示图表。
            //myChart.setOption(option);
        }
        $(document).ready(function(){
            loadStatistics();
        });
    </script>
</@footer>