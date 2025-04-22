document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("userForm");
    const tableContainer = document.getElementById("tableContainer");
    const formContainer = document.getElementById("formContainer");
    const formRow = formContainer.querySelector(".row");
    const tableBody = document.getElementById("SubjectsTableBody");

    const apiUrl = "http://localhost:8080/api/v1/subjects/";

    // Crear botón dinámico "Crear Nuevo"
    const nuevoBtn = document.createElement("button");
    nuevoBtn.className = "btn btn-success mb-3";
    nuevoBtn.textContent = "Crear Nuevo";
    nuevoBtn.addEventListener("click", () => {
        form.reset();
        delete form.dataset.editing;
        ocultarTabla();
        mostrarFormulario();
    });
    tableContainer.insertBefore(nuevoBtn, tableContainer.firstChild);

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

    crearSubjects();
    cargarSubjects();

    function crearSubjects() {
        form.addEventListener("submit", function (e) {
            e.preventDefault();

            const idEditando = form.dataset.editing;

            const subjectsClass = {
                id_Subjects: idEditando ? parseInt(idEditando) : 0,
                subjectsClasses: document.getElementById("SubjectsClasss").value.trim()
            };

            const metodo = idEditando ? "PUT" : "POST";
            const url = apiUrl;

            fetch(url, {
                method: metodo,
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(subjectsClass),
                mode: "cors"
            })
                .then((response) => {
                    if (!response.ok) throw new Error("Error al guardar");
                    return response.json();
                })
                .then(() => {
                    form.reset();
                    delete form.dataset.editing;
                    cargarSubjects();
                    ocultarFormulario();
                    mostrarTabla();
                })
                .catch((error) => console.error("Error al guardar:", error));
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
                                <button class="btn btn-sm btn-warning editar-btn" data-id="${id}">Editar</button>
                                <button class="btn btn-sm btn-danger eliminar-btn" data-id="${id}">Eliminar</button>
                            </td>
                        `;
                        tableBody.appendChild(row);
                    });
                } else {
                    tableBody.innerHTML = `
                        <tr>
                            <td colspan="3" class="text-center">No hay registros</td>
                        </tr>
                    `;
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
