<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<fmt:setLocale value="${Locale}" scope="session" />
<fmt:bundle basename="resources.pagecontent" prefix = "user_table." >

    <html>
    <head>
        <link rel="stylesheet" href="css/new.css" type="text/css" />
        <title>Parsed table</title>
    </head>
    <body>
    <c:import url="header.jsp" />
    <div class="container">
      <div class="column">
          <form name="registrationButton" method="POST" action="controller">
              <input type="hidden" name="command" value="create_tour" />
              <input type="submit" value=<fmt:message key="create_tour" />  class="button"/>

          </form>
      </div>
    <div class="working">
        <table class="main_table">
            <tr class="navigation">
                <td><fmt:message key="name" /></td>
                <td><fmt:message key="regular" /></td>
                <td><fmt:message key="email" /></td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${users}" var="element">
                <c:if test="${!element.isAdmin}">
                <tr>
                    <td>${element.name}</td>
                    <td> <c:if test="${element.isRegular}">
                        <img src=img/check-point.png>
                    </c:if></td>
                    <td>${element.email}</td>
                    <td>
                    <form name="deleteButton" method="POST" action="controller">
                        <input type="hidden" name="command" value="delete_user" />
                        <input type="hidden" name="userEmail" value="${element.email}">
                        <input type="submit" value="<fmt:message key="delete" />"  class="button"/>
                    </form>
                    </td>

                    <td>
                        <form name="regularButton" method="POST" action="controller">
                            <input type="hidden" name="command" value="make_regular" />
                            <input type="hidden" name="userEmail" value="${element.email}">
                            <input type="submit" value="<fmt:message key="make_regular" />"  class="button"/>
                        </form>
                    </td>
                </tr>
                </c:if>
            </c:forEach>
        </table>

    </div>
    </div>
    </body>
    </html>
</fmt:bundle>
