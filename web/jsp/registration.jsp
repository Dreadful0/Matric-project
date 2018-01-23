<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setBundle basename="text" var="rb" />
    <c:import url="components/dependencies.jsp"/>
    <title>Title</title>
</head>
<body>
    <c:import url="components/navigation.jsp"/>
    <div class="container">
        <div class="row">
            <h1>Registration</h1>
            <div class="col-sm-6 col-lg-6 col-lg-offset-3 col-sm-offset-3" data-effect="slide-right">
                <form class="form-horizontal" action="/Main" method="post">
                    <div class="form-group">
                        <label for="inputFirstName" class="col-lg-3 control-label">First name</label>
                        <div class="col-lg-9">
                            <input type="text" name="inputFirstName" class="form-control" id="inputFirstName" placeholder="First name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputSecondName" class="col-lg-3 control-label">Second name</label>
                        <div class="col-lg-9">
                            <input type="text" name="inputSecondName" class="form-control" id="inputSecondName" placeholder="Second name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="col-lg-3 control-label">Email</label>
                        <div class="col-lg-9">
                            <input type="text" name="inputEmail" class="form-control" id="inputEmail" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-lg-3 control-label">Password</label>
                        <div class="col-lg-9">
                            <input type="password" name="inputPassword" class="form-control" id="inputPassword" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-3 col-lg-9">
                            <button type="submit" class="btn btn-default"
                                    name="command" value="REGISTRATION">
                                Sign up
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <c:import url="components/error.jsp"/>
    <c:import url="components/footer.jsp"/>
    <c:import url="components/scripts.jsp"/>
</body>
</html>
