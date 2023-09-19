<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginForm</title>
</head>
<body>

<h1>loginForm.jsp</h1>

<!-- action 속성에 j_spring_security_check를 넣어줘야 시큐리티 인증 절차를 거칠 수 있다. -->
<c:url var="loginUrl" value="j_spring_security_check"/>
<form action="${loginUrl}" method="post">
	
	<c:if test="${empty pageContext.request.userPrincipal}"><!-- 로그아웃 상태인가? -->
		<p>Log-Out</p>
	</c:if>
	
	<s:authorize ifNotGranted="ROLE_USER"><!-- 로그아웃 상태인가? -->
		<p>Log-Out</p>
	</s:authorize>
	
	<!-- 로그인 실패 메시지를 출력한다. -->
	<c:if test="${param.ng != null}">
		<p>
			Login NG!!!<br/>
			
			<!-- exception이 발생되지 않았을 때 메시지를 출력한다. -->
			<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
				message: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
			</c:if>
			
		</p>
	</c:if>
	
	<%--  
		시큐리티를 사용하려면 id와 password는 미리 정해진 키워드인 j_username과 j_password를
		사용해야 security-context.xml 파일의 <security:authentication-manager>에서 사용할
		수 있다.
	--%>
	ID: <input type="text" name="j_username"/><br/>
	PW: <input type="password" name="j_password"/><br/>
	<input type="submit" value="LOGIN">
</form>
</body>
</html>