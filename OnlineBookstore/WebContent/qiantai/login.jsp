<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${appTitle }</title>
<jsp:include page="./header.jsp"></jsp:include> 
</head>
<body> 
<!--header-->	
<jsp:include page="./menu.jsp"><jsp:param value="product" name="type"/> </jsp:include>
<div class="login_sec">
	 <div class="container">
		 <ol class="breadcrumb">
		  <c:if test="${not empty loginErrorMessage }">
				<div class="alert alert-error"><strong>Info：</strong><br /><i class="icon-exclamation-sign"></i> ${loginErrorMessage}</div>
			</c:if>
			<%session.removeAttribute("loginErrorMessage"); %>
		 </ol>
		 <h2>Login</h2>
		 <div class="col-md-6 log">			 
				 <p>Welcome, please enter the fowlling to continue.</p>
				 <form id="registration_form" action="${pageContext.request.contextPath}/com/userLogin.action" method="post">
					 <h5>User Name::</h5>	
					 <input type="text" value="" name="name">
					 <h5>Password:</h5>
					 <input type="password" value="" name="password">					
					 <input type="submit" value="Login">					 
					  <a class="acount-btn" href="${ctx}/qiantai/account.jsp">Create an Account</a>
				 </form>
		 </div>	
		 <div class="clearfix"></div>
	 </div>
</div>
<!---->
<jsp:include page="./foot.jsp"></jsp:include> 
</body>
</html>