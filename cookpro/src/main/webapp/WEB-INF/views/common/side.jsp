<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.header {
	background-color: #fff;
}
.header__top {
    background: #faa741;
}
.header__top__inner {
    height: 0px;
    padding-top: 0px
}
.header__top__left {
	float: right;
}
.header__top__left ul li span {
	color: #fff;
}
.header__menu ul li a {
	color: #2d2d2d;
	font-weight: 400;
	font-size: 15px;
}
.header__top__left ul li a {
	color: #fff;
}
.mango_logo img {
	width: 160px;
    margin-top: 8px;
}
</style>
</head>
<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>
 <!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="header__top__inner">
                            <div class="header__top__left">
                                <ul>                                                                           
                                    <c:if test="${sessionScope.id!=null }">
	                                 	&nbsp;<span style="font-size:9.5pt"> ${id }님, 안녕하세요!</span>
	                            	  </c:if>
                                </ul>
                            </div>
                            
                        </div>
                    </div>
                </div>
                <div class="canvas__open"><i class="fa fa-bars"></i></div>
            </div>
        </div>
        <div class="container">
            <div class="row">
            	<div class="col-lg-2 mango_logo">
            		<a href="../main/main.do"><img src="img/logo.png" alt=""></a>
            	</div>
                <div class="col-lg-10">
                    <nav class="header__menu mobile-menu">
                        <ul>
                            <li><a href=>HOME</a></li>
                            <li><a href=>MY냉장고</a></li>
                            <li><a href=>레시피</a></li>
                           	<li><a href=>추천</a>
                            	<ul class="dropdown">
                                    <li><a href=>오늘의 요리</a></li>
                                    <li><a href=>태그</a></li>
                                </ul>
                            </li>
                            <li><a href=>커뮤니티</a></li>
                            <li><a href=>최저가</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- Header Section End -->
    
</body>
</html>
