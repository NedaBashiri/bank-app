<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Register</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/assets/bootstrap.min.css">
	<link rel="stylesheet" href="/assets/main.css">
	<script src="/assets/jquery.min.js"></script>
	<script src="/assets/bootstrap.min.js"></script>
</head>
<body>
<%
	String message = (String) request.getAttribute("message");
%>

<jsp:include page="/header.jsp"/>
<div class="signpage">
	<% if(message != null){ %>
	<div class="alert alert-danger"><p><%= message %></p></div>
	<% } %>
	<form class="register_form" action="SignUp" method="post">
		
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<div class="rs_form_box">
					<h3 class="form_section_title">
						Personal Information
					</h3>
					<div class="input-group">
						<label>Name*</label>
						<input type="text" name="name" class="form-controller">
					</div>
					<div class="input-group">
						<label>Email address*</label>
						<input type="email" name="email" class="form-controller">
					</div>

					<div class="input-group">
						<label>Password*</label>
						<input type="password" name="password" class="form-controller">
					</div>

				</div>
			</div>

			<div class="col-xs-12 col-sm-12 text-center">
				<div class="rs_btn_group">
					<button class="btn btn-default pull-left" name="submit" type="submit">Register</button>
					<a href="Login.jsp" class="btn btn-default">Login here.</a>
				</div>
			</div>
		</div>
	</form>
</div>
</body>
</html>