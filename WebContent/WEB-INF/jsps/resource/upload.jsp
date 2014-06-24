<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<form>
	<div id="queue"></div>
	<input id="file_upload" name="myfile" type="file" multiple="true" />
	<!-- <textarea id="SWFUpload_Console" wrap="off" style="font-family: monospace; overflow: auto; width: 700px; height: 350px; margin: 5px;"></textarea> -->
</form>
<script type="text/javascript">
$('#file_upload').uploadify({
	//'debug'    : true,
	'swf'      : '${base }/res/uploadify/uploadify.swf',
	'uploader' : '${admin_base}/resource/upload.at',
	'buttonText' : '浏    览...',
	'button_image_url' : '${base}/res/uploadify/browse-btn.png',
	'fileTypeExts' : '*.gif; *.jpg; *.png; *.css; *.js;',
	'method'   : 'post',
	'formData' : {
		'id':'${param.id}',
		'jsessionid':'${pageContext.session.id}'
	},
	'onQueueComplete' : function(queueData) {
		$("#fileData").datagrid("reload");
    }
});
</script>