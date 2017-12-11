<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台消息管理</title>
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
		<li class="active"><a href="${ctx}/msg/systemMessage/">平台消息列表</a></li>
		<shiro:hasPermission name="msg:systemMessage:edit"><li><a href="${ctx}/msg/systemMessage/form">平台消息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="systemMessage" action="${ctx}/msg/systemMessage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>消息标题：</label>
				<form:input path="msgTitle" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>消息类型：</label>
				<form:select path="msgType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>消息内容：</label>
				<form:input path="msgContent" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li><label>消息是否多播：</label>
				<form:input path="isMsgMulti" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>消息系统：</label>
				<form:input path="msgSys" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>消息标题</th>
				<th>消息类型</th>
				<th>发送人</th>
				<th>接收人</th>
				<th>消息时间</th>
				<th>消息是否多播</th>
				<th>消息系统</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="msg:systemMessage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="systemMessage">
			<tr>
				<td><a href="${ctx}/msg/systemMessage/form?id=${systemMessage.id}">
					${systemMessage.msgTitle}
				</a></td>
				<td>
					${fns:getDictLabel(systemMessage.msgType, '', '')}
				</td>
				<td>
					${systemMessage.sendPerson}
				</td>
				<td>
					${systemMessage.receivePerson}
				</td>
				<td>
					<fmt:formatDate value="${systemMessage.msgDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${systemMessage.isMsgMulti}
				</td>
				<td>
					${systemMessage.msgSys}
				</td>
				<td>
					<fmt:formatDate value="${systemMessage.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${systemMessage.remarks}
				</td>
				<shiro:hasPermission name="msg:systemMessage:edit"><td>
    				<a href="${ctx}/msg/systemMessage/form?id=${systemMessage.id}">修改</a>
					<a href="${ctx}/msg/systemMessage/delete?id=${systemMessage.id}" onclick="return confirmx('确认要删除该平台消息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>