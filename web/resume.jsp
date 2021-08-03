<%--
  Created by IntelliJ IDEA.
  User: Dmitrii
  Date: 03.08.2021
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <th>uuid</th>
        <th>fullName</th>
    </tr>

    <c:forEach var="resume" items="${resumes}">

        <tr>
            <td>${resume.uuid}</td>
            <td>${resume.fullName}</td>
        </tr>

    </c:forEach>
</table>

</body>
</html>
