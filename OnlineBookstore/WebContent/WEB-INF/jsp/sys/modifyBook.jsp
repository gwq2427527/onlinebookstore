<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<h2 class="contentTitle">Modify Book</h2>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/updateBook.action" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this,navTabAjaxDone)">
		<input type="hidden" value="${modifybean.id}" name="id"/>
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl class="nowrap">
							<dt>Image：</dt>
							<dd>
								<img alt="" src="${ctx }/resource/${modifybean.imgpath}" width="150px" height="150px">
								<br/>
								<input name="attachment.attachmentPath" value="${modifybean.imgpath }" type="hidden">
								<input class="readonly" name="attachment.fileName" value="" readonly="readonly" type="text"/>
								<a class="btnAttach" href="${ctx }/sys/toUpload.action" lookupGroup="attachment" width="560" height="300" title="Attachment ">Attachment </a>
							</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Type：</dt>
							<dd>
								<select name="bookType.id" class="combox" id="dfsrwer34333333333333333" >
									<c:forEach items="${list }" var="item">
										<option value="${item.id }">${item.name }</option>
									</c:forEach>
								</select>
							</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Name：</dt>
							<dd><input name="name" value="${modifybean.name}" type="text"  class="required"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>ISBN：</dt>
							<dd><input name="sid" value="${modifybean.sid}" type="text"  class="required"/></dd>
							</dl></td>
					</tr>
					
					<tr>
						<td colspan="2"><dl>
							<dt>Price：</dt>
							<dd><input name="price" value="${modifybean.price}" type="text"  class="required number"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Discount Price：</dt>
							<dd><input name="offPrice" value="${modifybean.offPrice}" type="text"  class="required number"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Discount：</dt>
							<dd><input name="offPercent"  value="${modifybean.offPercent}" type="text"  class="required digits"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Author：</dt>
							<dd><input name="author" value="${modifybean.author}" type="text"  class="required"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Press：</dt>
							<dd><input name="press" value="${modifybean.press}" type="text"  class="required"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Year：</dt>
							<dd><input name="publicationYear" value="${modifybean.publicationYear}" type="text"  class="digits required"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Note：</dt>
							<dd>
							<textarea rows="10" cols="80" name="note">${modifybean.note }</textarea>
							</dd>
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
<script type="text/javascript">$("#dfsrwer34333333333333333").val("${modifybean.bookType.id}");
</script>