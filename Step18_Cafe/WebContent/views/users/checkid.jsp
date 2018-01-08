<%@page import="test.users.dao.UsersDao"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
	<c:when test="${isValid }">
		{"response":true}
	</c:when>
	<c:otherwise>
		{"response":false}
	</c:otherwise>
</c:choose>
