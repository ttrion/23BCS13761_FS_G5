const { useState, useRef, useEffect } = React;

function App() {

    const songs = [
        {
            id: 1,
            title: "SoundHelix Song 1",
            artist: "Artist 1",
            url: "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        },
        {
            id: 2,
            title: "SoundHelix Song 2",
            artist: "Artist 2",
            url: "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3"
        },
        {
            id: 3,
            title: "SoundHelix Song 3",
            artist: "Artist 3",
            url: "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3"
        }
    ];

    const [currentIndex, setCurrentIndex] = useState(0);
    const [isPlaying, setIsPlaying] = useState(false);
    const [volume, setVolume] = useState(1);
    const [recentlyPlayed, setRecentlyPlayed] = useState([]);

    const audioRef = useRef(null);

    useEffect(() => {

        audioRef.current.volume = volume;

    }, [volume]);

    useEffect(() => {

        if (isPlaying) {
            audioRef.current.play();
        } else {
            audioRef.current.pause();
        }

    }, [isPlaying, currentIndex]);

    const playPause = () => {

        setIsPlaying(!isPlaying);
    };

    const nextSong = () => {

        const next =
            (currentIndex + 1) % songs.length;

        addToRecent(songs[currentIndex]);

        setCurrentIndex(next);
        setIsPlaying(true);
    };

    const previousSong = () => {

        const prev =
            (currentIndex - 1 + songs.length) %
            songs.length;

        addToRecent(songs[currentIndex]);

        setCurrentIndex(prev);
        setIsPlaying(true);
    };

    const selectSong = (index) => {

        addToRecent(songs[currentIndex]);

        setCurrentIndex(index);
        setIsPlaying(true);
    };

    const addToRecent = (song) => {

        setRecentlyPlayed(prev => {

            const updated = [
                song,
                ...prev.filter(
                    item => item.id !== song.id
                )
            ];

            return updated.slice(0, 5);
        });
    };

    return (
        <div className="player">

            <h1>Spotify Music Player</h1>

            <div className="now-playing">

                <h2>
                    {songs[currentIndex].title}
                </h2>

                <p>
                    {songs[currentIndex].artist}
                </p>

            </div>

            <audio
                ref={audioRef}
                src={songs[currentIndex].url}
                onEnded={nextSong}
            />

            <div className="controls">

                <button onClick={previousSong}>
                    ⏮
                </button>

                <button onClick={playPause}>
                    {isPlaying ? "Pause" : "Play"}
                </button>

                <button onClick={nextSong}>
                    ⏭
                </button>

            </div>

            <div className="volume">

                <label>
                    Volume:
                </label>

                <input
                    type="range"
                    min="0"
                    max="1"
                    step="0.1"
                    value={volume}
                    onChange={(e) =>
                        setVolume(
                            Number(e.target.value)
                        )
                    }
                />

            </div>

            <div className="queue">

                <h2>Song Queue</h2>

                {songs.map((song, index) => (
                    <div
                        key={song.id}
                        className="song"
                        onClick={() =>
                            selectSong(index)
                        }
                    >
                        {song.title}
                    </div>
                ))}

            </div>

            <div className="recent">

                <h2>Recently Played</h2>

                {recentlyPlayed.length === 0 &&
                    <p>No songs played yet</p>
                }

                {recentlyPlayed.map(song => (
                    <div
                        key={song.id}
                        className="song"
                    >
                        {song.title}
                    </div>
                ))}

            </div>

        </div>
    );
}

ReactDOM
    .createRoot(
        document.getElementById("root")
    )
    .render(<App />);