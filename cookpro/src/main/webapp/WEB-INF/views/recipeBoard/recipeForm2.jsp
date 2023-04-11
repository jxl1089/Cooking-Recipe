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
<link rel="stylesheet" href="${contextPath }/resources/css/style.css">
<title>레시피쓰기</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	let cnt = 1;
	function backToList(){
		let form = document.articleForm;
		form.action="${path}/recipeboard/recipeList.do";
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
		$("#d_file").append("<br><input type='file' name='file"+cnt+"' > <button value='테스트'></button>");
		cnt++;
	}
	
  
	
	
</script>
<style>
	.r_detail {
		border : 1px solid black; 
		width:600px;
		height:600px;
	}
	
	#img-selector {
    	    display: none;
		}
		
	 #editor img {
    	max-width: 100%;
    }
</style>
</head>
<body>
	<input id="img-selector" type="file" accept="image/*"/>
	<form name="recipeForm" method="post" action="${path }/recipeboard/addRecipe.do" enctype="multipart/form-data">
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
				<td align="left">
				<div class="editor-menu">
				    <button type="button" id="btn-bold">
      					  <b>B</b>
 					</button>
					<button type="button" id="btn-italic">
        					<i>I</i>
    				</button>
    				<button type="button" id="btn-underline">
        				<u>U</u>
    				</button>
    				<button type="button" id="btn-strike">
        				<s>S</s>
    				</button>
    				<button type="button" id="btn-ordered-list">
        					OL
    				</button>
    				<button type="button" id="btn-unordered-list">
        					UL
    				</button>
    				
    				<button type="button" id="btn-image">
        					IMG
    				</button>
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<!-- <textarea name="recipe_detail" rows="10" cols="69" maxlength="4000"></textarea> -->
					<div align="left" class="r_detail" contentEditable="true" id="recipe_detail" name="recipe_detail">
						
					</div>
				</td>
			</tr>
			<!--  <tr>
				<td align="right">요리사진 추가</td>
				<td align="left"><input type="button" value="파일추가" onclick="fn_addFile()"></td>
			</tr>
			<tr>
				<td colspan="4"><div id="d_file"></div></td>
			</tr>-->
			<tr>
				<td align="right"></td>
				<td colspan="2">
					<input type="submit" value="글쓰기">
					<input type="button" value="목록" onclick="backToList(this.form)">
				</td>
			</tr>
		</table>
	</form>
</body>
<script>
	const editor = document.getElementById('editor');
	const btnBold = document.getElementById('btn-bold');
	const btnItalic = document.getElementById('btn-italic');
	const btnUnderline = document.getElementById('btn-underline');
	const btnStrike = document.getElementById('btn-strike');
	const btnOrderedList = document.getElementById('btn-ordered-list');
	const btnUnorderedList = document.getElementById('btn-unordered-list');
	const btnImage = document.getElementById('btn-image');
    const imageSelector = document.getElementById('img-selector');

	btnBold.addEventListener('click', function () {
	    setStyle('bold');
	});

	btnItalic.addEventListener('click', function () {
	    setStyle('italic');
	});

	btnUnderline.addEventListener('click', function () {
	    setStyle('underline');
	});

	btnStrike.addEventListener('click', function () {
	    setStyle('strikeThrough')
	});

	btnOrderedList.addEventListener('click', function () {
	    setStyle('insertOrderedList');
	});

	btnUnorderedList.addEventListener('click', function () {
	    setStyle('insertUnorderedList');
	});

	function setStyle(style) {
	    document.execCommand(style);
    //focusEditor(); 오류땜에 사용끔
	}
	
    btnImage.addEventListener('click', function () {
        imageSelector.click();
    });

    imageSelector.addEventListener('change', function (e) {
        const files = e.target.files;
        if (!!files) {
            insertImageDate(files[0]);
        }
    });
    
    function insertImageDate(file) {
        const reader = new FileReader();
        reader.addEventListener('load', function (e) {
            document.execCommand('insertImage', false, `${reader.result}`);
        });
        reader.readAsDataURL(file);
    }

// 버튼 클릭 시 에디터가 포커스를 잃기 때문에 다시 에디터에 포커스를 해줌 오류땜에 사용끔
/*function focusEditor() {
    editor.focus({preventScroll: true});
}*/
</script>
</html>