<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="main-layout">
		<a href=""><button class="main-btn">Recipe</button></a>
		<a href=""><button class="main-btn">Review</button></a>
		<a href=""><button class="main-btn">QnA</button></a>
		<br>
		<a href=""><img class="main-image" src="${contextPath }/resources/image/bibimbap.jpg"></a>
		<a href=""><img class="main-image" src="${contextPath }/resources/image/pasta.jpg"></a>
		<a href=""><img class="main-image" src="${contextPath }/resources/image/sushi.jpg"></a>
		<a href=""><img class="main-image" src="${contextPath }/resources/image/mandu.jpg"></a>
	</div>
</body>
</html>