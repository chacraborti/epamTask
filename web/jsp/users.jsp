<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <title>Parsed table</title>
</head>
<body>
<table class="main_table">
    <tr class="navigation">
        <td>id</td>
        <td>name</td>
        <td>isAdmin</td>
        <td>isRegular</td>
        <td>email</td>
        <td>password</td>
    </tr>
    <c:forEach items="${users}" var="element">
        <tr>
            <td>${element.id}</td>
            <td>${element.name}</td>
            <td>${element.isAdmin}</td>
            <td>${element.isRegular}</td>
            <td>${element.email}</td>
            <td>${element.password}</td>

        </tr>
    </c:forEach>
</table>
<br/>
</body>
</html>
