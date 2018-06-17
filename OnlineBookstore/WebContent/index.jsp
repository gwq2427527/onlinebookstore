<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Online Bookstore</title>
<link href="themes/login/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="themes/login/jQuery1.7.js"></script>
<script type="text/javascript" src="themes/login/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="themes/login/jquery1.42.min.js"></script>
<script type="text/javascript" src="themes/login/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="themes/login/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
var currenttabindex = 0;
$(document).ready(function(){
	var $tab_li = $('#tab ul li');
	$tab_li.hover(function(){
		$(this).addClass('selected').siblings().removeClass('selected');
		var index = $tab_li.index(this);
		$('div.tab_box > div').eq(index).show().siblings().hide();
		if(index != currenttabindex){$("#checkcode"+index).click();}
		currenttabindex = index;
	});	
});
</script>
<script type="text/javascript">
$(function(){
/*学生登录信息验证*/
$("#stu_username_hide").focus(function(){
 var username = $(this).val();
 if(username=='Account'){
 $(this).val('');
 }
});
$("#stu_username_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('Account');
 }
});
$("#stu_password_hide").focus(function(){
 var username = $(this).val();
 if(username=='psw'){
 $(this).val('');
 }
});
$("#stu_password_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('psw');
 }
});
$("#stu_code_hide").focus(function(){
 var username = $(this).val();
 if(username=='Verification Code'){
 $(this).val('');
 }
});
$("#stu_code_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('Verification Code');
 }
});
$(".stu_login_error").Validform({
	tiptype:function(msg,o,cssctl){
		var objtip=$(".stu_error_box");
		cssctl(objtip,o.type);
		objtip.text(msg);
	},
	ajaxPost:false
});
/*导师登录信息验证*/
$("#tea_username_hide").focus(function(){
 var username = $(this).val();
 if(username=='Account'){
 $(this).val('');
 }
});
$("#tea_username_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('Account');
 }
});
$("#tea_password_hide").focus(function(){
 var username = $(this).val();
 if(username=='psw'){
 $(this).val('');
 }
});
$("#tea_password_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('psw');
 }
});
$("#tea_code_hide").focus(function(){
 var username = $(this).val();
 if(username=='Verification Code'){
 $(this).val('');
 }
});
$("#tea_code_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('Verification Code');
 }
});
$(".tea_login_error").Validform({
	tiptype:function(msg,o,cssctl){
		var objtip=$(".tea_error_box");
		cssctl(objtip,o.type);
		objtip.text(msg);
	},
	ajaxPost:false
});
 
$("#sec_username_hide").focus(function(){
 var username = $(this).val();
 if(username=='Account'){
 $(this).val('');
 }
});
$("#sec_username_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('Account');
 }
});
$("#sec_password_hide").focus(function(){
 var username = $(this).val();
 if(username=='psw'){
 $(this).val('');
 }
});
$("#sec_password_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('psw');
 }
});
$("#sec_code_hide").focus(function(){
 var username = $(this).val();
 if(username=='Verification Code'){
 $(this).val('');
 }
});
$("#sec_code_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('Verification Code');
 }
});
$(".sec_login_error").Validform({
	tiptype:function(msg,o,cssctl){
		var objtip=$(".sec_error_box");
		cssctl(objtip,o.type);
		objtip.text(msg);
	},
	ajaxPost:false
});
});
</script>
<script type="text/javascript">
$(function(){
	$(".screenbg ul li").each(function(){
		$(this).css("opacity","0");
	});
	$(".screenbg ul li:first").css("opacity","1");
	var index = 0;
	var t;
	var li = $(".screenbg ul li");	
	var number = li.size();
	function change(index){
		li.css("visibility","visible");
		li.eq(index).siblings().animate({opacity:0},3000);
		li.eq(index).animate({opacity:1},3000);
	}
	function show(){
		index = index + 1;
		if(index<=number-1){
			change(index);
		}else{
			index = 0;
			change(index);
		}
	}
	t = setInterval(show,8000);
 
	var width = $(window).width();
	$(".screenbg ul img").css("width",width+"px");
});
</script>
</head>

