
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>INDEX</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/assets/bootstrap.min.css">
    <script src="/assets/jquery.min.js"></script>
    <script src="/assets/bootstrap.min.js"></script>
  </head>
  <body>
  <%
    request.setAttribute("home","active");
  %>
  <jsp:include page="/header.jsp"/>

  <div>
    <h2>Welcome to Bank Application</h2>
  </div>

  </body>
</html>
