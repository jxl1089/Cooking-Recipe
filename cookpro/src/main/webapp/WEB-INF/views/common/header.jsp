<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<c:set var="result" value="${param.result }" />
<c:if test="${result == 'loginFailed' }">
<script>
	window.onload = function(){
		alert("아이디/패스워드가 다릅니다. 다시 로그인 하세요.");
	}
</script>
</c:if>
<c:if test="${result == 'logout' }">
<script>
	window.onload = function(){
		alert("로그아웃 되었습니다. 다시 로그인 하세요.");
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

<table border="0" width="100%"">
	<tr>
		<td>
			<a href="${contextPath }/">
				<img id="logo" src="${contextPath }/resources/image/logo.png">
			</a>
		</td>
		<td>
			<%@ include file="nav.jsp" %>
		</td>
		<td>
			<c:choose>
				<c:when test="${isLogOn == true && member != null }">
					<h4>${member.name }님의 레시피</h4>
					<a class="no-underline-orange" href="${contextPath }/member/logout.do">로그아웃</a>
					<a class="no-underline-orange" href="${contextPath }/member/myPageForm.do">마이페이지</a>
					<a class="no-underline-orange" href="${contextPath }/reviewBoard/review_Form.do">리뷰게시판</a>
				</c:when>
				<c:otherwise>
					<form method="post" action="${contextPath }/member/login.do">
						<table align="right">
							<tr align="center">
								<td width="400"><input type="text" name="id" placeholder="아이디를 입력하세요"></td>
							</tr>
							<tr align="center">
								<td width="400"><input type="password" name="pwd" placeholder="비밀번호를 입력하세요"></td>
							</tr>
							<tr align="center">
								<td width="400"><input class="button" type="submit" value="로그인"><input class="button" type="button" value="회원가입" onclick="location.href='${contextPath }/member/memberForm.do';"></td>
							</tr>
						</table>
					</form>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<hr>