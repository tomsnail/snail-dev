<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>地区管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dict/regionInfo/">地区列表</a></li>
		<shiro:hasPermission name="dict:regionInfo:edit"><li><a href="${ctx}/dict/regionInfo/form">地区添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="regionInfo" action="${ctx}/dict/regionInfo/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>地区名称：</label>
				<form:input path="regionName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>大区名称：</label>
				<form:input path="areaName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>邮政编号：</label>
				<form:input path="postNum" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<shiro:hasPermission name="dict:regionInfo:edit"><li><a class="btn btn-primary" href="${ctx}/dict/regionInfo/updateCache">更新缓存</a></li></shiro:hasPermission>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>地区名称</th>
				<th>父地区ID</th>
				<th>名称</th>
				<th>大区名称</th>
				<th>地区深度</th>
				<th>邮政编号</th>
				<th>行政编号</th>
				<th>排序</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="dict:regionInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/dict/regionInfo/form?id={{row.id}}">
				{{row.regionName}}
			</a></td>
			<td>
				{{row.parent.id}}
			</td>
			<td>
				{{row.name}}
			</td>
			<td>
				{{row.areaName}}
			</td>
			<td>
				{{row.regionDepth}}
			</td>
			<td>
				{{row.postNum}}
			</td>
			<td>
				{{row.adminNum}}
			</td>
			<td>
				{{row.sortNo}}
			</td>
			<td>
				{{row.remarks}}
			</td>
			<td>
				{{row.updateDate}}
			</td>
			<shiro:hasPermission name="dict:regionInfo:edit"><td>
   				<a href="${ctx}/dict/regionInfo/form?id={{row.id}}">修改</a>
				<a href="${ctx}/dict/regionInfo/delete?id={{row.id}}" onclick="return confirmx('确认要删除该地区及所有子地区吗？', this.href)">删除</a>
				<a href="${ctx}/dict/regionInfo/form?parent.id={{row.id}}">添加下级地区</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>