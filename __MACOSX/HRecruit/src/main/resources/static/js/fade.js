document.addEventListener("DOMContentLoaded", function () {
    const sections = document.querySelectorAll(".fade-in");

    const observer = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add("fade-in-visible");
                observer.unobserve(entry.target); // Stop observing once faded in
            }
        });
    }, {
        threshold: 0.3, // Trigger when 30% of the section is visible
    });

    sections.forEach(section => {
        observer.observe(section);
    });
});
