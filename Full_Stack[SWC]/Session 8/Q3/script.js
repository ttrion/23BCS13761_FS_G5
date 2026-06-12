const products = [
    {
        id: 1,
        name: "iPhone 15",
        category: "Electronics",
        price: 80000,
        rating: 5
    },
    {
        id: 2,
        name: "Samsung TV",
        category: "Electronics",
        price: 45000,
        rating: 4
    },
    {
        id: 3,
        name: "T-Shirt",
        category: "Fashion",
        price: 1000,
        rating: 4
    },
    {
        id: 4,
        name: "Jeans",
        category: "Fashion",
        price: 2000,
        rating: 3
    },
    {
        id: 5,
        name: "JavaScript Book",
        category: "Books",
        price: 600,
        rating: 5
    },
    {
        id: 6,
        name: "Novel",
        category: "Books",
        price: 400,
        rating: 4
    }
];

function saveFilters() {

    const filters = {
        category: document.getElementById("category").value,
        price: document.getElementById("price").value,
        rating: document.getElementById("rating").value
    };

    localStorage.setItem(
        "productFilters",
        JSON.stringify(filters)
    );
}

function loadFilters() {

    const savedFilters = JSON.parse(
        localStorage.getItem("productFilters")
    );

    if (!savedFilters) return;

    document.getElementById("category").value =
        savedFilters.category;

    document.getElementById("price").value =
        savedFilters.price;

    document.getElementById("rating").value =
        savedFilters.rating;
}

function applyFilters() {

    saveFilters();

    const category =
        document.getElementById("category").value;

    const price =
        document.getElementById("price").value;

    const rating =
        document.getElementById("rating").value;

    let filteredProducts = products.filter(product => {

        let categoryMatch =
            !category ||
            product.category === category;

        let priceMatch =
            !price ||
            product.price <= Number(price);

        let ratingMatch =
            !rating ||
            product.rating >= Number(rating);

        return (
            categoryMatch &&
            priceMatch &&
            ratingMatch
        );
    });

    renderProducts(filteredProducts);
}

function renderProducts(productData) {

    const productList =
        document.getElementById("productList");

    productList.innerHTML = "";

    if (productData.length === 0) {
        productList.innerHTML =
            "<h3>No products found</h3>";
        return;
    }

    productData.forEach(product => {

        productList.innerHTML += `
            <div class="product-card">
                <h3>${product.name}</h3>
                <p>Category: ${product.category}</p>
                <p>Price: ₹${product.price}</p>
                <p>Rating: ⭐ ${product.rating}</p>
            </div>
        `;
    });
}

document
    .getElementById("applyFilter")
    .addEventListener("click", applyFilters);

loadFilters();
applyFilters();