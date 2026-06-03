const feed = document.getElementById("feed");
const loader = document.getElementById("loader");

let page = 1;
let isLoading = false;

// Create items
function renderItems(count = 10) {
    for (let i = 1; i <= count; i++) {
        const div = document.createElement("div");
        div.classList.add("post");
        div.textContent = `Post ${(page - 1) * count + i}`;
        feed.appendChild(div);
    }
}

// Simulated API Call
function fetchData() {
    if (isLoading) return; // Prevent duplicate calls

    isLoading = true;
    loader.classList.remove("hidden");

    setTimeout(() => {
        renderItems(10);

        page++;
        isLoading = false;
        loader.classList.add("hidden");
    }, 1500);
}

// Initial Data
fetchData();

// Infinite Scroll
window.addEventListener("scroll", () => {
    const scrollPosition =
        window.innerHeight + window.scrollY;

    const pageHeight =
        document.documentElement.scrollHeight;

    // Trigger when 100px from bottom
    if (scrollPosition >= pageHeight - 100) {
        fetchData();
    }
});