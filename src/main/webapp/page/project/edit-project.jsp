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
<div class="layui-form layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label required">项目编号</label>
        <div class="layui-input-block">
            <input disabled type="text" name="projectNo" lay-verify="required" lay-reqtext="项目编号不能为空" placeholder="请输入项目编号" value="" class="layui-input">
            <tip>填写自己管理账号的名称。</tip>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">项目名称</label>
        <div class="layui-input-block">
            <input type="text" name="projectName" lay-verify="required" lay-reqtext="项目名称为空" placeholder="请输入项目名称" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">价格</label>
        <div class="layui-input-block">
            <input type="text" name="projectPrice" lay-verify="required" lay-reqtext="价格为空" placeholder="请输入价格" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
</div>
<script src="../../lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>

    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        var tmpParam = {};
        tmpParam['projectNo'] = getUrlParam('projectNo');
        layui.$.ajax({
            url:'/project/list.do',
            type:'post',
            dataType:'json',
            contentType: 'application/json',
            data:JSON.stringify(tmpParam),
            timeout:2000,
            success:function(res){
                var data = res[0];
                $("input[name=projectNo]").val(data['projectNo'])
                $("input[name=projectName]").val(data['projectName'])
                $("input[name=projectPrice]").val(data['projectPrice'])
            },
            error:function () {
                layer.msg("获取数据失败")
            }
        })


        //监听提交
        form.on('submit(saveBtn)', function (data) {
            var index = layer.alert("请确认是否提交", {
                title: '提示'
            }, function () {

                $.ajax({
                    url:'/project/edit.do',
                    type:'post',
                    dataType:'json',
                    contentType: 'application/json',
                    data:JSON.stringify(data.field),
                    timeout:2000,
                    success:function(data){
                        console.log(data);
                        if(data.status == 'success'){
                            layer.msg("修改成功");
                            layer.close(index);
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                            parent.location.reload()

                        }else{
                            layer.msg("修改失败")
                        }
                    },
                    error:function () {
                        layer.msg("修改失败")
                    }
                })

                // 关闭弹出层
                layer.close(index);

                var iframeIndex = parent.layer.getFrameIndex(window.name);
                // parent.layer.close(iframeIndex);
                // parent.location.reload();

            });

            return false;
        });

    });
</script>
</body>
</html>