document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector(".signup-form");
    const emailInput = document.querySelector("#email");
    const passwordInput = document.querySelector("#password");
    const confirmPasswordInput = document.querySelector("#confirm-password");

    form.addEventListener("submit", (e) => {
        e.preventDefault(); // Prevent form submission
        clearErrors(); // Clear previous errors

        let errors = [];

        // Email validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(emailInput.value)) {
            errors.push({ field: emailInput, message: "Invalid email format. Please include '@' and a domain." });
        }

        // Password validation
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        if (!passwordRegex.test(passwordInput.value)) {
            errors.push({
                field: passwordInput,
                message: "Password must have at least 8 characters, 1 uppercase, 1 lowercase, 1 number, and 1 special character."
            });
        }

        // Confirm Password
        if (passwordInput.value !== confirmPasswordInput.value) {
            errors.push({ field: confirmPasswordInput, message: "Passwords do not match." });
        }

        // Display Errors
        if (errors.length > 0) {
            displayErrors(errors);
        } else {
            form.submit(); // Submit the form if no errors
        }
    });

    // Clear previous error states
    function clearErrors() {
        const errorMessages = document.querySelectorAll(".error-message");
        errorMessages.forEach((msg) => msg.remove());

        const errorFields = document.querySelectorAll(".error");
        errorFields.forEach((field) => field.classList.remove("error"));
    }

    // Display errors below fields
    function displayErrors(errors) {
        errors.forEach((error) => {
            const field = error.field;
            const errorMsgContainer = field.parentNode.querySelector(".error-message");
            field.classList.add("error"); // Add error styling
            errorMsgContainer.textContent = error.message; // Update the error message
        });
    }
    
});
