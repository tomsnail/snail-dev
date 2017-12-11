<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>subtest管理</title>
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
		<li class="active"><a href="${ctx}/subtest/qqData/">subtest列表</a></li>
		<shiro:hasPermission name="subtest:qqData:edit"><li><a href="${ctx}/subtest/qqData/form">subtest添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="qqData" action="${ctx}/subtest/qqData/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>test：</label>
				<form:input path="testqq" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>test</th>
				<th>数据统计</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="subtest:qqData:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qqData">
			<tr>
				<td><a href="${ctx}/subtest/qqData/form?id=${qqData.id}">
					${qqData.testqq}
				</a></td>
				<td>
					${qqData.count}
				</td>
				<td>
					<fmt:formatDate value="${qqData.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${qqData.remarks}
				</td>
				<shiro:hasPermission name="subtest:qqData:edit"><td>
    				<a href="${ctx}/subtest/qqData/form?id=${qqData.id}">修改</a>
					<a href="${ctx}/subtest/qqData/delete?id=${qqData.id}" onclick="return confirmx('确认要删除该subtest吗？', this.href)">删除</a>
					<a href="${ctx}/subtest/qqData/updatestatus?id=${qqData.id}" onclick="return confirmx('确认要更新该subtest吗？', this.href)">更新</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>