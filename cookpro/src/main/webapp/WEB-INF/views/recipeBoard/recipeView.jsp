<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="recipe" value="${recipeMap.recipe }" />
<c:set var="imageFileList" value="${recipeMap.imageFileList }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피</title>
</head>
<body>
	<h1>글보기</h1>
<style>
	#tr_btn_modify{
		display:none;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function backToList(obj){
		obj.action="${contextPath}/recipeboard/recipeList.do";
		obj.submit();
	}
	
	function fn_enable(obj){
		document.getElementById("rp_title").disabled=false;
		document.getElementById("rp_detail").disabled=false;
	/*	imageFileName = document.getElementById("rp_imageFileName");
		if(imageFileName != null && imageFileName.length != 0){
			document.getElementById("rp_imageFileName").disabled=false;
		}*/
		document.getElementById("tr_btn_modify").style.display="block";
		document.getElementById("tr_btn").style.display="none";
	}
	
	function fn_modify_recipe(obj){
		obj.action ="${contextPath}/recipeboard/modRecipe.do";
		obj.method = "post";
		obj.submit();
	}
	
	function fn_remove_recipe(url, recipe_no){
		let form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		
		let recipe_noInput = document.createElement("input");
		articleNoInput.setAttribute("type", "hidden");
		articleNoInput.setAttribute("name", "recipe_no");
		articleNoInput.setAttribute("value", recipe_no);
		
		form.appendChild(articleNoInput);
		document.body.appendChild(form);
		form.submit();
	}
	
	function readURL(input){
		if(input.files && input.files[0]) {
			let reader = new FileReader();
			reader.onload = function(e) {
				$("#preview").attr("src", e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	function likeup(){
		
	}
	
	function dislikeup(){
		
	}
	
</script>
</body>
	<form name="frmRecipe" method="post" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td width="150" align="center" bgcolor="#D3D3D3">글번호</td>
				<td>
					<input type="text" value="${recipe.recipe_no }" disabled="disabled">
					<input type="hidden" value="${recipe.recipe_no }" name="recipe_no">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" value="${recipe.id }" name="id" disabled="disabled">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" value="<fmt:formatDate value='${recipe.writeDate}'/>" disabled="disabled">
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#D3D3D3">제목</td>
				<td>
					<select name="recipe_tab" class="category" data-filter-message-isnull="탭을 선택하시기 바랍니다" disabled="disabled">
						<option>한식</option>
						<option>중식</option>
						<option>일식</option>
						<option>양식</option>
						<option>기타</option>
					</select>
					<input type="text" value="${recipe.recipe_title }" name="recipe_title" id="rp_title" disabled="disabled">
				</td>				
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">내용</td>
				<td>
					<div rows="20" cols="60" name="recipe_detail" id="rp_detail" disabled="disabled">
						${recipe.recipe_detail}</div>
				</td>
			</tr>
			<tr>
				<td><input type="button" value="추천" onclick=""></td>
				<td><input type="button" value="비추천" onclick=""></td>
			</tr>
			<tr id="tr_btn_modify">
				<td colspan="2" align="center"	>
					<input type="button" value="수정반영하기" onclick="fn_modify_article(frmRecipe)">
					<input type='button' value="취소" onclick="backToList(frmRecipe)">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<c:if test="${member.id == article.id }">
						<input type="button" value="수정하기" onclick="fn_enable(this.form)">
						<input type="button" value="삭제하기" onclick="fn_remove_article('${contextPath}/recipeboard/removeRecipe.do', ${recipe.recipe_no })">
					</c:if>
					<input type="button" value="리스트로 돌아가기" onclick="backToList(this.form)">
				</td>
			</tr>
		</table>
	</form>
</html>