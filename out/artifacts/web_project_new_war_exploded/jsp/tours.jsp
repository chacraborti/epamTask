<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%><html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<fmt:setLocale value="${Locale}" scope="session" />

<fmt:bundle basename="resources.pagecontent" prefix = "title." >
    <html>

    <head>
        <link rel="stylesheet" href="css/new.css" type="text/css" />
        <title><fmt:message key="tours" /></title>
    </head>
    <body>
    <c:import url="header.jsp" />
    <div ><h2 class="pagename"><fmt:message key="tours" /></h2></div>
        <c:forEach items="${tours}" var="element" >
            <div class="content-wrapper">
                <div class="content-card">
                    <div class="part" id="country" >${element.country}</div>
                    <div class="part">${element.date.time}</div>
                    <div class="part">
                        <ctg:tour-type tourType="${element.tourType}"></ctg:tour-type>
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
                    <div class="part">
                        <c:if test="${element.isHot}">
                            <img src=img/hot.jpg>
                    </c:if>
                    </div>
                </div>
                <div class="content-card">
                    <div class="part">
                        <c:choose>
                            <c:when test="${!isAdmin}">
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
