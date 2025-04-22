document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("userForm");
    const tableContainer = document.getElementById("tableContainer");
    const formContainer = document.getElementById("formContainer");
    const formRow = formContainer.querySelector(".row");
    const tableBody = document.getElementById("userRolTableBody");

    const apiUrl = "http://localhost:8080/api/v1/userRol/";

    // Crear botón dinámico "Crear Nuevo" y agregarlo al lado de los otros botones
    const nuevoBtn = document.createElement("button");
    nuevoBtn.className = "btn btn-success mb-3";
    nuevoBtn.textContent = "Crear Nuevo";
    nuevoBtn.addEventListener("click", () => {
        form.reset();
        delete form.dataset.editing;
        ocultarTabla();
        mostrarFormulario();
    });

    // Agregar el botón al contenedor de la tabla
    const buttonContainer = document.createElement("div");
    buttonContainer.className = "d-flex justify-content-start mb-3";
    buttonContainer.appendChild(nuevoBtn);
    tableContainer.insertBefore(buttonContainer, tableContainer.firstChild);

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
        nuevoBtn.classList.remove("ocultar");
    }

    function ocultarTabla() {
        tableContainer.classList.add("ocultar");
        nuevoBtn.classList.add("ocultar");
    }

    crearUserRol();
    cargarUserRoles();

    function crearUserRol() {
        form.addEventListener("submit", function (e) {
            e.preventDefault();

            const idEditando = form.dataset.editing;

            // Estructura según el DTO en el backend para UserRol
            const userRolData = {
                id: idEditando ? parseInt(idEditando) : 0,
                id_rol: parseInt(document.getElementById("Id_rol").value.trim()),
                id_user: parseInt(document.getElementById("Id_User").value.trim())
            };

            // Verificar si los valores están vacíos
            if (!userRolData.id_rol || !userRolData.id_user) {
                alert("Todos los campos son obligatorios.");
                return;
            }

            console.log("Datos que se enviarán:", userRolData);

            const metodo = idEditando ? "PUT" : "POST";
            const url = apiUrl;

            fetch(url, {
                method: metodo,
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(userRolData),
                mode: "cors"
            })
                .then((response) => {
                    if (!response.ok) {
                        return response.text().then(text => {
                            console.error("Error response:", text);
                            throw new Error("Error al guardar");
                        });
                    }
                    return response.json();
                })
                .then((data) => {
                    console.log('Datos guardados:', data);
                    form.reset();
                    delete form.dataset.editing;
                    cargarUserRoles();
                    ocultarFormulario();
                    mostrarTabla();
                })
                .catch((error) => {
                    console.error("Error al guardar:", error);
                    alert("Error al guardar: " + error.message);
                });
        });
    }

    function cargarUserRoles() {
        fetch(apiUrl, {
            method: "GET",
            mode: "cors"
        })
            .then((response) => {
                if (!response.ok) throw new Error("Error al cargar datos");
                return response.json();
            })
            .then((data) => {
                tableBody.innerHTML = "";

                const userRoles = Array.isArray(data) ? data : [];

                if (userRoles.length > 0) {
                    userRoles.forEach((userRole) => {
                        // Ajusta estos campos según la estructura real de tu respuesta API
                        const id = userRole.id !== undefined ? userRole.id : 'N/A';
                        const idRol = userRole.id_rol !== undefined ? userRole.id_rol : 'N/A';
                        const idUser = userRole.id_user !== undefined ? userRole.id_user : 'N/A';
                        const name = userRole.name !== undefined ? userRole.name : 'N/A';
                        const lastName = userRole.lastName !== undefined ? userRole.lastName : 'N/A';
                        const user = userRole.user !== undefined ? userRole.user : 'N/A';
                        const password = userRole.password !== undefined ? userRole.password : 'N/A';
                        const mail = userRole.mail !== undefined ? userRole.mail : 'N/A';

                        const row = document.createElement("tr");
                        row.innerHTML = `
                            <td>${id}</td>
                            <td>${name}</td>
                            <td>${lastName}</td>
                            <td>${user}</td>
                            <td>${password}</td>
                            <td>${mail}</td>
                            <td>
                                <button class="btn btn-sm btn-warning editar-btn" data-id="${id}">Editar</button>
                                <button class="btn btn-sm btn-danger eliminar-btn" data-id="${id}">Eliminar</button>
                            </td>
                        `;
                        tableBody.appendChild(row);
                    });
                } else {
                    tableBody.innerHTML = `
                        <tr>
                            <td colspan="7" class="text-center">No hay registros</td>
                        </tr>
                    `;
                }

                document.querySelectorAll(".editar-btn").forEach((btn) => {
                    btn.addEventListener("click", () => {
                        const id = btn.getAttribute("data-id");
                        editarUserRol(id);
                    });
                });

                document.querySelectorAll(".eliminar-btn").forEach((btn) => {
                    btn.addEventListener("click", () => {
                        const id = btn.getAttribute("data-id");
                        eliminarUserRol(id);
                    });
                });
            })
            .catch((error) => {
                console.error("Error al cargar:", error);
                tableBody.innerHTML = `<tr><td colspan="7" class="text-center">Error al cargar datos</td></tr>`;
            });
    }

    function editarUserRol(id) {
        fetch(`${apiUrl}${id}`, { method: "GET", mode: "cors" })
            .then((response) => {
                if (!response.ok) throw new Error("Error al obtener dato");
                return response.json();
            })
            .then((data) => {
                form.dataset.editing = data.id;
                document.getElementById("Id_rol").value = data.id_rol;
                document.getElementById("Id_User").value = data.id_user;
                ocultarTabla();
                mostrarFormulario();
            })
            .catch((error) => console.error("Error al editar:", error));
    }

    function eliminarUserRol(id) {
        if (!confirm("¿Estás seguro de eliminar este registro?")) return;

        fetch(`${apiUrl}${id}`, { method: "DELETE", mode: "cors" })
            .then((response) => {
                if (!response.ok) throw new Error("Error al eliminar");
                cargarUserRoles();
            })
            .catch((error) => console.error("Error al eliminar:", error));
    }
});