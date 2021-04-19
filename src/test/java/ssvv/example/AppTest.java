package ssvv.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ssvv.example.controller.Controller;
import ssvv.example.model.Assignment;
import ssvv.example.model.Grade;
import ssvv.example.model.LaboratoryProblem;
import ssvv.example.model.Student;
import ssvv.example.repository.*;
import ssvv.example.validator.*;

import static junit.framework.TestCase.*;

/** Unit test for simple App. */
public class AppTest {
  private StudentRepository studentRepository;
  private LaboratoryRepository laboratoryRepository;
  private AssignmentRepository assignmentRepository;
  private GradeRepository gradeRepository;
  private Controller controller;
  private Validator<Student> studentValidator;
  private Validator<LaboratoryProblem> laboratoryProblemValidator;
  private Validator<Assignment> assignmentValidator;
  private Validator<Grade> gradeValidator;
  private final int NUMBER_OF_INITIAL_STUDENTS = 4;
  private final int NUMBER_OF_INITIAL_ASSIGNMENTS = 2;
  private final int NUMBER_OF_INITIAL_GRADES = 1;

  @After
  public void tearDown() {
    assignmentRepository.deleteAll();
    studentRepository.deleteAll();
    laboratoryRepository.deleteAll();
    gradeRepository.deleteAll();
    studentRepository = null;
    gradeRepository = null;
    studentValidator = null;
    laboratoryProblemValidator = null;
    assignmentValidator = null;
    gradeValidator = null;
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
    gradeRepository = new GradeRepository("files/test_grades.txt");
    studentValidator = new StudentValidator();
    laboratoryProblemValidator = new LaboratoryProblemValidator();
    assignmentValidator = new AssignmentValidator();
    gradeValidator = new GradeValidator();
    controller =
        new Controller(
            studentRepository,
            laboratoryRepository,
            assignmentRepository,
            gradeRepository,
            studentValidator,
            laboratoryProblemValidator,
            assignmentValidator,
            gradeValidator);
    controller.addStudent(new Student("1", "1", "name1", 1));
    controller.addStudent(new Student("2", "2", "name2", 2));
    controller.addStudent(new Student("3", "3", "name3", 3));
    controller.addStudent(new Student("31", "31", "name31", 31));

    controller.addLaboratoryProblem(new LaboratoryProblem("1", "description1", "name1"));
    controller.addLaboratoryProblem(new LaboratoryProblem("2", "description1", "name2"));
    controller.addLaboratoryProblem(new LaboratoryProblem("3", "description1", "name3"));
    controller.addLaboratoryProblem(new LaboratoryProblem("31", "description31", "name31"));

    controller.addAssignment(new Assignment("1", "1", "1"));
    controller.addGrade(new Grade("1", "1", 6));
    controller.addAssignment(new Assignment("31", "31", "31"));
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
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS + 1);
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

  @Test
  public void tc_1_BB_AddStudentCorrect() {
    try {
      controller.addStudent(new Student("4", "4", "Joseph", 20));
      assertTrue(true);
    } catch (ValidationException ex) {
      fail();
    }
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS+1);
  }
  @Test
  public void tc_2_BB_AddAssignmentCorrect() {
    try {
      controller.addAssignment(new Assignment("2", "2", "2"));
      assertTrue(true);
    } catch (ValidationException ex) {
      fail();
    }
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS+1);
  }
  @Test
  public void tc_3_BB_AddGradeCorrect() {
    try {
      controller.addGrade(new Grade("2", "31", 6));
      assertTrue(true);
    } catch (ValidationException ex) {
      fail();
    }
    assertEquals(controller.getNumberOfGrades(), NUMBER_OF_INITIAL_GRADES+1);
  }
  @Test
  public void tc_4_integrationT_AddStudentAddGradeAddAssignmentCorrect() {
    try {
      controller.addStudent(new Student("4", "4", "Joseph", 20));
      controller.addAssignment(new Assignment("2", "4", "2"));
      controller.addGrade(new Grade("2", "2", 6));
      assertTrue(true);
    } catch (ValidationException ex) {
      fail();
    }
    assertEquals(controller.getNumberOfGrades(), NUMBER_OF_INITIAL_GRADES+1);
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS+1);
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS+1);
  }
  @Test
  public void tc_1_incremental_integration_top_down_AddStudentCorrect() {
    try {
      controller.addStudent(new Student("4", "4", "Joseph", 20));
      assertTrue(true);
    } catch (ValidationException ex) {
      fail();
    }
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS+1);
  }
  @Test
  public void tc_2_incremental_integration_top_down_AddAssignmentCorrect() {
    try {
      controller.addStudent(new Student("4", "4", "Joseph", 20));
      controller.addAssignment(new Assignment("2", "4", "2"));
      assertTrue(true);
    } catch (ValidationException ex) {
      fail();
    }
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS+1);
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS+1);
  }
  @Test
  public void tc_3_incremental_integration_top_down_AddGradeCorrect() {
    try {
      controller.addStudent(new Student("4", "4", "Joseph", 20));
      controller.addAssignment(new Assignment("2", "4", "2"));
      controller.addGrade(new Grade("2", "2", 6));
      assertTrue(true);
    } catch (ValidationException ex) {
      fail();
    }
    assertEquals(controller.getNumberOfStudents(), NUMBER_OF_INITIAL_STUDENTS+1);
    assertEquals(controller.getNumberOfAssignemnts(), NUMBER_OF_INITIAL_ASSIGNMENTS+1);
    assertEquals(controller.getNumberOfGrades(), NUMBER_OF_INITIAL_GRADES+1);
  }
}
