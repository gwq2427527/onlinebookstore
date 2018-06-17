<%@ page pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<select class="" name="${param.inputName}">
<c:forEach items="${list1 }" var="item">
	<option value="${item.id }">${item.name }</option>
</c:forEach>
</select>