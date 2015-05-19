<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<fmt:setLocale value="${Locale}" scope="session" />

<fmt:bundle basename="resources.pagecontent" prefix = "title." >
    <html>

    <head>
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title><fmt:message key="tours" /></title>
    </head>
    <body>
    <c:import url="header.jsp" />
    <div>
        <div >
            <div ><h2 class="pagename"><fmt:message key="tours" /></h2></div>
        </div>
        <nav>
    <fmt:bundle basename="resources.pagecontent" prefix = "navigation." >
            <c:choose>
                <c:when test="${!user.isAdmin}">
        <div class="nav_button">
            <form name="registrationButton" method="POST" action="controller">
                <input type="hidden" name="command" value="view_orders" />
                <input type="submit" value=<fmt:message key="cart" />  class="button" />
            </form>
        </div>
                </c:when>
                <c:otherwise>
            <div class="nav_button">

                <form name="viewUsersButton" method="POST" action="controller">
                    <input type="hidden" name="command" value="view_users" />
                    <input type="submit" value=<fmt:message key="view_users" />  class="button" />
                </form>
            </div>
            <div class="nav_button">
                <form name="registrationButton" method="POST" action="controller">
                    <input type="hidden" name="command" value="create_tour" />
                    <input type="submit" value=<fmt:message key="create_tour" />  class="button" />
                </form>
            </div>
                </c:otherwise>
            </c:choose>
        </fmt:bundle>
        </nav>

        <c:forEach items="${tours}" var="element" >
            <div class="content-wrapper">
                <div class="content-card">
                    <div class="part" id="country" >${element.country}</div>
                    <div class="part">
                            ${ctg:dateFormatted(element.date)}
                    </div>
                    <div class="part">
                        <ctg:tour-type tourType="${element.tourType}"></ctg:tour-type>
                    </div>
                    <div class="part">
                        <c:if test="${element.isHot}">
                            <img src=img/hot.jpg>
                        </c:if>
                    </div>
                </div>
                <fmt:bundle basename="resources.pagecontent" prefix = "tour_table." >
                <div class="content-card">
                    <div class="part"><fmt:message key="cost" /> ${element.cost} $</div>
                    <div class="part">
                        <c:if test="${element.discount > 0}">
                            <fmt:message key="discount" /> ${element.discount}
                        </c:if>
                    </div>
                </div>
                <div class="content-card">
                    <div class="part">
                        <c:choose>
                            <c:when test="${!user.isAdmin}">
                                <form name="orderButton" method="POST" action="controller">
                                    <input type="hidden" name="command" value="order" />
                                    <input type="hidden" name="tourId" value="${element.id}">
                                    <input type="submit" value=<fmt:message key="order" />  class="button"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                            <form name="deleteTourButton" method="POST" action="controller">
                                <input type="hidden" name="command" value="delete_tour" />
                                <input type="hidden" name="tourId" value="${element.id}">
                                <input type="submit" value=<fmt:message key="delete" /> class="button"/>
                            </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                </fmt:bundle>
            </div>

        </c:forEach>
    <br/>
    </body>
    </html>
</fmt:bundle>
