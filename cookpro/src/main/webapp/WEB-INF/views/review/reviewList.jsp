<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<script>
	function fn_articleForm(isLogOn, reviewForm, loginForm){
		if(isLogOn != '' && isLogOn != 'false'){
			location.href=reviewForm;
		} else{
			alert('로그인 후 글쓰기가 가능합니다');
			location.href=loginForm+'?action=/member/loginForm.do';
		}
	}
</script>

<table class="main-table">
	<h1 style="text-align:center; color:#FF7629;">REVIEW</h1>
	<tr class="main-table" style="height:12px; color: white; font-size:18px; background: #FF7629; border-top:1px solid black;">
		<td>글번호</td>
		<td>작성자</td>
		<td>제목</td>
		<td>작성일자</td>
	</tr>
	<c:choose>
		<c:when test="${articlesList == null }">
			<tr class="main-table" height="10">
				<td colspan="4"><b><span style="font-size: 9pt;">등록된
							글이 없습니다</span></b></td>
			</tr>
		</c:when>
		<c:when test="${articlesList != null }">
			<c:forEach var="article" items="${articlesList }"
				varStatus="articleNum">
				<tr class="main-table">
					<td width="5%" >${articleNum.count }</td>
					<td width="10%">${article.id }</td>
					<td align="left" width="35%"><span
						style="padding-right: 30px;"></span> <c:choose>
							<c:when test="${article.level > 1 }">
								<c:forEach begin="1" end="${article.level }" step="1">
									<span style="padding-left: 20px;"></span>
								</c:forEach>
								<span style="font-size: 12px; color:#FF7629;">[답변]</span>
								<a class="no-underline"
									href="${contextPath }/review/viewArticle.do?articleNo=${article.articleNo }">${article.title }</a>
							</c:when>
							<c:otherwise>
								<a class="no-underline"
									href="${contextPath }/review/viewArticle.do?articleNo=${article.articleNo }">${article.title }</a>
							</c:otherwise>
						</c:choose></td>
					<td width="10%"><fmt:formatDate value="${article.writeDate }"></fmt:formatDate></td>
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
</table>
<div class="cls2">
	<c:if test="${totArticles != null }">
		<c:choose>
			<c:when test="${totArticles > 100 }">
				<c:forEach var="page" begin="1" end="10" step="1">
					<c:if test="${section > 1 && page==1 }">
						<a class="no-uline"
							href="${contextPath }/review/reviewList.do?section=${section}&pageNum=${(section-1)*10+1}">&nbsp;pre</a>
					</c:if>
					<a class="no-uline"
						href="${contextPath }/review/reviewList.do?section=${section}&pageNum=${page }">${(section-1)*10+page}</a>
					<c:if test="${page == 10 }">
						<a class="no-uline"
							href="${contextPath }/review/reviewList.do?section=${section+1}&pageNum=${section*10+1}">&nbsp;next</a>
					</c:if>
				</c:forEach>
			</c:when>
			<c:when test="${totArticles == 100 }">
				<c:forEach var="page" begin="1" end="10" step="1">
					<a class="no-uline" href="">{page}</a>
				</c:forEach>
			</c:when>
			<c:when test="${totArticles < 100 }">
				<c:forEach var="page" begin="1" end="${totArticles/10 + 1 }"
					step="1">
					<c:choose>
						<c:when test="${page == pageNum }">
							<a class="sel-page"
								href="${contextPath }/review/reviewList.do?section=${section}&pageNum=${page}">${page }</a>
						</c:when>
						<c:otherwise>
							<a class="no-uline"
								href="${contextPath }/review/reviewList.do?section=${section}&pageNum=${page}">${page }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
		</c:choose>
	</c:if>
</div>
<a class="no-underline-orange" href="javascript:fn_articleForm('${isLogOn }','${contextPath }/review/reviewForm.do','${contextPath }/member/loginForm.do')"><b style="font-size:20px;"><br>글쓰기</b></a>

