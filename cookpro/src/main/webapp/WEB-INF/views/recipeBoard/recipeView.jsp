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
<style>
	#tr_btn_modify{
		display:none;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="//cdn.ckeditor.com/4.19.0/full/ckeditor.js"></script>
<script>
	function backToList(obj){
		obj.action="${contextPath}/recipeboard/recipeList.do";
		obj.submit();
	}
	
	function fn_enable(obj){
		document.getElementById("rp_title").disabled=false;
		document.getElementById("rp_detail").style.display="none";
		document.getElementById("modDiv").style.display="block";
		
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
	
	function likeup(obj){
		obj.action ="${contextPath}/recipeboard/recipeLike.do";
		obj.method = "post";
		obj.submit();
	}
	
	function dislikeup(obj){
		obj.action ="${contextPath}/recipeboard/recipeDislike.do";
		obj.method = "post";
		obj.submit();
	}
	
</script>
</body>
	<form name="frmRecipe" method="post" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr width="600">
				<td width="150" align="center" style="height:12px; color: white; font-size:18px; background: #FF7629;">글번호</td>
				<td width="150" align="left"><input type="text" value="${recipe.recipe_no }" disabled="disabled" size="20">
					<input type="hidden" value="${recipe.recipe_no }" name="recipe_no"></td>
				<td width="150" align="center" style="height:12px; color: white; font-size:18px; background: #FF7629;">작성일자</td>
				<td width="150" align="left">
					<input type="text" value="<fmt:formatDate value='${recipe.writeDate}'/>" disabled="disabled" size="20">
				</td>
			</tr>
			<tr>
				<td width="150" align="center" style="height:12px; color: white; font-size:18px; background: #FF7629;">작성자</td>
				<td width="400" colspan="3" align="left"><input type="text" value="${recipe.id }" name="id" disabled="disabled" size="75"></td>
			</tr>
			<tr>
				<td width="150" align="center" style="height:12px; color: white; font-size:18px; background: #FF7629;">제목</td>
				<td width="400" colspan="3" align="left"> 
					<select name="recipe_tab" class="category" data-filter-message-isnull="탭을 선택하시기 바랍니다" disabled="disabled">
						<option>한식</option>
						<option>중식</option>
						<option>일식</option>
						<option>양식</option>
						<option>기타</option>
					</select>
					<input cols="200" type="text" value="${recipe.recipe_title }" name="recipe_title" id="rp_title" disabled="disabled" size="65">
				</td>				
			</tr>
			<tr height="300">
				<td width="600" colspan="4" align="center">
					<div name="recipe_detail" id="rp_detail">
						${recipe.recipe_detail}
					</div>
					<div id="modDiv" style="display:none">
						<textarea  rows="60" cols="80" name="recipe_detail" id="rp_moddetail" > 
							${recipe.recipe_detail}
						</textarea>	
						<script >
						CKEDITOR.replace("rp_moddetail",{
							 filebrowserUploadUrl : "${contextPath}/recipeboard/imageUpload.do"
						});
						</script>
					</div>
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input class="button" style="font-size: 20px;" type="button" value=" 추천 " onclick="likeup(this.form)"></td>
				<td colspan="2" align="center"><input class="button" style="font-size: 20px;" type="button" value=" 비추천 " onclick="dislikeup(this.form)"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr id="tr_btn_modify">
				<td colspan="2" align="center"	>
					<input class="button" type="button" value="수정반영하기" onclick="fn_modify_recipe(frmRecipe)">
					<input class="button" type='button' value="취소" onclick="backToList(frmRecipe)">
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr id="tr_btn">
				<td colspan="4" align="center">
					<c:if test="${member.id == recipe.id }">
						<input class="button" type="button" value="수정하기" onclick="fn_enable(this.form)">
						<input class="button" type="button" value="삭제하기" onclick="fn_remove_recipe('${contextPath}/recipeboard/removeRecipe.do', ${recipe.recipe_no })">
					</c:if>
					<input class="button" type="button" value="리스트로 돌아가기" onclick="backToList(this.form)">
				</td>
			</tr>
		</table>
	</form>
</html>