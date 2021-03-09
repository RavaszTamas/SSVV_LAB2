package controller;

import model.Student;
import repository.StudentRepository;
import repository.ValidationException;
import validator.Validator;

public class StudentController {
    StudentRepository studentRepository;
    Validator<Student> studentValidator;
    public StudentController(StudentRepository studentRepository, Validator<Student> validator) {
        this.studentRepository = studentRepository;
        this.studentValidator = validator;
    }

    public void addStudent(Student student){
        try{
            studentValidator.validate(student);
            studentRepository.saveStudent(student);
        }
        catch (ValidationException ignored){}

    }
    public int getNumberOfStudents(){
        return studentRepository.getAll().size();
    }
}
