document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("userForm");
    const tableContainer = document.getElementById("tableContainer");
    const formContainer = document.getElementById("formContainer");
    const formRow = formContainer.querySelector(".row");
    const tableBody = document.getElementById("userTableBody");

    const apiUrl = "http://localhost:8080/api/v1/rol/";

    // Inicialmente mostrar la tabla
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

    // Cargar roles al inicio
    cargarRoles();

    // Configurar manejo del formulario
    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const idEditando = form.dataset.editing;

        const rol = {
            id_rol: idEditando ? parseInt(idEditando) : 0,
            roletype: document.getElementById("type_Role").value.trim()
        };

        console.log("Rol a enviar:", rol);

        const metodo = idEditando ? "PUT" : "POST";
        const url = apiUrl;

        fetch(url, {
            method: metodo,
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(rol),
            mode: "cors",
            credentials: "include",
        })
        .then((response) => {
            if (!response.ok) {
                throw new Error("Error en la respuesta del servidor");
            }
            return response.json();
        })
        .then((data) => {
            console.log(`Rol ${idEditando ? "actualizado" : "creado"} correctamente:`, data);
            form.reset();
            delete form.dataset.editing;
            
            // Esta es la parte clave: cargar los roles, ocultar el formulario y mostrar la tabla
            cargarRoles();
            ocultarFormulario();
            mostrarTabla();
        })
        .catch((error) => {
            console.error("Error al guardar rol:", error);
            cargarRoles();
            ocultarFormulario();
            mostrarTabla();
        });
    });

    function cargarRoles() {
        fetch(apiUrl, {
            method: "GET",
            mode: "cors",
        })
        .then((response) => {
            if (!response.ok) {
                throw new Error("Error al cargar roles");
            }
            const contentType = response.headers.get("content-type");
            if (contentType && contentType.includes("application/json")) {
                return response.json();
            } else {
                return response.text();
            }
            
        })
        .then((data) => {
            console.log("Roles cargados:", data);

            tableBody.innerHTML = "";

            const hayDatos = Array.isArray(data) && data.length > 0;

            if (hayDatos) {
                data.forEach((rol) => {
                    // Manejar diferentes posibles nombres de propiedades
                    const id = rol.id_Rol || rol.id_rol || "N/A";
                    const type_role = rol.roleType || rol.roletype || "N/A";

                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${id}</td>
                        <td>${type_role}</td>
                        <td>
                            <button class="btn btn-sm btn-info crear-btn">Crear</button>
                            <button class="btn btn-sm btn-warning editar-btn" data-id="${id}">Editar</button>
                            <button class="btn btn-sm btn-danger eliminar-btn" data-id="${id}">Eliminar</button>
                        </td>
                    `;
                    tableBody.appendChild(row);
                });
            } else {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td colspan="3" class="text-center">No hay roles registrados</td>
                `;
                tableBody.appendChild(row);

                const actionRow = document.createElement("tr");
                actionRow.innerHTML = `
                    <td colspan="3" class="text-center">
                        <button class="btn btn-sm btn-info crear-btn">Crear Rol</button>
                        <button class="btn btn-sm btn-warning editar-btn" disabled>Editar</button>
                        <button class="btn btn-sm btn-danger eliminar-btn" disabled>Eliminar</button>
                    </td>
                `;
                tableBody.appendChild(actionRow);
            }

            // Configurar eventos para los botones
            configurarBotones();
        })
        .catch((error) => {
            console.error("Error al cargar roles:", error);
            tableBody.innerHTML = `
                <tr>
                    <td colspan="3" class="text-center">Error al cargar roles</td>
                </tr>
                <tr>
                    <td colspan="3" class="text-center">
                        <button class="btn btn-sm btn-info crear-btn">Crear Rol</button>
                        <button class="btn btn-sm btn-warning editar-btn" disabled>Editar</button>
                        <button class="btn btn-sm btn-danger eliminar-btn" disabled>Eliminar</button>
                    </td>
                </tr>
            `;
            
            configurarBotones();
        });
    }

    function configurarBotones() {
        // Configurar botón de crear
        document.querySelectorAll(".crear-btn").forEach((btn) => {
            btn.addEventListener("click", () => {
                form.reset();
                delete form.dataset.editing;
                ocultarTabla();
                mostrarFormulario();
            });
        });

        // Configurar botones de editar
        document.querySelectorAll(".editar-btn:not([disabled])").forEach((btn) => {
            btn.addEventListener("click", () => {
                const id = btn.getAttribute("data-id");
                editarRol(id);
            });
        });

        // Configurar botones de eliminar
        document.querySelectorAll(".eliminar-btn:not([disabled])").forEach((btn) => {
            btn.addEventListener("click", () => {
                const id = btn.getAttribute("data-id");
                eliminarRol(id);
            });
        });
    }

    function editarRol(id) {
        fetch(apiUrl + id, {
            method: "GET",
            mode: "cors",
        })
        .then((response) => {
            if (!response.ok) {
                throw new Error("No se pudo obtener el rol");
            }
            return response.json();
        })
        .then((rol) => {
            // Manejar diferentes posibles nombres de propiedades
            document.getElementById("type_Role").value = rol.roleType || rol.roletype || "";

            console.log("Datos cargados en el formulario:", {
                type_Role: document.getElementById("type_Role").value
            });

            form.dataset.editing = id;

            ocultarTabla();
            mostrarFormulario();
        })
        .catch((error) => {
            console.error("Error al cargar rol para edición:", error);
            alert("Error al cargar los datos del rol para editar");
        });
    }

    function eliminarRol(id) {
        const confirmar = confirm("¿Estás seguro de que deseas eliminar este rol?");
        if (!confirmar) return;

        fetch(apiUrl + id, {
            method: "DELETE",
            mode: "cors",
        })
        .then((response) => {
            if (!response.ok) {
                throw new Error("No se pudo eliminar el rol");
            }
            return response.text();
        })
        .then(() => {
            console.log("Rol eliminado correctamente");
            cargarRoles();
        })
        .catch((error) => {
            console.error("Error al eliminar rol:", error);
            alert("Error al eliminar: " + error.message);
        });
    }
});