<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.fashionstore.model.Product" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>FashionStore | Home</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/home.css">

</head>

<body>

<jsp:include page="partials/navbar.jsp" />

<div class="content-wrapper">

    <!-- HERO -->

    <section class="hero-section">

        <div class="hero-content">

            <h1>Discover Fashion That Defines You</h1>

            <p>
                Explore premium collections for Men, Women & Kids
            </p>

            <div class="home-search">

                <form action="${pageContext.request.contextPath}/products"
                      method="get">

                    <input type="text"
                           name="search"
                           placeholder="Search products...">

                    <button type="submit">
                        Search
                    </button>

                </form>

            </div>

        </div>

    </section>

    <!-- PRODUCTS -->

    <section class="featured-section">

        <h2 class="section-title">
            Featured Products
        </h2>

        <div class="featured-grid">

            <%
                List<Product> products =
                    (List<Product>) request.getAttribute("products");

                if(products != null){

                    int count = 0;

                    for(Product p : products){

                        if(count == 4){
                            break;
                        }

                        count++;
            %>

            <a href="${pageContext.request.contextPath}/product?id=<%= p.getProductId() %>"
               class="product-card">

                <img src="${pageContext.request.contextPath}/assets/images/<%= p.getImageUrl() %>"
                     class="product-img">

                <div class="product-info">

                    <h3>
                        <%= p.getProductName() %>
                    </h3>

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

    </section>

</div>

<jsp:include page="partials/footer.jsp" />

</body>
</html>