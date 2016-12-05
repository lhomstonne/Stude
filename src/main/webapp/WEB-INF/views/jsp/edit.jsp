<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="fr">
<jsp:include page="../fragments/header.jsp" />

<body>
<div class="container">
    <h2>ShopItem Information</h2>
    <form:form method="POST" action="${pageContext.request.contextPath}/add" commandName="itemForm" >

        <form:input path="id" style="visibility: hidden"/>

        <div class="form-group">
            <label class="col-sm-2 control-label">Name</label>
            <div class="col-sm-10"><form:input path="name" /></div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">Description</label>
            <div class="col-sm-10"><form:input path="description" /></div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">Price</label>
            <div class="col-sm-10"><form:input path="price" /></div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>

    </form:form>
    <br/>
    <a href="${pageContext.request.contextPath}/list" class="btn btn-default">List</a>
</div>
</body>

<jsp:include page="../fragments/footer.jsp" />

</html>