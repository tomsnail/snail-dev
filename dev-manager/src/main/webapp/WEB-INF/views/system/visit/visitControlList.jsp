<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>访问控制管理</title>
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
		
		function refreshCache(){
			 $.get("${ctx}/visit/visitControl/refreshCache", function(result){
				    alert(result);
				});
		}
		function clearCache(){
			 $.get("${ctx}/visit/visitControl/clearCache", function(result){
				    alert(result);
				});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/visit/visitControl/">访问控制列表</a></li>
		<shiro:hasPermission name="visit:visitControl:edit"><li><a href="${ctx}/visit/visitControl/form">访问控制添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="visitControl" action="${ctx}/visit/visitControl/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>访问IP：</label>
				<form:input path="visitIp" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>是否限制：</label>
				<form:select path="isLimit" class="input-medium">
					<form:option value=" " label=" "/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<shiro:hasPermission name="visit:visitControl:edit">
					<a href="#" class="btn btn-primary" onclick="refreshCache()">刷新缓存</a>
					<a href="#" class="btn btn-primary" onclick="clearCache()">清空缓存</a>
				</shiro:hasPermission>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>访问IP</th>
				<th>是否限制</th>
				<th>限制时间</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="visit:visitControl:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="visitControl">
			<tr>
				<td><a href="${ctx}/visit/visitControl/form?id=${visitControl.id}">
					${visitControl.visitIp}
				</a></td>
				<td>
					${fns:getDictLabel(visitControl.isLimit, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${visitControl.limitTime}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					${visitControl.remarks}
				</td>
				<td>
					<fmt:formatDate value="${visitControl.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="visit:visitControl:edit"><td>
    				<a href="${ctx}/visit/visitControl/form?id=${visitControl.id}">修改</a>
    				<a href="${ctx}/visit/visitControl/delete?id=${visitControl.id}" onclick="return confirmx('确认要删除该条目吗？', this.href)">删除</a>
    				
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>