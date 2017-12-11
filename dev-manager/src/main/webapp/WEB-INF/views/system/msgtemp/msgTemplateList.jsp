<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息模板管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/msgtemp/msgTemplate/">消息模板列表</a></li>
		<shiro:hasPermission name="msgtemp:msgTemplate:edit"><li><a href="${ctx}/msgtemp/msgTemplate/form">消息模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="msgTemplate" action="${ctx}/msgtemp/msgTemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模板名称：</label>
				<form:input path="templName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>模板编号：</label>
				<form:input path="templCode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>模板类型：</label>
				<form:select path="templType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('msg_context_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>发送方式：</label>
				<form:select path="templSendType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('msg_send_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否启用：</label>
				<form:select path="isUse" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>模板名称</th>
				<th>模板描述</th>
				<th>模板编号</th>
				<th>模板类型</th>
				<th>发送方式</th>
				<th>是否启用</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="msgtemp:msgTemplate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="msgTemplate">
			<tr>
				<td><a href="${ctx}/msgtemp/msgTemplate/form?id=${msgTemplate.id}">
					${msgTemplate.templName}
				</a></td>
				<td>
					${msgTemplate.templDesc}
				</td>
				<td>
					${msgTemplate.templCode}
				</td>
				<td>
					${fns:getDictLabel(msgTemplate.templType, 'msg_context_type', '')}
				</td>
				<td>
					${fns:getDictLabel(msgTemplate.templSendType, 'msg_send_type', '')}
				</td>
				<td>
					${fns:getDictLabel(msgTemplate.isUse, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${msgTemplate.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${msgTemplate.remarks}
				</td>
				<shiro:hasPermission name="msgtemp:msgTemplate:edit"><td>
    				<a href="${ctx}/msgtemp/msgTemplate/form?id=${msgTemplate.id}">修改</a>
					<a href="${ctx}/msgtemp/msgTemplate/delete?id=${msgTemplate.id}" onclick="return confirmx('确认要删除该消息模板吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>