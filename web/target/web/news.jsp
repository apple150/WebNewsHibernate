<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language='JavaScript' type='text/javascript'>
	window.onload = function() {
		document.getElementById('newsByPageSel').options[${requestScope.newsByPage/3}].selected = true;
	}
	function newsByPageSelector(sel) {
		document.getElementById('newsByPage').value = sel;
		document.getElementById('form').submit();
	}
	function pageSelector(sel) {
		document.getElementById('startPage').value = sel;
		document.getElementById('form').submit();
	}
	function deleteNews(idNews, newsHeader) {
		if (confirm("Удалить новость \"" + newsHeader + "\"")) {
			document.getElementById('do').value = 'del';
 			document.getElementById('id').value = idNews;
 			document.getElementById('form').submit();
		}	
	}
	function addNews() {
			document.getElementById('do').value = 'showEditNews';
			document.getElementById('id').value = '';
 			document.getElementById('form').submit();
	}
	function editNews(idNews) {
			document.getElementById('do').value = 'showEditNews';
			document.getElementById('id').value = idNews;
			document.getElementById('form').submit();
	}
	function logout() {
			document.getElementById('do').value = 'logout';
			document.getElementById('form').submit();
	}
	function showNews(idNews) {
		document.getElementById('do').value = 'show';
		document.getElementById('id').value = idNews;
		document.getElementById('form').submit();
	}
	function sort(column, ordr) {
		document.getElementById('column').value = column;
		document.getElementById('sortOrder').value = ordr;
		document.getElementById('form').submit();
	}
</script>

<title>News</title>
</head>
<body>
	<form action="" method="post" id="form">
		<input type="hidden" name="newsByPage" id="newsByPage" value="${requestScope.newsByPage}">
		<input type="hidden" name="startPage" id="startPage" value="${requestScope.startPage}">
		<input type="hidden" name="do" id="do">
		<input type="hidden" name="id" id="id">
		<input type="hidden" name="column" id="column" value ="${requestScope.column}">
		<input type="hidden" name="sortOrder" id="sortOrder" value ="${requestScope.sortOrder}">
		<div style="text-align: center">
			Показывать по: <select id="newsByPageSel"
				onchange="newsByPageSelector(this.value)">
				<option value=""></option>
				<option value="3">3</option>
				<option value="6">6</option>
				<option value="9">9</option>
			</select>
            &nbsp&nbsp&nbsp<input type="button" onclick="addNews()" value="Добавить новость">
            &nbsp&nbsp&nbsp<input type="button" onclick="logout()" value="Выйти">
		</div>
        <br>
		<table border="1" style="border-collapse: collapse;" align="center">
			<tr>
                <th>Date</th>
                <th>Title</th>
				<%--<th>Date<div><input type="button" onclick="sort('newsDate','asc')" value="&#x2191" />--%>
				<%--<input type="button" onclick="sort('newsDate','desc')" value="&#x2193" /></div></th>--%>
				<%--<th>Header<div><input type="button" onclick="sort('header','asc')" value="&#x2191" />--%>
				<%--<input type="button" onclick="sort('header','desc')" value="&#x2193" /></div></th>--%>
				<th>Category<div>
				<input type="button" onclick="sort('category','asc')" value="&#x2191" />
				<input type="button" onclick="sort('category','desc')" value="&#x2193" /></div></th>
				<th>Tags</th>
				<th>Author<div><input type="button" onclick="sort('user','asc')" value="&#x2191" />
				<input type="button" onclick="sort('user','desc')" value="&#x2193" /></div></th>
				<th>Comments</th>
                <th>&nbsp</th>
                <th>&nbsp</th>
			</tr>
			<c:forEach var="news" items="${requestScope.news}">
				<tr>
					<td><fmt:formatDate value="${news.newsDetail.drelease}" pattern="yyyy-MM-dd" /></td>
					<td>${news.title} <input type="button" onclick="showNews(${news.newsId})"	value="->" /></td>
					<td>${news.category.categoryName}</td>
					<td><c:forEach var="tag" items="${news.tag}">${tag.tagName}<br></c:forEach></td>
					<td>${news.user.name} ${news.user.surName}</td>
					<td align="center">${news.comment.size()}</td>
					<td><input type="button" onclick="editNews(${news.newsId})"	value="Редактировать" /></td>
					<td><input type="button"
						onclick="deleteNews(${news.newsId},'${news.title}')"
						value="Удалить" /></td>
				</tr>
			</c:forEach>
		</table>
        <br>
		<div style="text-align: center">
			<c:if test="${requestScope.startPage > 1}">
				<input type="button" onclick="pageSelector(1)" value="|<-" />
				<input type="button"
					onclick="pageSelector(${requestScope.startPage-1})" value="<-">
			</c:if>
			${requestScope.startPage}
			<c:if test="${requestScope.startPage < requestScope.lastPage}">
				<input type="button"
					onclick="pageSelector(${requestScope.startPage + 1})" value="->">
				<input type="button"
					onclick="pageSelector(${requestScope.lastPage})" value="->|">
			</c:if>
		</div>
	</form>
</body>
</html>