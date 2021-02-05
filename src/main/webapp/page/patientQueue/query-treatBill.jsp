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
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>

<div class="layuimini-container">
    <div class="layuimini-main">


        <form class="layui-form" action="" style="padding:20px;">

            <table class="layui-hide" id="project_table"></table>

        </form>

        <pre class="layui-code">

        </pre>

    </div>
</div>

<script src="../../lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="../../js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }


    layui.use(['table', 'form', 'tableSelect'], function () {
        var $ = layui.jquery,
            table = layui.table,
            form = layui.form

        table.render({
            elem: '#project_table'
            , url: '/treatBill/list.do'
            , method: 'post'
            ,contentType: 'application/json;charset=UTF-8'
            ,where:{'treatBillNo':getUrlParam('treatBillNo')}
            , cols: [[
                {type: 'radio'}
                , {field: 'treatBillNo', width: 200, title: '治疗单编号'}
                , {field: 'patientName', width: 200, title: '病人姓名'}
                , {field: 'projectName', width: 200, title: '项目名称'}
                , {field: 'treatTotalCount', width: 100, title: '总治疗次数'}
                , {field: 'treatUseCount', title: '已用治疗次数', width: 100}
                , {field: 'createTime', title: '开单时间', width: 200}
            ]]
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": 0, //解析接口状态
                    "msg": "", //解析提示文本
                    "count": res.length, //解析数据长度
                    "data": res //解析数据列表
                };
            }
            , page: false
        });

    });
</script>
</body>
</html>