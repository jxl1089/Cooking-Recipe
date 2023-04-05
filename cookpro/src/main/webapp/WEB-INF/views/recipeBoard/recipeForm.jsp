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
	let cnt =1;
	function fn_addFile(){
		$("#d_file").append("<br>" + "<input type='file' name='file"+cnt+"'>");
		cnt++;
	}
	function addRow(){
		
	}
</script>
<title>Insert title here</title>
</head>
<body>
	
	<form name="recipeForm" action="post" action="${contextPath }/recipeboard/addNewRecipe.do" enctype="multipart/form-data">
		<table border="1" align="center">
			<tr>
				<td align="center">
					<input type="button" id="addBtn" name="addBtn" value="칸 추가" onclick="rowAdd()">
					<tbody id="tblShow"></tbody>
				</td>
			</tr>
			<tr>
				<td align="right"></td>
				<td colspan="2">
					<input type="submit" value="글쓰기">
					<input type="button" value="목록보기" onclick="backToList(this.form)">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>