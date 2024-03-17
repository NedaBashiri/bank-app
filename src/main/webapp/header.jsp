<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="https://class.fanapcampus.com">fanapcampus </a>
        </div>
        <ul class="nav navbar-nav">
            <li class="<%=request.getAttribute("home")%>">
                <a href="/index.jsp">Home</a>

            </li>
            <li class="<%=request.getAttribute("bank")%>" >
                <a href="/bank/accountList">Account</a>
            </li>
        </ul>
    </div>
</nav>