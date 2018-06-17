<%@ page pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${ctx}/sel/selectSchool.action">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="" />
	<input type="hidden" name="numPerPage" value="" />
	<input type="hidden" name="orderField" value="" />
</form>
<!-- <dd><input name="school.id" value="" type="hidden" /> -->
<!-- 	<input name="school.schoolName" type="text" readonly="readonly" class="required" size="30"/> -->
<!-- 	<a class="btnLook" href="${ctx}/sel/selectSchool.action" lookupGroup="school">选择学校</a> -->
<!-- </dd> -->
<div class="pageHeader">
<form rel="pagerForm" onsubmit="return dwzSearch(this, 'dialog');" action="${ctx}/sel/selectSchool.action" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					名称：<input type="text" name="s_schoolName" value=""/>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="118" targetType="dialog">
		<thead>
			<tr>
				<th width="100" orderfield="sid">名称</th>
				<th width="200" orderfield="name">类型</th>
				<th width="200" orderfield="name">所在地</th>
				<th width="50">选择带回</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${SESSION_PAGE_UNIT.list}" var="p">
			<tr target="sid_user" rel="${p.id }">
				<td >${p.schoolName}</td>
				<td >${p.schoolType}</td>
				<td >${p.schoolLocation}</td>
				<td >
					<a class="btnSelect" href="javascript:$.bringBack({ id:'${p.id }',schoolName:'${p.schoolName}'})" title="查找带回">选择</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>共${SESSION_PAGE_UNIT.totalNumber}条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${SESSION_PAGE_UNIT.totalNumber}" numPerPage="${SESSION_PAGE_UNIT.itemsPerPage}" pageNumShown="10" currentPage="${SESSION_PAGE_UNIT.currentPageNumber}"></div>
	</div>
</div>
<%
util.Page p = (util.Page)session.getAttribute("SESSION_PAGE_UNIT");
if(p!=null)p.getList().clear();
%>
