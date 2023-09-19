<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome</title>
</head>
<body>

<h1>welcome.jsp</h1>

<c:if test="${not empty pageContext.request.userPrincipal}">
	<p>${pageContext.request.userPrincipal.name} Log-In</p>
</c:if>

<!-- spring-security-taglibs를 사용해 ROLE_ADMIN 그룹으로 들어온 사용자가 있는지 확인한다. -->
<s:authorize ifAnyGranted="ROLE_ADMIN">
	<p><s:authentication property="name"/> Log-In</p>
</s:authorize>

<!-- logout -->
<a href="${pageContext.request.contextPath}/j_spring_security_logout">logout</a>

</body>
</html>