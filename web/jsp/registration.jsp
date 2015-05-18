<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:bundle basename="resources.pagecontent" prefix = "label." >
<html>
<head>
    <title></title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<c:import url="header.jsp" />
<div class="login">
<div class="login-card">
    <form name="registerForm" method="POST" action="controller">
    <input type="hidden" name="command" value="submit-signup"  />
    <fmt:message key="mail" /><br/>

    <input name="email"  type="email"  placeholder="<fmt:message key="valid_email" />" style="width: 100%; height: 44px;" /><br/>
    <fmt:message key="password" /><br/>
    <input name="password" type="password" value="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$" title=<fmt:message key="valid_password" /> />
    <fmt:message key="repite_password" /><br/>
    <input name="repite-password" type="password" value="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$" title=<fmt:message key="valid_password" />/>
    <fmt:message key="name" /><br/>
    <input name="name" type="text" required />
        ${errorUserExist}</br>
        ${passwordNotMatch}</br>
        <button type="submit" class="login login-submit"><fmt:message key="submit" /></button>
    </form>

</div>
    </div>
</body>
</html>
</fmt:bundle>