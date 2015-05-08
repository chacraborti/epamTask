<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="resources.pagecontent" prefix = "header." >
    <html>
    <head>
        <title>Header</title>
        <link href="css/new.css" rel="stylesheet">
    </head>

    <body>
    <header>
        <div class="wrapper">

            <div class="section" id="logo">
                <img src="http://firepic.org/images/2015-04/29/kqs2gjzrm7cu.jpg">
            </div>

            <div class="section" id="hello">
                <form name="helloForm" method="POST" action="controller">
                    <p><fmt:message key="hello" /> ${user.name}</p>
                </form>
            </div>

            <div class="section" id="logout">
                <form name="logoutForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="logout" />
                        <p><input type="submit" value=<fmt:message key="log_out" /> class="button"></p>

                </form>
                </form>
            </div>

        </div>
    </header>
    </body></html>
</fmt:bundle>
