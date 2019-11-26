<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet">  
 <script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script> 
 <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
    
<title>茶叶蛋管理平台</title>
</head>
<body>
<nav class="navbar navbar-default" style="background:#000099">
<%@ include file="top.jsp" %>
</nav>
<div class="row">
	<div class="col-md-2">
		<div style="margin-left:20px ">
			<ul class="nav nav-pills nav-stacked">
			  <li class="active"><a href="javascript:showFuction('/admin/articles')">文章列表</a></li>
			  <li><a href="javascript:showFuction('/user/hello')">测试</a></li>
			  <li><a href="javascript:showFuction('/admin/links')">友情链接管理</a></li>
			  <li><a href="#">发布文章</a><input type="hidden" name="ifLink" value="${ifLink }"></li>
			  <li>投票管理
			  	<ul class="nav nav-pills nav-stacked">
			  		<li><a href="#">投票列表</a></li>
			  		<li><a href="javascript:showFuction('/vote/push')">新建投票</a></li>
			  	</ul>
			  </li>
			  <li class="divider"></li>
			  <li><a href="javascript:showFuction('/admin/users')">用户管理</a></li>
			</ul>
		</div>
	</div>
	<div class="col-md-10"  style=" min-height:500px; border-left: solid">
		<div id="content">
			
		</div>
	</div>
</div>


<nav class="navbar navbar-inverse navbar-fixed-bottom" 
role="navigation">
	<div align="center"> <font color="blue" size="5"> ----茶叶蛋吃蛋白不吃蛋黄--- </font> </div>
</nav>

<script type="text/javascript">
$(function(){
	$("#content").load('/admin/articles');
	if($("[name=ifLink]").val()==1){
		showFuction("/admin/links")
	}
})
	function showFuction(url){
		$("#content").load(url)
	}
</script>


</body>
</html>