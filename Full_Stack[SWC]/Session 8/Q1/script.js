let cart = [];

function addToCart(id, name, price) {

    const existingItem = cart.find(item => item.id === id);

    if (existingItem) {
        existingItem.quantity++;
    } else {
        cart.push({
            id,
            name,
            price,
            quantity: 1
        });
    }

    renderCart();
}

function updateQuantity(id, change) {

    const item = cart.find(item => item.id === id);

    if (!item) return;

    item.quantity += change;

    if (item.quantity <= 0) {
        cart = cart.filter(product => product.id !== id);
    }

    renderCart();
}

function removeItem(id) {

    cart = cart.filter(item => item.id !== id);

    renderCart();
}

function renderCart() {

    const cartItems = document.getElementById("cartItems");
    const totalPrice = document.getElementById("totalPrice");

    cartItems.innerHTML = "";

    let total = 0;

    cart.forEach(item => {

        const subtotal = item.price * item.quantity;
        total += subtotal;

        cartItems.innerHTML += `
            <tr>
                <td>${item.name}</td>
                <td>₹${item.price}</td>
                <td>
                    <button onclick="updateQuantity(${item.id}, -1)">-</button>
                    ${item.quantity}
                    <button onclick="updateQuantity(${item.id}, 1)">+</button>
                </td>
                <td>₹${subtotal}</td>
                <td>
                    <button onclick="removeItem(${item.id})">
                        Remove
                    </button>
                </td>
            </tr>
        `;
    });

    totalPrice.textContent = total;
}