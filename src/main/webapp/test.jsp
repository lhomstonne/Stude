<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/SampleDB">
select version()
</sql:query>

<html>
  <head>
    <title>Datasource sanity check</title>
  </head>
  <body>

  <h2>Results</h2>

<c:forEach var="row" items="${rs.rows}">
    Version ${row["version()"]}
</c:forEach>

  </body>
</html>