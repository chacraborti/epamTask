<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${Locale}" scope="session"/>
<fmt:bundle basename="resources.pagecontent" prefix="label.">
    <html>
    <head>
        <title><fmt:message key="travel"/></title>
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
    <c:import url="header.jsp"/>
    <div class="lang_panel">
        <form name="languageForm" method="POST" action="controller">
            <input type="hidden" name="command" value="language"/>
            <button type="submit" name="language" value="En" class="button"><img src="img/en.jpg"></button>
            <button type="submit" name="language" value="Ru" class="button"><img src="img/ru.jpg"></button>
        </form>
    </div>

    <div class="login">
        <div class="login-card">
            <form name="loginForm" method="POST" action="controller">
                <input type="hidden" name="command" value="login"/>
                <fmt:message key="mail"/><br/>
                <input type="text" name="login" required value=""/><br/>
                <fmt:message key="password"/><br/>
                <input type="password" name="password" required value=""/><br/>
                    ${errorLoginPassMessage} <br/>
                    ${wrongAction} <br/>
                    ${nullPage} <br/>
                    ${fillFormError} <br/>
                    ${registrationSuccess} <br/>
                <button type="submit" class="login login-submit" style="padding-top: 10px"><fmt:message
                        key="log_in"/></button>
            </form>
            <p><fmt:message key="or"/></p>

            <div class="login-help">
                <form method="GET" action="controller">
                    <input type="hidden" name="page" value="sign_up">
                    <button class="button" name="command" value="to_go_to"><fmt:message key="sign_up"/></button>
                </form>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
