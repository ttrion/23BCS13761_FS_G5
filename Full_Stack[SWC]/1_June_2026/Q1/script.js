const tabs = document.querySelectorAll(".tab");
const contents = document.querySelectorAll(".content");

tabs.forEach(tab => {
    tab.addEventListener("click", () => {

        tabs.forEach(t => t.classList.remove("active"));

        contents.forEach(content => {
            content.classList.remove("active");
        });

        tab.classList.add("active");

        const tabId = tab.getAttribute("data-tab");
        document.getElementById(tabId).classList.add("active");
    });
});