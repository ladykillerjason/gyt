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
                            <label class="layui-form-label">开始时间</label>
                            <div class="layui-input-inline">
                                <input type="datetime-local" name="startTime" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结束时间</label>
                            <div class="layui-input-inline">
                                <input type="datetime-local" name="endTime" autocomplete="off" class="layui-input">
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
            url: '/stat/statMoneyOfKaidanRen.do',
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
                {field: 'kaidanName', width: 500, title: '开单人姓名'},
                {field: 'totalSum', width: 1000, title: '总收益'},
            ]],
            // limits: [10, 15, 20, 25, 50, 100],
            // limit: 15,
            // page: 1,
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
                    'startTime':$("input[name='startTime']").val()===""?"":$("input[name='startTime']").val().replace("T"," ")+":00",
                    'endTime':$("input[name='endTime']").val()===""?"":$("input[name='endTime']").val().replace("T"," ")+":00"
                },
            }, 'data');

            return false;
        });


    });
</script>

</body>
</html>