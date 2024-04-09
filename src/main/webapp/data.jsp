<%@ page import="java.sql.ResultSet" %>
<%@ page import="service.FunctionJDBC" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            background-color: #f2f2f2;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            border: 1px solid #ddd;
            background-color: #fff;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        form {
            text-align: center;
            margin-top: 20px;
        }

        input[type="text"], input[type="submit"] {
            padding: 10px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h1>User Information</h1>

<form action="${pageContext.servletContext.contextPath}/data" method="post">
    <label for="id">Enter User ID:</label>
    <input type="text" id="id" name="id">
    <input type="submit" value="Submit">
</form>

<table>
    <tr>
        <th>User ID</th>
        <th>Login</th>
        <th>Password</th>
    </tr>
    <%
        try {
            ResultSet resultSet = FunctionJDBC.getUsers();
            // Выводим информацию о каждом пользователе в таблицу
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
    %>
    <tr>
        <td><%= id %></td>
        <td><%= login %></td>
        <td><%= password %></td>
    </tr>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</table>

<form action="${pageContext.servletContext.contextPath}/logout" method="post">
    <input type="submit" value="Logout" name="Logout">
</form>

</body>
</html>
