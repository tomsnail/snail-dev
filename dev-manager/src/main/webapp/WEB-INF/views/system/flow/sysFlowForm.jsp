<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统流程管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/flow/sysFlow/">系统流程列表</a></li>
		<li class="active"><a href="${ctx}/flow/sysFlow/form?id=${sysFlow.id}">系统流程<shiro:hasPermission name="flow:sysFlow:edit">${not empty sysFlow.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="flow:sysFlow:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysFlow" action="${ctx}/flow/sysFlow/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">流程：</label>
			<div class="controls">
				<form:select path="startFlowId" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getAllDictList()}" itemLabel="description" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下一个流程：</label>
			<div class="controls">
				<sys:treeselect id="nextFlowIds0" name="nextFlowIds" value="${sysFlow.nextFlowIds}" labelName="parent.name" labelValue="${sysFlow.nextFlowIds}" checked="true" notAllowSelectParent="true"
					title="父ID" url="/sys/dict/treeDataForFlow" extId="${sysFlow.nextFlowIds}" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流程描述：</label>
			<div class="controls">
				<form:input path="flowDesc" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流程名称：</label>
			<div class="controls">
				<form:input path="flowName" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="flow:sysFlow:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>