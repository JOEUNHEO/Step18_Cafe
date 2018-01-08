<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/users/loginform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<form action="login.do?url=${url }" method="post">
				<div class="form-group">
					<label for="id">아이디</label>
					<input type="text" name="id" id="id" class="form-control" value="${savedId }" />
					<p class="help-block">반드시 입력하세요</p>
				</div>
				<div class="form-group">
					<label for="pwd">비밀번호</label>
					<input type="password" name="pwd" id="pwd" class="form-control" />
				</div>
				<div class="checkbox">
					<label>
						<input type="checkbox" name="isSave" value="yes" />
						아이디 저장
					</label>
				</div>
				<button type="submit" class="btn btn-primary">로그인</button>
				<button type="reset" class="btn btn-warning">취소</button>
			</form>
		</div>	
	</div>
</div>
</body>
</html>