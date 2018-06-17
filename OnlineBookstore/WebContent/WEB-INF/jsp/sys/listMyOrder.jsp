<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/sys/queryMyOrder.action">
	<input type="hidden" name="pageNum" value="" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sys/queryMyOrder.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>Order ID：</label>
				<input type="text" name="s_userOrder.sid" value=""/>
			</li>
			<li>
				<label>Order Date：</label>
				<input type="text" name="s_userOrder.addDate" value=""/>
			</li>
			<li>
				<label>Status：</label>
				<select name="s_userOrder.status">
				<option value="">Select Status</option>
				<option value="Shipping">Shipping</option>
				<option value="Shipped">Shipped</option>
				<option value="Received">Received</option>
				</select>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">Search</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
<!-- 			<li><a class="add" href="${ctx}/sys/add2BookType.action" target="navTab"  rel="baseAdd" title="Add One"> <span>Add</span></a></li> -->
			<li><a class="edit" href="${ctx}/sys/getMyOrder.action?uid={sid_select}" target="navTab" rel="baseAdd" warn="Select the row you want to operate" title="Order"><span>Edit / View</span></a></li>
<!-- 			<li><a title="Delete All Selected?" target="selectedTodo" rel="ids" postType="string" href="${ctx}/sys/deleteBookType.action" class="delete" warn="Select the row you want to operate"><span>Delete</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
<!-- 				<th width="3%"><input type="checkbox" group="ids" class="checkboxCtrl"></th> -->
				<th width="100">Order ID</th>
				<th width="100">Date</th>
				<th width="100">Price</th>
				<th width="100">Status</th>
				<th width="100">Book ISBN</th>
				<th width="100">Book Name</th>
				<th width="70">Operate</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${SESSION_PAGE.list}" var="item">
			<tr target="sid_select" rel="${item.id }">
<!-- 				<td><input name="ids" value="${item.id }" type="checkbox"></td> -->
				<td>${item.userOrder.sid}</td>
				<td>${item.userOrder.addDate}</td>
				<td>${item.price}</td>
				<td>${item.userOrder.status}</td>
				<td>${item.book.sid}</td>
				<td>${item.book.name}</td>
				<td width="70">
					<a title="Order"  href="${ctx}/sys/getBookType.action?uid=${item.id}" class="btnEdit" target="navTab" rel="baseAdd">Edit One</a>
<!-- 					<a title="Delete All Selected?" target="ajaxTodo" href="${ctx}/sys/deleteBookType.action?ids=${item.id}" class="btnDel" style="margin-left: 10px">Delete</a> -->
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>Total: ${SESSION_PAGE.totalNumber}</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${SESSION_PAGE.totalNumber}" numPerPage="${SESSION_PAGE.itemsPerPage}" pageNumShown="10" currentPage="${SESSION_PAGE.currentPageNumber}"></div>
	</div>
</div>
<%@ include file="../common/clear.jsp"%>
