<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="result" value="${param.result }" />
<c:if test="${result == 'loginfailed' }">
<script>
	window.onload = function(){
		alert("아이디 / 패스워드가 다릅니다. 다시 로그인 하세요.");
	}
</script>
</c:if>
<c:if test="${result == 'logout' }">
<script>
	window.onload = function(){
		alert("로그아웃이 되었습니다. 다시 로그인 하세요.");
	}
</script>
</c:if>
<c:if test="${result == 'notLogin' }">
<script>
	window.onload = function(){
		alert("로그인 되어 있지 않습니다. 로그인 하세요.");
	}
</script>
</c:if>
<form method="post" action="${contextPath }/member/login.do">
	<h1 style="text-align: center; color:#FF7629;">로그인</h1>
	<br>
	<table align="center" height="200">
		<tr align="center">
			<td width="200" align="right">아이디</td>
			<td width="400"><input type="text" name="id" placeholder="아이디를 입력하세요"></td>
		</tr>
		<tr align="center">
			<td width="100" align="right">암호</td>
			<td width="400"><input type="password" name="pwd" placeholder="비밀번호를 입력하세요"></td>
		</tr>
		<tr align="center">
			<td colspan="2"><input class="button" type="submit" value="로그인"> <input class="button" type="reset" value="다시입력"></td>
		</tr>
		<tr align="center">
			<td colspan="2"><a class="no-underline-orange" href="${contextPath }/member/searchForm.do">아이디/비밀번호 찾기</a></td>
		</tr>
	</table>
</form>