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
        <label class="layui-form-label required">编号</label>
        <div class="layui-input-block">
            <input type="text" name="docNo" lay-verify="required" lay-reqtext="编号不能为空" placeholder="请输入编号" value="" class="layui-input">
<!--            <tip>填写自己管理账号的名称。</tip>-->
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="docName" lay-verify="required" lay-reqtext="医生姓名不能为空" placeholder="请输入医生姓名" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">密码</label>
        <div class="layui-input-block">
            <input type="text" name="docPass" lay-verify="required" lay-reqtext="登陆密码不能为空" placeholder="请输入登陆密码" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">手机</label>
        <div class="layui-input-block">
            <input type="text" name="docPhone" lay-verify="required" lay-reqtext="手机号不能为空" placeholder="请输入医生手机号" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">职位</label>
        <div class="layui-input-block">
            <input type="radio" name="docTitle" value="开单人" title="开单人" checked="">
            <input type="radio" name="docTitle" value="治疗师" title="治疗师">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
<script src="../../lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            var index = layer.alert('请确认是否提交', {
                title: '提示'
            }, function () {
                $.ajax({
                    url:'/doctor/add.do',
                    type:'post',
                    dataType:'json',
                    contentType: 'application/json',
                    data:JSON.stringify(data.field),
                    timeout:2000,
                    success:function(data){
                        console.log(data);
                        if(data.status == 'success'){
                            layer.msg("新增成功");
                            layer.close(index);
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                            parent.location.reload()
                        }else{
                            layer.msg("新增失败")
                        }
                    },
                    error:function () {
                        layer.msg("新增失败")
                    }
                })
                // 关闭弹出层
                layer.close(index);

                var iframeIndex = parent.layer.getFrameIndex(window.name);
                parent.layer.close(iframeIndex);

            });

            return false;
        });

    });
</script>
</body>
</html>