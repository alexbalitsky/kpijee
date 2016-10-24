<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<center>
    <h1>Upload a car owner</h1>
    <form method="post" action="addCarOwner" enctype="multipart/form-data">
        <table border="0">
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" size="50"/></td>
            </tr>
            <tr>
                <td>Surname: </td>
                <td><input type="text" name="surname" size="50"/></td>
            </tr>
            <tr>
                <td>Address: </td>
                <td><input type="text" name="address" size="50"/></td>
            </tr>
            <tr>
                <td>Car ids: </td>
                <td><input type="text" name="carIds" size="50"/></td>
            </tr>
            <tr>
                <td>Secure data: </td>
                <td><input type="text" name="secureData" size="50"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>