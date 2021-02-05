<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
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

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
<!--                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>-->
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
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
            url: '/patient/list.do',
            method: 'post',
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
                // {type: "checkbox", width: 50},
                // {field: 'id', width: 200, title: 'ID', sort: true,hide:true},
                {field: 'patientNo', width: 200, title: '编号', sort: true},
                {field: 'patientName', width: 200, title: '姓名'},
                {field: 'patientPhone', width: 200, title: '手机'},
                {field: 'patientSex', width: 200, title: '性别', },
                {field: 'patientAge', width: 200, title: '年龄'},
                {field: 'patientMemo', width: 300, title: '备注', sort: true},
                {title: '操作', minWidth: 200, toolbar: '#currentTableBar', align: "center"}
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
                , where:{'patientNo':$("input[name='patientNo']").val(),
                    'patientName':$("input[name='patientName']").val()
                },
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加病人信息',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['80%', '80%'],
                    content: './add-patient.jsp',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {

                var index = layer.open({
                    title: '编辑病人信息',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['80%', '80%'],
                    // content: './edit-patient.jsp?id='+data['id']+'&patientNo='+data['patientNo']+'&patientName='+data['patientName']+
                    //     '&patientSex='+data['patientSex']+'&patientAge='+data['patientAge']+'&patientMemo='+data['patientMemo'],
                    content: './edit-patient.jsp?patientNo='+data['patientNo'],
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    // obj.del();
                    // layer.close(index);
                    $.ajax({
                        url:'/patient/delete.do',
                        type:'post',
                        dataType:'json',
                        contentType: 'application/json',
                        data:JSON.stringify(data),
                        timeout:2000,
                        success:function(data){
                            console.log(data);
                            if(data.status == 'success'){
                                layer.msg("删除成功");
                                layer.close(index);
                                window.location.reload();

                            }else{
                                layer.msg("删除失败")
                            }
                        },
                        error:function () {
                            layer.msg("删除失败")
                        }
                    })
                });
            }
        });

    });
</script>

</body>
</html>