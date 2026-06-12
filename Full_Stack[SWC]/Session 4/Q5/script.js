const { useState } = React;

function App() {

    const [selectedProfile, setSelectedProfile] =
        useState(null);

    const profiles = [
        {
            id: 1,
            name: "Alex",
            avatar:
                "https://i.pravatar.cc/150?img=1"
        },
        {
            id: 2,
            name: "Emma",
            avatar:
                "https://i.pravatar.cc/150?img=2"
        },
        {
            id: 3,
            name: "John",
            avatar:
                "https://i.pravatar.cc/150?img=3"
        }
    ];

    const movies = [
        {
            id: 1,
            title: "Movie One",
            poster:
                "https://picsum.photos/300/170?random=1",
            trailer:
                "https://www.w3schools.com/html/mov_bbb.mp4"
        },
        {
            id: 2,
            title: "Movie Two",
            poster:
                "https://picsum.photos/300/170?random=2",
            trailer:
                "https://www.w3schools.com/html/movie.mp4"
        },
        {
            id: 3,
            title: "Movie Three",
            poster:
                "https://picsum.photos/300/170?random=3",
            trailer:
                "https://www.w3schools.com/html/mov_bbb.mp4"
        },
        {
            id: 4,
            title: "Movie Four",
            poster:
                "https://picsum.photos/300/170?random=4",
            trailer:
                "https://www.w3schools.com/html/movie.mp4"
        }
    ];

    if (!selectedProfile) {

        return (
            <ProfileSelection
                profiles={profiles}
                onSelect={
                    setSelectedProfile
                }
            />
        );
    }

    return (
        <div className="app">

            <header>
                <h1>NETFLIX</h1>

                <div className="profile-info">
                    {selectedProfile.name}
                </div>
            </header>

            <section className="hero">

                <div className="hero-content">

                    <h2>
                        Stranger Things
                    </h2>

                    <p>
                        Watch the latest
                        season now.
                    </p>

                    <button>
                        ▶ Play
                    </button>

                </div>

            </section>

            <section className="movie-row">

                <h2>
                    Trending Now
                </h2>

                <div className="movies">

                    {movies.map(movie => (

                        <MovieCard
                            key={movie.id}
                            movie={movie}
                        />

                    ))}

                </div>

            </section>

            <MobileNav />

        </div>
    );
}

function ProfileSelection({
    profiles,
    onSelect
}) {

    return (
        <div className="profiles">

            <h1>
                Who's Watching?
            </h1>

            <div className="profile-grid">

                {profiles.map(profile => (

                    <div
                        key={profile.id}
                        className="profile-card"
                        onClick={() =>
                            onSelect(
                                profile
                            )
                        }
                    >

                        <img
                            src={
                                profile.avatar
                            }
                            alt=""
                        />

                        <p>
                            {
                                profile.name
                            }
                        </p>

                    </div>
                ))}

            </div>

        </div>
    );
}

function MovieCard({ movie }) {

    const [hovered, setHovered] =
        useState(false);

    return (
        <div
            className="movie-card"
            onMouseEnter={() =>
                setHovered(true)
            }
            onMouseLeave={() =>
                setHovered(false)
            }
        >

            {hovered ? (

                <video
                    src={movie.trailer}
                    autoPlay
                    muted
                    loop
                />

            ) : (

                <img
                    src={movie.poster}
                    alt=""
                />

            )}

            <div className="movie-title">
                {movie.title}
            </div>

        </div>
    );
}

function MobileNav() {

    return (
        <nav className="mobile-nav">

            <button>
                🏠
                <span>Home</span>
            </button>

            <button>
                🔍
                <span>Search</span>
            </button>

            <button>
                🎬
                <span>New</span>
            </button>

            <button>
                ⬇️
                <span>Downloads</span>
            </button>

        </nav>
    );
}

ReactDOM
    .createRoot(
        document.getElementById(
            "root"
        )
    )
    .render(<App />);