<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>访问地址管理</title>
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
		<li class="active"><a href="${ctx}/conf/visitAddress/">访问地址列表</a></li>
		<shiro:hasPermission name="conf:visitAddress:edit"><li><a href="${ctx}/conf/visitAddress/form">访问地址添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="visitAddress" action="${ctx}/conf/visitAddress/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分组名称：</label>
				<form:select path="grpgName" class="input-xlarge required">
					<form:option label="" value=""></form:option>
					<form:options items="${fns:getDictList('access_group_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>负责人：</label>
				<form:select path="author" class="input-xlarge required">
					<form:option label="" value=""></form:option>
					<form:options items="${fns:getDictList('access_author_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>访问地址：</label>
				<form:input path="visitAddr" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>功能描述：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>版本：</label>
				<form:select path="verInfo" class="input-xlarge required">
						<form:option label="" value=""></form:option>
						<form:options items="${fns:getDictList('access_version_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否发布：</label>
				<form:select path="isRelease" class="input-medium" >
					<form:option value=" " label=" " />
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>是否验签：</label>
				<form:select path="isSign" class="input-medium">
					<form:option value=" " label=" "/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>是否鉴权：</label>
				<form:select path="isAuth" class="input-medium">
					<form:option value=" " label=" "/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>是否内部：</label>
				<form:select path="isInner" class="input-medium">
					<form:option value=" " label=" "/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li><label>请求类型：</label>
				<form:select path="httpType" class="input-medium">
					<form:option value=" " label=" "/>
					<form:options items="${fns:getDictList('access_http_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<shiro:hasPermission name="conf:visitAddress:edit"><td>
    				<a id="btnSubmit" class="btn btn-primary" type="button" href="${ctx}/conf/visitAddress/updateCache" value="更新缓存">更新缓存</a>
			</td></shiro:hasPermission>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>访问地址</th>
				<th>请求类型</th>
				<th>分组名称</th>
				<th>发布</th>
				<th>功能描述</th>
				<th>负责人</th>
				<th>验签</th>
				<th>鉴权</th>
				<th>默认版本</th>
				<th>版本</th>
				<th>用户信息</th>
				<th>内部地址</th>
				<th>更新时间</th>
				<shiro:hasPermission name="conf:visitAddress:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="visitAddress">
			<tr>
				<td><a href="${ctx}/conf/visitAddress/form?id=${visitAddress.id}">
					${visitAddress.visitAddr}
				</a></td>
				<td>
					${fns:getDictLabel(visitAddress.httpType, 'access_http_type', '')}
				</td>
				<td>
					${visitAddress.grpgName}
				</td>
				<td>
					${fns:getDictLabel(visitAddress.isRelease, 'yes_no', '')}
				</td>
				<td>
					${visitAddress.remarks}
				</td>
				<td>
					${visitAddress.author}
				</td>
				<td>
					${fns:getDictLabel(visitAddress.isSign, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(visitAddress.isAuth, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(visitAddress.isDefVer, 'yes_no', '')}
				</td>
				<td>
					${visitAddress.verInfo}
				</td>
				<td>
					${fns:getDictLabel(visitAddress.isAddUser, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(visitAddress.isInner, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${visitAddress.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="conf:visitAddress:edit"><td>
    				<c:choose>
						<c:when test="${visitAddress.isRelease == '1'}">
							<a href="${ctx}/conf/visitAddress/useOper?id=${visitAddress.id}&isRelease=0">停用</a>
						</c:when>
						<c:otherwise>
							<a href="${ctx}/conf/visitAddress/useOper?id=${visitAddress.id}&isRelease=1">发布</a>
						</c:otherwise>
					</c:choose>
					<a href="${ctx}/conf/visitAddress/form?id=${visitAddress.id}">修改</a>
					<a href="${ctx}/conf/visitAddress/delete?id=${visitAddress.id}" onclick="return confirmx('确认要删除该访问地址吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>