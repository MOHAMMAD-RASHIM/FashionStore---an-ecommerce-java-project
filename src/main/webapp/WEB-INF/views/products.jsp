<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.fashionstore.model.Product" %>

<!DOCTYPE html>
<html>
<head>

<title>All Products</title>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/assets/css/style.css">

<link rel="stylesheet"
href="<%= request.getContextPath() %>/assets/css/home.css">

</head>

<body>

<jsp:include page="partials/navbar.jsp" />

<div class="content-wrapper">

    <!-- SEARCH -->
    <div class="home-search">

        <form action="<%= request.getContextPath() %>/products"
              method="get">

            <input type="text"
                   name="search"
                   placeholder="Search for products...">

            <button type="submit">
                Search
            </button>

        </form>

    </div>

    <!-- FILTERS -->
    <div class="filter-section">

        <a href="<%= request.getContextPath() %>/products"
           class="filter-btn">
           All
        </a>

        <a href="<%= request.getContextPath() %>/products?category=1"
           class="filter-btn">
           Men
        </a>

        <a href="<%= request.getContextPath() %>/products?category=2"
           class="filter-btn">
           Women
        </a>

        <a href="<%= request.getContextPath() %>/products?category=3"
           class="filter-btn">
           Kids
        </a>

    </div>

    <h2 class="section-title">
        All Products
    </h2>

    <div class="featured-grid">

        <%
            List<Product> products =
                (List<Product>) request.getAttribute("products");

            if(products != null && !products.isEmpty()){

                for(Product p : products){
        %>

        <a href="<%= request.getContextPath() %>/product?id=<%= p.getProductId() %>"
           class="product-card">

            <img src="<%= request.getContextPath() %>/assets/images/<%= p.getImageUrl() %>"
                 class="product-img">

            <div class="product-info">

                <h3><%= p.getProductName() %></h3>

                <p class="price">
                    ₹ <%= p.getPrice() %>
                </p>

                <span class="view-btn">
                    View Details
                </span>

            </div>

        </a>

        <%
                }
            }
        %>

    </div>

</div>

<jsp:include page="partials/footer.jsp" />

</body>
</html>