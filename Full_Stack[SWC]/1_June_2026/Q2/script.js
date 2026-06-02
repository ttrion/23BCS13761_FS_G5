const itemList = document.getElementById("itemList");

for (let i = 1; i <= 1000; i++) {
    const li = document.createElement("li");
    li.className = "item";
    li.innerHTML = `
        <span>Product ${i}</span>
        <button class="delete-btn">Delete</button>
    `;
    itemList.appendChild(li);
}

itemList.addEventListener("click", function (e) {
    if (e.target.classList.contains("delete-btn")) {
        e.target.parentElement.remove();
    }
});