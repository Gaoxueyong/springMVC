<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>区域管理</title>
	<meta name="decorator" content="default"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10">
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<link href="${ctx }/static/static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx }/static/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet">
	<link href="${ctx }/static/static/jquery-select2/3.4/select2.min.css" rel="stylesheet">
	<link href="${ctx }/static/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet">
	<link href="${ctx }/static/static/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
	<link href="${ctx }/static/static/common/jeesite.css" type="text/css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/js/webuploader.css" rel="external nofollow" >
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/webuploader.js"></script>
</head>

<body>
<!-- 2.创建页面元素 -->
<div id="uploader" class="wu-example">
	<!--用来存放文件信息-->
	<div id="thelist" class="uploader-list"></div>
	<div class="btns">
		<div id="filePicker">选择文件</div>
		<button id="ctlBtn" class="btn btn-default">开始上传</button>
	</div>
</div>
<!-- 3.添加js代码 -->
<script type="text/javascript">
    var fileArray = [];
    var guid = WebUploader.Base.guid();
    var $list = $("#thelist");
    var $btn = $("#ctlBtn");
    var timer;
    var state = 'pending'; // 上传文件初始化
    var uploader = WebUploader.create({

        // swf文件路径
        swf:"../js/Uploader.swf",

        // 文件接收服务端。
        server:"/sys/file/upload",

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker',

        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        chunked:true,
        duplicate: true,
//         每块文件大小（默认5M）
        chunkSize:5*1024*1024,
        // 开启几个并非线程（默认3个）
        threads:3,
        // 在上传当前文件时，准备好下一个文件
        prepareNextFile:true,
        formData:{"guid":guid}
    });
    // 当有文件被添加进队列的时候
    uploader.on( 'fileQueued', function( file ) {
        $list.append( '<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
            '</div>' );
    });

    //点击上传之前调用的方法
    uploader.on("uploadStart", function (file) {
        var paramOb = {"guid": guid, "filedId": file.source.ruid}
        uploader.options.formData.guid = guid;
        fileArray.push(paramOb);
    });

    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css( 'width', percentage * 100 + '%' );
    });


    //文件成功、失败处理
    uploader.on('uploadSuccess', function (file) {
//        var successDuid = file.source.ruid;
        var chunks = file.blocks.length;
        var realFileName = file.name;
        var folder = file.id;
        clearInterval(timer);
        $.post("/sys/file/merge", {"guid": guid,"chunks":chunks,"realFileName":realFileName,"folder":folder}, function (data, status) {
//            alert("文件上传成功！")
        });
        $( '#'+file.id ).find('p.state').text('已上传');
    });

    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text('上传出错');
    });
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });

    $btn.on('click', function () {
        if (state === 'uploading') {
            uploader.stop();
        } else {
            uploader.upload();
            timer = setInterval(function () {
                var useTime = parseInt($("#useTime").html());
                useTime = useTime + 1;
                $("#useTime").html(useTime);
            }, 1000);
        }
    });


</script>
</body>
</html>