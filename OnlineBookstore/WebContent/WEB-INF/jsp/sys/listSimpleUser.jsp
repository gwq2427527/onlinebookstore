<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/sys/querySimpleUser.action">
	<input type="hidden" name="pageNum" value="" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx}/sys/querySimpleUser.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>Name：</label>
				<input type="text" name="s_user.userName" value=""/>
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
<!-- 			<li><a class="add" href="${ctx}/sys/add2SimpleUser.action" target="navTab"  rel="baseAdd" title="Add One"> <span>Add</span></a></li> -->
			<li><a class="edit" href="${ctx}/sys/getSimpleUser.action?uid={sid_select}" target="navTab" rel="baseAdd" warn="Select the row you want to operate" title="Edit One"><span>Edit</span></a></li>
			<li><a title="Delete All Selected?" target="selectedTodo" rel="ids" postType="string" href="${ctx}/sys/deleteSimpleUser.action" class="delete" warn="Select the row you want to operate"><span>Delete</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="3%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100">Account</th>
				<th width="100">Name</th>
				<th width="100">Gender</th>
				<th width="100">Phone</th>
				<th width="100">Email</th>
				<th width="100">Birth</th>
				<th width="100">Address</th>
				<th width="100">Type</th>
				<th width="100">Actived</th>
				<th width="70">Operate</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${SESSION_PAGE.list}" var="item">
			<tr target="sid_select" rel="${item.id }">
				<td><input name="ids" value="${item.id }" type="checkbox"></td>
				<td>${item.user.uname}</td>
				<td>${item.user.userName}</td>
				<td>
					<c:if test="${item.user.userGender ==1}">Male</c:if>
					<c:if test="${item.user.userGender ==0}">Female</c:if>
				</td>
				<td>${item.user.userPhone}</td>
				<td>${item.user.userEmail}</td>
				<td>${item.user.userBirth}</td>
				<td>${item.user.userAddress}</td>
				<td>${item.type}</td>
				<td>${item.checked}</td>
				<td width="70">
					<a title="Edit One"  href="${ctx}/sys/getSimpleUser.action?uid=${item.id}" class="btnEdit" target="navTab" rel="baseAdd">Edit One</a>
					<a title="Delete All Selected?" target="ajaxTodo" href="${ctx}/sys/deleteSimpleUser.action?ids=${item.id}" class="btnDel" style="margin-left: 10px">Delete</a>
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
