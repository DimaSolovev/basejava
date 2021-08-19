<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page import="com.urise.webapp.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">

        <input type="hidden" name="uuid" value="${resume.uuid}">
        <h1>Имя:</h1>
        <dl><input type="text" required pattern="^\S+\s?\S*" title="Введите ваше имя без пробелов вначале" name="fullName"
                   size=50 value="${resume.fullName}"></dl>

        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <hr>

        <h3>Секции:</h3>

        <c:forEach var="type" items="<%=SectionType.values()%>">

            <c:choose>
                <c:when test="${resume.getSection(type)==null}">
                    <h2><a>${type.title}</a></h2>
                    <c:choose>
                        <c:when test="${type=='OBJECTIVE'||type=='PERSONAL'}">
                            <input type='text' name='${type}' size=75>
                        </c:when>
                        <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <textarea name='${type}' cols=75
                              rows=5></textarea>
                        </c:when>
                    </c:choose>
                </c:when>

                <c:when test="${resume.getSection(type)!=null}">

                    <c:set var="section" value="${resume.getSection(type)}"/>
                    <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
                    <h2><a>${type.title}</a></h2>

                    <c:choose>
                        <c:when test="${type=='OBJECTIVE'||type=='PERSONAL'}">
                            <input type='text' name='${type}' size=75 value='<%=section%>'>
                        </c:when>
                        <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <textarea name='${type}' cols=75
                              rows=5><%=String.join("\n", ((ListSection) section).getItems())%></textarea>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
        </c:forEach>

        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>