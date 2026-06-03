const tasks = document.querySelectorAll(".task");
const columns = document.querySelectorAll(".task-list");

let draggedTask = null;

tasks.forEach(task => {
    task.addEventListener("dragstart", () => {
        draggedTask = task;
    });
});

columns.forEach(column => {
    column.addEventListener("dragover", (e) => {
        e.preventDefault();
    });

    column.addEventListener("drop", () => {
        column.appendChild(draggedTask);
    });
});