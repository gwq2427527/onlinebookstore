<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/sys/queryBook.action">
	<input type="hidden" name="pageNum" value="" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sys/queryBook.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>Name：</label>
				<input type="text" name="s_name" value=""/>
			</li>
			<li>
				<label>ISBN：</label>
				<input type="text" name="s_sid" value=""/>
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
			<li><a class="add" href="${ctx}/sys/add2Book.action" target="navTab"  rel="baseAdd" title="Add One"> <span>Add</span></a></li>
			<li><a class="edit" href="${ctx}/sys/getBook.action?uid={sid_select}" target="navTab" rel="baseAdd" warn="Select the row you want to operate" title="Edit One"><span>Edit</span></a></li>
			<li><a title="Delete All Selected?" target="selectedTodo" rel="ids" postType="string" href="${ctx}/sys/deleteBook.action" class="delete" warn="Select the row you want to operate"><span>Delete</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="3%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100">Name</th>
				<th width="100">ISBN</th>
				<th width="100">Type</th>
				<th width="100">Price</th>
				<th width="100">Discount Price</th>
				<th width="100">Discount</th>
				<th width="100">Author</th>
				<th width="100">Press</th>
				<th width="100">Year</th>
				<th width="70">Operate</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${SESSION_PAGE.list}" var="item">
			<tr target="sid_select" rel="${item.id }">
				<td><input name="ids" value="${item.id }" type="checkbox"></td>
				<td>${item.name}</td>
				<td>${item.sid}</td>
				<td>${item.bookType.name}</td>
				<td>${item.price}</td>
				<td>${item.offPrice}</td>
				<td>${item.offPercent}</td>
				<td>${item.author}</td>
				<td>${item.press}</td>
				<td>${item.publicationYear}</td>
				<td width="70">
					<a title="Edit One"  href="${ctx}/sys/getBook.action?uid=${item.id}" class="btnEdit" target="navTab" rel="baseAdd">Edit One</a>
					<a title="Delete All Selected?" target="ajaxTodo" href="${ctx}/sys/deleteBook.action?ids=${item.id}" class="btnDel" style="margin-left: 10px">Delete</a>
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
