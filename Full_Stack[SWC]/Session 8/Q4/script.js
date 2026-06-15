const { useState, useEffect } = React;

function App() {
    const [coins, setCoins] = useState([]);
    const [search, setSearch] = useState("");
    const [filter, setFilter] = useState("all");
    const [loading, setLoading] = useState(true);

    const fetchCoins = async () => {
        try {
            const response = await fetch(
                "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20&page=1&sparkline=false"
            );

            const data = await response.json();
            setCoins(data);
            setLoading(false);
        } catch (error) {
            console.error("Error fetching data:", error);
        }
    };

    useEffect(() => {
        fetchCoins();

        const interval = setInterval(() => {
            fetchCoins();
        }, 10000);

        return () => clearInterval(interval);
    }, []);

    const filteredCoins = coins.filter(coin => {
        const matchesSearch =
            coin.name
                .toLowerCase()
                .includes(search.toLowerCase());

        if (filter === "gainers") {
            return (
                matchesSearch &&
                coin.price_change_percentage_24h > 0
            );
        }

        if (filter === "losers") {
            return (
                matchesSearch &&
                coin.price_change_percentage_24h < 0
            );
        }

        return matchesSearch;
    });

    return (
        <div className="container">
            <h2>Crypto Price Tracker</h2>

            <div className="controls">
                <input
                    type="text"
                    placeholder="Search cryptocurrency..."
                    value={search}
                    onChange={(e) =>
                        setSearch(e.target.value)
                    }
                />

                <select
                    value={filter}
                    onChange={(e) =>
                        setFilter(e.target.value)
                    }
                >
                    <option value="all">
                        All Coins
                    </option>

                    <option value="gainers">
                        Top Gainers
                    </option>

                    <option value="losers">
                        Top Losers
                    </option>
                </select>
            </div>

            {loading ? (
                <p className="loading">
                    Loading cryptocurrency data...
                </p>
            ) : (
                <div className="crypto-list">
                    {filteredCoins.map(coin => (
                        <div
                            className="crypto-card"
                            key={coin.id}
                        >
                            <div className="coin-info">
                                <img
                                    src={coin.image}
                                    alt={coin.name}
                                />

                                <div>
                                    <div className="coin-name">
                                        {coin.name}
                                    </div>

                                    <div className="coin-symbol">
                                        {coin.symbol}
                                    </div>
                                </div>
                            </div>

                            <div>
                                <div className="price">
                                    $
                                    {coin.current_price.toLocaleString()}
                                </div>

                                <div
                                    className={
                                        coin.price_change_percentage_24h >= 0
                                            ? "positive"
                                            : "negative"
                                    }
                                >
                                    {coin.price_change_percentage_24h.toFixed(
                                        2
                                    )}
                                    %
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

ReactDOM.createRoot(
    document.getElementById("root")
).render(<App />);