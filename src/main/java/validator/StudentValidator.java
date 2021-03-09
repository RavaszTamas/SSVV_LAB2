package validator;


import model.Student;
import repository.ValidationException;

public class StudentValidator implements Validator<Student> {

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        if(entity.getID().equals("")){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getID() == null){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getName() == ""){
            throw new ValidationException("Nume incorect!");
        }
        if(entity.getAge() < 0) {
            throw new ValidationException("Grupa incorecta!");
        }
        if(entity.getSerialNumber() == null){
            throw new ValidationException("Email incorect!");
        }
    }
}
