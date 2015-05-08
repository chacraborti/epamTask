<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<fmt:setLocale value="${Locale}" scope="session" />
<fmt:bundle basename="resources.pagecontent" prefix = "tour_table" >

    <html>
    <head>
        <link rel="stylesheet" href="css/new.css" type="text/css" />
        <title>Cart/title>
    </head>
    <body>
    <c:import url="header.jsp" />
        <%--<div>pusher</div>--%>
    <c:forEach items="${tours}" var="element">
        <div class="content-wrapper">
            <div class="content-card">
                <div class="part" id="country" >${element.country}</div>
                <div class="part">${element.date.time}</div>
                <div class="part">
                    <c:choose>
                        <c:when test="${element.tourType eq 'EXCURSION'}">
                            <img src=img/excursion.jpg>
                        </c:when>
                        <c:when test="${element.tourType eq 'SHOPPING'}">
                            <img src=img/shopping.jpg>
                        </c:when>
                        <c:when test="${element.tourType eq 'REST'}">
                            <img src=img/rest.jpg>
                        </c:when>
                        <c:otherwise>
                            <img src=img/nichego0_0.jpg>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="content-card">
                <div class="part"><fmt:message key="cost" /> ${element.cost} $</div>
                <div class="part">
                    <c:if test="${element.discount > 0}">
                        <fmt:message key="discount" /> ${element.discount}
                    </c:if>
                </div>
                <div class="part">
                    <c:if test="${element.isHot}">
                        <img src=img/hot.jpg>
                    </c:if>
                </div>
            </div>
            <div class="content-card">
                <div class="part">
                    <form name="orderButton" method="POST" action="controller">
                        <input type="hidden" name="command" value="order" />
                        <input type="hidden" name="tourId" value="${element.id}">
                        <input type="submit" value=<fmt:message key="order" />  class="button"/>
                    </form>
                </div>
            </div>
        </div>

    </c:forEach>


    <br/>
    </body>
    </html>
</fmt:bundle>
