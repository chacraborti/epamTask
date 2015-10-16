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
        <h2 class="pagename"><fmt:message key="cart"/></h2>
    </div>
    <nav>
        <fmt:bundle basename="resources.pagecontent" prefix="navigation.">
            <div class="nav_button">
                <form name="registrationButton" method="GET" action="controller">
                    <input type="hidden" name="command" value="view_tours"/>
                    <input type="submit" value=
                        <fmt:message key="tours"/>  class="button"/>
                </form>
            </div>
        </fmt:bundle>
    </nav>
    <div style="margin-left: 100px">
            ${emptyCart}
    </div>
    <c:forEach items="${orders}" var="element">
        <c:if test="${element.orderStatus != 'CANCELED'}">
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
            <fmt:bundle basename="resources.pagecontent" prefix="tour_table.">
                <div class="content-card">
                    <c:choose>
                        <c:when test="${user.discount>0}">
                            <div class="part" style="text-decoration: line-through; float: left">
                                <fmt:message key="cost"/> ${element.tour.cost}$
                            </div>
                            <div class="part" style="font-size: 16pt; color: red"><fmt:message
                                    key="your_cost"/> ${(element.tour.cost*(100-user.discount))/100}$
                            </div>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="cost"/> ${element.tour.cost}$
                        </c:otherwise>
                    </c:choose>
                    <div class="part">
                        <c:if test="${element.tour.discount > 0}">
                            <fmt:message key="discount"/> ${element.tour.discount}
                        </c:if>
                    </div>
                </div>

                <div class="content-card">
                    <c:if test="${element.orderStatus=='ACTIVE'}">
                        <div class="part">
                            <form name="cancelButton" method="PUT" action="controller">
                                <input type="hidden" name="command" value="cancel_order"/>
                                <input type="hidden" name="orderId" value="${element.id}">
                                <input type="submit" value=
                                    <fmt:message key="cancel"/>  class="button"/>
                            </form>
                        </div>

                        <div class="part">
                            <form name="payButton" method="PUT" action="controller">
                                <input type="hidden" name="command" value="pay_order"/>
                                <input type="hidden" name="orderId" value="${element.id}">
                                <input type="submit" value=
                                    <fmt:message key="pay"/>  class="button"/>
                            </form>
                        </div>
                    </c:if>
                    <div class="part" style="color: green; font-size: 16pt; font-weight: bold;">
                        <c:if test="${element.orderStatus=='PAID'}">
                            <fmt:message key="paid"/>
                        </c:if>
                    </div>
                </div>
                </div>
            </fmt:bundle>
        </c:if>
    </c:forEach>
    <br/>
    </body>
    </html>
</fmt:bundle>
