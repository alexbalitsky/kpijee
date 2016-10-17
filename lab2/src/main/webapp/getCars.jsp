<%@ page import="java.util.List" %>
<%@ page import="entity.Car" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cars</title>
</head>
<body>
<center>
    <% List<Car> cars = (List<Car>) request.getAttribute("cars");%>

    <h1>CARS</h1>

    <table border="2">
        <%
            for(int i=0; i< cars.size() ;i++){%>
        <tr>
            <td><%= ((Car)cars.get(i)).getBrand()%></td>
            <td><%= ((Car)cars.get(i)).getNumber()%></td>
            <td><%= ((Car)cars.get(i)).getColour()%></td>
            <td><%= ((Car)cars.get(i)).getPrice()%></td>
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