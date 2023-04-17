<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="recipe" value="${recipeMap.recipe }" />
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
		recipe_noInput.setAttribute("type", "hidden");
		recipe_noInput.setAttribute("name", "recipe_no");
		recipe_noInput.setAttribute("value", recipe_no);
		
		form.appendChild(recipe_noInput);
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
		obj.action ="${contextPath}/recipeboard/recipeLike.do";
		obj.method = "post";
		obj.submit();
	}
	
	function dislikeup(){
		obj.action ="${contextPath}/recipeboard/recipeDislike.do";
		obj.method = "post";
		obj.submit();
	}
	
</script>
</body>
	<form name="frmRecipe" method="post" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr align="left" width="300">
				<td align="center">
					글 번호:
				</td>
				<td width="150">
					<input type="text" value="${recipe.recipe_no }" disabled="disabled" >
					<input type="hidden" value="${recipe.recipe_no }" name="recipe_no">
				</td>
				<td align="center">
					게시 날짜:
				</td>
				<td>
					<input type="text" value="<fmt:formatDate value='${recipe.writeDate}'/>" disabled="disabled">
				</td>
			</tr>
			<tr align="left">
				<td align="center">
					작성자:
				</td>
				<td>
					 <input type="text" value="${recipe.id }" name="id" disabled="disabled">
				</td>
			</tr>
			<tr align="left">
				<td width="150"  align="center" bgcolor="#D3D3D3">제목</td>
				<td width="600"> 
					<select name="recipe_tab" class="category" data-filter-message-isnull="탭을 선택하시기 바랍니다" disabled="disabled">
						<option>한식</option>
						<option>중식</option>
						<option>일식</option>
						<option>양식</option>
						<option>기타</option>
					</select>
					<input cols="200" type="text" value="${recipe.recipe_title }" name="recipe_title" id="rp_title" disabled="disabled" style="width:500px;">
				</td>				
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">내용</td>
				<td width="600">
					<div rows="60" cols="60" name="recipe_detail" id="rp_detail" disabled="disabled">
						${recipe.recipe_detail}</div>
				</td>
			</tr>
			<tr>
				<td><input type="button" value="추천" onclick=""></td>
				<td><input type="button" value="비추천" onclick=""></td>
			</tr>
			<tr id="tr_btn_modify">
				<td colspan="2" align="center"	>
					<input type="button" value="수정반영하기" onclick="fn_modify_recipe(frmRecipe)">
					<input type='button' value="취소" onclick="backToList(frmRecipe)">
				</td>
			</tr>
			<tr id="tr_btn">
				<td colspan="2" align="center">
					<c:if test="${member.id == recipe.id }">
						<input type="button" value="수정하기" onclick="fn_enable(this.form)">
						<input type="button" value="삭제하기" onclick="fn_remove_recipe('${contextPath}/recipeboard/removeRecipe.do', ${recipe.recipe_no })">
					</c:if>
					<input type="button" value="리스트로 돌아가기" onclick="backToList(this.form)">
				</td>
			</tr>
		</table>
	</form>
</html>