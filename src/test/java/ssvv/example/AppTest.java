package ssvv.example;

import ssvv.example.controller.Controller;
import ssvv.example.model.Assignment;
import ssvv.example.model.LaboratoryProblem;
import ssvv.example.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ssvv.example.repository.AssignmentRepository;
import ssvv.example.repository.LaboratoryRepository;
import ssvv.example.repository.StudentRepository;
import ssvv.example.repository.ValidationException;
import ssvv.example.validator.AssignmentValidator;
import ssvv.example.validator.LaboratoryProblemValidator;
import ssvv.example.validator.StudentValidator;
import ssvv.example.validator.Validator;

import static junit.framework.TestCase.*;

/** Unit test for simple App. */
public class AppTest {
  private StudentRepository studentRepository;
  private LaboratoryRepository laboratoryRepository;
  private AssignmentRepository assignmentRepository;
  private Controller controller;
  private Validator<Student> studentValidator;
  private Validator<LaboratoryProblem> laboratoryProblemValidator;
  private Validator<Assignment> assignmentValidator;
  private final int NUMBER_OF_INITIAL_STUDENTS = 3;
  private final int NUMBER_OF_INITIAL_ASSIGNMENTS = 1;

  @After
  public void tearDown() {
    assignmentRepository.deleteAll();
    studentRepository.deleteAll();
    laboratoryRepository.deleteAll();
    studentRepository = null;
    studentValidator = null;
    laboratoryProblemValidator = null;
    assignmentValidator = null;
    controller = null;
    //    File file = new File("files/test_students.txt");
    //    file.delete();
  }

  @Before
  public void setUp() {
    //    File file = new File("files/test_students.txt");
    //    try { file.createNewFile(); } catch (IOException ignored) { }
    assignmentRepository = new AssignmentRepository("files/test_assignments.txt");
    laboratoryRepository = new LaboratoryRepository("files/test_laboratory_problems.txt");
    studentRepository = new StudentRepository("files/test_students.txt");
    studentValidator = new StudentValidator();
    laboratoryProblemValidator = new LaboratoryProblemValidator();
    assignmentValidator = new AssignmentValidator();
    controller =
        new Controller(
            studentRepository,
            laboratoryRepository,
            assignmentRepository,
            studentValidator,
            laboratoryProblemValidator,
            assignmentValidator);
    controller.addStudent(new Student("1", "1", "name1", 1));
    controller.addStudent(new Student("2", "2", "name2", 2));
    controller.addStudent(new Student("3", "3", "name3", 3));

    controller.addLaboratoryProblem(new LaboratoryProblem("1", "description1", "name1"));
    controller.addLaboratoryProblem(new LaboratoryProblem("2", "description1", "name2"));
    controller.addLaboratoryProblem(new LaboratoryProblem("3", "description1", "name3"));

    controller.addAssignment(new Assignment("1", "1", "1"));
  }

  @Test
  public void tc_1_AddStudentCorrectNameNotEmpty() {
    controller.addStudent(new Student("4", "4", "Joseph", 4));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS + 1);
  }

  // actually tc_2 can't change now due to how testlink works...
  @Test
  public void tc_1_AddStudentCorrectNameEmpty() {
    controller.addStudent(new Student("4", "4", "", 4));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS);
  }

  @Test
  public void tc_3_AddStudentIdCorrect() {
    controller.addStudent(new Student("4", "4", "Joseph", 4));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS + 1);
  }

  @Test
  public void tc_4_AddStudentIdEmpty() {
    controller.addStudent(new Student("", "4", "Joseph", 4));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS);
  }

  @Test
  public void tc_5_AddStudentSerialNumberCorrect() {
    controller.addStudent(new Student("4", "4", "Joseph", 4));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS + 1);
  }

  @Test
  public void tc_6_AddStudentSerialNumberEmpty() {
    controller.addStudent(new Student("4", "", "Joseph", 4));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS);
  }

  @Test
  public void tc_7_AddStudentAgeCorrect() {
    controller.addStudent(new Student("4", "4", "Joseph", 4));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS + 1);
  }

  @Test
  public void tc_8_AddStudentAgeSmallerThanZero() {
    controller.addStudent(new Student("4", "4", "Joseph", -1));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS);
  }

  @Test
  public void tc_9_AddStudentAgeLargerThan100() {
    controller.addStudent(new Student("4", "4", "Joseph", 101));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS);
  }

  @Test
  public void tc_10_AddStudentBVA_Age_minusOne() {
    controller.addStudent(new Student("4", "4", "Joseph", -1));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS);
  }

  @Test
  public void tc_11_AddStudentBVA_Age_zero() {
    controller.addStudent(new Student("4", "4", "Joseph", 0));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS + 1);
  }

  @Test
  public void tc_12_AddStudentBVA_Age_one() {
    controller.addStudent(new Student("4", "4", "Joseph", 1));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS + 1);
  }

  @Test
  public void tc_13_AddStudentBVA_Age_99() {
    controller.addStudent(new Student("4", "4", "Joseph", 99));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS + 1);
  }

  @Test
  public void tc_14_AddStudentBVA_Age_100() {
    controller.addStudent(new Student("4", "4", "Joseph", 100));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS + 1);
  }

  @Test
  public void tc_15_AddStudentBVA_Age_101() {
    controller.addStudent(new Student("4", "4", "Joseph", 101));
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS);
  }

  @Test
  public void tc_1_wbt_AddAssignment_InvalidId() {
    controller.addAssignment(new Assignment("", "1", "1"));
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS);
  }

  @Test
  public void tc_2_wbt_AddAssignment_InvalidStudentId() {
    controller.addAssignment(new Assignment("2", "", "1"));
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS);
  }

  @Test
  public void tc_3_wbt_AddAssignment_NullId() {
    controller.addAssignment(new Assignment(null, "1", "1"));
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS);
  }

  @Test
  public void tc_4_wbt_AddAssignment_NullStudentId() {
    controller.addAssignment(new Assignment("2", null, "1"));
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS);
  }

  @Test
  public void tc_5_wbt_AddAssignment_InvalidLaboratoryProblemId() {
    controller.addAssignment(new Assignment("2", "1", ""));
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS);
  }

  @Test
  public void tc_6_wbt_AddAssignment_NullLaboratoryProblemId() {
    controller.addAssignment(new Assignment("2", "1", null));
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS);
  }

  @Test
  public void tc_7_wbt_AddAssignment_NonExitingStudentId() {
    try {
      controller.addAssignment(new Assignment("2", "1234", "1"));
      fail();
    } catch (ValidationException ex) {
      assertTrue(true);
    }
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS);
  }

  @Test
  public void tc_8_wbt_AddAssignment_NonExitingLaboratoryProblemId() {
    try {
      controller.addAssignment(new Assignment("2", "1", "1234"));
      fail();
    } catch (ValidationException ex) {
      assertTrue(true);
    }
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS);
  }
  @Test
  public void tc_9_wbt_AddAssignmentCorrectInput() {
    try {
      controller.addAssignment(new Assignment("2", "2", "2"));
      assertTrue(true);
    } catch (ValidationException ex) {
      fail();
    }
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS+1);
  }
  @Test
  public void tc_10_wbt_AddAssignment_ExistingAssignmentId() {
    try {
      controller.addAssignment(new Assignment("1", "2", "2"));
      fail();
    } catch (ValidationException ex) {
      assertTrue(true);
    }
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS);
  }
}
