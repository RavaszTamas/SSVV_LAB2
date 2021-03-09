package ssvv.example;

import controller.StudentController;
import model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.StudentRepository;
import validator.StudentValidator;
import validator.Validator;
import org.junit.Assert;

/** Unit test for simple App. */
public class AppTest {
  private StudentRepository repository;
  private StudentController controller;
  private Validator<Student> studentValidator;

  @After
  public void tearDown() {
    repository.deleteAll();
    repository = null;
    studentValidator = null;
    controller = null;

  }

  @Before
  public void setUp() {
    repository = new StudentRepository("files/test_students.txt");
    studentValidator = new StudentValidator();
    controller = new StudentController(repository,studentValidator);


  }

  @Test
  public void tc_1_AddStudentCorrectNameNotEmpty() {
    controller.addStudent(new Student("1", "1", "name", 1));
    Assert.assertEquals(controller.getNumberOfStudents(),1);
  }

  @Test
  public void tc_1_AddStudentCorrectNameEmpty() {

    controller.addStudent(new Student("1", "1", "", 1));
    Assert.assertEquals(controller.getNumberOfStudents(),0);

  }
}
