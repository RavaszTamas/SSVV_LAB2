package ssvv.example.model;

public class Assignment {
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getLaboratoryProblemId() {
        return laboratoryProblemId;
    }

    public void setLaboratoryProblemId(String laboratoryProblemId) {
        this.laboratoryProblemId = laboratoryProblemId;
    }

    public Assignment(String id, String studentId, String laboratoryProblemId) {
        this.id = id;
        this.studentId = studentId;
        this.laboratoryProblemId = laboratoryProblemId;
    }

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Assignment(String id) {
        this.id = id;
    }

    String studentId;
    String laboratoryProblemId;

}
