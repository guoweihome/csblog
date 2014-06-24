$(document).ajaxStart(function(){
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
}).ajaxStop(function(){
	parent.$.messager.progress('close');
});

function isJson(obj){
	var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;    
	return isjson;
}

function dealResult(json,callback){
	if(json&&isJson(json)&&json.STATUS&&json.STATUS=="300"){
		parent.$.messager.alert("错误",json.msg,"error");
		return false;
	}
	return true;
}

function ajaxError(xhr, ajaxOptions, thrownError){
	parent.$.messager.alert("错误","Http status: " + xhr.status + " " + xhr.statusText + "\brajaxOptions: " + ajaxOptions + "\brthrownError:"+thrownError + "\br" +xhr.responseText,"error");
}

function goAjax(options){
	var opts = $.extend({type:"post",dataType:"json",cache:false,async:true,traditional:false},options);
	$.ajax({
		type:opts.type,
		url:opts.url,
		async:opts.async,
		data:opts.data,
		dataType:opts.dataType,
		cache: opts.cache,
		traditional:opts.traditional,
		success: function(json){
			if(dealResult(json)){
				if($.isFunction(opts.callback)){
					opts.callback(json);
				}
			}
		},
		error: ajaxError
	});
}