package ssvv.example.controller;

import ssvv.example.model.Assignment;
import ssvv.example.model.LaboratoryProblem;
import ssvv.example.model.Student;
import ssvv.example.repository.AssignmentRepository;
import ssvv.example.repository.LaboratoryRepository;
import ssvv.example.repository.StudentRepository;
import ssvv.example.repository.ValidationException;
import ssvv.example.validator.Validator;

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
      Validator<Assignment> assignmentValidator) {
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
    } catch (ValidationException ignored) {
      return;
    }

    if (studentRepository.findById(assignment.getStudentId()) == null) {
      throw new ValidationException("Student with id wasn't found");
    }
    if (laboratoryRepository.findById(assignment.getLaboratoryProblemId()) == null) {
      throw new ValidationException("Student with id wasn't found");
    }
    assignmentRepository.saveEntity(assignment);
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
