<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="article" value="${recipeMap.recipe }" />
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
		obj.action="${contextPath}/recipeboard/recipeList.di";
		obj.submit();
	}
	
	function fn_enable(obj){
		document.getElementById("i_title").disabled=false;
		document.getElementById("i_detail").disabled=false;
		imageFileName = document.getElementById("i_imageFileName");
		if(imageFileName != null && imageFileName.length != 0){
			document.getElementById("i_imageFileName").disabled=false;
		}
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
	
</script>
</body>
	<form name="frmRecipe" method="post" enctype="multipart/form-data">
	
	</form>
</html>