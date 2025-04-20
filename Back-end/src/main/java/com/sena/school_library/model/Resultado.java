    package com.sena.school_library.model;

    import com.fasterxml.jackson.annotation.JsonBackReference;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id; // Cambiado de OneToOne a ManyToOne
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;

    @Entity(name = "Result")
    public class Resultado {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Id_Result", length = 10)
        private int Id_Result;

        @Column(name = "Note")
        private double Note;

        @ManyToOne
        @JoinColumn(name = "response_student_id")
        @JsonBackReference
        private responseStudent responseStudent;

        public Resultado() {
        }

        public Resultado(int id_Result, double note, com.sena.school_library.model.responseStudent responseStudent) {
            Id_Result = id_Result;
            Note = note;
            this.responseStudent = responseStudent;
        }

        public int getId_Result() {
            return Id_Result;
        }

        public void setId_Result(int id_Result) {
            Id_Result = id_Result;
        }

        public double getNote() {
            return Note;
        }

        public void setNote(double note) {
            Note = note;
        }

        public responseStudent getResponseStudent() {
            return responseStudent;
        }

        public void setResponseStudent(responseStudent responseStudent) {
            this.responseStudent = responseStudent;
        }


    }
