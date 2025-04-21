document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("userForm");
    const tableContainer = document.getElementById("tableContainer");
    const ocultar = document.querySelector(".ocultar");
    const tableBody = document.getElementById("userTableBody");
    const formContainer = document.getElementById("formContainer"); // Contenedor del formulario

    const apiUrl = "http://localhost:8080/api/v1/user/";




    // Mostrar el formulario
    function ocultarFormulario() {
        formContainer.classList.add("ocultar");
    }
    // Mostrar el formulario
    function mostrarTabla() {
        ocultar.classList.remove("ocultar");
    }

    // Cargar usuarios al iniciar
    cargarUsuarios();

    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const nuevoUsuario = {
            id_user: 0, // Este se puede omitir si el backend lo asigna automáticamente
            name: document.getElementById("name").value.trim(),
            lastname: document.getElementById("lastname").value.trim(),
            user: document.getElementById("username").value.trim(),
            mail: document.getElementById("mail").value.trim(),
            password: document.getElementById("password").value.trim(),
        };

        console.log("Enviando usuario:", nuevoUsuario); // útil para depuración

        fetch(apiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(nuevoUsuario),
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Error en la respuesta del servidor");
                }
                return response.text(); // Cambié a .text() en vez de .json()
            })
            .then((data) => {
                console.log("Usuario creado correctamente:", data);
                form.reset();
                cargarUsuarios(); // Recargar usuarios después de crear
                ocultarFormulario()
                mostrarTabla();


            })
            .catch((error) => {
                console.error("Error al crear usuario:", error);

            });
    });

    function cargarUsuarios() {
        fetch(apiUrl)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Error al cargar usuarios");
                }
                return response.json();
            })
            .then((data) => {
                console.log("Usuarios cargados:", data);
                tableBody.innerHTML = ""; // Limpiar tabla

                data.forEach((usuario) => {
                    // Asegura que no se rompa si las relaciones vienen nulas o complejas
                    const id = usuario.id_User || usuario.id_user || "N/A";
                    const name = usuario.name || "N/A";
                    const lastname = usuario.lastName || usuario.lastname || "N/A";
                    const username = usuario.user || "N/A";
                    const password = usuario.password || "N/A";
                    const mail = usuario.mail || "N/A";

                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${id}</td>
                        <td>${name}</td>
                        <td>${lastname}</td>
                        <td>${username}</td>
                        <td>${password}</td>
                        <td>${mail}</td>
                    `;
                    tableBody.appendChild(row);
                });

            })
            .catch((error) => {
                console.error("Error al cargar usuarios:", error);
            });
    }
});
