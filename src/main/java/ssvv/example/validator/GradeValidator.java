package ssvv.example.validator;

import ssvv.example.model.Grade;
import ssvv.example.repository.ValidationException;

public class GradeValidator implements Validator<Grade> {

  /**
   * Valideaza un student
   *
   * @param entity - studentul pe care il valideaza
   * @throws ValidationException - daca studentul nu e valid
   */
  @Override
  public void validate(Grade entity) throws ValidationException {
    if (entity.getGradeId() == null) {
      throw new ValidationException("Id incorect!");
    }
    if (entity.getGradeId().equals("")) {
      throw new ValidationException("Id incorect!");
    }
    if (entity.getAssignmentId() == null) {
      throw new ValidationException("Incorrect name!");
    }
    if (entity.getAssignmentId().equals("")) {
      throw new ValidationException("Incorrect name!");
    }
      if(entity.getGradeValue() < 0) {
          throw new ValidationException("Incorrect group!");
      }
      if(entity.getGradeValue() > 100) {
          throw new ValidationException("Incorrect group!");
      }
  }
}
