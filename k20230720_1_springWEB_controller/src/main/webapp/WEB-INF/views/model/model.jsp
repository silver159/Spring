<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>model</title>
</head>
<body>
<h1>model.jsp입니다.</h1>

<!-- 
	컨트롤러에서 Model 인터페이스 객체에 저장돼서 넘어오는 데이터는 EL을 사용해서
	받는다.
 -->
id: ${id} <br/>
pw: ${pw} <br/>
name: ${name} <br/>

</body>
</html>