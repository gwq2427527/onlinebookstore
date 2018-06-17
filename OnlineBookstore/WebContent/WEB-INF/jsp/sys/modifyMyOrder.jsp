<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<h2 class="contentTitle">Order Detail</h2>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/updateUserOrderBook.action" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this)">
		<input type="hidden" value="${modifybean.id}" name="userOrderBook.id"/>
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>Order ID：</dt>
							<dd>${modifybean.userOrder.sid }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Buyer：</dt>
							<dd>${modifybean.userOrder.buyer.user.userName }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Buyer Phone：</dt>
							<dd>${modifybean.userOrder.buyer.user.userPhone }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Buyer Address：</dt>
							<dd>${modifybean.userOrder.buyer.user.userAddress}</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Order Date：</dt>
							<dd>${modifybean.userOrder.addDate }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Order Status：</dt>
							<dd>${modifybean.userOrder.status }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Book Price：</dt>
							<dd>${modifybean.price }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Book ISBN：</dt>
							<dd>${modifybean.book.sid }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Book Name：</dt>
							<dd ><a href="${ctx}/com/getBook.action?uid=${modifybean.book.id}" target="blank" style="color:blue">${modifybean.book.name }</a></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Score：</dt>
							<dd>
							<select name="userOrderBook.score" id="updatesim544444444444assid">
							<option value="5">5</option>
							<option value="4">4</option>
							<option value="3">3</option>
							<option value="2">2</option>
							<option value="1">1</option>
							</select>
							</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Comment：</dt>
							<dd>
							<textarea rows="6" cols="80" name="userOrderBook.comment">${modifybean.comment }</textarea>
							</dd>
							</dl></td>
					</tr>
					 
				</table>
				 
		</div>
		<div class="formBar">
			<ul>
			<c:if test="${modifybean.userOrder.status=='Shipped' }">
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">Check And Received</button></div></div></li>
			</c:if>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">$("#updatesim544444444444assid").val("${modifybean.score}");
</script>