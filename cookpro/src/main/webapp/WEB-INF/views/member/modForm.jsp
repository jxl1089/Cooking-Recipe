<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(function(){
	/* 인증번호 전송 */
	 $('#authSend').click(function() {
	    	if($("#email").val()==""){
				alert("이메일을 입력해주세요.");
				$("#email").focus();
			} else{
				$.ajax({
				type:"post",
				url:"/cookpro/member/mail.do",
				data:{"email":$("#email").val()},
				success:function(data, textStatus){
					alert('인증번호가 전송되었습니다.')
					$("#authNo").val("").trigger("focus");
				}, error:function(data, textStatus){
					alert("에러가 발생했습니다");
				}
			}); 
		}
	 });
 	
	 /* 인증번호 확인 */
	 $('#authCheck').click(function() {
	    	if($("#authNo").val()==""){
				alert("인증번호를 입력해주세요.");
				$("#authNo").focus();
			} else{
				$.ajax({
				type:"post",
				url:"/cookpro/member/auth.do",
				data:{"authNo":$("#authNo").val()},
				success:function(data, textStatus){
					if(data == "Y"){
						alert('인증번호 확인이 완료되었습니다.');
						$("#authCheck").attr("value", "Y");
						document.getElementById('authNo').readOnly = true;
					} else{
						alert("인증번호가 일치하지 않습니다. 다시 입력해주세요.");
						$("#authNo").val("").trigger("focus");
					}
				}, error:function(data, textStatus){
					alert("에러가 발생했습니다");
				}
			}); 
		}
	 });
});

/* 비어있는 항목 체크 */
function modSubmit() {
    let frm = document.frm;
    	
    if($("#pwd").val()==""){
		alert("비밀번호를 입력해주세요.");
		$("#pwd").focus();
		return false;
	}
		
    if($("#authCheck").val() == "N"){
		alert("인증번호 확인이 필요합니다.");
		return false;
	}
		
	if($("#name").val()==""){
		alert("이름을 입력해주세요.");
		$("#name").focus();
		return false;
	}
		
	let gender = $("input:radio[name=gender]:checked").val();
 	if(gender === undefined){
		alert("성별을 선택해주세요.");
		return false;
	}

	frm.submit();  
}
</script>

<body>
	<form name="frm" method="post" action="${contextPath }/member/modMember.do">
		<h1 style="text-align:center; color:#FF7629;">회원 정보 수정</h1>
		<br>
		<table align="center">
			<tr>
				<td width="200"><p align="right">아이디</p></td>
				<td width="400">
					<input type="text" name="id" value=${member.id } disabled="disabled">
					<input type="hidden" name="id" value="${member.id }">
				</td>
			</tr>
			<tr>
				<td width="200"><p align="right">암호</p></td>
				<td width="400"><input type="password" name="pwd" id="pwd" value=${member.pwd }></td>
			</tr>
			<tr>
				<td width="200"><p align="right">이메일</p></td>
				<td width="400"><input type="email" name="email" id="email" value=${member.email }></td>
				<td width="100"><button class="button" type="button" id="authSend" value="N">인증번호 발송</button></td>
			</tr>
			<tr>
				<td width="200"><p align="right">인증번호 입력</p></td>
				<td width="400"><input type="text" name="authNo" id="authNo"></td>
				<td width="100"><button class="button" type="button" id="authCheck" value="N">인증번호 확인</button></td>
			</tr>
			<tr>
				<td width="200"><p align="right">이름</p></td>
				<td width="400"><input type="text" name="name" id="name" value=${member.name }></td>
			</tr>
			<tr>
				<td width="200"><p align="right">성별</p></td>
				<td width="400"><input type="radio" name="gender" value=${member.gender } checked="checked">${member.gender }</td>
			</tr>
			<tr>
				<td width="200"><p align="right">&nbsp;</p></td>
				<td width="400">
					<button class="button" type="button" onclick="modSubmit()">수정하기</button>
					<input class="button" type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>

</body>
