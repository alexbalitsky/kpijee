<%@ page import="java.util.List" %>
<%@ page import="entity.Car" %>
<%@ page import="entity.CarOwner" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Car owners</title>
</head>
<body>
<center>
    <% List<CarOwner> carOwners = (List<CarOwner>) request.getAttribute("carOwners");%>

    <h1>CARS</h1>

    <table border="2">
        <%for(int i=0; i< carOwners.size() ;i++){%>
        <tr>
            <td><%= ((CarOwner)carOwners.get(i)).getName()%></td>
            <td><%= ((CarOwner)carOwners.get(i)).getSurname()%></td>
            <td><%= ((CarOwner)carOwners.get(i)).getAddress()%></td>
            <td><%= ((CarOwner)carOwners.get(i)).getSecureData().getUsername()%></td>
            <td><%= ((CarOwner)carOwners.get(i)).getSecureData().getPassword()%></td>
            <td>

            </td>
        </tr>
        <%}%>
    </table>


</center>

<form method="post" action="index.jsp" enctype="multipart/form-data">
    <table border="0">
        <tr>
            <td>
                <input type="submit" value="get to main page">
            </td>
        </tr>
    </table>
</form>
</body>
</html>