<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统流程管理</title>
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
		<li class="active"><a href="${ctx}/flow/sysFlow/">系统流程列表</a></li>
		<shiro:hasPermission name="flow:sysFlow:edit"><li><a href="${ctx}/flow/sysFlow/form">系统流程添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysFlow" action="${ctx}/flow/sysFlow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>流程：</label>
				<form:select path="startFlowId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getAllDictList()}" itemLabel="description" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>流程名称：</label>
				<form:input path="flowName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>流程名称</th>
				<th>开始流程</th>
				<th>下一流程</th>
				<th>流程描述</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="flow:sysFlow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysFlow">
			<tr>
				<td>
					${sysFlow.flowName}
				</td>
				<td>
					${sysFlow.startFlowId}
				</td>
				<td>
					${sysFlow.nextFlowIds}
				</td>
				
				<td>
					${sysFlow.flowDesc}
				</td>
				<td>
					<fmt:formatDate value="${sysFlow.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysFlow.remarks}
				</td>
				<shiro:hasPermission name="flow:sysFlow:edit"><td>
    				<a href="${ctx}/flow/sysFlow/form?id=${sysFlow.id}">修改</a>
					<a href="${ctx}/flow/sysFlow/delete?id=${sysFlow.id}" onclick="return confirmx('确认要删除该系统流程吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>