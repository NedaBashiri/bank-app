<%@ page import="java.util.List" %>
<%@ page import="entity.BankAccount" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account CRUD</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/assets/bootstrap.min.css">
    <script src="/assets/jquery.min.js"></script>
    <script src="/assets/bootstrap.min.js"></script>

</head>
<body>
<%
    request.setAttribute("bank", "active");
%>
<jsp:include page="/header.jsp"/>
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">AccountCRUD</div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <td>ID</td>
                    <td>AccountNumber</td>
                    <td>Account Holder Name</td>
                    <td>Balance</td>
                    <td>Account Type</td>
                    <td>Amount withdraw/deposit</td>
                    <td>ACTION</td>
                    <td>ACTION</td>
                </tr>
                </thead>
                <tbody>
                <%
                    BankAccount bankAccount = (BankAccount) request.getAttribute("account");
                %>
                <tr>
                    <td><input type="text" class="form-control" name="id" value="<%=bankAccount.getId()%>" readonly/>
                    </td>
                    <td><input type="text" class="form-control" name="accountHolderName"
                               value="<%=bankAccount.getAccountNumber()%>" readonly/>
                    </td>
                    <td><input type="text" class="form-control" name="accountHolderName"
                               value="<%=bankAccount.getAccountHolderName()%>"readonly/>
                    </td>
                    <td><input type="text" class="form-control" name="balance" value="<%=bankAccount.getBalance()%>"readonly/>
                    </td>
                    <td><input type="text" class="form-control" name="accountType"
                               value="<%=bankAccount.getAccountType()%>"readonly/>
                    </td>
                    <td><input type="text" class="form-control" name="amount" id="amount"/>
                    </td>
                    <td><input type="submit" value="withdraw" class="btn btn-info" onclick="withdrawAccount(<%=bankAccount.getId()%>)"/>
                    </td>
                    <td><input type="button" value="deposit" class="btn btn-danger"
                               onclick="depositAccount(<%=bankAccount.getId()%>)"></td>
                </tr>

                </tbody>
            </table>

            <script>
                function withdrawAccount(id) {
                    var amount = document.getElementById("amount").value;
                    window.location = "/bank/withdrawAccount?id=" + id + "&amount="+amount;
                }

                function depositAccount(id) {
                    var amount = document.getElementById("amount").value;
                    window.location = "/bank/depositAccount?id=" + id+ "&amount="+amount;;
                }
            </script>
        </div>
    </div>
</div>
</body>
</html>
