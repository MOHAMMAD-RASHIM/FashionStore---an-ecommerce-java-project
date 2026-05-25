<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <style>
        body {
            font-family: Arial;
            background: #f5f5f5;
        }

        .container {
            width: 350px;
            margin: 100px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        input {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
        }

        button {
            width: 95%;
            padding: 10px;
            background: orange;
            color: white;
            border: none;
            cursor: pointer;
        }

        a {
            text-decoration: none;
            color: blue;
        }
    </style>
</head>

<body>

<div class="container">
    <h2>Login</h2>

    <% if(request.getParameter("error") != null) { %>
        <p style="color:red;">Invalid Email or Password</p>
    <% } %>

    <form action="<%= request.getContextPath() %>/user" method="post">

        <input type="hidden" name="action" value="login">

        <input type="email" name="email" placeholder="Enter Email" required>
        <input type="password" name="password" placeholder="Enter Password" required>

        <button type="submit">Login</button>
    </form>

    <p>Don't have an account?
        <a href="<%= request.getContextPath() %>/register">Register</a>
    </p>
</div>

</body>
</html>