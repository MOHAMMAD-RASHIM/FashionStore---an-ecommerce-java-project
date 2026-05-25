<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.fashionstore.model.Order" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Your Orders</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/style.css">

</head>

<body>

<jsp:include page="partials/navbar.jsp" />

<div class="content-wrapper">

    <div style="width:90%; max-width:1100px; margin:60px auto;">

        <h1 class="section-title">
            📦 Order History
        </h1>

        <%
            List<Order> orders =
                (List<Order>) request.getAttribute("orders");

            if (orders != null && !orders.isEmpty()) {

                for (Order order : orders) {
        %>

        <!-- ORDER CARD -->

        <div class="order-card">

            <div style="
                display:flex;
                justify-content:space-between;
                align-items:center;
                margin-bottom:20px;
                flex-wrap:wrap;
                gap:15px;
            ">

                <h2>
                    Order #<%= order.getOrderId() %>
                </h2>

                <span class="order-status">
                    <%= order.getOrderStatus() %>
                </span>

            </div>

            <p style="margin-bottom:12px;">
                <strong>Date:</strong>
                <%= order.getOrderDate() %>
            </p>

            <p style="margin-bottom:12px;">
                <strong>Payment:</strong>
                <%= order.getPaymentMethod() %>
            </p>

            <p style="margin-bottom:20px;">
                <strong>Total:</strong>
                ₹ <%= order.getTotalAmount() %>
            </p>

            <a href="${pageContext.request.contextPath}/order-details?id=<%= order.getOrderId() %>"
               class="buy-btn"
               style="text-decoration:none; display:inline-block;">

               View Details

            </a>

        </div>

        <%
                }

            } else {
        %>

        <!-- EMPTY ORDERS -->

        <div style="
            background:white;
            padding:60px;
            border-radius:20px;
            text-align:center;
            box-shadow:0 5px 18px rgba(0,0,0,0.08);
        ">

            <h2 style="margin-bottom:20px;">
                You haven't placed any orders yet.
            </h2>

            <a href="${pageContext.request.contextPath}/products"
               class="buy-btn"
               style="text-decoration:none;">

               Start Shopping

            </a>

        </div>

        <%
            }
        %>

    </div>

</div>

<jsp:include page="partials/footer.jsp" />

</body>
</html>