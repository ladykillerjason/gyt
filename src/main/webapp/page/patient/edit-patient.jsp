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
        <label class="layui-form-label required">病人编号</label>
        <div class="layui-input-block">
            <input disabled type="text" name="patientNo" lay-verify="required" lay-reqtext="病人编号不能为空" placeholder="请输入病人编号" value="" class="layui-input">
            <tip>填写病人编号。</tip>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">病人名字</label>
        <div class="layui-input-block">
            <input type="text" name="patientName" lay-verify="required" lay-reqtext="病人名字不能为空" placeholder="请输入病人名字" value="" class="layui-input">
            <tip>填写病人名字。</tip>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="patientSex" value="男" title="男" checked="">
            <input type="radio" name="patientSex" value="女" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-block">
            <input type="text" name="patientAge" placeholder="请输入年龄" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">手机</label>
        <div class="layui-input-block">
            <input type="number" name="patientPhone" lay-verify="required" lay-reqtext="手机不能为空" placeholder="请输入手机" value="" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注信息</label>
        <div class="layui-input-block">
            <textarea name="patientMemo" class="layui-textarea" placeholder="请输入备注信息"></textarea>
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
        tmpParam['patientNo'] = getUrlParam('patientNo');
        layui.$.ajax({
            url:'/patient/list.do',
            type:'post',
            dataType:'json',
            contentType: 'application/json',
            data:JSON.stringify(tmpParam),
            timeout:2000,
            success:function(res){
                var data = res[0];
                $("input[name=patientNo]").val(data['patientNo'])
                $("input[name=patientName]").val(data['patientName'])
                $("input[name=patientAge]").val(data['patientAge'])
                $("input[name=patientPhone]").val(data['patientPhone'])
                $("input[name=patientSex]").val(data['patientSex'])
                $("textarea[name=patientMemo]").val(data['patientMemo'])
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
                    url:'/patient/edit.do',
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