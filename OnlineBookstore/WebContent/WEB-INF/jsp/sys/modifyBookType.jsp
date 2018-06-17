<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<h2 class="contentTitle">Modify Book Type</h2>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/updateBookType.action" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this)">
		<input type="hidden" value="${modifybean.id}" name="id"/>
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>Name：</dt>
							<dd><input name="name" value="${modifybean.name}" type="text"  class="required"/></dd>
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
