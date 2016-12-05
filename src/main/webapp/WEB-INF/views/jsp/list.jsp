<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
<jsp:include page="../fragments/header.jsp" />

<body>
<div class="container">
    <h2>ShopItem list</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.name}</td>
                <td>${item.description}</td>
                <td>${item.price}</td>
                <td>
                    <spring:url value="/show/${item.id}" var="showUrl" />
                    <spring:url value="/delete/${item.id}" var="deleteUrl" />
                    <spring:url value="/update/${item.id}" var="updateUrl" />

                    <button class="btn btn-info" onclick="location.href='${showUrl}'">Query</button>
                    <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                    <button class="btn btn-danger" onclick="this.disabled=true;location.href='${deleteUrl}'">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>