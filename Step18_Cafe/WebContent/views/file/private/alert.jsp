<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/file/alert.jsp</title>
</head>
<body>
<c:choose>
	<c:when test="${isSuccess }">
		<script>
			alert("파일을 업로드 했습니다!");
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("업로드 실패!!");
		</script>
	</c:otherwise>
</c:choose>
<script>
	location.href="${url}";
</script>
</body>
</html>