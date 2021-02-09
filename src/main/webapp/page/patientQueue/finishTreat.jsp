<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加排队信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../../css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">

                        <div class="layui-inline">
                            <label class="layui-form-label">治疗单号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="treatBillNo" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">病人编号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="patientNo" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">病人姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="patientName" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="finishTreat">结束治疗</a>
        </script>

        <script type="text/html" id="currentTableBar2">
            <input type="text" name="zhiliaoNo" lay-verify="required" lay-reqtext="编号不能为空" placeholder="请输入治疗师编号"
                   value="" class="layui-input" style="width: 140px;height: 32px;">
        </script>

    </div>
</div>
<script src="../../lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            url: '/treatBill/listInQueue.do',
            method: 'post',
            where:{'isOver':'否'},
            contentType: 'application/json;charset=UTF-8',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": 0, //解析接口状态
                    "msg": "", //解析提示文本
                    "count": res.length, //解析数据长度
                    "data": res //解析数据列表
                };
            },
            cols: [[
                // {type: 'radio'}
                {field: 'treatBillId', width: 200, title: '治疗单ID',hide:true}
                , {field: 'treatBillNo', width: 200, title: '治疗单编号'}
                , {field: 'patientName', width: 200, title: '病人姓名'}
                , {field: 'projectName', width: 200, title: '项目名称'}
                , {field: 'treatTotalCount', width: 200, title: '总治疗次数'}
                , {field: 'treatUseCount', title: '已用治疗次数', width: 200}
                , {field: 'createTime', title: '开单时间', width: 300}
                , {title: '治疗师编号', minWidth: 100, toolbar: '#currentTableBar2', align: "center"}
                , {title: '操作', minWidth: 100, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: 1,
            skin: 'line'
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var result = JSON.stringify(data.field);

            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                }
                , where:{
                    'treatBillNo':$("input[name='treatBillNo']").val(),
                    'patientNo':$("input[name='patientNo']").val(),
                    'patientName':$("input[name='patientName']").val()
                },
            }, 'data');

            return false;
        });


        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'finishTreat') {
                var index = layer.alert("确认结束治疗", {
                    title: '提示'
                }, function () {
                    console.log(data);
                    var tm = {};
                    tm['patientNo'] = data['patientNo'];
                    tm['treatBillNo'] = data['treatBillNo'];
                    tm['treatBillId'] = data['treatBillId'];
                    tm['projectNo'] = data['projectNo'];
                    tm['zhiliaoNo'] = $("input[name='zhiliaoNo']").val().trim();
                    tm['treatCount'] = data['treatUseCount']+1;  // 前者第几次治疗， 后者已经治疗了几次
                    layui.$.ajax({
                        url:'/treatLog/add.do',
                        type:'post',
                        dataType:'json',
                        contentType: 'application/json',
                        data:JSON.stringify(tm),
                        timeout:2000,
                        success:function(res){
                            if(res.status == 'success'){
                                layer.msg("操作成功");
                                setTimeout( function(){
                                    var iframeIndex = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(iframeIndex);
                                    parent.location.reload();
                                }, 1 * 1000 );

                            }else{
                                layer.msg("操作失败")
                            }

                        },
                        error:function () {
                            layer.msg("操作失败")
                        }
                    })

                    $(window).on("resize", function () {
                        layer.full(index);
                    });
                    return false;
                    // 关闭弹出层
                    layer.close(index);

                    var iframeIndex = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(iframeIndex);

                });

            } else if (obj.event === 'delete') {
                layer.confirm('真的删除么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            }
        });

    });
</script>

</body>
</html>