<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:bundle basename="resources.pagecontent" prefix = "error." >
<html><head><title><fmt:message key="error" /><br/></title></head>
<body>
<fmt:message key="error" />
    ${pageContext.request.contextPath}
￼</body>
</html>
</fmt:bundle>
