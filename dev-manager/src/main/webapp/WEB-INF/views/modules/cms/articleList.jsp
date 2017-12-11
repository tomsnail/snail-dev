<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function viewComment(href){
			top.$.jBox.open('iframe:'+href,'查看评论',$(top.document).width()-220,$(top.document).height()-120,{
				buttons:{"关闭":true},
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
					$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
					$("body", h.find("iframe").contents()).css("margin","10px");
				}
			});
			return false;
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		$.get('${ctx}/cms/article/getRefeashNum',function(res){
			$('#refreshNum').html("");
			$('#refreshNum').append(res);
		});
	</script>
	<shiro:hasPermission name="cms:article:edit">
		<script type="text/javascript">
			
		function updateCdn(id){
			$.get('${ctx}/cms/article/updateCdn?id='+id,function(res){
				alert(res);
				$.get('${ctx}/cms/article/getRefeashNum',function(res){
					$('#refreshNum').html("");
					$('#refreshNum').append(res);
				});
			});
		}
		</script>
	</shiro:hasPermission>
</head>
<body onload="loadNum">
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cms/article/?category.id=${article.category.id}">文章列表</a></li>
		<shiro:hasPermission name="cms:article:edit"><li><a href="<c:url value='${fns:getAdminPath()}/cms/article/form?id=${article.id}&category.id=${article.category.id}'><c:param name='category.name' value='${article.category.name}'/></c:url>">文章添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="article" action="${ctx}/cms/article/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>栏目：</label><sys:treeselect id="category" name="category.id" value="${article.category.id}" labelName="category.name" labelValue="${article.category.name}"
					title="栏目" url="/cms/category/treeData" module="article" notAllowSelectRoot="false" cssClass="input-small"/>
		<label>标题：</label><form:input path="title" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
		<label>状态：</label><form:radiobuttons onclick="$('#searchForm').submit();" path="delFlag" items="${fns:getDictList('cms_del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>&nbsp;
		<label id="refreshNum"></label>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>栏目</th><th>标题</th><th>状态</th><th>发布者</th><th>发布时间</th><th>更新时间</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="article">
			<tr>
				<td><a href="javascript:" onclick="$('#categoryId').val('${article.category.id}');$('#categoryName').val('${article.category.name}');$('#searchForm').submit();return false;">${article.category.name}</a></td>
				<td><a href="${ctx}/cms/article/form?id=${article.id}" title="${article.title}">${fns:abbr(article.title,40)}</a></td>
				<td>${fns:getDictLabel(article.delFlag, 'cms_del_flag', '')}</td>
				<td>${article.user.name}</td>
				<td><fmt:formatDate value="${article.publishDate}" type="both"/></td>
				<td><fmt:formatDate value="${article.updateDate}" type="both"/></td>
				<td>
					<a href="${article.staticUrl}" target="_blank">访问</a>
					<shiro:hasPermission name="cms:article:edit">
	    				<a href="${ctx}/cms/article/form?id=${article.id}">详情</a>
	    				<c:if test="${article.delFlag!=0}">
									<a href="#" onclick="return confirmx('确认要更新该文章吗？', function(){updateCdn('${article.id}');})" >更新CDN</a>
						</c:if>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>