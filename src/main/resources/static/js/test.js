let currentIndex = 0;

function moveCarousel(direction) {
    const carousel = document.querySelector(".carousel");
    const slides = document.querySelectorAll(".carousel img");
    const slideWidth = slides[0].offsetWidth + 20; // Includes gap

    const visibleSlides = Math.floor(carousel.offsetWidth / slideWidth);
    const maxIndex = slides.length - visibleSlides;

    currentIndex += direction;
    if (currentIndex < 0) currentIndex = 0;
    if (currentIndex > maxIndex) currentIndex = maxIndex;

    carousel.style.transform = `translateX(${-currentIndex * slideWidth}px)`;
}

// Ensure window resizing is handled to reset the view.
window.addEventListener("resize", () => {
    currentIndex = 0;
    const carousel = document.querySelector(".carousel");
    carousel.style.transform = `translateX(0px)`;
});
