<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统配置管理</title>
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
		<li class="active"><a href="${ctx}/config/sysConfig/">系统配置列表</a></li>
		<shiro:hasPermission name="config:sysConfig:edit"><li><a href="${ctx}/config/sysConfig/form">系统配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysConfig" action="${ctx}/config/sysConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sys_config_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>描述</th>
				<th>编码</th>
				<th>类型</th>
				<th>是否启用</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="config:sysConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysConfig">
			<tr>
				<td><a href="${ctx}/config/sysConfig/form?id=${sysConfig.id}">
					${sysConfig.desc}
				</a></td>
				<td>
					${sysConfig.name}
				</td>
				<td>
					${fns:getDictLabel(sysConfig.type, 'sys_config_type', '')}
				</td>
				<td>
					${fns:getDictLabel(sysConfig.isUse, 'yes_no', '')}
				</td>
				<td>
					${sysConfig.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sysConfig.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="config:sysConfig:edit"><td>
    				<a href="${ctx}/config/sysConfig/form?id=${sysConfig.id}">修改</a>
					<a href="${ctx}/config/sysConfig/delete?id=${sysConfig.id}" onclick="return confirmx('确认要删除该系统配置吗？', this.href)">删除</a>
					<c:if test="${sysConfig.type=='103002'}">
						<a href="${ctx}/config/sysConfig/clearCache?id=${sysConfig.id}" onclick="return confirmx('确认要清除该系统配置吗？', this.href)">清除缓存</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>