<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${appTitle }</title>
<jsp:include page="./header.jsp"></jsp:include>
<script type="text/javascript" src="${ctx }/qiantai/js/img.js"></script>
<!-- /start menu -->
</head>
<body> 
<jsp:include page="./menu.jsp"><jsp:param value="index" name="type"/> </jsp:include> 
<div class="banner">
	 <div class="container">
	 </div>
</div>
<!---->
<div class="welcome">
	 <div class="container">
		 <div class="col-md-3 welcome-left">
			 <h2>Welcome to best online store</h2>
		 </div>
		 <div class="col-md-9 welcome-right">
			 <h3>LOW price High quality.</h3>
			 <p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.</p>
		 </div>
	 </div>
</div>
<!---->
<div class="bride-grids">
	 <div class="container">
		 <div class="col-md-4 bride-grid">
			 <div class="content-grid l-grids">
				 <figure class="effect-bubba">
						<img src="${ctx }/qiantai/images/book1.jpg" alt=""/>
						<figcaption>
							<h4>book </h4>
							<p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</p>																
						</figcaption>			
				 </figure>
				 <div class="clearfix"></div>
				 <h3>book</h3>
			 </div>
			 <div class="content-grid l-grids">
				 <figure class="effect-bubba">
						<img src="${ctx }/qiantai/images/book2.jpg" alt=""/>
						<figcaption>
							<h4>book</h4>
							<p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</p>																
						</figcaption>			
				 </figure>	
				 <div class="clearfix"></div>
				 <h3>book</h3>
			 </div>
		 </div>
		 <div class="col-md-4 bride-grid">
				<div class="content-grid l-grids">
						<img src="${ctx }/qiantai/images/book4.jpg" alt=""/>
						
				 <h3>book</h3>
			 </div>
		 </div>
		 <div class="col-md-4 bride-grid">
			 <div class="content-grid l-grids">
				 <figure class="effect-bubba">
						<img src="${ctx }/qiantai/images/book3.jpg" alt=""/>
						<figcaption>
							<h4>book </h4>
							<p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</p>																
						</figcaption>			
				 </figure>	
				 <div class="clearfix"></div>
				 <h3>book</h3>
			 </div>
			 <div class="content-grid l-grids">
				 <figure class="effect-bubba">
						<img src="${ctx }/qiantai/images/book5.jpg" alt=""/>
						<figcaption>
							<h4>book </h4>
							<p>xxxxxxxxxxxxxxxxxxxxxxxxxxxxx</p>																
						</figcaption>			
				 </figure>
					<div class="clearfix"></div>
				 <h3>book</h3>
			 </div>
		 </div>
		 <div class="clearfix"></div>
	 </div>
</div>
<!---->
<div class="featured">
	 <div class="container">
		 <h3>New Books</h3>
		 	<c:forEach items="${list }" var="item" varStatus="vsitem">
		 		<c:if test="${vsitem.count==1 || vsitem.count%4==1 }" var="test111">
				 <div class="feature-grids">
					 <div class="col-md-3 feature-grid jewel">
						 <a href="${ctx}/com/getBook.action?uid=${item.id}"><img height="194px" width="166px"  src="${ctx }/resource/${item.imgpath}" alt=""/>	
							 <div class="arrival-info">
								 <h4>${item.name }</h4>
								 <p>$ ${item.offPrice }</p>
								 <span class="pric1"><del>$ ${item.price }</del></span>
								 <span class="disc">[${item.offPercent }% Off]</span>
							 </div>
							 
							 </a>
					 </div>
				 </div>
		 		</c:if>
		 		<c:if test="${!test111 }">
		 			<div class="col-md-3 feature-grid jewel">
						 <a href="${ctx}/com/getBook.action?uid=${item.id}"><img height="194px" width="166px"  src="${ctx }/resource/${item.imgpath}" alt=""/>	
							 <div class="arrival-info">
								 <h4>${item.name }</h4>
								 <p>$ ${item.offPrice }</p>
								 <span class="pric1"><del>$ ${item.price }</del></span>
								 <span class="disc">[${item.offPercent }% Off]</span>
							 </div>
							 
							 </a>
					 </div>
					 <c:if test="${vsitem.count%4==0 }" var="test111">
					 	 <div class="clearfix"></div>
					 </c:if>
		 		</c:if>
		 	</c:forEach>
		  
	 </div>
</div>
<!---->
<div class="arrivals">
	 <div class="container">	
		 <h3>Recommended Books</h3>
		 <div class="arrival-grids">			 
			 <ul id="flexiselDemo1">
				 <li>
					 <a href="javascript:;"><img src="${ctx}/qiantai/images/a11.jpg" alt=""/>	
					 <div class="arrival-info">
					 </div>
					 </a>
				 </li>
				 <li>
					 <a href="javascript:;"><img src="${ctx}/qiantai/images/a22.jpg" alt=""/>
						<div class="arrival-info">
					 </div>
					 </a>
				 </li>
				 <li>
					 <a href="javascript:;"><img src="${ctx}/qiantai/images/a33.jpg" alt=""/>	
						<div class="arrival-info">
					 </div>
					 </a>
				 </li>
				 <li>
				    <a href="javascript:;"> <img src="${ctx}/qiantai/images/a6.jpg" alt=""/>	
						<div class="arrival-info">
					 </div>
					</a>
				 </li>
				</ul>
				<script type="text/javascript">
				 $(window).load(function() {			
				  $("#flexiselDemo1").flexisel({
					visibleItems: 4,
					animationSpeed: 1000,
					autoPlay: true,
					autoPlaySpeed: 3000,    		
					pauseOnHover:true,
					enableResponsiveBreakpoints: true,
					responsiveBreakpoints: { 
						portrait: { 
							changePoint:480,
							visibleItems: 1
						}, 
						landscape: { 
							changePoint:640,
							visibleItems: 2
						},
						tablet: { 
							changePoint:768,
							visibleItems: 3
						}
					}
				});
				});
				</script>
				<script type="text/javascript" src="${ctx}/qiantai/js/jquery.flexisel.js"></script>			 
		  </div>
	 </div>
</div>
<!---->
 
<!---->
<jsp:include page="./foot.jsp"></jsp:include> 	 
</body>
</html>
