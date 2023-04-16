<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<table class="footer_p">
	<tr text-align='center'>
		<th>COMPANY</th>
		<th>NOTICE</th>
		<th>SUPPORT</th>
	</tr>
	<tr>
		<td><br></td>
	</tr>
	<tr>
		<td>(주)우리의레시피</td>
		<td>고객센터 운영시간</td>
		<td style='font-size: 18px;'><b>1234-5678</b></td>
	</tr>
	<tr>
		<td>대표: 에이콘 아카데미</td>
		<td>10:00 ~ 17:00</td>
		<td><a class="no-underline" href="${contextPath }/member/mailForm.do">이메일 문의하기</a></td>
	</tr>
	<tr>
		<td>서울특별시 마포구 양화로 122 3층, 4층</td>
		<td style='font-size: 10px;'>(주말·공휴일 휴무)</td>
	</tr>
</table>
<br><hr><br>
<table width=100%;>
	<tr>
		<td style='color:gray; text-align: left; font-size: 12px;'>© copyright 우리의레시피. All rights reserved.</td>
		<td style='text-align: right; font-size: 12px;'><a style='text-decoration: none; color:gray;' href="http://www.naver.com">후원사이트 바로가기</a></td>
	</tr>
</table>