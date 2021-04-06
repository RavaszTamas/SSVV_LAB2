package ssvv.example.model;

public class Grade {

    String gradeId;
    String assignmentId;
    int gradeValue;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public Grade(String gradeId, String assignmentId, int gradeValue) {
        this.gradeId = gradeId;
        this.assignmentId = assignmentId;
        this.gradeValue = gradeValue;
    }

    public String toString() {
        return gradeId + "," + assignmentId + "," + gradeValue ;
    }

}
