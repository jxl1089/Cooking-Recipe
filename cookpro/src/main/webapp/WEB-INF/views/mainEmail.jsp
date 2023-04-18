<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<style>
* {
  box-sizing: border-box;
}

input[type=text], input[type=email], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

input[type=submit] {
  background-color: #FF7629;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: right;
}

input[type=submit]:hover {
  background-color: white;
  color: #FF7629;
  border: 1px solid #FF7629;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

.col-25 {
  float: left;
  width: 25%;
  margin-top: 6px;
}

.col-75 {
  float: left;
  width: 75%;
  margin-top: 6px;
}

/* Clear floats after the columns */
.row::after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
/* @media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
  }
} */
</style>

<h2 style="color:#FF7629;">이메일 문의</h2>
<p style="font-size:15px;">우리의레시피는 회원 여러분들의 많은 연락을 기다립니다!</p>

<div class="container">
  <form method="post" action="${contextPath }/member/csMail.do">
  <div class="row">
    <div class="col-25">
      <label for="name">이름</label>
    </div>
    <div class="col-75">
      <input type="text" id="name" name="name">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="email">이메일</label>
    </div>
    <div class="col-75">
      <input type="email" id="email" name="email" placeholder="답변받을 이메일 주소를 작성해주세요">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="subject">제목</label>
    </div>
    <div class="col-75">
    	<input type="text" id="subject" name="subject">
<!--       <select id="title" name="title">
        <option value="australia">Australia</option>
        <option value="canada">Canada</option>
        <option value="usa">USA</option>
      </select> -->
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="content">문의 내용</label>
    </div>
    <div class="col-75">
      <textarea id="content" name="content" style="height:200px"></textarea>
    </div>
  </div>
  <br>
  <div class="row">
    <input type="submit" value="문의하기">
  </div>
  </form>
</div>