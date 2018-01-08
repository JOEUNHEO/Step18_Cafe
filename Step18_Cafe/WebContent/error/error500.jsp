<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error/error500.jsp</title>
</head>
<body>
<p>Oops! 서버 내부적으로 오류가 발생했습니다.</p>
<p>조속히 조치 하겠습니다.</p>
<a href="${pageContext.request.contextPath }/">인덱스로 가기</a>
</body>
</html>