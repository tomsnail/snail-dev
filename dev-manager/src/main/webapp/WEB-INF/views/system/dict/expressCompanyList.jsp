<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>快递公司管理</title>
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
		<li class="active"><a href="${ctx}/dict/expressCompany/">快递公司列表</a></li>
		<shiro:hasPermission name="dict:expressCompany:edit"><li><a href="${ctx}/dict/expressCompany/form">快递公司添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="expressCompany" action="${ctx}/dict/expressCompany/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公司名称：</label>
				<form:input path="coName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('express_use_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>编号：</label>
				<form:input path="num" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>公司名称</th>
				<th>状态</th>
				<th>编号</th>
				<th>首字母</th>
				<th>排序</th>
				<th>公司网站</th>
				<th>图标地址</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="dict:expressCompany:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="expressCompany">
			<tr>
				<td><a href="${ctx}/dict/expressCompany/form?id=${expressCompany.id}">
					${expressCompany.coName}
				</a></td>
				<td>
					${fns:getDictLabel(expressCompany.status, 'express_use_type', '')}
				</td>
				<td>
					${expressCompany.num}
				</td>
				<td>
					${expressCompany.inl}
				</td>
				<td>
					${expressCompany.sortNo}
				</td>
				<td>
					${expressCompany.coWebsite}
				</td>
				<td>
					${expressCompany.iconAddr}
				</td>
				<td>
					${expressCompany.remarks}
				</td>
				<td>
					<fmt:formatDate value="${expressCompany.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="dict:expressCompany:edit"><td>
    				<a href="${ctx}/dict/expressCompany/form?id=${expressCompany.id}">修改</a>
					<a href="${ctx}/dict/expressCompany/delete?id=${expressCompany.id}" onclick="return confirmx('确认要删除该快递公司吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>