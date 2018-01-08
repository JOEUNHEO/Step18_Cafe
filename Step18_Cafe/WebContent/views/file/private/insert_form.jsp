<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/file/insert_form.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<form action="insert.do" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<input type="hidden" name="writer" value="${id }" />
					<label for="writer">작성자</label>
					<input type="text" id="writer" class="form-control" value="${id }" disabled="disabled" />
				</div>
				<div class="form-group">
					<label for="title">제목</label>
					<input type="text" name="title" id="title" class="form-control" />
				</div>
				<div class="form-group">
					<label for="myFile">첨부</label>
					<input type="file" name="myFile" id="myFile" class="form-control" />
				</div>
				<button type="submit" class="btn btn-primary">확인</button>
				<button type="reset" class="btn btn-warning">취소</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>