<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:bundle basename="resources.pagecontent" prefix="error.">
    <html>
    <head><title><fmt:message key="error"/><br/></title></head>
    <body>
    <fmt:message key="error"/>
    Request from ${pageContext.errorData.requestURI} is failed
    <br/>
    Servlet name: ${pageContext.errorData.servletName}
    <br/>
    Status code: ${pageContext.errorData.statusCode}
    <br/>
    Exception: ${pageContext.exception}
    <br/>
    Message from exception: ${pageContext.exception.message}
    ￼
    </body>
    </html>
</fmt:bundle>
