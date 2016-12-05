<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="fr">
<jsp:include page="../fragments/header.jsp" />

<body>

<div class="container">

    <h2>Submitted ShopItem Information</h2>
    <br />

    <div class="row">
        <label class="col-sm-2">Name</label>
        <div class="col-sm-10">${name}</div>
    </div>
    <div class="row">
        <label class="col-sm-2">Description</label>
        <div class="col-sm-10">${description}</div>
    </div>
    <div class="row">
        <label class="col-sm-2">Price</label>
        <div class="col-sm-10">${price}</div>
    </div>
    <br/>
    <a href="${pageContext.request.contextPath}/list" class="btn btn-default">List</a>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>