const hamburger = document.querySelector("#toggle-btn");
console.log("JS cargado correctamente ✅"); // prueba

hamburger.addEventListener("click", function () {
    console.log("Botón clickeado 👌"); // prueba
    document.querySelector("#sidebar").classList.toggle("expand");
});