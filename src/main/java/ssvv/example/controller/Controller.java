package ssvv.example.controller;

import ssvv.example.model.Assignment;
import ssvv.example.model.Grade;
import ssvv.example.model.LaboratoryProblem;
import ssvv.example.model.Student;
import ssvv.example.repository.*;
import ssvv.example.validator.Validator;

public class Controller {
  StudentRepository studentRepository;
  LaboratoryRepository laboratoryRepository;
  AssignmentRepository assignmentRepository;
  GradeRepository gradeRepository;
  Validator<Student> studentValidator;
  Validator<LaboratoryProblem> laboratoryProblemValidator;
  Validator<Assignment> assignmentValidator;
  Validator<Grade> gradeValidator;

  public Controller(
      StudentRepository studentRepository,
      LaboratoryRepository laboratoryRepository,
      AssignmentRepository assignmentRepository,
      GradeRepository gradeRepository,
      Validator<Student> studentValidator,
      Validator<LaboratoryProblem> laboratoryProblemValidator,
      Validator<Assignment> assignmentValidator,
      Validator<Grade> gradeValidator
      ) {
    this.studentRepository = studentRepository;
    this.gradeRepository = gradeRepository;
    this.laboratoryRepository = laboratoryRepository;
    this.assignmentRepository = assignmentRepository;
    this.studentValidator = studentValidator;
    this.laboratoryProblemValidator = laboratoryProblemValidator;
    this.assignmentValidator = assignmentValidator;
    this.gradeValidator = gradeValidator;
  }

  public void addStudent(Student student) {
    try {
      studentValidator.validate(student);
      studentRepository.saveEntity(student);
    } catch (ValidationException ignored) {
    }
  }
  public void addGrade(Grade grade) {
    try {
      gradeValidator.validate(grade);
    } catch (ValidationException ignored) {
      return;
    }

    if (assignmentRepository.findById(grade.getAssignmentId()) == null) {
      throw new ValidationException("Assignment with existing id!");
    }
    gradeRepository.saveEntity(grade);
  }

  public void addLaboratoryProblem(LaboratoryProblem laboratoryProblem) {
    try {
      laboratoryProblemValidator.validate(laboratoryProblem);
      laboratoryRepository.saveEntity(laboratoryProblem);
    } catch (ValidationException ignored) {
    }
  }

  public void addAssignment(Assignment assignment) {
    try {
      assignmentValidator.validate(assignment);
    } catch (ValidationException ignored) {
      return;
    }
    if (assignmentRepository.findById(assignment.getId()) != null) {
      throw new ValidationException("Assignment with existing id!");
    }

    if (studentRepository.findById(assignment.getStudentId()) == null) {
      throw new ValidationException("Student with id wasn't found");
    }
    if (laboratoryRepository.findById(assignment.getLaboratoryProblemId()) == null) {
      throw new ValidationException("Laboratory problem with id wasn't found");
    }
    assignmentRepository.saveEntity(assignment);
  }

  public int getNumberOfStudents() {
    return studentRepository.getAll().size();
  }

  public int getNumberOfGrades() {
    return gradeRepository.getAll().size();
  }

  public int getNumberOfAssignemnts() {
    return assignmentRepository.getAll().size();
  }

  public int getNumberOfLaboratoryProblems() {
    return laboratoryRepository.getAll().size();
  }
}
