<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Cookie[] cookies = request.getCookies();
    boolean isLogin = false;

    if (cookies != null) {
        isLogin = Arrays.stream(cookies)
                .anyMatch(cookie -> "isUserLogin".equals(cookie.getName()) && "true".equals(cookie.getValue()));
    }
%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="https://class.fanapcampus.com">fanapcampus </a>
        </div>
        <ul class="nav navbar-nav">
            <li class="<%=request.getAttribute("home")%>">
                <a href="/index.jsp">Home</a>

            </li>
            <% if (isLogin) {
            %>
            <li class="<%=request.getAttribute("bank")%>" >
                <a href="/bank/accountList">Account</a>
            </li>
            <li class="nav-item"><a href="/Logout" class="nav-link">Logout</a></li>
            <%
            } else {
            %>
            <li class="nav-item"><a href="/Register.jsp" class="nav-link">Register</a></li>
            <li class="nav-item"><a href="/Login.jsp" class="nav-link">Login</a></li>
            <% } %>
        </ul>
    </div>
</nav>