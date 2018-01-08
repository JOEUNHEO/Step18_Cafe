<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/users/signupform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<script src="../resources/js/jquery-3.2.1.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<form action="signup.do" method="post" id="signupForm">
				<div class="form-group">
					<label for="id">아이디</label>
					<div class="input-group">
						<input type="text" name="id" id="id" class="form-control"/>
						<span class="input-group-btn">
							<button id="checkId" class="btn btn-info">중복 확인</button>	
						</span>
					</div>
				</div>
				<div class="form-group">
					<label for="pwd">비밀번호</label>
					<input type="text" name="pwd" id="pwd" class="form-control" />
				</div>
				<div class="form-group">
					<label for="email">이메일</label>
					<input type="text" name="email" id="email" class="form-control" />
				</div>
				<button type="submit" class="btn btn-primary">회원 가입</button>
				<button type="reset" class="btn btn-warning">취소</button>
			</form>
		</div>
	</div>
</div>
<script>
	var isCheckId = false;
	
	//중복 확인 버튼을 클릭 했을 때,
	$("#checkId").click(function(){
		//입력한 id를 읽어온다.
		var id = $("#id").val();
		//문자열을 ajax 요청을 통해서 서버에 전송
		$.ajax({
			method:"post",
			url:"checkid.do",
			data:{id:id},
			success:function(data){
				if(data.response){//존재하는 아이디이면,
					alert("이미 사용하는 아이디 입니다.");
					$("#id").val("");
					$("#id").focus();
				}else{//존재하지 않으면
					alert("사용 가능한 아이디 입니다.");
					isCheckId = true;
				}
			}
		});
		
		return false;
	});
	
	//가입 버튼을 클릭 했을 때,
	$("#signupForm").submit(function(event){
		if(!isCheckId){//중복 확인을 통과 하지 못했다면,
			alert("아이디 중복 확인 하세요!");
			event.preventDefault();//폼전송 막기
		}
	});
</script>
</body>
</html>