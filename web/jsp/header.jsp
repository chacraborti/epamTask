<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:bundle basename="resources.pagecontent" prefix="header.">
    <html>
    <head>
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>
    <header>
        <div class="wrapper">

            <div class="section" id="logo">
                <img src="http://firepic.org/images/2015-04/29/kqs2gjzrm7cu.jpg">
            </div>

            <div class="section" id="hello">
                <form name="helloForm" method="GET" action="controller">
                    <c:if test="${user!=null}">
                        <p><fmt:message key="hello"/> ${user.name}!</p>
                    </c:if>
                </form>
            </div>


            <div class="section" id="logout">

                <form name="logoutForm" method="GET" action="controller">
                    <input type="hidden" name="command" value="logout"/>

                    <p><input type="submit" value=
                        <fmt:message key="log_out"/> class="button"></p>

                </form>
            </div>

        </div>
    </header>
    </body>
    </html>
</fmt:bundle>