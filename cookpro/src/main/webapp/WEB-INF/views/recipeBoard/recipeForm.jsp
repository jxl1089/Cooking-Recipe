

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
<script src="//cdn.ckeditor.com/4.19.0/full/ckeditor.js"></script>
<script>
function readURL(input) {
	if(input.files && input.files[0]){
		let reader = new FileReader();
		reader.onload = function(e) {
			$('#preview').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
function backToList(obj){
	obj.action = "${contextPath }/recipeboard/recipeList.do";
	obj.submit();
}
let cnt = 1;

</script>
<title>Insert title here</title>
</head>
<body>
	<form name="recipeForm" method="post" action="${contextPath }/recipeboard/addRecipe.do" enctype="multipart/form-data">
		<table>
			<tr align="left">
				<td>
					<select name="recipe_tab" class="category" data-filter-message-isnull="탭을 선택하시기 바랍니다">
						<option>한식</option>
						<option>중식</option>
						<option>일식</option>
						<option>양식</option>
						<option>기타</option>
					</select>
					<input type="text" name="recipe_title" maxlength="50" placeholder="제목"><br>
				</td>
			</tr>
			<tr>
				<td>
				<div>
					<textarea cols="60" rows="60" id="detail" name="recipe_detail"></textarea>
					<script >
						CKEDITOR.replace("detail",{
							 filebrowserUploadUrl : "${contextPath}/recipeboard/imageUpload.do"
						});
					</script>
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글쓰기">
					<input type="button" value="목록보기" onclick="backToList(this.form)">
				</td>
			</tr>
		</table>

	</form>
</body>
</html>

<!--
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath }/resources/css/style.css">
<title>레시피쓰기</title>
<script src="https://cdn.ckeditor.com/ckeditor5/37.0.1/classic/ckeditor.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	let cnt = 1;
	function backToList(){
		let form = document.recipeForm;
		form.action="${contextPath}/recipeboard/recipeList.do";
		form.submit();
	}
	
	/*function submitData(){
		var detail = document.getElementById("recipe_detail").innerHTML;
	
		let recipeForm = document.recipeForm;
		
		let recipe_detail = document.createElement("input");

		recipe_detail.setAttribute("name", "recipe_detail");

		recipe_image.setAttribute("name", "recipe_imageList");

		recipeForm.append(recipe_detail);

		recipeForm.submit();
	}*/
	
	
</script>

<style>
  .ck-editor__editable { height: 400px; }
   .ck-content { font-size: 12px; }
</style>
</head>
<body>
	<form name="recipeForm" method="post" action="${contextPath }/recipeboard/addRecipe.do" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td colspan="2" align="left"><input type="text" size="20" maxlength="100" name="name" value="${member.name }" readonly="readonly"></td>
			</tr>
			<tr>
				<td align="left">
					<select name="recipe_tab" class="category" data-filter-message-isnull="탭을 선택하시기 바랍니다">
						<option>한식</option>
						<option>중식</option>
						<option>일식</option>
						<option>양식</option>
						<option>기타</option>
					</select>
					<input type="text" size="63" maxlength="500" name="recipe_title" placeholder="제목">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea align="left" id="recipe_detail" name="recipe_detail" style="overflow:scroll; width:700px; height:400px;"></textarea>

					<script>
						 var ckeditor_config = {
						   resize_enaleb : false,
						   enterMode : CKEDITOR.ENTER_BR,
						   shiftEnterMode : CKEDITOR.ENTER_P,
						   filebrowserUploadUrl : "/recipeboard/imageUpload.do"
						 };
						 
						 CKEDITOR.replace("recipe_detail", ckeditor_config);
					</script>
				</td>
			</tr>
			<tr>
				<td align="right"></td>
				<td colspan="2">
					<input type="submit" value="글쓰기">
					<input type="button" value="목록" onclick="backToList(this.form)">
				</td>
			</tr>
		</table>
	</form>
	
<!-- 	<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
     <script>
      ClassicEditor.create( document.querySelector( '#recipe_detail' ),{
    	  ckfinder: {
  			uploadUrl : '${contextPath }/recipeboard/imageUpload.do'
  			}
  		})
  		.then(editor => {
  			console.log(editor);
  			console.log('Editor was initialized');
  		})
  		.catch(error => {
  			console.error(error);
      	} );
    </script> 
</body>
</html>-->

