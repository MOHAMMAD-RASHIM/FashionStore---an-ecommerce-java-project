<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - FashionStore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .register-container {
            width: 400px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        input { width: 100%; padding: 12px; margin: 10px 0; border: 1px solid #ddd; border-radius: 5px; box-sizing: border-box; }
        .btn-register { width: 100%; padding: 12px; background: #008000; color: white; border: none; border-radius: 5px; cursor: pointer; font-weight: bold; }
        .btn-register:hover { background: #006400; }
        .error-msg { color: #d9534f; margin-bottom: 15px; font-weight: bold; }
    </style>
</head>
<body>

    <jsp:include page="partials/navbar.jsp" />

    <div class="content-wrapper">
        <div class="register-container">
            <h2>Create Account</h2>

            <% if(request.getParameter("error") != null) { %>
                <div class="error-msg">
                    <% if("exists".equals(request.getParameter("msg"))) { %>
                        Email or Phone already registered!
                    <% } else { %>
                        Registration Failed. Please try again.
                    <% } %>
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/user" method="post">
                <input type="hidden" name="action" value="register">
                
                <input type="text" name="name" placeholder="Full Name" required>
                <input type="email" name="email" placeholder="Email Address" required>
                <input type="text" name="phone" placeholder="Phone Number" required>
                <input type="password" name="password" placeholder="Password" required>
                <input type="text" name="address" placeholder="Shipping Address" required>

                <button type="submit" class="btn-register">Register</button>
            </form>

            <p style="margin-top:20px;">Already have an account? 
                <a href="${pageContext.request.contextPath}/login" style="color: #007bff; text-decoration: none;">Login</a>
            </p>
        </div>
    </div>

    <jsp:include page="partials/footer.jsp" />

</body>
</html>