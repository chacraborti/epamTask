<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<fmt:setLocale value="${Locale}" scope="session"/>
<fmt:bundle basename="resources.pagecontent" prefix="user_table.">

    <html>
    <head>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <title><fmt:message key="users"/></title>
    </head>
    <body>
    <c:import url="header.jsp"/>
    <div>
        <div><h2 class="pagename"><fmt:message key="users"/></h2></div>
        <nav>
            <fmt:bundle basename="resources.pagecontent" prefix="navigation.">
                <div class="nav_button">
                    <form method="GET" action="controller">
                        <input type="hidden" name="page" value="create_tour">
                        <button class="button" name="command" value="to_go_to"><fmt:message key="create_tour"/></button>
                    </form>
                </div>
                <div class="nav_button">
                    <form method="GET" action="controller">
                        <input type="hidden" name="command" value="view_tours"/>
                        <input type="submit" value=
                            <fmt:message key="tours"/>  class="button"/>
                    </form>
                        <%--<form method="GET" action="controller">--%>
                        <%--<input type="hidden" name="page" value="view_tours">--%>
                        <%--<button class="button" name="command" value="to_go_to"><fmt:message key="tours"/></button>--%>
                        <%--</form>--%>
                </div>
                <div class="nav_button">
                    <form method="GET" action="controller">
                        <input type="hidden" name="command" value="view_paid_orders"/>
                        <input type="submit" value=
                            <fmt:message key="paid_orders"/> class="button"/>
                    </form>
                </div>
            </fmt:bundle>
        </nav>
    </div>
    <div class="working">
        <table class="main_table">
            <tr class="navigation">
                <td><fmt:message key="name"/></td>
                <td><fmt:message key="regular"/></td>
                <td><fmt:message key="email"/></td>
                <td></td>
                    <%--<td></td>--%>
                <td><fmt:message key="discount"/></td>
            </tr>
            <c:forEach items="${users}" var="element">
                <c:if test="${!element.isAdmin}">
                    <tr>
                        <td>${element.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${element.isRegular}">
                                    <img src=img/check-point.png>
                                </c:when>
                                <c:otherwise>
                                    <form name="regularButton" method="PUT" action="controller">
                                        <input type="hidden" name="command" value="make_regular"/>
                                        <input type="hidden" name="userEmail" value="${element.email}">
                                        <input type="submit" value="<fmt:message key="make_regular" />" class="button"/>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${element.email}</td>
                            <%--<td>--%>
                            <%--<form name="deleteButton" method="POST" action="controller" >--%>
                            <%--<input type="hidden" name="command" value="delete_user" />--%>
                            <%--<input type="hidden" name="userEmail" value="${element.email}">--%>
                            <%--<input type="hidden" name="confirm" value="${element.email}">--%>
                            <%--<button type="submit" class="login login-submit" ><fmt:message key="delete"  /></button>--%>
                            <%--&lt;%&ndash;<script>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;function submitDeleteFunction() {&ndash;%&gt;--%>
                            <%--&lt;%&ndash;return(confirm('<fmt:message key="submit_delete"  />'));&ndash;%&gt;--%>
                            <%--&lt;%&ndash;}&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</script>&ndash;%&gt;--%>
                            <%--</form>--%>
                            <%--</td>--%>

                        <td style="padding-top: 25px">
                            <c:if test="${element.isRegular}">
                                <form name="discount" method="POST" action="controller">
                                    <input type="hidden" name="command" value="user_discount"/>
                                    <input type="hidden" name="userEmail" value="${element.email}">

                                    <input name="discount" type="number" value="" style="width: 50px;" min="1" required
                                           max="50"/>%
                                    <input type="submit" value="<fmt:message key="set_discount" />" class="button"/>
                                </form>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${element.discount>0}">
                                ${element.discount}%
                            </c:if>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>

    </div>
    </body>
    </html>
</fmt:bundle>
