<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>开治疗单</title>
    <link rel="stylesheet" href="../../lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../../css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">


        <form class="layui-form" action="" style="padding:20px;">

            <div class="layui-form-item">
                <label class="layui-form-label required">开单人编号</label>
                <div class="layui-input-inline">
                    <input type="text" name="kaidanNo" lay-verify="required" lay-reqtext="开单人医生不能为空" placeholder="请输入开单人编号" value="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">病人编号</label>
                <div class="layui-input-inline">
                    <input type="text" name="patientNo" lay-verify="required" lay-reqtext="病人编号不能为空" placeholder="请输入病人编号" value="" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">病人名字</label>
                <div class="layui-input-inline">
                    <input type="text" name="patientName" lay-verify="required" lay-reqtext="病人名字不能为空" placeholder="请输入病人名字" value="" class="layui-input">
                </div>
            </div>

            <table class="layui-hide" id="project_table"></table>

<!--            <button type="button" class="layui-btn" lay-filter="submit">提交</button>-->
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
        </form>

        <pre class="layui-code">

        </pre>

    </div>
</div>

<script src="../../lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="../../js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['table', 'form', 'tableSelect'], function () {
        var $ = layui.jquery,
            table = layui.table,
            form = layui.form

        table.render({
            elem: '#project_table'
            , url: '/project/list.do'
            , method: 'post',
            contentType: 'application/json;charset=UTF-8'
            , cols: [[
                {type: 'checkbox'}
                , {field: 'projectNo', width: 300, title: '项目编号'}
                , {field: 'projectName', width: 300, title: '项目名称', sort: true}
                , {field: 'projectPrice', width: 300, title: '价格', sort: true}
                , {field: 'treatTotalCount', title: '治疗次数', width: 205,edit: 'text'}
            ]]
            , parseData: function (res) { //res 即为原始返回的数据
                for(var i in res){
                    res[i]['treatTotalCount'] = 3;
                }
                return {
                    "code": 0, //解析接口状态
                    "msg": "", //解析提示文本
                    "count": res.length, //解析数据长度
                    "data": res //解析数据列表
                };
            }
            , page: true
        });


        //监听提交
        form.on('submit(demo1)', function (data) {
            data = data.field;
            var newData = {};
            newData['kaidanNo'] = data['kaidanNo'];
            newData['patientNo'] = data['patientNo'];
            newData['patientName'] = data['patientName'];
            var projects = [];

            var checkStatus = table.checkStatus('project_table')
            var selectData = checkStatus.data;
            console.log(checkStatus)
            for(var i in selectData){
                var tm = {};
                tm['projectNo'] = selectData[i]['projectNo'];
                tm['treatTotalCount'] = selectData[i]['treatTotalCount'];
                projects.push(tm)
            }
            newData['projects'] = projects;

            $.ajax({
                url: '/treatBill/add.do',
                type: 'post',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(newData),
                timeout: 2000,
                success: function (ret) {
                    console.log(ret);
                    if (ret.status == 'success') {
                        layer.msg("新增成功");

                        var iframeIndex = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(iframeIndex);
                        setTimeout(function(){
                            window.location.href="success.jsp"
                        },1000)
                    } else {
                        layer.msg("新增失败")
                    }
                },
                error: function () {
                    layer.msg("新增失败")
                }
            })
            return false;
        });
    });
</script>
</body>
</html>