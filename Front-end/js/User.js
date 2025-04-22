document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("userForm");
    const tableContainer = document.getElementById("tableContainer");
    const formContainer = document.getElementById("formContainer");
    const formRow = formContainer.querySelector(".row");
    const tableBody = document.getElementById("userTableBody");

    const apiUrl = "http://localhost:8080/api/v1/user/";

    mostrarTabla();

    function ocultarFormulario() {
        formRow.classList.add("ocultar");
    }

    function mostrarFormulario() {
        formRow.classList.remove("ocultar");
    }

    function mostrarTabla() {
        tableContainer.classList.remove("ocultar");
        tableContainer.querySelector('div').classList.remove("ocultar");
    }

    function ocultarTabla() {
        tableContainer.classList.add("ocultar");
    }

    crearUsuarios();
    cargarUsuarios();

    function crearUsuarios() {
        form.addEventListener("submit", function (e) {
            e.preventDefault();

            const idEditando = form.dataset.editing;

            // Corrección: aseguramos que cada campo del formulario corresponda con el campo correcto en el objeto usuario
            const usuario = {
                id_user: idEditando ? parseInt(idEditando) : 0,
                name: document.getElementById("name").value.trim(),
                lastname: document.getElementById("lastname").value.trim(),
                user: document.getElementById("username").value.trim(),
                // Aseguramos que mail tome el valor del campo mail
                mail: document.getElementById("mail").value.trim(),
                // Aseguramos que password tome el valor del campo password
                password: document.getElementById("password").value.trim(),
            };

            const metodo = idEditando ? "PUT" : "POST";
            // Usar solo la URL base para PUT ya que el controlador lo espera así
            const url = apiUrl;

            fetch(url, {
                method: metodo,
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(usuario),
                mode: "cors",
                credentials: "include", // Agregar esto para cookies si es necesario
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
        fetch(apiUrl, {
            method: "GET",
            mode: "cors",  // Habilitar CORS
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Error al cargar usuarios");
                }
                return response.json();
            })
            .then((data) => {
                tableBody.innerHTML = "";
                
                // Verificar si hay datos
                const hayDatos = Array.isArray(data) && data.length > 0;

                if (hayDatos) {
                    // Si hay datos, mostrar la tabla con los usuarios
                    data.forEach((usuario) => {
                        const id = usuario.id_User || usuario.id_user || "N/A";
                        const name = usuario.name || "N/A";
                        const lastname = usuario.lastName || usuario.lastname || "N/A";
                        const username = usuario.user || "N/A";
                        // Corrección: aseguramos mostrar los campos en el orden correcto
                        const mail = usuario.mail || "N/A";
                        const password = usuario.password || "N/A";

                        const row = document.createElement("tr");
                        row.innerHTML = `
                            <td>${id}</td>
                            <td>${name}</td>
                            <td>${lastname}</td>
                            <td>${username}</td>
                            <td>${mail}</td>
                            <td>${password}</td>
                            <td>
                                <button class="btn btn-sm btn btn-info crear-btn" data-id="">Crear</button>
                                <button class="btn btn-sm btn-warning editar-btn" data-id="${id}">Editar</button>
                                <button class="btn btn-sm btn-danger eliminar-btn" data-id="${id}">Eliminar</button>
                            </td>
                        `;
                        tableBody.appendChild(row);
                    });
                } else {
                    // Si no hay datos, mostrar un mensaje y solo el botón de crear habilitado
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td colspan="7" class="text-center">No hay usuarios registrados</td>
                    `;
                    tableBody.appendChild(row);
                    
                    // Agregar una fila adicional con solo el botón de crear
                    const actionRow = document.createElement("tr");
                    actionRow.innerHTML = `
                        <td colspan="7" class="text-center">
                            <button class="btn btn-sm btn btn-info crear-btn" data-id="">Crear Usuario</button>
                            <button class="btn btn-sm btn-warning editar-btn" data-id="" disabled>Editar</button>
                            <button class="btn btn-sm btn-danger eliminar-btn" data-id="" disabled>Eliminar</button>
                        </td>
                    `;
                    tableBody.appendChild(actionRow);
                }

                // Configurar eventos para los botones
                document.querySelectorAll(".crear-btn").forEach((btn) => {
                    btn.addEventListener("click", () => {
                        form.reset();
                        delete form.dataset.editing;
                        ocultarTabla();
                        mostrarFormulario();
                    });
                });

                // Solo agregar eventos a los botones de editar si están habilitados
                document.querySelectorAll(".editar-btn:not([disabled])").forEach((btn) => {
                    btn.addEventListener("click", () => {
                        const id = btn.getAttribute("data-id");
                        editarUsuario(id);
                    });
                });

                // Solo agregar eventos a los botones de eliminar si están habilitados
                document.querySelectorAll(".eliminar-btn:not([disabled])").forEach((btn) => {
                    btn.addEventListener("click", () => {
                        const id = btn.getAttribute("data-id");
                        eliminarUsuario(id);
                    });
                });
            })
            .catch((error) => {
                console.error("Error al cargar usuarios:", error);
                // En caso de error, mostrar un mensaje de error y solo habilitar el botón de crear
                tableBody.innerHTML = `
                    <tr>
                        <td colspan="7" class="text-center">Error al cargar usuarios</td>
                    </tr>
                    <tr>
                        <td colspan="7" class="text-center">
                            <button class="btn btn-sm btn btn-info crear-btn" data-id="">Crear Usuario</button>
                            <button class="btn btn-sm btn-warning editar-btn" data-id="" disabled>Editar</button>
                            <button class="btn btn-sm btn-danger eliminar-btn" data-id="" disabled>Eliminar</button>
                        </td>
                    </tr>
                `;
                
                // Agregar evento al botón de crear en caso de error
                document.querySelectorAll(".crear-btn").forEach((btn) => {
                    btn.addEventListener("click", () => {
                        form.reset();
                        delete form.dataset.editing;
                        ocultarTabla();
                        mostrarFormulario();
                    });
                });
            });
    }

    function editarUsuario(id) {
        fetch(apiUrl + id, {
            method: "GET",
            mode: "cors",  // Habilitar CORS
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("No se pudo obtener el usuario");
                }
                return response.json();
            })
            .then((usuario) => {
                // Importante: asegurarse de asignar correctamente cada valor al campo correspondiente
                document.getElementById("name").value = usuario.name || "";
                document.getElementById("lastname").value = usuario.lastname || usuario.lastName || "";
                document.getElementById("username").value = usuario.user || "";
                // Corrección: mail va en el campo mail
                document.getElementById("mail").value = usuario.mail || "";
                // Corrección: password va en el campo password
                document.getElementById("password").value = usuario.password || "";

                // Imprimir en consola para verificar
                console.log("Datos cargados en el formulario:", {
                    name: document.getElementById("name").value,
                    lastname: document.getElementById("lastname").value,
                    username: document.getElementById("username").value,
                    mail: document.getElementById("mail").value,
                    password: document.getElementById("password").value
                });

                form.dataset.editing = id;

                ocultarTabla();
                mostrarFormulario();
            })
            .catch((error) => {
                console.error("Error al cargar usuario para edición:", error);
                alert("Error al cargar los datos del usuario para editar");
            });
    }

    function eliminarUsuario(id) {
        const confirmar = confirm("¿Estás seguro de que deseas eliminar este usuario?");
        if (!confirmar) return;

        fetch(apiUrl + id, {
            method: "DELETE",
            mode: "cors",  // Habilitar CORS
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error("No se pudo eliminar el usuario");
                }
                return response.text();
            })
            .then(() => {
                cargarUsuarios();
            })
            .catch((error) => {
                console.error("Error al eliminar usuario:", error);
            });
    }
});