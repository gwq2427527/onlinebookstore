<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<h2 class="contentTitle">Modify User</h2>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/updateSimpleUser.action" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this)">
		<input type="hidden" value="${modifybean.id }" name="id">
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>Account：</dt>
							<dd><input name="user.uname" value="${modifybean.user.uname }" type="text"  class="required" readonly="readonly"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Password：</dt>
							<dd> 
								<input name="user.userPassword"  value="" id="w_validation_pwd" type="password"  class="alphanumeric" minlength="6" maxlength="20"/>
							</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl class="nowrap">
							<dt>Re-password：</dt>
							<dd><input name="repassword"  value=""  type="password" class="" equalto="#w_validation_pwd"/><span class="info">(Empty to not modify)</span></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Name：</dt>
							<dd><input name="user.userName" value="${modifybean.user.userName }" type="text"  class="required"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Gender：</dt>
							<dd>
								<select name="user.userGender">
									<option value="0" <c:if test="${modifybean.user.userGender==0}">selected="selected"</c:if> >Female</option>
									<option value="1" <c:if test="${modifybean.user.userGender==1}">selected="selected"</c:if>>Male</option>
								</select>
							</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Phone：</dt>
							<dd><input name="user.userPhone" value="${modifybean.user.userPhone }" type="text"  class="phone"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Email：</dt>
							<dd><input name="user.userEmail" value="${modifybean.user.userEmail }" type="text"  class="email"/></dd>
							</dl></td>
					</tr>
					
					<tr>
						<td colspan="2"><dl>
							<dt>Birth：</dt>
							<dd><input name="user.userBirth" value="${modifybean.user.userBirth }" type="text"  class="date"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Address：</dt>
							<dd><input name="user.userAddress" value="${modifybean.user.userAddress }" type="text"  class=""/></dd>
							</dl></td>
					</tr>
				</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">Submit</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset" class="reset">Reset</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
