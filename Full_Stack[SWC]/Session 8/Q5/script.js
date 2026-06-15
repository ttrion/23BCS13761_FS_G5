const { useState } = React;

function App() {
    const productsData = [
        {
            id: 1,
            name: "Wireless Headphones",
            price: 79,
            image:
                "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400"
        },
        {
            id: 2,
            name: "Smart Watch",
            price: 129,
            image:
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400"
        },
        {
            id: 3,
            name: "Laptop",
            price: 899,
            image:
                "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=400"
        },
        {
            id: 4,
            name: "Gaming Mouse",
            price: 49,
            image:
                "https://images.unsplash.com/photo-1527814050087-3793815479db?w=400"
        },
        {
            id: 5,
            name: "Bluetooth Speaker",
            price: 99,
            image:
                "https://images.unsplash.com/photo-1589003077984-894e133dabab?w=400"
        },
        {
            id: 6,
            name: "Keyboard",
            price: 69,
            image:
                "https://images.unsplash.com/photo-1511467687858-23d96c32e4ae?w=400"
        }
    ];

    const [cart, setCart] = useState([]);
    const [search, setSearch] = useState("");

    const addToCart = product => {
        setCart([...cart, product]);
    };

    const filteredProducts = productsData.filter(product =>
        product.name
            .toLowerCase()
            .includes(search.toLowerCase())
    );

    const total = cart.reduce(
        (sum, item) => sum + item.price,
        0
    );

    return (
        <>
            <nav className="navbar">
                <div className="logo">amazon</div>

                <div className="search-box">
                    <input
                        type="text"
                        placeholder="Search Amazon"
                        value={search}
                        onChange={e =>
                            setSearch(e.target.value)
                        }
                    />
                </div>

                <div className="nav-links">
                    <div className="nav-item">
                        Account
                    </div>

                    <div className="nav-item">
                        Orders
                    </div>

                    <div className="cart">
                        Cart ({cart.length})
                    </div>
                </div>
            </nav>

            <div className="container">
                <div className="products">
                    {filteredProducts.map(product => (
                        <div
                            className="product-card"
                            key={product.id}
                        >
                            <img
                                src={product.image}
                                alt={product.name}
                            />

                            <h3>{product.name}</h3>

                            <div className="price">
                                ${product.price}
                            </div>

                            <button
                                onClick={() =>
                                    addToCart(product)
                                }
                            >
                                Add to Cart
                            </button>
                        </div>
                    ))}
                </div>

                <div className="checkout">
                    <h2>Checkout Preview</h2>

                    {cart.length === 0 ? (
                        <p className="empty">
                            Cart is empty
                        </p>
                    ) : (
                        <>
                            {cart.map((item, index) => (
                                <div
                                    className="cart-item"
                                    key={index}
                                >
                                    <span>
                                        {item.name}
                                    </span>

                                    <span>
                                        ${item.price}
                                    </span>
                                </div>
                            ))}

                            <div className="total">
                                Total: ${total}
                            </div>

                            <button className="checkout-btn">
                                Proceed to Checkout
                            </button>
                        </>
                    )}
                </div>
            </div>
        </>
    );
}

ReactDOM.createRoot(
    document.getElementById("root")
).render(<App />);