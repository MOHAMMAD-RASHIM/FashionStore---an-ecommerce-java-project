<%@ page session="true" %>

<%
String uri = request.getRequestURI();
%>

<nav class="navbar">

    <a href="<%= request.getContextPath() %>/home" class="logo">
        FashionStore
    </a>

    <ul class="nav-links">

        <li>
            <a class="<%= uri.contains("/home") ? "active-nav" : "" %>"
               href="<%= request.getContextPath() %>/home">
               Home
            </a>
        </li>

        <li>
            <a class="<%= uri.contains("/products") || uri.contains("/product") ? "active-nav" : "" %>"
               href="<%= request.getContextPath() %>/products">
               Products
            </a>
        </li>

        <li>
            <a class="<%= uri.contains("/cart") ? "active-nav" : "" %>"
               href="<%= request.getContextPath() %>/cart">
               Cart
            </a>
        </li>

        <li>
            <a class="<%= uri.contains("/orders") || uri.contains("/order-details") ? "active-nav" : "" %>"
               href="<%= request.getContextPath() %>/orders">
               Orders
            </a>
        </li>

        <%
            String userName = (String) session.getAttribute("userName");

            if (userName == null) {
        %>

            <li>
                <a class="<%= uri.contains("/login") ? "active-nav" : "" %>"
                   href="<%= request.getContextPath() %>/login">
                   Login
                </a>
            </li>

            <li>
                <a class="<%= uri.contains("/register") ? "active-nav" : "" %>"
                   href="<%= request.getContextPath() %>/register">
                   Register
                </a>
            </li>

        <%
            } else {
        %>

            <li class="user">
                Hi, <%= userName %>
            </li>

            <li>
                <a href="<%= request.getContextPath() %>/logout"
                   class="logout">
                   Logout
                </a>
            </li>

        <%
            }
        %>

    </ul>

</nav>