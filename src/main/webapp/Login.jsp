<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/assets/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/main.css">
    <script src="/assets/jquery.min.js"></script>
    <script src="/assets/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="/header.jsp"/>
<%
    response.setHeader("cache-control", "no-store");

    String message = (String) request.getAttribute("message");
%>

<div class="signpage">
    <form class="register_form form_login" action="LogIn" method="post">

        <div class="row">
            <div class="col-xs-12 col-sm-8">
                <div class="home_page_slider">
                    <div class="item">
                        <div class="rs_single_slide" style="background-image:url(images/baas-banking-as-a-service-.png)">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-4">
                <% if (message != null && !message.isEmpty()) { %>
                <div class="alert alert-danger"><p><%= message %>
                </p></div>
                <% } %>
                <div class="rs_form_box">
                    <h3 class="form_section_title">
                        Login
                    </h3>
                    <div class="input-group">
                        <label>Email address*</label>
                        <input type="email" name="email" class="form-controller">
                    </div>
                    <div class="input-group">
                        <label>Password</label>
                        <input type="password" name="password" class="form-controller">
                    </div>
                </div>
                <div class="text-center">
                    <div class="rs_btn_group">
                        <button class="btn btn-default" name="submit" type="submit">Login</button>
                        <a href="Register.jsp" class="btn btn-default">Register</a>
                    </div>
                </div>
            </div>

        </div>
    </form>
</div>
<body/>
<html/>