<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<!--header-->	
<div class="top_bg">
	<div class="container">
		<div class="header_top-sec">
			<div class="top_left">
				<ul>
				<c:if test="${not empty SimpleUser }">
					<li class="top_link">Welcome:<a href="javascript:;">${SimpleUser.user.userName }</a></li>
					<li class="top_link"><a href="${ctx }/com/toUserManage.action" target="_blank">[My Account]</a></li>
					<li class="top_link"><a href="${ctx }/com/userLogout.action" style="color:black">Logout</a></li>
				</c:if>
				<c:if test="${empty SimpleUser }">
					<li class="top_link"><a href="${ctx}/qiantai/login.jsp">Login</a></li>					
				</c:if>
				<li class="top_link"><a href="${ctx}/index.jsp">ADMIN</a></li>	
				</ul>
			</div>
				<div class="clearfix"> </div>
		</div>
	</div>
</div>
<div class="header-top">
	 <div class="header-bottom">
		 <div class="container">			
				<div class="logo">
					<a href="index.html"><h1>Bookstore</h1></a>
				</div>
			 <!---->
		 
			 <div class="top-nav">
				<ul class="memenu skyblue"><li class="active" id="menuli_index"><a href="${ctx}/com/index.action">Home</a></li>
					<li class="grid" id="menuli_product"><a href="${ctx}/com/list.action">Books</a></li>
				</ul>
				<div class="clearfix"> </div>
			 </div>
			 <!---->
			 <div class="cart box_1">
				 <a href="${ctx}/com/toCart.action">
					<h3> My Cart
					<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
					</h3>
				</a>
			 	<div class="clearfix"> </div>
			 </div>
			 <div class="clearfix"> </div>
			 <!---->			 
			 </div>
			<div class="clearfix"> </div>
	  </div>
</div>
<%String type = request.getParameter("type");
%>
<script type="text/javascript">
<!--
var pageii;
$("li[id^='menuli_']").each(function(){
	  if($(this).attr("id")=="menuli_"+"<%=type%>"){
		  $(this).addClass("active");
	  }else{
		  $(this).removeClass("active");
	  }
});
</script>
<!---->