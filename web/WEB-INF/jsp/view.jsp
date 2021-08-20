<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.model.TextSection" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>

    <table >
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
            <tr>
                <td valign="top"><a name="type.name">${type.title}: </a></td>

                    <c:choose>
                        <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                            <td>
                                <%=((TextSection) section).getContent()%>
                            </td>
                        </c:when>

                        <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                            <td>
                                <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                                    ${item}<br>
                                </c:forEach>
                            </td>
                        </c:when>

                        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                            <c:forEach var="orgs" items="<%=((OrganizationSection) section).getOrganizations()%>">
                                   <tr>
                                       <td>
                                           ${orgs.homePage}
                                       </td>
                                   </tr>
                                    <c:forEach var="position" items="${orgs.positions}">
                                        <tr>
                                            <td>${position.startDate} ${position.endDate}</td>
                                            <td>${position.title} ${position.description}</td>
                                        </tr>
                                    </c:forEach>
                            </c:forEach>
                        </c:when>

                </c:choose>
            </tr>
        </c:forEach>
    </table>

</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
