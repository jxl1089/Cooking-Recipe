<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function backToList(obj){
		obj.action = "${contextPath}/board/listArticles.do";
		obj.submit();
	}

</script>
<title>Insert title here</title>
</head>
<body>
	<form name="recipeForm" action="post" action="${contextPath }/recipeboard/addNewRecipe.do" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td>
						<select name="category" class="category" data-filter-message-isnull="탭을 선택하시기 바랍니다">
							<option>한식</option>
							<option>중식</option>
							<option>일식</option>
							<option>양식</option>
							<option>기타</option>
						</select>
					</td>
				</tr>
			</tbody>
		
		</table>
	</form>
</body>
</html>