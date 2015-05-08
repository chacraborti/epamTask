<%--
  Created by IntelliJ IDEA.
  User: ilona
  Date: 07.05.15
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/new.css" type="text/css" />
</head>
<body>
    <div class="working">

        <div class="login">

            <div class="login-card">

                <form name="tourForm" method="POST" action="controller">

                    <input type="hidden" name="command" value="submit_create_tour"  />

                    <fmt:message key="country" /><br/>
                    <input name="country"  type="text" value="country"/><br/>
                    <fmt:message key="date" /><br/>
                    <input name="date"  type="date" value="date"/><br/>
                    <fmt:message key="tourType" /><br/>
                    <select name="tourType">
                        <option value="REST">Rest</option>
                        <option value="EXCURSION">Excursion</option>
                        <option value="SHOPPING">Shopping</option>
                    </select><br/>
                    <fmt:message key="isHot" /><br/>
                    <input type="checkbox" name="isHot" value="isHot"/></br>
                    <fmt:message key="isHot" /><br/>
                    <input name="cost" type="number" value="cost"/><br/>
                    <fmt:bundle basename="resources.pagecontent" prefix = "label." >
                        <button type="submit" class="login login-submit"><fmt:message key="submit" /></button>
                    </fmt:bundle>
                </form>

            </div>
        </div>
    </div>
</body>
</html>
