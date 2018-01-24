<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="text" var="rb" />
<c:if test="${not empty param.error}">
    <div class="alert alert-danger col-sm-3 col-lg-3">
        <button type="button" class="close" data-dismiss="alert">×</button>
        <strong><fmt:message key="errors.error" bundle="${rb}"/></strong>
        <fmt:message key="errors.${param.error}" bundle="${rb}"/>
    </div>
</c:if>
