package com.example.registration;

public class students {
    String studentId;
    String studentName;

    String studentSection;


    public  students(){

    }

    public students(String studentId, String studentName, String studentSection) {
        this.studentId = studentId;
        this.studentName = studentName;

        this.studentSection = studentSection;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }



    public String getStudentSection() {
        return studentSection;
    }
}
