<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
/* 아이디 찾기 */
function searchId(){
	if($("#name1").val()==""){
		alert("이름을 입력해주세요.");
		$("#name1").focus();
		return false;
	}
		
	if($("#email1").val()==""){
		alert("이메일을 입력해주세요.");
		$("#email1").focus();
		return false;
	}
		
	$.ajax({
		type:"post",
		url:"/cookpro/member/searchId.do",
		data:{"name":$("#name1").val(), "email":$("#email1").val()},
		success:function(data, textStatus){
			if(data != null && data != ""){
				alert("아이디는 " + data + " 입니다.");
				$("#name1").val("");
				$("#email1").val("");
			} else{
				alert("일치하는 정보가 없습니다");
			}
		}, error:function(data, textStatus){
			alert("에러가 발생했습니다");
		}
	});
}

/* 비밀번호 찾기 */
function searchPwd(){
	if($("#id").val()==""){
		alert("아이디를 입력해주세요.");
		$("#id").focus();
		return false;
	}
	
	if($("#name2").val()==""){
		alert("이름을 입력해주세요.");
		$("#name2").focus();
		return false;
	}
		
	if($("#email2").val()==""){
		alert("이메일을 입력해주세요.");
		$("#email2").focus();
		return false;
	}
		
	$.ajax({
		type:"post",
		url:"/cookpro/member/searchPwd.do",
		data:{"id":$("#id").val(), "name":$("#name2").val(), "email":$("#email2").val()},
		success:function(data, textStatus){
			if(data != null && data != ""){
				alert("비밀번호는 " + data + " 입니다.");
				$("#id").val("");
				$("#name2").val("");
				$("#email2").val("");
			} else{
				alert("일치하는 정보가 없습니다");
			}
		}, error:function(data, textStatus){
			alert("에러가 발생했습니다");
		}
	});
}
</script>

<h2 style="text-align:center; color:#FF7629;">아이디 찾기</h2>
<br>
<table align='center'>
	<tr align="left">
		<th width="150">이름</th>
		<td width="200"><input type="text" name="name" id="name1" placeholder="이름을 입력하세요"></td>
	</tr>
	<tr align="left">
		<th width="150">이메일</th>
		<td width="200"><input type="email" name="email" id="email1" placeholder="이메일을 입력하세요"></td>
	</tr>
	<tr>
		<th></th>
	</tr>
	<tr align="center">
		<td colspan="2"><button class="button" type="button" onclick="searchId()">아이디 찾기</button></td>
	</tr>
</table>

<br>
<br>

<h2 style="text-align:center; color:#FF7629;">비밀번호 찾기</h2>
<br>
<table align='center'>
	<tr align="left">
		<th width="150">아이디</th>
		<td width="200"><input type="text" name="id" id="id" placeholder="아이디를 입력하세요"></td>
	</tr>
	<tr align="left">
		<th width="150">이름</th>
		<td width="200"><input type="text" name="name" id="name2" placeholder="이름을 입력하세요"></td>
	</tr>
	<tr align="left">
		<th width="150">이메일</th>
		<td width="200"><input type="email" name="email" id="email2" placeholder="이메일을 입력하세요"></td>
	</tr>
	<tr>
		<th></th>
	</tr>
	<tr align="center">
		<td colspan="2"><button class="button" type="button" onclick="searchPwd()">비밀번호 찾기</button></td>
	</tr>
</table>