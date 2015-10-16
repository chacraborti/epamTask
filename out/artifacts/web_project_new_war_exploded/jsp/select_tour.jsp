<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <div><h2 class="pagename"><fmt:message key="create_tour"/></h2></div>
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
            <form name="toursButton" method="GET" action="controller">
                <input type="hidden" name="command" value="view_tours"/>
                <input type="submit" value=
                    <fmt:message key="tours"/>  class="button"/>
            </form>
        </div>
    </fmt:bundle>
</nav>
<div class="login" id="tour">
    <fmt:bundle basename="resources.pagecontent" prefix="tour_table.">
        <div class="login-card">

            <form name="tourForm" method="POST" action="controller">

                <input type="hidden" name="command" value="submit_create_tour"/>

                <fmt:message key="country"/>
                <input name="country" type="text" required/><br/>
                <fmt:message key="date"/>
                <input name="date" type="date" min="${current}" required/><br/>
                <fmt:message key="tourType"/>
                <select name="tourType">
                    <option value="REST"><fmt:message key="rest"/></option>
                    <option value="EXCURSION"><fmt:message key="excursion"/></option>
                    <option value="SHOPPING"><fmt:message key="shopping"/></option>
                </select><br/>
                <fmt:message key="isHot"/>
                <input type="checkbox" name="isHot" value="isHot"/></br>
                <fmt:message key="cost"/>
                <input name="cost" type="number" value="cost" min="1" required/></br>

                <button type="submit" class="login login-submit"><fmt:message key="submit"/></button>

            </form>

        </div>
    </fmt:bundle>
</div>

</body>
</html>

