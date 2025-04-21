document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("userForm");
    const tableContainer = document.getElementById("tableContainer");
    const formContainer = document.getElementById("formContainer");
    const formRow = formContainer.querySelector(".row"); // Seleccionar el div.row interno
    const tableBody = document.getElementById("userTableBody");

    const apiUrl = "http://localhost:8080/api/v1/user/";

    function ocultarFormulario() {
        formRow.classList.add("ocultar"); // Aplicar la clase al div.row, no al formContainer
    }

    function mostrarFormulario() {
        formRow.classList.remove("ocultar"); // Quitar la clase del div.row, no del formContainer
    }

    function mostrarTabla() {
        tableContainer.classList.remove("ocultar");
    }

    function ocultarTabla() {
        tableContainer.classList.add("ocultar");
    }

    crearUsuarios(); // Registrar el evento desde el principio
    cargarUsuarios(); // Cargar usuarios al iniciar

    function crearUsuarios(){
        form.addEventListener("submit", function (e) {
            e.preventDefault();
    
            const idEditando = form.dataset.editing;
    
            const usuario = {
                id_user: idEditando ? parseInt(idEditando) : 0,
                name: document.getElementById("name").value.trim(),
                lastname: document.getElementById("lastname").value.trim(),
                user: document.getElementById("username").value.trim(),
                mail: document.getElementById("mail").value.trim(),
                password: document.getElementById("password").value.trim(),
            };
    
            const metodo = idEditando ? "PUT" : "POST";
            const url = idEditando ? apiUrl + idEditando : apiUrl;
    
            fetch(url, {
                method: metodo,
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(usuario),
            })
                .then((response) => {
                    if (!response.ok) {
                        throw new Error("Error en la respuesta del servidor");
                    }
                    return response.text();
                })
                .then((data) => {
                    console.log(`Usuario ${idEditando ? "actualizado" : "creado"} correctamente:`, data);
                    form.reset();
                    delete form.dataset.editing;
                    cargarUsuarios();
                    ocultarFormulario();
                    mostrarTabla();
                })
                .catch((error) => {
                    console.error("Error al guardar usuario:", error);
                });
        });
    }
   

    function cargarUsuarios() {
        fetch(apiUrl)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Error al cargar usuarios");
                }
                return response.json();
            })
            .then((data) => {
                tableBody.innerHTML = "";

                data.forEach((usuario) => {
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
                            <td>
                                <button class="btn btn-sm btn btn-info crear-btn" data-id="">Crear</button>
                                <button class="btn btn-sm btn-warning editar-btn" data-id="${id}">Editar</button>
                                <button class="btn btn-sm btn-danger eliminar-btn" data-id="${id}">Eliminar</button>
                            </td>
                        `;
                    tableBody.appendChild(row);
                });

                // Corregir el evento click del botón crear
                document.querySelectorAll(".crear-btn").forEach((btn) => {
                    btn.addEventListener("click", () => {
                        form.reset(); // Limpia el formulario
                        delete form.dataset.editing; // Asegura que no estás editando
                        ocultarTabla(); // Oculta la tabla
                        mostrarFormulario(); // Muestra el formulario
                        console.log("Formulario mostrado"); // Para depuración
                    });
                });
                
                document.querySelectorAll(".editar-btn").forEach((btn) => {
                    btn.addEventListener("click", () => {
                        const id = btn.getAttribute("data-id");
                        editarUsuario(id);
                    });
                });

                document.querySelectorAll(".eliminar-btn").forEach((btn) => {
                    btn.addEventListener("click", () => {
                        const id = btn.getAttribute("data-id");
                        eliminarUsuario(id);
                    });
                });
            })
            .catch((error) => {
                console.error("Error al cargar usuarios:", error);
            });
    }

    function editarUsuario(id) {
        fetch(apiUrl + id)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("No se pudo obtener el usuario");
                }
                return response.json();
            })
            .then((usuario) => {
                document.getElementById("name").value = usuario.name || "";
                document.getElementById("lastname").value = usuario.lastname || usuario.lastName || "";
                document.getElementById("username").value = usuario.user || "";
                document.getElementById("mail").value = usuario.mail || "";
                document.getElementById("password").value = usuario.password || "";

                form.dataset.editing = id;

                ocultarTabla();
                mostrarFormulario();
            })
            .catch((error) => {
                console.error("Error al cargar usuario para edición:", error);
            });
    }

    function eliminarUsuario(id) {
        const confirmar = confirm("¿Estás seguro de que deseas eliminar este usuario?");
        if (!confirmar) return;

        fetch(apiUrl + id, {
            method: "DELETE"
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("No se pudo eliminar el usuario");
                }
                return response.text();
            })
            .then(() => {
                console.log("Usuario eliminado correctamente");
                cargarUsuarios();
            })
            .catch((error) => {
                console.error("Error al eliminar usuario:", error);
            });
    }
});