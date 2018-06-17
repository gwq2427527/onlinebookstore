<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<h2 class="contentTitle">Order Detail</h2>
<div class="pageContent">
	<form method="post" action="${ctx}/sys/updateUserOrder.action" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this)">
		<input type="hidden" value="${modifybean.id}" name="id"/>
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>ID：</dt>
							<dd>${modifybean.sid }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Buyer：</dt>
							<dd>${modifybean.buyer.user.userName }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Buyer Phone：</dt>
							<dd>${modifybean.buyer.user.userPhone }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Buyer Address：</dt>
							<dd>${modifybean.buyer.user.userAddress}</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Date：</dt>
							<dd>${modifybean.addDate }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Money：</dt>
							<dd>${modifybean.money }</dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>Status：</dt>
							<dd>${modifybean.status }</dd>
							</dl></td>
					</tr>
					 
				</table>
				<div class="tabs">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li class="selected"><a href="javascript:void(0)"><span>Books</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height: 250px;">
				<div>
					<table class="list nowrap"  width="100%">
						<thead>
							<tr>
								<th>ISBN</th>
								<th>Name</th>
								<th>Price</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.book.sid }</td>
								<td>${item.book.name }</td>
								<td>${item.book.offPrice }</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				 
			</div>
			<div class="tabsFooter">
				<div class="tabsFooterContent"></div>
			</div>
		</div>
		</div>
		<div class="formBar">
			<ul>
			<c:if test="${modifybean.status=='Shipping' }">
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">Do Ship</button></div></div></li>
			</c:if>
			</ul>
		</div>
	</form>
</div>
