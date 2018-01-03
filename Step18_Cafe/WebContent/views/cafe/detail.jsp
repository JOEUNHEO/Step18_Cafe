<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/cafe/detail.jsp</title>
<style>
	.content{
		border: 1px solid red;
	}
</style>
</head>
<body>
<h3>상세글 내용 페이지</h3>
<%-- 로그인한 회원이 작성한 글이라면 수정, 삭제 링크를 출력한다 --%>
<c:if test="${id eq dto.writer }">
	<a href="private/updateform.do?num=${dto.num }">수정</a>
	<a href="javascript:deleteCheck()">삭제</a>
</c:if>
<table>
	<tr>
		<th>글번호</th>
		<td>${dto.num }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${dto.writer }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${dto.title }</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${dto.viewCount }</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${dto.regdate }</td>
	</tr>
</table>
<div class="content">${dto.content }</div>
<script>
	function deleteCheck(){
		var isDelete = confirm("글을 삭제 하시겠습니까?");
		if(isDelete){
			location.href="private/delete.do?num=${dto.num}";
		}
	}
</script>
</body>
</html>