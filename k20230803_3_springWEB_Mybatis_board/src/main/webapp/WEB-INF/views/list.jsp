<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 보기</title>
<style type="text/css">
	
	a {
		color: black;
		text-decoration: none;
	}
	a:hover {
		color: red;
		text-decoration: none;
		font-weight: bold;
	}
	
</style>
</head>
<body>

<fmt:requestEncoding value="UTF-8"/>
<table width="1000" align="center" border="1" cellpadding="5" cellspacing="0">
	<tr>
		<th colspan="5">게시판 보기</th>
	</tr>
	<tr>
		<td colspan="5" align="right">
			${boardList.totalCount}(${boardList.currentPage}/${boardList.totalPage})
		</td>
	</tr>
	<tr>
		<th style="width: 70px;">글번호</th>
		<th style="width: 610px;">제목</th>
		<th style="width: 100px;">이름</th>
		<th style="width: 150px;">작성일</th>
		<th style="width: 70px;">조회수</th>
	</tr>
	<c:set var="list" value="${boardList.list}"/>
	<c:if test="${list.size() == 0 }">
	<tr>
		<td colspan="5">
			<marquee>테이블에 저장된 글이 없습니다.</marquee>
		</td>
	</tr>
	</c:if>
	
	<!-- 메인글이 있으면 메인글의 개수만큼 반복하며 글 제목을 출력한다. -->
	<c:if test="${list.size() !=0 }">
	<c:forEach var="vo" items="${list}">
	<tr>
		<td align="center">
			${vo.idx}
		</td>
		<td>
			<!-- 레벨에 따른 들여쓰기 -->
			<c:if test="${vo.lev > 0}">
				<c:forEach var="i" begin="1" end="${vo.lev}" step="1">
					&nbsp;&nbsp;&nbsp;&nbsp;
				</c:forEach>
				Re.
				<img alt="reply" src="images/reply.png">
			</c:if>
			
			<!-- 제목에 태그를 적용할 수 없게 한다. -->
			<c:set var="subject" value="${fn:replace(vo.subject, '<', '&lt;')}"/>
			<c:set var="subject" value="${fn:replace(subject, '>', '&gt;')}"/>
			
			<!-- 제목에 하이퍼링크를 걸어준다. -->
			<a href="increment?idx=${vo.idx}&currentPage=${boardList.currentPage}">
			${vo.subject}
			</a>
			
			<c:if test="${vo.hit > 10}">
				<img alt="HOT" src="images/hot.gif">
			</c:if>
		</td>
		<td align="center">
			<!-- 이름에 태그를 적용할 수 없게 한다. -->
			<c:set var="name" value="${fn:replace(vo.name, '<', '&lt;')}"/>
			<c:set var="name" value="${fn:replace(name, '>', '&gt;')}"/>
			${name}
		</td>
		<td align="center">
			<fmt:formatDate value="${vo.writeDate}" pattern="yyyy.MM.dd(E)"/>
		</td>
		<td align="center">${vo.hit}</td>
	</tr>
	</c:forEach>
	</c:if>
	
	<!-- 페이지 이동 버튼 -->
	<tr>
		<td colspan="5" align="center">
			
			<!-- 처음으로 -->
			<c:if test="${boardList.currentPage >1}">
				<button 
					type="button" 
					class='button button1' 
					title="첫 페이지로 이동합니다." 
					onclick="location.href='?currentPage=1'"
				>처음</button>
			</c:if>
			
			<c:if test="${boardList.currentPage <= 1}">
				<button 
					type="button" 
					class='button button2' 
					disabled="disabled" 
					title="이미 첫 페이지 입니다"
				>처음</button>
			</c:if>
			
			<!-- 10페이지 앞으로 -->
			<c:if test="${boardList.startPage >1}">
				<button 
					type="button" 
					class='button button1' 
					title="이전 10 페이지로 이동합니다." 
					onclick="location.href='?currentPage=${boardList.startPage - 1}'"
				>이전</button>
			</c:if>
			
			<c:if test="${boardList.startPage <= 1}">
				<button 
					type="button" 
					class='button button2' 
					disabled="disabled" 
					title="이미 첫 10페이지 입니다"
				>이전</button>
			</c:if>
			
			<!-- 페이지 이동버튼 -->
			<c:forEach var="i" begin="${boardList.startPage}" end="${boardList.endPage}" step="1">
				<c:if test="${boardList.currentPage == i}">
					<button 
						class='button button2' 
						type='button' 
						disabled='disabled'
					>${i}</button>
				</c:if>
				
				<c:if test="${boardList.currentPage != i}">
					<button 
						class='button button1' 
						type='button' 
						title="${i}페이지로 이동합니다." 
						onclick="location.href='?currentPage=${i}'"
					>${i}</button>
				</c:if>
			</c:forEach>
			
			<!-- 10페이지 뒤로 -->
			<c:if test="${boardList.endPage < boardList.totalPage}">
				<button 
					type="button" 
					class='button button1' 
					title="다음 10 페이지로 이동합니다." 
					onclick="location.href='?currentPage=${boardList.endPage + 1}'"
				>다음</button>
			</c:if>
			
			<c:if test="${boardList.endPage >= boardList.totalPage}">
				<button 
					type="button" 
					class='button button2' 
					disabled="disabled" 
					title="이미 마지막 10페이지 입니다"
				>다음</button>
			</c:if>
			
			<!-- 마지막 -->
			<c:if test="${boardList.currentPage < boardList.totalPage}">
				<button 
					type="button" 
					class='button button1' 
					title="마지막 페이지로 이동합니다." 
					onclick="location.href='?currentPage=${boardList.totalPage}'"
				>마지막</button>
			</c:if>
			
			<c:if test="${boardList.currentPage >= boardList.totalPage}">
				<button 
					type="button" 
					class='button button2' 
					disabled="disabled" 
					title="이미 마지막 페이지 입니다"
				>마지막</button>
			</c:if>
	
		</td>
	</tr>
	
	
	<!-- 글쓰기 버튼 -->
	<tr class="table-secondary">
		<td colspan="5" align="right">
			<input 
				class="btn btn-outline-primary btn-sm" 
				type="button" 
				value="글쓰기"
				style="font-size: 13px;"
				onclick="location.href='insert'"
			/>
		</td>
	</tr>	
	
</table>


</body>
</html>