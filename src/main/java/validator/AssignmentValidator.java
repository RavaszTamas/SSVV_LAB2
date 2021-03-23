package validator;


import model.Assignment;
import model.Student;
import repository.ValidationException;

public class AssignmentValidator implements Validator<Assignment> {

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Assignment entity) throws ValidationException {
        if(entity.getId().equals("")){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getId() == null){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getLaboratoryProblemId().equals("")){
            throw new ValidationException("Incorrect name!");
        }
        if(entity.getLaboratoryProblemId() == null){
            throw new ValidationException("Incorrect name!");
        }
        if(entity.getStudentId().equals("")){
            throw new ValidationException("Incorrect name!");
        }
        if(entity.getStudentId() == null){
            throw new ValidationException("Incorrect name!");
        }
    }
}
