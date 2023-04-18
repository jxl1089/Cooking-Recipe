<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="article" value="${articleMap.article }" />
<c:set var="imageFileList" value="${articleMap.imageFileList }" />

<style>
	#tr_btn_modify {
		display:none;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function backToList(obj){
		obj.action="${contextPath}/qna/qnaList.do";
		obj.submit();
	}
	
	function fn_enable(obj){
		document.getElementById("i_title").disabled=false;
		document.getElementById("i_content").disabled=false;
		imageFileName = document.getElementById("i_imageFileName");
		if(imageFileName != null && imageFileName.length != 0){
			document.getElementById("i_imageFileName").disabled=false;
		}
		document.getElementById("tr_btn_modify").style.display="block";
		document.getElementById("tr_btn").style.display="none";
	}
	
	function fn_modify_article(obj){
		obj.action = "${contextPath}/qna/modArticle.do";
		obj.submit();
	}
	
	function fn_remove_article(url, articleNo){
		let form = document.createElement("form");
		form.setAttribute("method", "get");
		form.setAttribute("action", url);
		
		let articleNoInput = document.createElement("input");
		articleNoInput.setAttribute("type", "hidden");
		articleNoInput.setAttribute("name", "articleNo");
		articleNoInput.setAttribute("value", articleNo);
		
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
	function fn_reply_form(url, parentNo){
		let form = document.createElement("form");
		form.setAttribute("method", "get");
		form.setAttribute("action",url);
		
		let parentInput = document.createElement("input");
		parentInput.setAttribute("type","hidden");
		parentInput.setAttribute("name","parentNo");
		parentInput.setAttribute("value",parentNo);
		
		form.appendChild(parentInput);
		document.body.appendChild(form);
		form.submit();
	}
</script>

	<form name="frmArticle" method="post" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">글번호</td>
				<td>
					<input type="text" value="${article.articleNo }" disabled="disabled">
					<input type="hidden" value="${article.articleNo }" name="articleNo">
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">작성자</td>
				<td><input type="text" value="${article.id }" name="id" disabled="disabled"></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">제목</td>
				<td>
				<input type="text" value="${article.title }" name="title" id="i_title" disabled="disabled">
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">내용</td>
				<td>
					<textarea rows="20" cols="60" name="content" id="i_content" disabled="disabled">${article.content }</textarea>
				</td>
			</tr>
			<c:choose>
				<c:when test="${not empty imageFileList && imageFileList != null }">
					<c:forEach var="item" items="${imageFileList }" varStatus="status">
						<tr>
							<td width="20%" align="center" bgcolor="#FF9933" rowspan="2">
								이미지${status.count }
							</td>
							<td>
								<input type="hidden" name="originalFileName" value="${item.imageFileName }">
								<img src="${contextPath }/download.do?imageFileName=${item.imageFileName }&articleNo=${article.articleNo }" id="preview"><br>
							</td>
						</tr>
						<tr>
							<td>
								<input type="file" name="imageFileName" id="i_imageFileName" onchange="readURL(this)">
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr id="tr_file_upload">
						<td width="150" align="center" bgcolor="#ff9933" rowspan="2">
							이미지
						</td>
						<td>
							<input type="hidden" name="originalFileName" value="${article.imageFileName }">
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<img id="preview" /><br>
							<input type="file" name="imageFileName" id="i_imageFileName" disabled="disabled" onchange="readURL(this);">
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td width="20%" align="center" bgcolor="#FF9933">
					등록일자
				</td>
				<td>
					<input type="text" value="<fmt:formatDate value='${article.writeDate }' />" disabled="disabled">
				</td>
			</tr>
			<tr id="tr_btn_modify">
				<td colspan="2" align="center">
					<input type="button" value="수정반영하기" onclick="fn_modify_article(frmArticle)">
					<input type='button' value="취소" onclick="backToList(frmArticle)">
				</td>
			</tr>
			<tr id="tr_btn">
				<td colspan="2" align="center">
				<c:if test="${member.id == article.id }">
					<input type="button" value="수정하기" onclick="fn_enable(this.form)">
					<input type="button" value="삭제하기" onclick="fn_remove_article('${contextPath}/qna/removeArticle.do', ${article.articleNo })">
				</c:if>
					<input type="button" value="리스트로 돌아가기" onclick="backToList(this.form)">
					<input type="button" value="답글쓰기" onclick="fn_reply_form('${contextPath }/qna/qnaReplyForm.do', ${article.articleNo })">
				</td>
			</tr>
		</table>
	</form>

