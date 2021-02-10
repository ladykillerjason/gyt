<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>上传此次治疗的图片</title>
    <link rel="stylesheet" href="/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="/lib/jq-module/zyupload/zyupload-1.0.0.min.css" media="all">
    <link rel="stylesheet" href="/css/public.css" media="all">
</head>
<body>

<div class="layui-form layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label required">治疗单号</label>
        <div class="layui-input-block">
            <input disabled type="text" name="treatBillNo" lay-verify="required" lay-reqtext="治疗编号不能为空"  value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">治疗师编号</label>
        <div class="layui-input-block">
            <input disabled type="text" name="docNo" lay-verify="required" lay-reqtext="治疗师编号不能为空"  value="" class="layui-input">
        </div>
    </div>

    <div id="zyupload" class="zyupload"></div>

<%--    <div class="layui-form-item">--%>
<%--        <button type="button" class="layui-btn" id="test2">多图片上传</button>--%>
<%--        <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">--%>
<%--            预览图：--%>
<%--            <div class="layui-upload-list" id="demo2"></div>--%>
<%--        </blockquote>--%>
<%--    </div>--%>

</div>



<script src="/lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="/lib/jq-module/zyupload/zyupload-1.0.0.min.js" charset="utf-8"></script>
<script type="text/javascript">

    $(function () {
        // 初始化插件
        $("#zyupload").zyUpload({
            width: "650px",                 // 宽度
            height: "400px",                 // 宽度
            itemWidth: "140px",                 // 文件项的宽度
            itemHeight: "115px",                 // 文件项的高度
            url: "/treatLog/upload",  // 上传文件的路径
            fileType: ["jpg", "png", "jpeg", "gif"],// 上传文件的类型
            fileSize: 51200000,                // 上传文件的大小
            multiple: true,                    // 是否可以多个文件上传
            dragDrop: true,                    // 是否可以拖动上传文件
            tailor: false,                    // 是否可以裁剪图片
            del: false,                    // 是否可以删除文件
            finishDel: false,  				  // 是否在上传文件完成后删除预览
            /* 外部获得的回调接口 */
            onSelect: function (selectFiles, allFiles) {    // 选择文件的回调方法  selectFile:当前选中的文件  allFiles:还没上传的全部文件
                console.info("当前选择了以下文件：");
                console.info(selectFiles);
            },
            onDelete: function (file, files) {              // 删除一个文件的回调方法 file:当前删除的文件  files:删除之后的文件
                console.info("当前删除了此文件：");
                console.info(file.name);
            },
            onSuccess: function (file, response) {          // 文件上传成功的回调方法
                console.info("此文件上传成功：");
                console.info(file.name);
                console.info("此文件上传到服务器地址：");
                console.info(response);
                $("#uploadInf").append("<p>上传成功，文件地址是：" + response + "</p>");
            },
            onFailure: function (file, response) {          // 文件上传失败的回调方法
                console.info("此文件上传失败：");
                console.info(file.name);
            },
            onComplete: function (response) {           	  // 上传完成的回调方法
                console.info("文件上传完成");
                console.info(response);
            }
        });

    });


    // layui.use('upload', function(){
    //     var $ = layui.jquery
    //         ,upload = layui.upload;
    //     //多图片上传
    //     upload.render({
    //         elem: '#test2'
    //         ,url: 'https://httpbin.org/post' //改成您自己的上传接口
    //         ,multiple: true
    //         ,before: function(obj){
    //             //预读本地文件示例，不支持ie8
    //             obj.preview(function(index, file, result){
    //                 $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
    //             });
    //         }
    //         ,done: function(res){
    //             //上传完毕
    //         }
    //     });
    //
    // });
</script>

</body>
</html>