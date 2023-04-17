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
		<a href="${contextPath }/recipeboard/recipeList.do"><button class="main-btn">Recipe</button></a>
		<a href="${contextPath }/reviewBoard/review_listArticles.do"><button class="main-btn">Review</button></a>
		<a href=""><button class="main-btn">QnA</button></a>
		<br>
		<%@ include file="mainView.jsp" %>
		<br>
		<table class="main-table">
			<tr>
				<th>
					주간 인기 레시피
				</th>
				<th>
					주간 인기 리뷰
				</th>
			</tr>
			<tr>
				<td>
					추천수 기준 나열 해야함
				</td>
				<td>
					추천수 기준 나열 해야함
				</td>
			</tr>
		</table>
	</div>
</body>
</html>