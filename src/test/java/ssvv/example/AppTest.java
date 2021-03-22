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

import java.io.File;
import java.io.IOException;

/** Unit test for simple App. */
public class AppTest {
  private StudentRepository repository;
  private StudentController controller;
  private Validator<Student> studentValidator;
  private final int NUMBER_OF_INITIAL_STUDENTS = 3;
  @After
  public void tearDown() {
    repository.deleteAll();
    repository = null;
    studentValidator = null;
    controller = null;
    File file = new File("files/test_students.txt");
    file.delete();
  }

  @Before
  public void setUp() {
    File file = new File("files/test_students.txt");
    try { file.createNewFile(); } catch (IOException ignored) { }
    repository = new StudentRepository("files/test_students.txt");
    studentValidator = new StudentValidator();
    controller = new StudentController(repository,studentValidator);
    controller.addStudent(new Student("1", "1", "name1", 1));
    controller.addStudent(new Student("2", "2", "name2", 2));
    controller.addStudent(new Student("3", "3", "name3", 3));


  }

  @Test
  public void tc_1_AddStudentCorrectNameNotEmpty() {
    controller.addStudent(new Student("4", "4", "Joseph", 4));
    Assert.assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS +1);
  }

  //actually tc_2 can't change now due to how testlink works...
  @Test
  public void tc_1_AddStudentCorrectNameEmpty() {
    controller.addStudent(new Student("4", "4", "", 4));
    Assert.assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS);
  }

  @Test
  public void tc_3_AddStudentIdCorrect() {
    controller.addStudent(new Student("4", "4", "Joseph", 4));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS+1);
  }

  @Test
  public void tc_4_AddStudentIdEmpty() {
    controller.addStudent(new Student("", "4", "Joseph", 4));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS);
  }

  @Test
  public void tc_5_AddStudentSerialNumberCorrect() {
    controller.addStudent(new Student("4", "4", "Joseph", 4));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS+1);
  }

  @Test
  public void tc_6_AddStudentSerialNumberEmpty() {
    controller.addStudent(new Student("4", "", "Joseph", 4));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS);
  }

  @Test
  public void tc_7_AddStudentAgeCorrect() {
    controller.addStudent(new Student("4", "4", "Joseph", 4));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS+1);
  }

  @Test
  public void tc_8_AddStudentAgeSmallerThanZero() {
    controller.addStudent(new Student("4", "4", "Joseph", -1));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS);
  }

  @Test
  public void tc_9_AddStudentAgeLargerThan100() {
    controller.addStudent(new Student("4", "4", "Joseph", 101));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS);
  }
  @Test
  public void tc_10_AddStudentBVA_Age_minusOne() {
    controller.addStudent(new Student("4", "4", "Joseph", -1));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS);
  }
  @Test
  public void tc_11_AddStudentBVA_Age_zero() {
    controller.addStudent(new Student("4", "4", "Joseph", 0));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS+1);
  }
  @Test
  public void tc_12_AddStudentBVA_Age_one() {
    controller.addStudent(new Student("4", "4", "Joseph", 1));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS+1);
  }
  @Test
  public void tc_13_AddStudentBVA_Age_99() {
    controller.addStudent(new Student("4", "4", "Joseph", 99));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS+1);
  }
  @Test
  public void tc_14_AddStudentBVA_Age_100() {
    controller.addStudent(new Student("4", "4", "Joseph", 100));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS+1);
  }
  @Test
  public void tc_15_AddStudentBVA_Age_101() {
    controller.addStudent(new Student("4", "4", "Joseph", 101));
    Assert.assertEquals(controller.getNumberOfStudents(),NUMBER_OF_INITIAL_STUDENTS);
  }


}
