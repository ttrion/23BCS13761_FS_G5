const {
    useState,
    useEffect,
    useContext,
    createContext
} = React;

const ThemeContext = createContext();

function ThemeProvider({ children }) {

    const [darkMode, setDarkMode] = useState(
        JSON.parse(
            localStorage.getItem("darkMode")
        ) || false
    );

    useEffect(() => {

        localStorage.setItem(
            "darkMode",
            JSON.stringify(darkMode)
        );

    }, [darkMode]);

    return (
        <ThemeContext.Provider
            value={{
                darkMode,
                setDarkMode
            }}
        >
            {children}
        </ThemeContext.Provider>
    );
}

function Stories() {

    const stories = [
        "Alex",
        "John",
        "Emma",
        "Sophia",
        "Liam",
        "Noah",
        "Ava",
        "Mia"
    ];

    return (
        <div className="stories">

            {stories.map((story, index) => (

                <div
                    className="story"
                    key={index}
                >
                    <div className="story-circle">
                        {story.charAt(0)}
                    </div>

                    <span>{story}</span>
                </div>

            ))}

        </div>
    );
}

function Post({ post }) {

    return (
        <div className="post">

            <div className="post-header">
                {post.username}
            </div>

            <img
                src={post.image}
                alt="post"
            />

            <div className="post-body">
                <p>
                    ❤️ {post.likes} Likes
                </p>

                <p>
                    {post.caption}
                </p>
            </div>

        </div>
    );
}

function Feed() {

    const [posts, setPosts] = useState([]);
    const [page, setPage] = useState(1);

    const generatePosts = (pageNumber) => {

        const newPosts = [];

        for (let i = 1; i <= 5; i++) {

            newPosts.push({
                id:
                    pageNumber * 100 + i,

                username:
                    "user_" +
                    (pageNumber * 5 + i),

                likes:
                    Math.floor(
                        Math.random() * 5000
                    ),

                caption:
                    "Beautiful day! 🌞",

                image:
                    `https://picsum.photos/500/300?random=${
                        pageNumber * 10 + i
                    }`
            });
        }

        return newPosts;
    };

    useEffect(() => {

        setPosts(
            generatePosts(1)
        );

    }, []);

    useEffect(() => {

        function handleScroll() {

            if (
                window.innerHeight +
                window.scrollY >=
                document.body.offsetHeight - 100
            ) {

                const nextPage =
                    page + 1;

                setPosts(prev => [
                    ...prev,
                    ...generatePosts(
                        nextPage
                    )
                ]);

                setPage(nextPage);
            }
        }

        window.addEventListener(
            "scroll",
            handleScroll
        );

        return () =>
            window.removeEventListener(
                "scroll",
                handleScroll
            );

    }, [page]);

    return (
        <div>

            {posts.map(post => (

                <Post
                    key={post.id}
                    post={post}
                />

            ))}

        </div>
    );
}

function App() {

    const {
        darkMode,
        setDarkMode
    } = useContext(ThemeContext);

    return (
        <div
            className={
                darkMode
                    ? "app dark"
                    : "app light"
            }
        >

            <header>

                <h1>
                    Instagram Clone
                </h1>

                <button
                    onClick={() =>
                        setDarkMode(
                            !darkMode
                        )
                    }
                >
                    {darkMode
                        ? "☀ Light"
                        : "🌙 Dark"}
                </button>

            </header>

            <Stories />

            <Feed />

        </div>
    );
}

function Root() {

    return (
        <ThemeProvider>
            <App />
        </ThemeProvider>
    );
}

ReactDOM
    .createRoot(
        document.getElementById("root")
    )
    .render(<Root />);