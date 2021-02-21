<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 12/12/2020
  Time: 8:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>đây là trang admin</p>
<form action="/admin" method="post">
<input type="text" name="id" value="${user.id}" placeholder="id">
<input type="text" name="phone" value="${user.phone}" placeholder="phone">
<input type="text" name="address" value="${user.address}" placeholder="address">
<input type="submit" placeholder="send">
</form>
</body>
</html>
