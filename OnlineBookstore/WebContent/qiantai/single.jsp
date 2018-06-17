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
<div class="single-sec">
	 <div class="container">
		 <ol class="breadcrumb">
		 </ol>
		 <!-- start content -->	
		 <div class="col-md-9 det">
				 <div class="single_left">
					 <div class="flexslider">
							<ul class="slides">
								<li data-thumb="${ctx}/resource/${item.imgpath}">
									<img src="${ctx}/resource/${item.imgpath}" />
								</li>
								 
							</ul>
						</div>
						<!-- FlexSlider -->
						  <script defer src="${ctx }/qiantai/js/jquery.flexslider.js"></script>
						<link rel="stylesheet" href="${ctx }/qiantai/css/flexslider.css" type="text/css" media="screen" />

						<script>
						// Can also be used with $(document).ready()
						$(window).load(function() {
						  $('.flexslider').flexslider({
							animation: "slide",
							controlNav: "thumbnails"
						  });
						});
						function addGoods(id){
							var uname = "${SimpleUser.user.uname}";
							if(uname==""){
								alert("Please login to buy");
								return false;
							}
							window.location.href="${ctx}/com/add2Cart.action?uid="+id;
						}
						</script>
				 </div>
				  <div class="single-right">
					 <h3>${item.name }</h3>
					  <div class="cost">
						 <div class="prdt-cost">
							 <ul>
							 	<li>Score:</li>
								 <li class="active">${goodsScore}</li>
								 <li>MRP: <del>kr ${item.price }</del></li>								 
								 <li>Sellling Price:</li>
								 <li class="active">kr ${item.offPrice }</li>
								 <a href="javascript:;" onclick="addGoods('${item.id}')">BUY NOW</a>
							 </ul>
						 </div>
						  
						 <div class="clearfix"></div>
					  </div>
					  <div class="item-list">
						 <ul>
							 <li>Book Type: ${item.bookType.name }</li>
							 <li>Seller: ${item.seller.user.userName }</li>
							 <li>ISBN: ${item.sid }</li>
							 <li>Author: ${item.author }</li>
							 <li>Press: ${item.press }</li>
							 <li>Year: ${item.publicationYear }</li>
						 </ul>
					  </div>
					  <div class="single-bottom1">
						<p class="prod-desc">
						<img src="${ctx }/qiantai/images/share/facebook.jpg" width="35px" height="35px"/>
						<img src="${ctx }/qiantai/images/share/twitter.png" width="50px" height="50px"/>
						</p>
					 </div>	
					  				 
				  </div>
				  <div class="clearfix"></div>
					<div class="sofaset-info" style="word-wrap:break-word; word-break:break-all;display:block;">
						 ${item.note }
				  </div>
		  <!---->
		   <div class="product-table">
		   		 <div class="item-sec">
					 <h4>Sales Statistics</h4>
					 <table class="table table-bordered">
					 <tbody>
							<tr>
								<td><p>Today</p></td>
								<td><p>${today }</p></td>
							</tr>
							<tr>
								<td><p>This Week</p></td>
								<td><p>${week }</p></td>
							</tr>
							<tr>
								<td><p>This Month</p></td>
								<td><p>${month }</p></td>
							</tr>
							<tr>
								<td><p>Total</p></td>
								<td><p>${total }</p></td>
							</tr>
							 													
						</tbody>
						</table>
				 </div>		
				 <h3 style="margin-top: 30px">Comment:</h3>
				 <div class="item-sec">
				 <c:forEach items="${commentList }" var="item">
				 	<c:if test="${not empty item.comment }">
					 <h4>[ ${item.userOrder.buyer.user.userName } ]: ${item.comment }</h4>
				 	</c:if>
				 </c:forEach>
				 </div>		
				  
			</div>
		  		
			<!---->
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
								});
							});
						</script>
						<!-- script -->					 
				 </section>
			 </div> 
		     <div class="clearfix"></div>
	  </div>	 
</div>
 <jsp:include page="./foot.jsp"></jsp:include> 
 <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"0","bdPos":"right","bdTop":"100"},"image":{"viewList":["tsina","twi","fbook"],"viewText":"Share with：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>