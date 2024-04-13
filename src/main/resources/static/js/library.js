document.addEventListener("DOMContentLoaded", function () {
  const navLinks = document.querySelectorAll(".nav-link");

  navLinks.forEach((link) => {
    link.addEventListener("click", function (event) {
      event.preventDefault();
      navLinks.forEach((link) => link.classList.remove("active"));
      this.classList.add("active");
    });
  });

  document.getElementById("logout").addEventListener("click", function () {
    console.log("Logout clicked");
  });
});
