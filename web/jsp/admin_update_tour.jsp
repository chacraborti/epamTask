<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<fmt:setLocale value="${Locale}" scope="session"/>
<fmt:bundle basename="resources.pagecontent" prefix="title.">

    <html>
    <head>
        <link rel="stylesheet" href="css/new.css" type="text/css"/>
        <title><fmt:message key="create_tour"/></title>
    </head>
    <body>
    <c:import url="header.jsp"/>
    <div><h2 class="pagename"><fmt:message key="update"/></h2></div>
</fmt:bundle>
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
<div class="login" id="tour">
    <fmt:bundle basename="resources.pagecontent" prefix="tour_table.">
        <div class="login-card">
            <form name="tourForm" method="POST" action="controller">
                <input type="hidden" name="command" value="update_tour"/>
                <input type="hidden" name="tourId" value="${tourId}">

                <h2><fmt:message key="update"/> #${tourId}</h2> </br>

                <fmt:message key="country"/>
                <input name="country" type="text" value="${tour.country}" required size="50"
                       pattern="^[а-яА-Яa-zA-Z''-'\s]{1,40}$"/><br/>
                <fmt:message key="date"/>

                <input name="date" type="date" min="${current}" value="${ctg:dateFormatted(tour.date)}" required/><br/>
                <fmt:message key="tourType"/>
                <select name="tourType">
                    <option value="REST"><fmt:message key="rest"/></option>
                    <option value="EXCURSION"><fmt:message key="excursion"/></option>
                    <option value="SHOPPING"><fmt:message key="shopping"/></option>
                </select><br/>
                <fmt:message key="isHot"/>
                <input type="checkbox" name="isHot" value="isHot"/></br>
                <fmt:message key="cost"/>
                <input name="cost" type="number" value="${tour.cost}" min="1" required/></br>
                <button type="submit" class="login login-submit"><fmt:message key="update"/></button>
            </form>
        </div>
    </fmt:bundle>
</div>

</body>
</html>
