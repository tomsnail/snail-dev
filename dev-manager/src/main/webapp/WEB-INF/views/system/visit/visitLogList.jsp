<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>访问日志管理</title>
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
		<li class="active"><a href="${ctx}/visit/visitLog/">访问日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="visitLog" action="${ctx}/visit/visitLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>地址编码：</label>
				<form:input path="addrCode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>访问IP：</label>
				<form:input path="visitIp" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>访问用户：</label>
				<form:input path="visitUserId" htmlEscape="false" maxlength="36" class="input-medium"/>
			</li>
			<li><label>用户名称：</label>
				<form:input path="visitUserName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>访问时间：</label>
				<input name="beginVisitTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${visitLog.beginVisitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endVisitTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${visitLog.endVisitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>访问地址</th>
				<th>地址编码</th>
				<th>访问IP</th>
				<th>访问用户</th>
				<th>用户名称</th>
				<th>访问时间</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="visit:visitLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="visitLog">
			<tr>
				<td><a href="${ctx}/visit/visitLog/form?id=${visitLog.id}">
					${visitLog.visitAddr}
				</a></td>
				<td>
					${visitLog.addrCode}
				</td>
				<td>
					${visitLog.visitIp}
				</td>
				<td>
					${visitLog.visitUserId}
				</td>
				<td>
					${visitLog.visitUserName}
				</td>
				<td>
					<fmt:formatDate value="${visitLog.visitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${visitLog.remarks}
				</td>
				<td>
					<fmt:formatDate value="${visitLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="visit:visitLog:edit"><td>
    				<a href="${ctx}/visit/visitLog/form?id=${visitLog.id}">修改</a>
					<a href="${ctx}/visit/visitLog/delete?id=${visitLog.id}" onclick="return confirmx('确认要删除该访问日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>