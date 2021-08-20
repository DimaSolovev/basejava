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
        <dl><input type="text" required pattern="^\S+\s?\S*" title="Введите ваше имя без пробелов вначале"
                   name="fullName"
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

                        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">

                            <dl>
                                <dt>Название учереждения:</dt>
                                <dd><input type="text" name='name' size=100></dd>
                            </dl>
                            <dl>
                                <dt>Сайт учереждения:</dt>
                                <dd><input type="text" name='url' size=100></dd>
                                </dd>
                            </dl>
                            <br>
                            <dl>
                                <dt>Начальная дата:</dt>
                                <dd>
                                    <input type="date" name="startDate">
                                </dd>
                            </dl>
                            <dl>
                                <dt>Конечная дата:</dt>
                                <dd>
                                    <input type="date" name="endDate">
                            </dl>
                            <dl>
                                <dt>Должность:</dt>
                                <dd><input type="text" name='title' size=75
                                >
                            </dl>
                            <dl>
                                <dt>Описание:</dt>
                                <dd><textarea name="description" rows=5
                                              cols=75></textarea></dd>
                            </dl>
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

                        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                            <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>">
                                <dl>
                                    <dt>Название учереждения:</dt>
                                    <dd><input type="text" name='name' size=100 value="${org.homePage.name}"></dd>
                                </dl>
                                <dl>
                                    <dt>Сайт учереждения:</dt>
                                    <dd><input type="text" name='url' size=100 value="${org.homePage.url}"></dd>
                                    </dd>
                                </dl>
                                <br>
                                    <c:forEach var="pos" items="${org.positions}">
                                        <jsp:useBean id="pos" type="com.urise.webapp.model.Organization.Position"/>
                                        <dl>
                                            <dt>Начальная дата:</dt>
                                            <dd>
                                                <input type="date" name="startDate"
                                                       value="<%=pos.getStartDate()%>" >
                                            </dd>
                                        </dl>
                                        <dl>
                                            <dt>Конечная дата:</dt>
                                            <dd>
                                                <input type="date" name="endDate"
                                                       value="<%=pos.getEndDate()%>" >
                                        </dl>
                                        <dl>
                                            <dt>Должность:</dt>
                                            <dd><input type="text" name='title' size=75
                                                       value="${pos.title}">
                                        </dl>
                                        <dl>
                                            <dt>Описание:</dt>
                                            <dd><textarea name="description" rows=5
                                                          cols=75>${pos.description}</textarea></dd>
                                        </dl>
                                    </c:forEach>


                            </c:forEach>
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