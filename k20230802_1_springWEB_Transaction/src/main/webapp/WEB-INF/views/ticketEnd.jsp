<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 내역</title>
</head>
<body>

<p>구매 내역</p>

${cardVO}<br/>
고객 아이디: ${cardVO.consumerId}<br/>
구매 티켓수: ${cardVO.amount}<br/><br/>
<!--  
${ticketInfo}<br/>
고객 아이디: ${ticketInfo.consumerId}<br/>
구매 티켓수: ${ticketInfo.amount}<br/><br/>
-->
<a href="ticket">돌아가기</a>

</body>
</html>