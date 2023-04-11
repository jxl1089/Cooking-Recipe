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
<title>레시피쓰기</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	let cnt = 1;
	function backToList(){
		let form = document.articleForm;
		form.action="${path}/recipeboard/recipeList.do"
		form.submit();
	}
	
	function readURL(input){
		if(input.files && input.files[0]){
			let reader = new FileReader();
			reader.onload = function(e){
				$('preivew').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0])
		}
	}
	function fn_addFile(){
		$("#d_file").append("<br><input type='file' name='file"+cnt+"' ");
		cnt++;
	}
</script>
</head>
<body>
	<form name="recipeForm" method="post" action="${path }/recipeboard/addRecipe.do" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td colspan="2" align="left"><input type="text" size="20" maxlength="100" name="name" value="${member.name }" readonly="readonly"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" size="63" maxlength="500" name="title" placeholder="제목"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="recipe_detail" rows="10" cols="69" maxlength="4000">
				</textarea></td>
			</tr>
			<tr>
				<td align="right">이미지파일 첨부</td>
				<td align="left"><input type="button" value="파일추가" onclick="fn_addFile()"></td>
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