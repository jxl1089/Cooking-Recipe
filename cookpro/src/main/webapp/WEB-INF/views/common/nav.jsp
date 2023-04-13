<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<style>
ul li {
	display:inline-block;
}
ul li a{
	text-decoration: none;
	color:black;
}

#menu{
  position:relative; 
  z-index: 99; 
  background-color: white;
  height: 50px;
  text-align: center;
}

[class*="main"] {
  background-color: white;
  border-radius: 5px 5px 5px 5px;
}

[class*="main"] ul {
  border-radius: 5px 5px 5px 5px;
  border: 1px solid #FF7629;
}
.main1{
  height: 100%;
  width: 750px;
  margin: 0 auto;
  display: inline-block;
}
.main1>li {
  width: 13%;
  line-height: 50px;
  position: relative;
}
.main1>li:hover .main2 {
  left: 0;
}
.main1>li a {
  display: block;
}
.main1>li a:hover {
  font-weight: bold;
}
.main2 {
  width:110%;
  position: absolute;
  top: 50px;
  left: -9999px;
  width: 100%;
  padding: 0px;
}
.main1>li:hover {
  border-bottom: 5px solid #FF7629;
}

.main2>li {
  position: relative;
}

.main2>li a{
  border-radius: 10px;
  margin: 10px;
}

</style>
<body>
<div id="menu">
		<ul class="main1">
			<li><a href="${contextPath }/">HOME</a></li>
			<li><a href="#">MY냉장고</a></li>
			<li><a href="#">레시피</a></li>
			<li style="border: none;"><a href="#">추천</a>
				<ul class="main2">
					<li><a href="#">오늘의 요리</a></li>
					<li><a href="#">태그</a></li>
				</ul></li>
			<li><a href="#">커뮤니티</a></li>
			<li><a href="#">최저가</a></li>
		</ul>
	</div>
</body>