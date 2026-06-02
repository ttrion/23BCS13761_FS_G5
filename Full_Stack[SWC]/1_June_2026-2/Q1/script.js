const form = document.getElementById("loginForm");

form.addEventListener("submit", function (e) {
    e.preventDefault();

    let isValid = true;

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    document.getElementById("emailError").textContent = "";
    document.getElementById("passwordError").textContent = "";

    // Required Validation
    if (email === "") {
        document.getElementById("emailError").textContent =
            "Email is required";
        isValid = false;
    }

    if (password === "") {
        document.getElementById("passwordError").textContent =
            "Password is required";
        isValid = false;
    }

    // Email Validation
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (email && !emailPattern.test(email)) {
        document.getElementById("emailError").textContent =
            "Enter a valid email";
        isValid = false;
    }

    // Password Validation
    const passwordPattern =
        /^(?=.*[!@#$%^&*])(?=.*[A-Za-z])(?=.*\d).{8,}$/;

    if (password && !passwordPattern.test(password)) {
        document.getElementById("passwordError").textContent =
            "Password must be 8+ chars, include a number and special character";
        isValid = false;
    }

    if (isValid) {
        alert("Form Submitted Successfully!");
    }
});