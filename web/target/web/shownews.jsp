<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language='JavaScript' type='text/javascript'>
	function back() {
		document.getElementById('do').value = '';
		document.getElementById('id').value = '';
		document.getElementById('idComm').value = '';
		document.getElementById('form').submit();
	}
	function saveComment() {
		document.getElementById('do').value = 'saveComm';
		document.getElementById('id').value = '${requestScope.news.newsId}';
		document.getElementById('idComm').value = '';
		document.getElementById('form').submit();
	}
	function deleteComment(idComments) {
		if (confirm("Удалить комментарий #" + idComments)) {
			document.getElementById('do').value = 'delComm';
			document.getElementById('id').value = '${requestScope.news.newsId}';
			document.getElementById('idComm').value = idComments;
			document.getElementById('form').submit();
		}	
	}
	function editComment(idComments) {
			document.getElementById('do').value = 'showEditComm';
			document.getElementById('id').value = '';
			document.getElementById('idComm').value = idComments;
			document.getElementById('form').submit();
	
	}
</script>
<head>
<style type="text/css">
table {
	border-collapse: collapse;
}
</style>
</head>
<title>News</title>
</head>
<body>
	<form id="form" action="" method="post">
		<input type="hidden" name="do" id="do">
		<input type="hidden" name="idComm" id="idComm">
		<input type="hidden" name="id" id="id">
		<table border="1" align="center">
			<tr>
				<td>Дата публикации</td>
				<td><fmt:formatDate value="${requestScope.news.newsDetail.drelease}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td>Заголовок</td>
				<td>${requestScope.news.title}</td>
			</tr>
			<tr>
				<td>Категория</td>
				<td>${requestScope.news.category.categoryName}</td>
			</tr>
			<tr>
				<td>Тэги</td>
				<td><c:forEach var="tags" items="${requestScope.news.tag}">${tags.tagName}<br>
					</c:forEach></td>
			</tr>
			<tr>
				<td>Пользователь</td>
				<td>${requestScope.news.user.name} ${requestScope.news.user.surName}</td>
			</tr>
            <tr>
                <td>Автор</td>
                <td>${requestScope.news.newsDetail.author}</td>
            </tr>
            <tr>
                <td>Агенство</td>
                <td>${requestScope.news.newsDetail.agency}</td>
            </tr>
			<tr>
				<td>Текст</td>
				<td>${requestScope.news.document}</td>
			</tr>
			<c:if test="${sessionScope.readComment}">
				<tr>
					<td colspan="2">Комментарии</td>
				</tr>
				<c:forEach var="comments" items="${requestScope.news.comment}">
					<tr>
						<td colspan="2">#${comments.commentId}
							${comments.user.name}
						</td>
					</tr>
					<tr>
						<td colspan="2">${comments.comment}<br></td>
						<td />
							<td><input type="button" value="Редактировать"
								onclick="editComment(${comments.commentId})"></td>
							<td><input type="button" value="Удалить"
								onclick="deleteComment(${comments.commentId})"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<table align="center">
			<tr>
				<td>Добавить комментарий</td>
			</tr>
			<tr>
				<td><textarea rows="4" cols="53" name="text"></textarea></td>
			</tr>
		</table>
		<div style="text-align: center">
				<input type="button" value="Добавить комментарий" onclick="saveComment()" />
			<input type="button" value="Назад" onclick="back()" />
		</div>
	</form>
</body>
</html>