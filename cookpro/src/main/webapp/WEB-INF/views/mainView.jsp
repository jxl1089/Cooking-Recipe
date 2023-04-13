<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath }/resources/css/mainView.css">
</head>
<body>
<div class="FlipCardcontainer">
		<div class="slider-container">
			<span class="slider-leftBtn sliderBtn"></span>
			<div class="card-container" id="slider">
				<div class="card">
					<a href=""><img class="TeamPic"
						src="${contextPath }/resources/image/bibimbap.jpg"></a>
					<p class="text">한식</p>
					
				</div>
				<div class="card">
					<a href=""><img class="TeamPic"
						src="${contextPath }/resources/image/pasta.jpg"></a>
					<p class="text">양식</p>
				</div>

				<div class="card">
					<a href=""><img class="TeamPic"
						src="${contextPath }/resources/image/sushi.jpg"></a>
					<p class="text">일식</p>
				</div>

				<div class="card">
					<a href=""><img class="TeamPic"
						src="${contextPath }/resources/image/mandu.jpg"></a>
					<p class="text">중식</p>
				</div>
			</div>
			<span class="slider-rightBtn sliderBtn"></span>
		</div>
	</div>
<script src="${contextPath }/resources/js/mainView.js"></script>
</body>
</html>
