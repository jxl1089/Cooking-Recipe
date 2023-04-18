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
		<a href="${contextPath }/recipeboard/recipeList.do"><button class="main-btn">RECIPE</button></a>
		<a href="${contextPath }/review/reviewList.do"><button class="main-btn">REVIEW</button></a>
		<a href="${contextPath }/qna/qnaList.do"><button class="main-btn">QnA</button></a>
		<br>
		<%@ include file="mainView.jsp" %>
		<br>
		<hr>
		<br>
		<%@ include file="mainEmail.jsp" %>
	</div>
</body>
</html>