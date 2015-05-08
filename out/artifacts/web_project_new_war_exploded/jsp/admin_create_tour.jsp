<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<fmt:setLocale value="${Locale}" scope="session" />
<fmt:bundle basename="resources.pagecontent" prefix = "tour_table." >

    <html>
    <head>
        <link rel="stylesheet" href="css/new.css" type="text/css" />
        <title>Parsed table</title>
    </head>
    <body>
    <c:import url="header.jsp" />

        <div class="working">
            <div class="column">
                <form name="viewUsersButton" method="POST" action="controller">
                    <input type="hidden" name="command" value="view_users" />
                    <input type="submit" value=<fmt:message key="view_users" />  class="button"/>
                </form>
            </div>
            <div class="login" id="tour">

                <div class="login-card">

                    <form name="tourForm" method="POST" action="controller">

                        <input type="hidden" name="command" value="submit_create_tour"  />

                        <fmt:message key="country" />
                        <input name="country"  type="text" /><br/>
                        <fmt:message key="date" />
                        <input name="date"  type="date" /><br/>
                        <fmt:message key="tourType" />
                        <select name="tourType">
                            <option value="REST">Rest</option>
                            <option value="EXCURSION">Excursion</option>
                            <option value="SHOPPING">Shopping</option>
                        </select><br/>
                        <fmt:message key="isHot" />
                        <input type="checkbox" name="isHot" value="isHot"/></br>
                        <fmt:message key="cost" />
                        <input name="cost" type="number" value="cost"/></br>

                        <button type="submit" class="login login-submit"><fmt:message key="submit" /></button>

                    </form>

                </div>
            </div>
        </div>
    </body>
    </html>
</fmt:bundle>
