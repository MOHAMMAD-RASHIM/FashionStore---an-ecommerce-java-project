<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.fashionstore.model.Product" %>

<%
Product p = (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<head>

<title><%= p.getProductName() %></title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/home.css">

</head>

<body>

<jsp:include page="partials/navbar.jsp" />

<div class="content-wrapper">

    <div class="product-detail-wrapper">

        <div class="product-detail-card">

            <div class="detail-image-section">

                <img src="${pageContext.request.contextPath}/assets/images/<%= p.getImageUrl() %>"
                     class="detail-image">

            </div>

            <div class="detail-info-section">

                <h1><%= p.getProductName() %></h1>

                <p class="detail-price">
                    ₹ <%= p.getPrice() %>
                </p>

                <p class="detail-description">
                    <%= p.getDescription() %>
                </p>

                <form action="${pageContext.request.contextPath}/add-to-cart"
                      method="post">

                    <input type="hidden"
                           name="id"
                           value="<%= p.getProductId() %>">

                    <label>Select Size</label>

                    <select name="size"
                            class="size-select">

                        <option>S</option>
                        <option selected>M</option>
                        <option>L</option>
                        <option>XL</option>

                    </select>

                    <button type="submit"
                            class="buy-btn">
                        Add To Cart
                    </button>

                </form>

            </div>

        </div>

    </div>

</div>

<jsp:include page="partials/footer.jsp" />

</body>
</html>