<body>
<div id="tab">
  <ul class="tab_menu">
    <li class="selected">Admin</li>
    <li>Buyer</li>
    <li>Seller</li>
  </ul>
  <div class="tab_box"> 
    <!-- 开始 -->
    <div>
      <div class="stu_error_box errormsg" ><c:if test="${not empty signErrorMessage }">${signErrorMessage }</c:if></div>
      <form action="${pageContext.request.contextPath }/com/login.action" method="post" class="stu_login_error">
      	<input type="hidden" value="SysUser" name="logintype"/>
        <div id="username">
          <label>&nbsp;Account:</label>
          <input type="text" id="stu_username_hide" name="loginid" value="admin" nullmsg="Account required！" datatype="s2-18" errormsg="Account ikn 2-18！" sucmsg=" "/>
          <!--ajaxurl="demo/valid.jsp"--> 
        </div>
        <div id="password">
          <label>Password:</label>
          <input type="password" id="stu_password_hide" name="password" value="123456" nullmsg="Password required！" datatype="*6-16" errormsg="Password in 6-16！" sucmsg=" "/>
        </div>
        <div id="code">
          <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Code: </label>
          <input type="text" id="stu_code_hide" name="checkcode"  value="Verification Code" nullmsg="Verification Code is required！" datatype="*4-4" errormsg="Verification Code is 4 chars！" sucmsg=" "/>
          <img id="checkcode0" src="${pageContext.request.contextPath }/checkcode" title="点击更换" alt="验证码占位图" onclick="this.src = '${pageContext.request.contextPath }/checkcode?' + Math.random();"/> </div>
        <div id="remember">
        </div>
        <div id="login">
          <button type="submit">Login</button>
        </div>
      </form>
    </div>
   <!-- 结束-->
   <!-- 开始-->
    <div class="hide">
     <div class="tea_error_box"></div>
      <form action="${pageContext.request.contextPath }/com/login.action" method="post" class="tea_login_error">
      	<input type="hidden" value="SimpleUser" name="logintype"/>
        <div id="username">
          <label>&nbsp;Account:</label>
          <input type="text" id="tea_username_hide" name="loginid" value="Account" nullmsg="Account required！" datatype="s2-18" errormsg="Account ikn 2-18！" sucmsg=" "/>
          <!--ajaxurl="demo/valid.jsp"--> 
        </div>
        <div id="password">
          <label>Password:</label>
          <input type="password" id="tea_password_hide" name="password" value="123456" nullmsg="Password required！" datatype="*6-16" errormsg="Password in 6-16！" sucmsg=" "/>
        </div>
        <div id="code">
          <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Code: </label>
          <input type="text" id="tea_code_hide" name="checkcode"  value="Verification Code" nullmsg="Verification Code is required！" datatype="*4-4" errormsg="Verification Code is 4 chars！" sucmsg=" "/>
          <img id="checkcode1" src="${pageContext.request.contextPath }/checkcode" title="点击更换" alt="验证码占位图" onclick="this.src = '${pageContext.request.contextPath }/checkcode?' + Math.random();"/> </div>
        <div id="remember"></div>
        <div id="login">
          <button type="submit">Login</button>
        </div>
      </form>
    </div>
     <!-- 结束-->
     <!-- 开始-->
    <div class="hide">
    <div class="sec_error_box"></div>
      <form action="${pageContext.request.contextPath }/com/login.action" method="post" class="sec_login_error">
      	<input type="hidden" value="SimpleUser" name="logintype"/>
        <div id="username">
          <label>&nbsp;Account:</label>
          <input type="text" id="sec_username_hide" name="loginid" value="Account" nullmsg="Account required！" datatype="s2-18" errormsg="Account ikn 2-18！" sucmsg=" "/>
          <!--ajaxurl="demo/valid.jsp"--> 
        </div>
        <div id="password">
          <label>Password:</label>
          <input type="password" id="sec_password_hide" name="password" value="123456" nullmsg="Password required！" datatype="*6-16" errormsg="Password in 6-16！" sucmsg=" "/>
        </div>
        <div id="code">
          <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Code: </label>
          <input type="text" id="sec_code_hide" name="checkcode"  value="Verification Code" nullmsg="Verification Code is required！" datatype="*4-4" errormsg="Verification Code is 4 chars！" sucmsg=" "/>
           <img id="checkcode2" src="${pageContext.request.contextPath }/checkcode" title="点击更换" alt="验证码占位图" onclick="this.src = '${pageContext.request.contextPath }/checkcode?' + Math.random();"/> </div>
        <div id="remember">
        </div>
        <div id="login">
          <button type="submit">Login</button>
        </div>
      </form>
    </div>
     <!-- 结束-->
  </div>
</div>
<div class="bottom">Copyright &copy; 2017</div>
<div class="screenbg">
  <ul>
    <li><a href="javascript:;"><img src="themes/login/0.jpg"></a></li>
    <li><a href="javascript:;"><img src="themes/login/1.jpg"></a></li>
    <li><a href="javascript:;"><img src="themes/login/2.jpg"></a></li>
    <li><a href="javascript:;"><img src="themes/login/3.jpg"></a></li>
  </ul>
</div>
</body>
</html>
<%session.removeAttribute("signErrorMessage"); %>
