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
<!---->
<!--header//-->
<div class="product-model">	 
	 <div class="container">
			<ol class="breadcrumb">
		 </ol>
		 <div class="col-md-9 product-model-sec">
		 			<c:forEach items="${list }" var="item">
					 <a href="${ctx}/com/getBook.action?uid=${item.id}"><div class="product-grid love-grid">
						<div class="more-product"><span> </span></div>						
						<div class="product-img b-link-stripe b-animate-go  thickbox">
							<img height="194px" width="166px" src="${ctx }/resource/${item.imgpath}" alt=""/>
							<div class="b-wrapper">
							<h4 class="b-animate b-from-left  b-delay03">
							<button class="btns"><span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>${item.name }</button>
							</h4>
							</div>
						</div></a>
						<div class="product-info simpleCart_shelfItem">
							<div class="product-info-cust prt_name">
								<h4>${item.name }</h4>
								<span class="item_price">kr${item.offPrice }</span>
							</div>	
							<div class="clearfix"></div>
						</div>
					</div>	
		 			</c:forEach>
					 
			</div>
			<div class="rsidebar span_1_of_left">
				 <section  class="sky-form">
					 <div class="product_right">
						 <h4 class="m_2"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>Classification</h4>
						 <div class="tab1">
							 <ul class="place">								
								 <li class="sort">Book Type</li>
								 <li class="by"><img src="${ctx}/qiantai/images/do.png" alt=""></li>
									<div class="clearfix"> </div>
							  </ul>
							 <div class="single-bottom">
							 	<c:forEach items="${list1 }" var="item">
									<a href="${ctx}/com/list.action?uid=${item.id}"><p>${item.name }</p></a>
							 	</c:forEach>					
						     </div>
					      </div>						  
						   
						  <!--script-->
						<script>
							$(document).ready(function(){
								//$(".tab1 .single-bottom").hide();
								$(".tab2 .single-bottom").hide();
								$(".tab3 .single-bottom").hide();
								$(".tab4 .single-bottom").hide();
								$(".tab5 .single-bottom").hide();
								
								$(".tab1 ul").click(function(){
									$(".tab1 .single-bottom").slideToggle(300);
									$(".tab2 .single-bottom").hide();
									$(".tab3 .single-bottom").hide();
									$(".tab4 .single-bottom").hide();
									$(".tab5 .single-bottom").hide();
								})
								$(".tab2 ul").click(function(){
									$(".tab2 .single-bottom").slideToggle(300);
									$(".tab1 .single-bottom").hide();
									$(".tab3 .single-bottom").hide();
									$(".tab4 .single-bottom").hide();
									$(".tab5 .single-bottom").hide();
								})
								$(".tab3 ul").click(function(){
									$(".tab3 .single-bottom").slideToggle(300);
									$(".tab4 .single-bottom").hide();
									$(".tab5 .single-bottom").hide();
									$(".tab2 .single-bottom").hide();
									$(".tab1 .single-bottom").hide();
								})
								$(".tab4 ul").click(function(){
									$(".tab4 .single-bottom").slideToggle(300);
									$(".tab5 .single-bottom").hide();
									$(".tab3 .single-bottom").hide();
									$(".tab2 .single-bottom").hide();
									$(".tab1 .single-bottom").hide();
								})	
								$(".tab5 ul").click(function(){
									$(".tab5 .single-bottom").slideToggle(300);
									$(".tab4 .single-bottom").hide();
									$(".tab3 .single-bottom").hide();
									$(".tab2 .single-bottom").hide();
									$(".tab1 .single-bottom").hide();
								})	
							});
						</script>
						<!-- script -->					 
				 </section>
				 			 				 
				  
				   <!---->
					 <script type="text/javascript" src="${ctx}/qiantai/js/jquery-ui.min.js"></script>
					 <link rel="stylesheet" type="text/css" href="${ctx}/qiantai/css/jquery-ui.css">
					 
					 <!---->
					 
				   	 	
			 </div>				 
	      </div>
		</div>
</div>
<jsp:include page="./foot.jsp"></jsp:include> 	
</body>
</html>