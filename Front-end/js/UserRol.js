document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("userForm");
    const tableContainer = document.getElementById("tableContainer");
    const formContainer = document.getElementById("formContainer");
    const formRow = formContainer.querySelector(".row");
    const tableBody = document.getElementById("userRolTableBody");
    const rolSelect = document.getElementById("Id_rol");
    const userSelect = document.getElementById("Id_User");

    const apiUrl = "http://localhost:8080/api/v1/userRol/";
    const rolApiUrl = "http://localhost:8080/api/v1/rol/"; // Ajusta esta URL según tu API
    const userApiUrl = "http://localhost:8080/api/v1/user/"; // Ajusta esta URL según tu API

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

    mostrarTabla();

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

    // Cargar roles y usuarios al iniciar
    cargarRoles();
    cargarUsuarios();
    crearUserRol();
    cargarUserRoles();

    // Función para cargar roles en el select
    function cargarRoles() {
        return new Promise((resolve, reject) => {
            fetch(rolApiUrl, {
                method: "GET",
                mode: "cors"
            })
                .then((response) => {
                    if (!response.ok) throw new Error("Error al cargar roles");
                    return response.json();
                })
                .then((data) => {
                    console.log("Datos de roles recibidos:", data); // Para depuración

                    // Limpiar opciones existentes excepto la primera
                    rolSelect.innerHTML = '<option value="">Seleccione un rol</option>';

                    // Verificar la estructura correcta según lo que mostraste antes
                    let roles = [];

                    if (Array.isArray(data)) {
                        roles = data;
                    } else if (data.userRoles && Array.isArray(data.userRoles)) {
                        roles = data.userRoles;
                    }

                    roles.forEach(rol => {
                        const option = document.createElement("option");
                        option.value = rol.id_Rol || rol.id_rol || rol.id || "";
                        option.textContent = rol.roleType || rol.role_type || rol.rol_name || "Rol sin nombre";
                        rolSelect.appendChild(option);
                    });
                    resolve();
                })
                .catch((error) => {
                    console.error("Error al cargar roles:", error);
                    reject(error);
                });
        });
    }

    // Función para cargar usuarios en el select
    function cargarUsuarios() {
        return new Promise((resolve, reject) => {
            fetch(userApiUrl, {
                method: "GET",
                mode: "cors"
            })
                .then((response) => {
                    if (!response.ok) throw new Error("Error al cargar usuarios");
                    return response.json();
                })
                .then((data) => {
                    console.log("Datos de usuarios recibidos:", data); // Para depuración

                    // Limpiar opciones existentes excepto la primera
                    userSelect.innerHTML = '<option value="">Seleccione un usuario</option>';

                    // Agregar cada usuario como una opción
                    const usuarios = Array.isArray(data) ? data : [];
                    usuarios.forEach(usuario => {
                        const option = document.createElement("option");
                        option.value = usuario.id_user || usuario.id; // Ajusta según la estructura de tu API

                        // Mostrar nombre completo de usuario si está disponible
                        let displayText = usuario.user_name || usuario.user || "Usuario";
                        if (usuario.name && usuario.lastName) {
                            displayText = `${usuario.name} ${usuario.lastName} (${displayText})`;
                        } else if (usuario.full_name) {
                            displayText = usuario.full_name;
                        }

                        option.textContent = displayText;
                        userSelect.appendChild(option);
                    });
                    resolve();
                })
                .catch((error) => {
                    console.error("Error al cargar usuarios:", error);
                    reject(error);
                });
        });
    }

    function crearUserRol() {
        form.addEventListener("submit", function (e) {
            e.preventDefault();

            const idEditando = form.dataset.editing;

            // Estructura según el DTO en el backend para UserRol
            const userRolData = {
                id_user_rol: idEditando ? parseInt(idEditando) : 0,
                id_rol: parseInt(document.getElementById("Id_rol").value),
                id_user: parseInt(document.getElementById("Id_User").value)
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
                    cargarUserRoles();
                    ocultarFormulario();
                    mostrarTabla();
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
                console.log("Datos de UserRoles recibidos:", data); // Para depuración

                const userRoles = Array.isArray(data) ? data : [];

                if (userRoles.length > 0) {
                    userRoles.forEach((userRole) => {
                        // Ajustado para usar las propiedades correctas según la estructura de la API
                        const id = userRole.id_user_rol !== undefined ? userRole.id_user_rol : 'N/A';
                        const idRol = userRole.id_rol !== undefined ? userRole.id_rol : 'N/A';
                        const idUser = userRole.id_user !== undefined ? userRole.id_user : 'N/A';

                        // Mostrar nombre de usuario completo si está disponible
                        let userName = userRole.user_name || 'N/A';
                        if (userRole.name && userRole.lastName) {
                            userName = `${userRole.name} ${userRole.lastName} (${userName})`;
                        } else if (userRole.full_name) {
                            userName = userRole.full_name;
                        }

                        // Mostrar nombre de rol
                        const rolName = userRole.rol_name || userRole.roleType || 'N/A';

                        const row = document.createElement("tr");
                        row.innerHTML = `
                            <td>${id}</td>
                            <td>${idUser}</td>
                            <td>${userName}</td>
                            <td>${idRol}</td>
                            <td>${rolName}</td>
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
                            <td colspan="6" class="text-center">No hay registros</td>
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
                tableBody.innerHTML = `<tr><td colspan="6" class="text-center">Error al cargar datos</td></tr>`;
            });
    }

    function editarUserRol(id) {
        fetch(`${apiUrl}${id}`, { method: "GET", mode: "cors" })
            .then((response) => {
                if (!response.ok) throw new Error("Error al obtener dato");
                return response.json();
            })
            .then((data) => {
                console.log("Datos recibidos para editar:", data);

                // Guardamos los IDs que necesitamos seleccionar
                const userId = data.id_user;
                const rolId = data.id_rol;

                console.log(`Necesitamos seleccionar: Usuario ID=${userId}, Rol ID=${rolId}`);

                // Primero cargamos ambos selectores con datos frescos
                Promise.all([cargarUsuarios(), cargarRoles()])
                    .then(() => {
                        // Guardamos el ID en el formulario para el envío
                        form.dataset.editing = data.id_user_rol;

                        // Ahora seleccionamos los valores correctos en los dropdowns
                        const rolSelect = document.getElementById("Id_rol");
                        const userSelect = document.getElementById("Id_User");

                        // Para debugging
                        console.log("Opciones de usuarios disponibles:",
                            [...userSelect.options].map(o => `${o.value} - ${o.text}`));

                        // Convertimos los IDs a string para la comparación con option.value
                        const userIdStr = String(userId);
                        const rolIdStr = String(rolId);

                        // Seleccionamos el usuario correcto
                        let userFound = false;
                        for (let i = 0; i < userSelect.options.length; i++) {
                            if (userSelect.options[i].value === userIdStr) {
                                userSelect.selectedIndex = i;
                                userFound = true;
                                console.log(`Usuario encontrado y seleccionado: ${userSelect.options[i].text}`);
                                break;
                            }
                        }

                        if (!userFound) {
                            console.warn(`No se encontró una opción para el usuario con ID ${userId}`);
                        }

                        // Seleccionamos el rol correcto
                        let rolFound = false;
                        for (let i = 0; i < rolSelect.options.length; i++) {
                            if (rolSelect.options[i].value === rolIdStr) {
                                rolSelect.selectedIndex = i;
                                rolFound = true;
                                console.log(`Rol encontrado y seleccionado: ${rolSelect.options[i].text}`);
                                break;
                            }
                        }

                        if (!rolFound) {
                            console.warn(`No se encontró una opción para el rol con ID ${rolId}`);
                        }

                        // Mostramos el formulario
                        ocultarTabla();
                        mostrarFormulario();
                    })
                    .catch(error => {
                        console.error("Error al cargar selectores para edición:", error);
                    });
            })
            .catch((error) => {
                console.error("Error al editar:", error);
                alert("Error al obtener los datos para editar");
            });
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