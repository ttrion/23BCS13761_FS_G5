const products = [
    {
        name: "iPhone 15",
        category: "Electronics",
        price: 799,
        image: "https://via.placeholder.com/250"
    },
    {
        name: "Samsung S24",
        category: "Electronics",
        price: 899,
        image: "https://via.placeholder.com/250"
    },
    {
        name: "T-Shirt",
        category: "Fashion",
        price: 50,
        image: "https://via.placeholder.com/250"
    },
    {
        name: "Sofa",
        category: "Home",
        price: 500,
        image: "https://via.placeholder.com/250"
    }
];

const productGrid = document.getElementById("productGrid");
const searchInput = document.getElementById("searchInput");
const priceRange = document.getElementById("priceRange");
const priceValue = document.getElementById("priceValue");

function renderProducts(items) {

    productGrid.innerHTML = "";

    items.forEach(product => {

        const card = document.createElement("div");

        card.classList.add("card");

        card.innerHTML = `
            <img src="${product.image}">
            <h3>${product.name}</h3>
            <p>${product.category}</p>
            <p class="price">$${product.price}</p>
            <button>Add to Cart</button>
        `;

        productGrid.appendChild(card);
    });
}

function filterProducts() {

    const searchText =
        searchInput.value.toLowerCase();

    const maxPrice =
        Number(priceRange.value);

    const filtered = products.filter(product => {

        return (
            product.name.toLowerCase().includes(searchText) &&
            product.price <= maxPrice
        );
    });

    renderProducts(filtered);
}

searchInput.addEventListener("input", filterProducts);

priceRange.addEventListener("input", () => {

    priceValue.textContent = priceRange.value;

    filterProducts();
});

renderProducts(products);