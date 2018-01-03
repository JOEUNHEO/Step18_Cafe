<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/home.jsp</title>
</head>
<body>
<h3>인덱스 페이지 입니다.</h3>
<c:choose>
	<c:when test="${not empty id }">
		<p><a href="users/private/info.do">${id }</a>님 로그인중...</p>
		<a href="users/logout.do">로그아웃</a>
		<a href="cafe/list.do">카페 글 목록보기</a>
	</c:when>
	<c:otherwise>
		<ul>
			<li><a href="users/signup_form.do">회원가입</a></li>
			<li><a href="users/login_form.do?url=${pageContext.request.contextPath }/home.do">로그인</a></li>
			<li><a href="cafe/list.do">카페 글 목록보기</a></li>
		</ul>
	</c:otherwise>
</c:choose>
</body>
</html>