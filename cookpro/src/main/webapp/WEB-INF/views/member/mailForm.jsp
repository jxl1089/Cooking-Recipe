<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<form method="post" action="${contextPath }/member/csMail.do">
	<table align="center">
		<h1 style="text-align: center; color:#FF7629;">이메일 문의</h1>
		<tr>
			<td width="200"><p align="left">이름</p></td>
			<td width="400" align="left"><input type="text" name="name"></td>
		</tr>
		<tr>
			<td width="200"><p align="left">이메일</p></td>
			<td width="400" align="left"><input size="50" type="email" name="email" placeholder="답변 받을 이메일 주소를 작성해주세요"></td>
		</tr>
		<tr>
			<td width="200"><p align="left">제목</p></td>
			<td width="400" align="left"><input size="50" maxlength="100" type="text" name="subject"></td>
		</tr>
		<tr>
			<td width="200"><p align="left">문의 내용</p></td>
			<td width="400" align="left"><textarea rows="10" cols="50" name="content"></textarea></td>
		</tr>
		<tr align="center">
			<td colspan="2"><input class="button" type="submit" value="문의하기"> <input class="button" type="reset" value="다시입력"></td>
		</tr>
	</table>
</form>
