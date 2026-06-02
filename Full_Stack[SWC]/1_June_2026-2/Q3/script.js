const products = [
    "iPhone 15",
    "Samsung Galaxy S24",
    "OnePlus 12",
    "MacBook Pro",
    "Dell XPS",
    "HP Pavilion",
    "AirPods Pro",
    "Sony Headphones",
    "Apple Watch",
    "iPad Air"
];

const searchInput = document.getElementById("searchInput");
const results = document.getElementById("results");

// Display items
function renderItems(items) {
    results.innerHTML = "";

    items.forEach(item => {
        const li = document.createElement("li");
        li.textContent = item;
        results.appendChild(li);
    });
}

// Debounce Function
function debounce(fn, delay) {
    let timer;

    return function (...args) {
        clearTimeout(timer);

        timer = setTimeout(() => {
            fn(...args);
        }, delay);
    };
}

// Search Logic
function filterItems() {
    const searchText = searchInput.value.toLowerCase();

    const filtered = products.filter(product =>
        product.toLowerCase().includes(searchText)
    );

    renderItems(filtered);
}

// Debounced Search
const debouncedSearch = debounce(filterItems, 300);

searchInput.addEventListener("input", debouncedSearch);

// Initial Render
renderItems(products);