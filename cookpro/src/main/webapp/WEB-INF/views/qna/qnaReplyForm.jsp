<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<%
	int parentNo = Integer.parseInt(request.getParameter("parentNo"));
%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function readURL(input){
		if(input.files && input.files[0]){
			let reader = new FileReader();
			reader.onload = function(e){
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function backToList(obj){
		obj.action = "${contextPath}/qna/qnaList.do";
		obj.submit();
	}
</script>

<form name="articleForm" method="post" action="${contextPath }/qna/addReply.do" enctype="multipart/form-data">
<input type="hidden" name="parentNo" value="<%=parentNo%>">
	<table border="0" align="center">
		<h1 style="text-align: center; color: #FF7629;">답글 쓰기</h1>
		<tr>
			<td width="200"><p align="left">글제목</p></td>
			<td colspan="2" width="400" align="left"><input type="text" size="55" maxlength="500" name="title"></td>
		</tr>
		<tr>
			<td width="200"><p align="left">글내용</p></td>
			<td colspan="2" width="400" align="left"><textarea rows="10" cols="55" name="content" maxlength="4000"></textarea></td>
		</tr>
		<tr align="center">
			<td colspan="3"><input class="button" type="submit" value="답글쓰기"> <input class="button" type="button" value="목록보기" onclick="backToList(this.form)"></td>
		</tr>
	</table>
</form>
