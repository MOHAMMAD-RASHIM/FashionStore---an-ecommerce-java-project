<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="com.fashionstore.model.Order" %>
<%@ page import="com.fashionstore.model.OrderItem" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Order Details</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/style.css">

<style>

.order-wrapper{
    min-height:80vh;
    width:90%;
    max-width:1200px;
    margin:50px auto;
}

.items-container{
    display:flex;
    flex-direction:column;
    gap:25px;
}

.item-card{
    background:white;
    padding:25px;
    border-radius:18px;
    box-shadow:0 4px 12px rgba(0,0,0,0.08);
}

.item-card h2{
    margin-bottom:15px;
}

.item-card p{
    margin-bottom:10px;
}

</style>

</head>

<body>

<jsp:include page="partials/navbar.jsp" />

<div class="content-wrapper">

<%
    Order order = (Order) request.getAttribute("order");

    List<OrderItem> items =
        (List<OrderItem>) request.getAttribute("orderItems");
%>

<div class="order-wrapper">

    <!-- BACK -->

    <a href="${pageContext.request.contextPath}/orders"
       style="
        text-decoration:none;
        color:orange;
        font-weight:600;
        font-size:18px;
    ">

        ← Back to Orders

    </a>

    <!-- ORDER INFO -->

    <div class="order-card" style="margin-top:30px;">

        <h1 style="margin-bottom:20px;">
            Order #<%= order.getOrderId() %>
        </h1>

        <p style="margin-bottom:10px;">
            <strong>Date:</strong>
            <%= order.getOrderDate() %>
        </p>

        <p style="margin-bottom:10px;">
            <strong>Status:</strong>

            <span class="order-status">
                <%= order.getOrderStatus() %>
            </span>
        </p>

        <p style="margin-bottom:10px;">
            <strong>Payment:</strong>
            <%= order.getPaymentMethod() %>
        </p>

        <p style="margin-bottom:10px;">
            <strong>Total:</strong>
            ₹ <%= order.getTotalAmount() %>
        </p>

    </div>

    <!-- ITEMS -->

    <h2 style="margin:40px 0 25px;">
        Ordered Items
    </h2>

    <div class="items-container">

        <%
            if(items != null){

                for(OrderItem item : items){
        %>

        <div class="item-card">

            <h2>
                <%= item.getProductName() %>
            </h2>

            <p>
                <strong>Quantity:</strong>
                <%= item.getQuantity() %>
            </p>

            <p>
                <strong>Price:</strong>
                ₹ <%= item.getUnitPrice() %>
            </p>

            <p>
                <strong>Subtotal:</strong>
                ₹ <%= item.getSubtotal() %>
            </p>

        </div>

        <%
                }
            }
        %>

    </div>

</div>

</div>

<jsp:include page="partials/footer.jsp" />

</body>
</html>