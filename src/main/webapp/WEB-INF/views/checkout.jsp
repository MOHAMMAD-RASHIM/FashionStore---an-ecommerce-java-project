<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Checkout</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">

<style>
.container {
    width: 400px;
    margin: 60px auto;
    background: white;
    padding: 25px;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

h2 {
    text-align: center;
    margin-bottom: 20px;
}

input, select {
    width: 100%;
    padding: 10px;
    margin: 10px 0;
    border-radius: 6px;
    border: 1px solid #ccc;
}

button {
    background: orange;
    border: none;
    padding: 12px;
    width: 100%;
    color: white;
    font-size: 16px;
    border-radius: 6px;
    cursor: pointer;
}

button:hover {
    background: darkorange;
}

.back {
    display: block;
    margin-top: 15px;
    text-align: center;
    text-decoration: none;
}
</style>
</head>

<body>

<jsp:include page="partials/navbar.jsp" />

<div class="container">
    <h2>🧾 Checkout</h2>

    <form action="<%= request.getContextPath() %>/place-order" method="post">

        <label>Delivery Address</label>
        <input type="text" name="address" placeholder="Enter your address" required>

        <label>Payment Method</label>
        <select name="payment">
            <option value="COD">Cash on Delivery</option>
        </select>

        <button type="submit">Place Order</button>
    </form>

    <a href="<%= request.getContextPath() %>/cart" class="back">⬅ Back to Cart</a>
</div>

<jsp:include page="partials/footer.jsp" />

</body>
</html>