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


            <form action="/bank/saveAccount" method="post">
                <div class="form-group">
                    <label for="accountNumber">Account Number</label>
                    <input type="text " class="form-control" name="accountNumber" id="accountNumber"/>
                </div>
                <div class="form-group">
                    <label for="accountHolderName">Account Holder Name</label>
                    <input type="text" class="form-control" name="accountHolderName" id="accountHolderName"/>
                </div>
                <div class="form-group">
                    <label for="balance">balance</label>
                    <input type="text" class="form-control" name="balance" id="balance"/>
                </div>
                <div class="form-group">
                    <label for="accountType">Account Type:</label>

                    <select name="accountType" id="accountType">
                        <option value="CHECKING_ACCOUNT">CHECKING ACCOUNT</option>
                        <option value="SAVINGS_ACCOUNT">SAVINGS ACCOUNT</option>
                    </select>
                </div>
                <input type="submit" class="btn btn-default" value="SAVE"/>
            </form>
            <table class="table table-hover">
                <thead>
                <tr>
                    <td>ID</td>
                    <td>AccountNumber</td>
                    <td>Account Holder Name</td>
                    <td>Balance</td>
                    <td>Account Type</td>
                    <td>ACTION</td>
                    <td>ACTION</td>
                </tr>
                </thead>
                <tbody>
                <%
                    List<BankAccount> accountList = (List<BankAccount>) request.getAttribute("list");
                    for (BankAccount account : accountList) {
                %>
                <tr>
                    <form action="/bank/updateAccount">
                        <td><input type="text" class="form-control" name="id" value="<%=account.getId()%>" readonly/>
                        </td>
                        <td><input type="text" class="form-control" name="accountHolderName"
                                   value="<%=account.getAccountNumber()%>"/>
                        </td>
                        <td><input type="text" class="form-control" name="accountHolderName"
                                   value="<%=account.getAccountHolderName()%>"/>
                        </td>
                        <td><input type="text" class="form-control" name="balance" value="<%=account.getBalance()%>"/>
                        </td>
                        <td><input type="text" class="form-control" name="accountType"
                                   value="<%=account.getAccountType()%>"/>
                        </td>
                        <td><input type="submit" value="WITHDRAW/DEPOSIT" class="btn btn-info" onclick="updateAccount(<%=account.getId()%>)"/>
                        </td>
                    </form>
                    <td><input type="button" value="DELETE" class="btn btn-danger"
                               onclick="deleteAccount(<%=account.getId()%>)"></td>
                </tr>

                <%
                    }
                %>
                </tbody>
            </table>

            <script>
                function deleteAccount(id) {
                    window.location = "/bank/deleteAccount?id=" + id;
                }

                function updateAccount(id) {
                    window.location = "/bank/updateAccount?id=" + id;
                }
            </script>
        </div>
    </div>
</div>
</body>
</html>
