<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/file/list.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<a class="btn btn-primary" href="private/insert_form.do">파일 올리기</a>
	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>파일명</th>
				<th>크기</th>
				<th>등록일</th>
				<th>다운로드</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tmp" items="${list }">
				<tr>
					<td>${tmp.num }</td>
					<td>${tmp.writer }</td>
					<td>${tmp.title }</td>
					<td>${tmp.orgFileName }</td>
					<td>${tmp.fileSize }</td>
					<td>${tmp.regdate }</td>
					<td><a class="btn btn-success" href="download.do?num=${tmp.num }">다운로드</a></td>
					<td>
						<!-- 로그인된 아이디ㅣ가 글 작성자와 같을때만 삭제 링크 출력 -->
						<c:if test="${id eq tmp.writer }">
							<a class="btn btn-danger" href="javascript:deleteConfirm(${tmp.num })">삭제</a>
						</c:if>
					</td>
				</tr>					
			</c:forEach>
		</tbody>
	</table>		
	<!-- 페이징 처리에 관련된 UI -->
	<div class="col-lg-6 col-lg-offset-3">
		<ul class="pagination">
			<c:choose>
				<c:when test="${startPageNum ne 1 }">
					<li><a href="list.do?pageNum=${startPageNum-1 }&condition=${condition}&keyword=${keyword}">&laquo;</a></li>
				</c:when>
				<c:otherwise>
					<li><a class="muted" href="javascript:">&laquo;</a></li>
				</c:otherwise>
			</c:choose>
		
			<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
				<c:choose>
					<c:when test="${i eq pageNum }">
						<li class="active"><a href="list.do?pageNum=${i }&condition=${condition}&keyword=${keyword}">${i }</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="list.do?pageNum=${i }&condition=${condition}&keyword=${keyword}">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${endPageNum lt totalPageCount }">
					<li><a href="list.do?pageNum=${endPageNum+1 }&condition=${condition}&keyword=${keyword}">&raquo;</a></li>
				</c:when>
				<c:otherwise>
					<li><a class="muted" href="javascript:">&raquo;</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- 검색어 관련 form -->
	<form class="form-inline" action="list.do" method="post">
		<div class="form-group">
			<select name="condition" id="condition">
				<option value="titlewriterfile" <c:if test="${condition eq 'titlewriterfile' }">selected</c:if> >제목+작성자+파일명</option>
				<option value="title" <c:if test="${condition eq 'title' }">selected</c:if>>제목</option>
				<option value="writer" <c:if test="${condition eq 'writer' }">selected</c:if>>작성자</option>
				<option value="orgFileName" <c:if test="${condition eq 'orgFileName'}">selected</c:if>>파일명</option>
			</select>
			<label for="condition">검색조건</label>
			<input value="${keyword }" type="text" name="keyword" placeholder="검색어"/>
		</div>
		<button type="submit" class="btn btn-default">검색</button>
	</form>
</div>
<script>
	function deleteConfirm(num){
		var result = confirm(num+" 번 파일을 삭제 하시겠습니까?");
		if(result){
			location.href="private/delete.do?num="+num;
		}
	}
</script>
</body>
</html>