<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script language='JavaScript' type='text/javascript'>
        function save() {
            document.getElementById('do').value = 'save';
            document.getElementById('id').value = '${requestScope.news.newsId}';
            document.getElementById('form').submit();
        }
        function back() {
            document.getElementById('do').value = '';
            document.getElementById('header').value = '';
            document.getElementById('categorySel').value = '';
            document.getElementById('text').value = '';
            document.getElementById('form').submit();
        }
    </script>
    <title>Edit news</title>
</head>
<body>
<form method="post" action="" id="form">
    <input type="hidden" name="do" id="do">
    <input type="hidden" name="id" id="id">
    <BR>
    <table border="1" style="border-collapse: collapse;" align="center">
        <tr>
            <td>Header</td>
            <td><input type="text" size="50" id="header" name="header" value='${requestScope.news.title}'></td>
        </tr>
        <tr>
            <td>Category</td>
            <td><select id="categorySel" name="category">
                <c:forEach var="cat" items="${requestScope.categories}">
                    <option value="${cat.categoryId}"
                            <c:if test="${requestScope.news.category.categoryName == cat.categoryName}">selected</c:if>>${cat.categoryName}</option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <td>Tag</td>
            <td><c:forEach var="tags" items="${requestScope.tags}">
                <input type="checkbox" name="tags" value="${tags.tagId}"of
                <c:forEach var="tagNews" items="${requestScope.news.tag}">
                       <c:if test="${tagNews.tagName == tags.tagName}">checked</c:if>
                </c:forEach>>${tags.tagName}<br>
            </c:forEach></td>
        </tr>
        <tr>
            <td>Text</td>
            <td><textarea id="text" rows="4" cols="53" name="text">${requestScope.news.document}</textarea></td>
        </tr>
    </table>
    <br>
    <div style="text-align: center">
        <input type="button" onclick="save()" value="Сохранить">
        <input type="button" onclick="back()" value="Назад">
    </div>
</form>
</body>
</html>