<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@page errorPage="error.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<fmt:setLocale value="${Locale}" scope="session"/>
<fmt:bundle basename="resources.pagecontent" prefix="title.">
    <html>
    <head>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <title><fmt:message key="cart"/></title>
    </head>
    <body>
    <c:import url="header.jsp"/>

    <div>
        <h2 class="pagename"><fmt:message key="paid_orders"/></h2>
    </div>
    <nav>
        <fmt:bundle basename="resources.pagecontent" prefix="navigation.">
            <div class="nav_button">
                <form name="viewUsersButton" method="GET" action="controller">
                    <input type="hidden" name="command" value="view_users"/>
                    <input type="submit" value=
                        <fmt:message key="view_users"/>  class="button"/>
                </form>
            </div>
            <div class="nav_button">
                <form name="registrationButton" method="GET" action="controller">
                    <input type="hidden" name="command" value="view_tours"/>
                    <input type="submit" value=
                        <fmt:message key="tours"/>  class="button"/>
                </form>
            </div>
            <div class="nav_button">
                <form method="GET" action="controller">
                    <input type="hidden" name="page" value="create_tour">
                    <button class="button" name="command" value="to_go_to"><fmt:message key="create_tour"/></button>
                </form>
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
    <c:forEach items="${orders}" var="element">
        <div class="content-wrapper">
            <div class="content-card">
                <div class="part" id="country">${element.tour.country}</div>
                <div class="part">${ctg:dateFormatted(element.tour.date)}</div>
                <div class="part">
                    <ctg:tour-type tourType="${element.tour.tourType}"></ctg:tour-type>
                </div>
                <div class="part">
                    <c:if test="${element.tour.isHot}">
                        <img src=img/hot.jpg>
                    </c:if>
                </div>
            </div>
            <div class="content-card">
                <div class="part" style=" font-size: 16pt; ">
                    <fmt:message key="paid"/> ${element.emailUser}
                </div>
            </div>
        </div>
    </c:forEach>
    <br/>
    </body>
    </html>
</fmt:bundle>
