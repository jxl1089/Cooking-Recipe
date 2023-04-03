<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	function delSubmit(){
		if(confirm("정말 탈퇴하시겠습니까?")){
			location.href="${contextPath }/member/delMember.do?id=${member.id}";
		} else{
			return false;
		}
	}
</script>
<h1 style="text-align:center;">${member.name }님의 마이페이지</h1>
<br>
<table align='center'>
	<tr align="left">
		<th width="150">회원정보 수정</th>
		<td width="200"><a href="${contextPath }/member/modForm.do">이메일 등 개인정보</a></td>
	</tr>
	<tr align="left">
		<th width="150">계정 관리</th>
		<td width="200"><a onclick="delSubmit()">회원탈퇴</a></td>
	</tr>
</table>