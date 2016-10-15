<%--
  Created by IntelliJ IDEA.
  User: obalitskyi
  Date: 10/6/16
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Hello, please, get the cars, or upload one</h1>
<form method="post" action="getCars" enctype="multipart/form-data">
    <table border="0">
        <tr>
            <td>
                <input type="submit" value="get cars">
            </td>
        </tr>
    </table>
</form>
<form method="post" action="Upload.jsp" enctype="multipart/form-data">
    <table border="0">
        <tr>
            <td>
                <input type="submit" value="upload car">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
