<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<form action="memberInsert" method="post">
	<table width="500" align="center" border="1" cellpadding="5" cellspacing="0">
		<tr>
			<td colspan="2">회원가입</td>
		</tr>
		<tr>
			<td width="100">이름</td>
			<td width="400">
				<input type="text" name="name"/>
			</td>
		</tr>
		<tr>
			<td width="100">아이디</td>
			<td width="400">
				<input type="text" name="id"/>
			</td>
		</tr>
		<tr>
			<td width="100">비밀번호</td>
			<td width="400">
				<input type="password" name="password"/>
			</td>
		</tr>
		<tr>
			<td width="100">이메일</td>
			<td width="400">
				<input type="email" name="email"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="회원가입"/>
				<input type="reset" value="다시쓰기"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>