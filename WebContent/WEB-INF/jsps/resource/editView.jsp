<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="p" class="easyui-panel" title="文件编辑-${file.name }" style="height: 800px;padding: 5px">
<textarea id="editText" rows="10" cols="30" style="width: 98%;height: 90%">${content }</textarea>

<br/><br/>
<center>
<a href="#" class="easyui-linkbutton" id="backBtn" data-options="iconCls:'icon-back'"  style="width:80px">返  回</a>
<a href="#" class="easyui-linkbutton" id="saveFileBtn" data-options="iconCls:'icon-save'"  style="width:80px">保  存</a>
</center>
</div>
