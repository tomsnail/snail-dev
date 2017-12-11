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
		<li class="active"><a href="${ctx}/dict/dictAreaInfo/">地区列表</a></li>
		<shiro:hasPermission name="dict:dictAreaInfo:edit"><li><a href="${ctx}/dict/dictAreaInfo/form">地区添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dictAreaInfo" action="${ctx}/dict/dictAreaInfo/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>地区名称：</label>
				<form:input path="areaName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>地区编号：</label>
				<form:input path="deptCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>地区名称</th>
				<th>地区父ID</th>
				<th>排序</th>
				<th>地区深度</th>
				<th>地区编号</th>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="dict:dictAreaInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/dict/dictAreaInfo/form?id={{row.id}}">
				{{row.areaName}}
			</a></td>
			<td>
				{{row.parent.id}}
			</td>
			<td>
				{{row.areaSort}}
			</td>
			<td>
				{{row.areaDeep}}
			</td>
			<td>
				{{row.deptCode}}
			</td>
			<td>
				{{row.updateDate}}
			</td>
			<td>
				{{row.remarks}}
			</td>
			<shiro:hasPermission name="dict:dictAreaInfo:edit"><td>
   				<a href="${ctx}/dict/dictAreaInfo/form?id={{row.id}}">修改</a>
				<a href="${ctx}/dict/dictAreaInfo/delete?id={{row.id}}" onclick="return confirmx('确认要删除该地区及所有子地区吗？', this.href)">删除</a>
				<a href="${ctx}/dict/dictAreaInfo/form?parent.id={{row.id}}">添加下级地区</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>