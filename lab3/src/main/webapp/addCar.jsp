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
    <h1>Upload a car</h1>
    <form method="post" action="addCar" enctype="multipart/form-data">
        <table border="0">
            <tr>
                <td>Brand: </td>
                <td><input type="text" name="brand" size="50"/></td>
            </tr>
            <tr>
                <td>Number: </td>
                <td><input type="text" name="number" size="50"/></td>
            </tr>
            <tr>
                <td>Colour: </td>
                <td><input type="text" name="colour" size="50"/></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="text" name="price" size="50"/></td>
            </tr>
            <tr>
                <td>driver ids: </td>
                <td><input type="text" name="driverIds" size="50"/></td>
            </tr>
            <tr>
                <td>car owner id: </td>
                <td><input type="button" value="Add Rows" onclick="addInput('dynamicInput');"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>

    <div id="dynamicInput">
        Entry 1<br><input type="text" name="myInputs[]">
    </div>

    <script type="text/javascript">
        var counter = 1;
        var limit = 3;


            function addInput(divName){
                if (counter == limit)  {
                    alert("You have reached the limit of adding " + counter + " inputs");
                }
                else {
                    var newdiv = document.createElement('div');
                    newdiv.innerHTML = "Entry " + (counter + 1) + "<br><input type='text' name='in" + counter +  "'/>";
                    document.getElementById(divName).appendChild(newdiv);
                    counter++;
                }
            }

    </script>



</center>
</body>
</html>