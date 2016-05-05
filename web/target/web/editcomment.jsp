<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script language='JavaScript' type='text/javascript'>
        function save() {
            document.getElementById('do').value = 'saveComm';
            document.getElementById('id').value = '${requestScope.id}';
            document.getElementById('idComm').value = '${requestScope.idComm}';
            document.getElementById('form').submit();
        }
        function back() {
            document.getElementById('do').value = 'show';
            document.getElementById('id').value = '${requestScope.id}';
            document.getElementById('idComm').value = '';
            document.getElementById('text').value = '';
            document.getElementById('form').submit();
        }
    </script>
    <title>Edit comment</title>
</head>
<body>
<form method="post" action="" id="form">
    <input type="hidden" name="do" id="do">
    <input type="hidden" name="id" id="id">
    <input type="hidden" name="idComm" id="idComm">
    <table border="1" style="border-collapse: collapse;" align="center">
        <tr>
            <td>Изменить комментарий</td>
        </tr>
        <tr>
            <td><textarea id="text" rows="4" cols="53" name="text">${requestScope.text}</textarea></td>
        </tr>
    </table>
    <div style="text-align: center">
        <input type="button" onclick="save()" value="Изменить">
        <input type="button" onclick="back()" value="Назад">
    </div>
</form>
</body>
</html>