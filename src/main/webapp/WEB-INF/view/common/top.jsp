<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
   <div class="navbar-header">
  	<p class="popover-options">
		<a href="#"  type="button"  title="<h4>不许点我</h4>" data-content="<h6>那就再点一下吧</h6>" id="tank">
			<img alt="Brand" src="/resource/images/dan.png" width="80px" >
		</a>
	</p>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav"  >
		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		
		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
      </ul>
      
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      
      <ul class="nav navbar-nav navbar-right">
       <li><a href="#"><img width="30px" height="30px" src="/resource/images/donghua.gif"/> </a></li>
        <c:choose>
	        <c:when  test="${sessionScope.SESSION_USER_KEY == null}">
	        	<li><a href="/user/register">注册</a></li>
	        	<li><a href="/user/login">登录</a></li>
	        </c:when>
	        <c:otherwise>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> 
		          	${sessionScope.SESSION_USER_KEY.username}
		           <span class="caret"></span></a>
		         <ul class="dropdown-menu ">
		            <li><a href="#">个人中心</a></li>
		            <li><a href="#">个人设置</a></li>
		            <li><a href="#">修改头像</a></li>
		            <li><a href="user/home">操作页面</a></li>
		            <c:if test="${sessionScope.SESSION_USER_KEY.role==1}">
		            	<li><a href="admin/index">管理页面</li>
		            </c:if>
		            <li role="separator" class="divider"></li>
		            <li><a href="/user/logout">退出登录</a></li>
		          </ul>
		        </li> 
	        </c:otherwise>
        </c:choose>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
   	<script type="text/javascript">
$(function () {
	$("#tank").popover({html : true });
	});
</script>