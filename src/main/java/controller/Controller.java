package controller;

import model.Assignment;
import model.LaboratoryProblem;
import model.Student;
import repository.AssignmentRepository;
import repository.LaboratoryRepository;
import repository.StudentRepository;
import repository.ValidationException;
import validator.Validator;

public class Controller {
  StudentRepository studentRepository;
  LaboratoryRepository laboratoryRepository;
  AssignmentRepository assignmentRepository;
  Validator<Student> studentValidator;
  Validator<LaboratoryProblem> laboratoryProblemValidator;
  Validator<Assignment> assignmentValidator;

  public Controller(
      StudentRepository studentRepository,
      LaboratoryRepository laboratoryRepository,
      AssignmentRepository assignmentRepository,
      Validator<Student> studentValidator,
      Validator<LaboratoryProblem> laboratoryProblemValidator,
      Validator<Assignment> assignmentValidator
    ) {
    this.studentRepository = studentRepository;
    this.laboratoryRepository = laboratoryRepository;
    this.assignmentRepository = assignmentRepository;
    this.studentValidator = studentValidator;
    this.laboratoryProblemValidator = laboratoryProblemValidator;
    this.assignmentValidator = assignmentValidator;
  }

  public void addStudent(Student student) {
    try {
      studentValidator.validate(student);
      studentRepository.saveEntity(student);
    } catch (ValidationException ignored) {
    }
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
      assignmentRepository.saveEntity(assignment);
    } catch (ValidationException ignored) {
    }
  }

  public int getNumberOfStudents() {
    return studentRepository.getAll().size();
  }
  public int getNumberOfAssignemnts() {
    return assignmentRepository.getAll().size();
  }
  public int getNumberOfLaboratoryProblems() {
    return laboratoryRepository.getAll().size();
  }
}
