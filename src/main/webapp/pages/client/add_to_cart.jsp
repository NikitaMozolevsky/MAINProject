<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 6/7/2022
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product to cart</title>
</head>
<body>
<form action="controller">
    Quantity: <input type="text" name="quantity" value="">
    <br>
    Dosage:
    <br>
    <label>
        <input type="radio" name="volume" value="20_ml" checked="on">
    </label>20ml<br/>
    <label>
        <input type="radio" name="volume" value="50_ml">
    </label>50ml<br/>
    <label>
        <input type="radio" name="volume" value="100_ml">
    </label>100ml<br/>
    <input type="hidden" name="command" value="create_order">
    <input type="submit" value="Add to cart">

</form>
</body>
</html>
