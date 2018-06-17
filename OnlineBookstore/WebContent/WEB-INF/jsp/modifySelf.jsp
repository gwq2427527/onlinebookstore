<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./common/head.jsp"%>
<h2 class="contentTitle">Personal Information</h2>
<div class="pageContent">
	<form method="post" action="${ctx}/com/modifySelf.action" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this)">
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>Account：</dt>
							<dd><input name="uname" value="${SESSION_BEAN.user.user.uname }" type="text"  class="required" readonly="readonly"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Password：</dt>
							<dd> 
								<input name="bean.userPassword"  value="" id="w_validation_pwd" type="password"  class="alphanumeric" minlength="6" maxlength="20"/>
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
							<dd><input name="bean.userName" value="${SESSION_BEAN.user.user.userName }" type="text"  class="required"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Gender：</dt>
							<dd>
								<select name="bean.userGender">
									<option value="0" <c:if test="${SESSION_BEAN.user.user.userGender==0}">selected="selected"</c:if> >FeMale</option>
									<option value="1" <c:if test="${SESSION_BEAN.user.user.userGender==1}">selected="selected"</c:if>>Male</option>
								</select>
							</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Phone：</dt>
							<dd><input name="bean.userPhone" value="${SESSION_BEAN.user.user.userPhone }" type="text"  class="phone"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Email：</dt>
							<dd><input name="bean.userEmail" value="${SESSION_BEAN.user.user.userEmail }" type="text"  class="email"/></dd>
							</dl></td>
					</tr>
					
					<tr>
						<td colspan="2"><dl>
							<dt>Birth：</dt>
							<dd><input name="bean.userBirth" value="${SESSION_BEAN.user.user.userBirth }" type="text"  class="date"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Address：</dt>
							<dd><input name="bean.userAddress" value="${SESSION_BEAN.user.user.userAddress }" type="text"  class=""/></dd>
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
