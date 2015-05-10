<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<fmt:setLocale value="${Locale}" scope="session" />
<fmt:bundle basename="resources.pagecontent" prefix = "title." >
    <html>
    <head>
        <link rel="stylesheet" href="css/new.css" type="text/css" />
        <title><fmt:message key="cart" /></title>
    </head>
    <body>
    <c:import url="header.jsp" />
    <div ><h3 class="pagename"><fmt:message key="cart" /></h3></div>
    <c:forEach items="${orders}" var="element">
        <c:if test="${element.orderStatus != 'CANCELED'}">
        <div class="content-wrapper">
            <div class="content-card">
                <div class="part" id="country" >${element.tour.country}</div>
                <div class="part">${element.tour.date.time}</div>
                <div class="part">
                    <c:choose>
                        <c:when test="${element.tour.tourType eq 'EXCURSION'}">
                            <img src=img/excursion.jpg>
                        </c:when>
                        <c:when test="${element.tour.tourType eq 'SHOPPING'}">
                            <img src=img/shopping.jpg>
                        </c:when>
                        <c:when test="${element.tour.tourType eq 'REST'}">
                            <img src=img/rest.jpg>
                        </c:when>
                        <c:otherwise>
                            <img src=img/nichego0_0.jpg>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <fmt:bundle basename="resources.pagecontent" prefix = "tour_table." >
            <div class="content-card">
                <div class="part"><fmt:message key="cost" /> ${element.tour.cost} $</div>
                <div class="part">
                    <c:if test="${element.tour.discount > 0}">
                        <fmt:message key="discount" /> ${element.tour.discount}
                    </c:if>
                </div>
                <div class="part">
                    <c:if test="${element.tour.isHot}">
                        <img src=img/hot.jpg>
                    </c:if>
                </div>
                <div class="part">
                    ${element.orderStatus}
                </div>
            </div>
                <c:if test="${element.orderStatus=='ACTIVE'}">
                <div class="content-card">
                    <div class="part">
                                <form name="cancelButton" method="POST" action="controller">
                                    <input type="hidden" name="command" value="cancel_order" />
                                    <input type="hidden" name="orderId" value="${element.id}">
                                    <input type="submit" value=<fmt:message key="cancel" />  class="button"/>
                                </form>
                    </div>

                    <div class="part">
                        <form name="payButton" method="POST" action="controller">
                            <input type="hidden" name="command" value="pay_order" />
                            <input type="hidden" name="orderId" value="${element.id}">
                            <input type="submit" value=<fmt:message key="pay" />  class="button"/>
                        </form>
                    </div>
                </div>
                </c:if>
                </div>
            </fmt:bundle>
        </div>
</c:if>
    </c:forEach>

    <br/>
    </body>
    </html>
</fmt:bundle>
