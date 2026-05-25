<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Cart - FashionStore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .cart-item { display: flex; gap: 20px; background: white; padding: 15px; margin: 15px 0; border-radius: 10px; align-items: center; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        .cart-item img { width: 120px; height: 120px; object-fit: cover; border-radius: 8px; }
        .qty-controls { display: flex; align-items: center; gap: 10px; margin: 10px 0; }
        .qty-btn { padding: 5px 12px; background: #eee; color: #333; text-decoration: none; border-radius: 4px; font-weight: bold; border: 1px solid #ccc; }
        .qty-btn:hover { background: orange; color: white; }
    </style>
</head>
<body>

    <jsp:include page="partials/navbar.jsp" />

    <div class="content-wrapper">
        <div class="container">
            <h2>🛒 Your Shopping Cart</h2>

            <%
            List<Map<String, Object>> cartData = (List<Map<String, Object>>) request.getAttribute("cartData");
            if (cartData != null && !cartData.isEmpty()) {
                for (Map<String, Object> item : cartData) {
            %>
            <div class="cart-item">
                <img src="${pageContext.request.contextPath}/assets/images/<%= item.get("image") %>" alt="product">
                
                <div class="details">
                    <h3><%= item.get("name") %></h3>
                    <p>Size: <%= item.get("size") %></p>
                    
                    <div class="qty-controls">
                        <a class="qty-btn" href="${pageContext.request.contextPath}/update-cart?id=<%= item.get("cartItemId") %>&action=dec">-</a>
                        <span><strong><%= item.get("qty") %></strong></span>
                        <a class="qty-btn" href="${pageContext.request.contextPath}/update-cart?id=<%= item.get("cartItemId") %>&action=inc">+</a>
                    </div>

                    <p>Price: ₹ <%= item.get("price") %></p>
                    <p><strong>Subtotal: ₹ <%= item.get("subtotal") %></strong></p>

                    <a href="${pageContext.request.contextPath}/update-cart?id=<%= item.get("cartItemId") %>&action=remove" 
                       style="color:red; text-decoration:none; font-size:14px;">❌ Remove Item</a>
                </div>
            </div>
            <% } %>

            <div style="text-align: right; margin-top: 30px;">
                <h3 style="font-size: 24px;">Total Amount: ₹ <%= request.getAttribute("total") %></h3>
                <br>
                <a href="${pageContext.request.contextPath}/checkout" 
                   style="padding: 12px 25px; background: green; color: white; text-decoration: none; border-radius: 5px; font-weight: bold;">
                   Proceed to Checkout
                </a>
            </div>
            <% } else { %>
                <div style="text-align:center; padding: 50px;">
                    <p style="font-size: 20px;">Your cart is empty 🛒</p>
                    <a href="${pageContext.request.contextPath}/products" style="color:orange;">Continue Shopping</a>
                </div>
            <% } %>
        </div>
    </div>

    <jsp:include page="partials/footer.jsp" />
</body>
</html>