<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>访问地址管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/conf/visitAddress/">访问地址列表</a></li>
		<li class="active"><a href="${ctx}/conf/visitAddress/form?id=${visitAddress.id}">访问地址<shiro:hasPermission name="conf:visitAddress:edit">${not empty visitAddress.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="conf:visitAddress:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="visitAddress" action="${ctx}/conf/visitAddress/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">分组名称：</label>
			<div class="controls">
				<form:select path="grpgName" class="input-xlarge required">
					<form:options items="${fns:getDictList('access_group_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">功能描述：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本：</label>
			<div class="controls">
				<form:select path="verInfo" class="input-xlarge required">
						<form:options items="${fns:getDictList('access_version_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">访问地址：</label>
			<div class="controls">
				<form:input path="visitAddr" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">HTTP类型：</label>
			<div class="controls">
				<form:select path="httpType" class="input-xlarge required">
						<form:options items="${fns:getDictList('access_http_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人：</label>
			<div class="controls">
				<form:select path="author" class="input-xlarge required">
					<form:options items="${fns:getDictList('access_author_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址类型：</label>
			<div class="controls">
				<form:radiobuttons path="addressType" items="${fns:getDictList('access_address_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正式环境URL地址：</label>
			<div class="controls">
				<form:input path="realAddress" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测试环境URL地址：</label>
			<div class="controls">
				<form:input path="testAddress" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">集成环境URL地址：</label>
			<div class="controls">
				<form:input path="comAddress" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">是否发布：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${visitAddress.id==null||visitAddress.id==''||visitAddress.id==undefined}">
						<form:radiobutton path="isRelease" value='1'  label="是"  checked="true"/>
						<form:radiobutton path="isRelease" value='0' label="否"/>
					</c:when>
					<c:otherwise>
						<form:radiobuttons path="isRelease" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" cssClass="required"/>
					</c:otherwise>
				</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否内部使用：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${visitAddress.id==null||visitAddress.id==''||visitAddress.id==undefined}">
						<form:radiobutton path="isInner" value='1'  label="是" />
						<form:radiobutton path="isInner" value='0' label="否" checked="true"/>
					</c:when>
					<c:otherwise>
						<form:radiobuttons path="isInner" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" cssClass="required"/>
					</c:otherwise>
				</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">是否验签：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${visitAddress.id==null||visitAddress.id==''||visitAddress.id==undefined}">
						<form:radiobutton path="isSign" value='1'  label="是" />
						<form:radiobutton path="isSign" value='0' label="否" checked="true"/>
					</c:when>
					<c:otherwise>
						<form:radiobuttons path="isSign" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" cssClass="required"/>
					</c:otherwise>
				</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否鉴权：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${visitAddress.id==null||visitAddress.id==''||visitAddress.id==undefined}">
							<form:radiobutton path="isAuth" value='1'  label="是" checked="true"/>
							<form:radiobutton path="isAuth" value='0' label="否" />
					</c:when>
					<c:otherwise>
						<form:radiobuttons path="isAuth" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" cssClass="required"/>
					</c:otherwise>
				</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否默认版本：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${visitAddress.id==null||visitAddress.id==''||visitAddress.id==undefined}">
							<form:radiobutton path="isDefVer" value='1'  label="是" checked="true"/>
							<form:radiobutton path="isDefVer" value='0' label="否" />
					</c:when>
					<c:otherwise>
						<form:radiobuttons path="isDefVer" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" cssClass="required"/>
					</c:otherwise>
				</c:choose>
				
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否添加版本：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${visitAddress.id==null||visitAddress.id==''||visitAddress.id==undefined}">
							<form:radiobutton path="isAddVer" value='1'  label="是" />
							<form:radiobutton path="isAddVer" value='0' label="否" checked="true"/>
					</c:when>
					<c:otherwise>
						<form:radiobuttons path="isAddVer" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" cssClass="required"/>
					</c:otherwise>
				</c:choose>
				
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否添加用户信息：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${visitAddress.id==null||visitAddress.id==''||visitAddress.id==undefined}">
							<form:radiobutton path="isAddUser" value='1'  label="是" />
							<form:radiobutton path="isAddUser" value='0' label="否" checked="true"/>
					</c:when>
					<c:otherwise>
						<form:radiobuttons path="isAddUser" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" cssClass="required"/>
					</c:otherwise>
				</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否记录访问日志：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${visitAddress.id==null||visitAddress.id==''||visitAddress.id==undefined}">
						<form:radiobutton path="isLogger" value='1'  label="是" />
						<form:radiobutton path="isLogger" value='0' label="否" checked="true"/>
					</c:when>
					<c:otherwise>
						<form:radiobuttons path="isLogger" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" cssClass="required"/>
					</c:otherwise>
				</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">日志级别：</label>
			<div class="controls">
				<form:select path="logLevel" class="input-xlarge required">
					<form:options items="${fns:getDictList('nginx_log_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">访问控制类型：</label>
			<div class="controls">
				<form:select path="accessLimitType" class="input-xlarge required">
					<form:options items="${fns:getDictList('access_limit_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">访问控制值：</label>
			<div class="controls">
				<form:input path="accessLimitValue" htmlEscape="false" maxlength="200" class="input-xlarge required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否降级：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${visitAddress.id==null||visitAddress.id==''||visitAddress.id==undefined}">
						<form:radiobutton path="isDegrade" value='1'  label="是" />
						<form:radiobutton path="isDegrade" value='0' label="否" checked="true"/>
					</c:when>
					<c:otherwise>
						<form:radiobuttons path="isDegrade" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" cssClass="required"/>
					</c:otherwise>
				</c:choose>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">降级返回内容：</label>
			<div class="controls">
				<c:choose>
					<c:when test="${visitAddress.id==null||visitAddress.id==''||visitAddress.id==undefined}">
						<textarea id="degradeContext" name="degradeContext" rows="4" maxlength="4000" class="input-xxlarge required">{'command':'nullResp','sequenceID':'','fingerprint':'','body':{},'status':'901','msg':'系统繁忙,请稍后再试','code':'','msgCode':''}</textarea>
						<span class="help-inline"><font color="red">*</font> </span>					
					</c:when>
					<c:otherwise>
						<form:textarea path="degradeContext" htmlEscape="false" rows="4" maxlength="4000" class="input-xxlarge required"/>
						<span class="help-inline"><font color="red">*</font> </span>					
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="conf:visitAddress:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>