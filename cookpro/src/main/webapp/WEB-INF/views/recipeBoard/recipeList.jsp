<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<script>
	function fn_recipeForm(isLogOn, recipeForm, loginForm) {
		if(isLogOn != '' && isLogOn != 'false') {
			location.href=recipeForm;
		} else {
			alert('로그인 후 글쓰기가 가능합니다.');
			location.href=loginForm+'?action=/recipeboard/recipeForm.do';
		}
	}
</script>
</head>
<body>
	<table align="center" border="1" width="100%">
		<tr height="10" align="center" bgcolor="lightgreen">
			<td>글번호</td>
			<td>작성자</td>
			<td>제목</td>
			<td>작성일자</td>
		</tr>
		<c:choose>
			<c:when test="${recipeList == null }">
				<tr height=10">
					<td colspan="4">
						<b><span style="font-size:9pt;"></span></b>
					</td>
				</tr>
			</c:when>
			<c:when test="${recipeList != null }">
				<c:forEach var="recipe" items="${recipeList }" varStauts="recipe_no">
					<tr align="center">
						<td width="5%">${recipeNum.count }</td>
						<td width="10%">${recipe.id }</td>
						<td align="left" width="35%">
							<span style="padding-right:30px;"></span>
							<a class="cls1" href="${contextPath }/recipeboard/recipeView.do?recipe_no=${recipe.recipe_no}">
								${recipe.title }
							</a>
						</td>
						<tr>
					</tr>
				
				</c:forEach>
			
			</c:when>
		</c:choose>
	</table>
</body>
</html>