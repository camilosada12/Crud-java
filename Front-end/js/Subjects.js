document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("userForm");
    const tableContainer = document.getElementById("tableContainer");
    const formContainer = document.getElementById("formContainer");
    const formRow = formContainer.querySelector(".row");
    const tableBody = document.getElementById("SubjectsTableBody");

    const apiUrl = "http://localhost:8080/api/v1/subjects/";



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

    crearSubjects();
    cargarSubjects();

    function crearSubjects() {
        form.addEventListener("submit", function (e) {
            e.preventDefault();

            const idEditando = form.dataset.editing;

            const subjectsData = {
                id_subject: idEditando ? parseInt(idEditando) : 0,  // Cambiado a id_subject (sin "s" al final)
                subjectClasses: document.getElementById("SubjectsClasss").value.trim()  // Cambiado a subjectClasses (sin "s" en "subject")
            };

            if (!subjectsData.subjectClasses) {
                alert("El campo 'SubjectsClasss' es obligatorio.");
                return;
            }

            const metodo = idEditando ? "PUT" : "POST";
            const url = apiUrl;

            fetch(url, {
                method: metodo,
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(subjectsData),
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
                    cargarSubjects();
                    ocultarFormulario();
                    mostrarTabla();
                })
                .catch((error) => {
                    console.error("Error al guardar:", error);
                    cargarSubjects();  // Recarga la tabla en caso de error
                    ocultarFormulario();
                    mostrarTabla();
                });
        });
    }

    function cargarSubjects() {
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

                const subjects = Array.isArray(data) ? data : [];

                if (subjects.length > 0) {
                    subjects.forEach((subject) => {
                        const id = subject.id_Subjects !== undefined ? subject.id_Subjects : 'N/A';
                        const subjectClass = subject.subjectsClasses !== undefined ? subject.subjectsClasses : 'N/A';

                        const row = document.createElement("tr");
                        row.innerHTML = `
                            <td>${id}</td>
                            <td>${subjectClass}</td>
                            <td>
                                <button class="btn btn-sm btn-primary crear-btn">Crear</button>
                                <button class="btn btn-sm btn-warning editar-btn" data-id="${id}">Editar</button>
                                <button class="btn btn-sm btn-danger eliminar-btn" data-id="${id}">Eliminar</button>
                            </td>
                        `;
                        tableBody.appendChild(row);
                    });

                    // Agregar evento a los botones de crear
                    document.querySelectorAll(".crear-btn").forEach((btn) => {
                        btn.addEventListener("click", () => {
                            form.reset();
                            delete form.dataset.editing;
                            ocultarTabla();
                            mostrarFormulario();
                        });
                    });
                } else {
                    tableBody.innerHTML = `
                         <tr>
                    <td colspan="3" class="text-center">No hay registros</td>
                    </tr>
                <tr>
            <td colspan="3" class="text-center">
                <button id="btnCrearNuevo" class="btn btn-success">Crear nuevo</button>
            </td>
                </tr>
                    `;
                }

                const btnCrearNuevo = document.getElementById("btnCrearNuevo");
                if (btnCrearNuevo) {
                    btnCrearNuevo.addEventListener("click", () => {
                        form.reset();
                        delete form.dataset.editing;
                        ocultarTabla();
                        mostrarFormulario();
                    });
                }

                document.querySelectorAll(".editar-btn").forEach((btn) => {
                    btn.addEventListener("click", () => {
                        const id = btn.getAttribute("data-id");
                        editarSubject(id);
                    });
                });

                document.querySelectorAll(".eliminar-btn").forEach((btn) => {
                    btn.addEventListener("click", () => {
                        const id = btn.getAttribute("data-id");
                        eliminarSubject(id);
                    });
                });
            })
            .catch((error) => {
                console.error("Error al cargar:", error);
                tableBody.innerHTML = `<tr><td colspan="3" class="text-center">Error al cargar datos</td></tr>`;
            });
    }

    function editarSubject(id) {
        fetch(`${apiUrl}${id}`, { method: "GET", mode: "cors" })
            .then((response) => {
                if (!response.ok) throw new Error("Error al obtener dato");
                return response.json();
            })
            .then((data) => {
                form.dataset.editing = data.id_Subjects;
                document.getElementById("SubjectsClasss").value = data.subjectsClasses;
                ocultarTabla();
                mostrarFormulario();
            })
            .catch((error) => console.error("Error al editar:", error));
    }

    function eliminarSubject(id) {
        if (!confirm("¿Estás seguro de eliminar este registro?")) return;

        fetch(`${apiUrl}${id}`, { method: "DELETE", mode: "cors" })
            .then((response) => {
                if (!response.ok) throw new Error("Error al eliminar");
                cargarSubjects();
            })
            .catch((error) => console.error("Error al eliminar:", error));
    }
});