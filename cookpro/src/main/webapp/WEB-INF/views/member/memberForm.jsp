<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 창</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(function(){
	
	/* 아이디 중복 체크 */
	$("#idChk").click(function(){
		if($("#id").val()==""){
			alert("아이디를 입력해주세요.");
			$("#id").focus();
		} else{
			$.ajax({
				type:"post",
				url:"/cookpro/member/check.do",
				data:{"id":$("#id").val()},
				success:function(data, textStatus){
					if(data == 1){
						alert("이미 사용 중인 아이디입니다. 다시 입력해주세요.");
						$("#id").val("").trigger("focus");
					} else{
						alert("사용 가능한 아이디입니다.");
						$("#idChk").attr("value", "Y");
						document.getElementById('id').readOnly = true; 
					}
				}, error:function(data, textStatus){
					alert("에러가 발생했습니다");
				}
			}); 
		}
	});
	
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
function checkJoin() {
    let frm = document.frm;
    	
    if($("#idChk").val() == "N"){
		alert("아이디 중복확인이 필요합니다.");
		return false;
	}
    	
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
</head>
<body>
	<form name="frm" method="post" action="/cookpro/member/addMember.do">
		<h1 style="text-align:center; color:#FF7629;">회원 가입 창</h1>
		<table align="center">
			<tr>
				<td width="200"><p align="right">아이디</p></td>
				<td width="400"><input type="text" name="id" id="id"></td>
				<td width="100"><button type="button" id="idChk" value="N">중복확인</button></td>
			</tr>
			<tr>
				<td width="200"><p align="right">비밀번호</p></td>
				<td width="400"><input type="password" name="pwd" id="pwd"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">이메일</p></td>
				<td width="400"><input type="email" name="email" id="email"></td>
				<td width="100"><button type="button" id="authSend" value="N">인증번호 발송</td>
			</tr>
			<tr>
				<td width="200"><p align="right">인증번호 입력</p></td>
				<td width="400"><input type="text" name="authNo" id="authNo"></td>
				<td width="100"><button type="button" id="authCheck" value="N">인증번호 확인</button></td>
			</tr>
			<tr>
				<td width="200"><p align="right">이름</p></td>
				<td width="400"><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">성별</p></td>
				<td width="400"><input type="radio" name="gender" value="남성">남성<input type="radio" name="gender" value="여성">여성</td>
			</tr>
			<tr>
				<td width="200"><p align="right">&nbsp;</p></td>
				<td width="400">
				 	<button type="button" onclick="checkJoin()">가입하기</button>
					<input type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>