const FETCH_REQUEST = "FETCH_REQUEST";
const FETCH_SUCCESS = "FETCH_SUCCESS";
const FETCH_ERROR = "FETCH_ERROR";

const fetchRequest = () => ({
    type: FETCH_REQUEST
});

const fetchSuccess = (data) => ({
    type: FETCH_SUCCESS,
    payload: data
});

const fetchError = (error) => ({
    type: FETCH_ERROR,
    payload: error
});

const initialState = {
    loading: false,
    data: [],
    error: ""
};

function reducer(state = initialState, action) {

    switch (action.type) {

        case FETCH_REQUEST:
            return {
                ...state,
                loading: true,
                error: ""
            };

        case FETCH_SUCCESS:
            return {
                loading: false,
                data: action.payload,
                error: ""
            };

        case FETCH_ERROR:
            return {
                loading: false,
                data: [],
                error: action.payload
            };

        default:
            return state;
    }
}

const store = Redux.createStore(
    reducer,
    Redux.applyMiddleware(ReduxThunk.default)
);

function fetchPosts() {

    return async function (dispatch) {

        dispatch(fetchRequest());

        try {

            const response = await fetch(
                "https://jsonplaceholder.typicode.com/posts"
            );

            const data = await response.json();

            dispatch(fetchSuccess(data.slice(0, 10)));

        } catch (error) {

            dispatch(fetchError("Failed to fetch data"));
        }
    };
}

function render() {

    const state = store.getState();

    const status = document.getElementById("status");
    const container = document.getElementById("dataContainer");

    container.innerHTML = "";

    if (state.loading) {
        status.textContent = "Loading...";
        return;
    }

    if (state.error) {
        status.textContent = state.error;
        return;
    }

    status.textContent = "";

    state.data.forEach(post => {

        const card = document.createElement("div");

        card.className = "card";

        card.innerHTML = `
            <h3>${post.title}</h3>
            <p>${post.body}</p>
        `;

        container.appendChild(card);
    });
}

store.subscribe(render);

document.getElementById("fetchBtn")
    .addEventListener("click", () => {
        store.dispatch(fetchPosts());
    });

render();