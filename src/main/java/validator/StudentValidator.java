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
        if(entity.getID() == null){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getID().equals("")){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getName() == null){
            throw new ValidationException("Incorrect name!");
        }
        if(entity.getName().equals("")){
            throw new ValidationException("Incorrect name!");
        }
        if(entity.getAge() == null) {
            throw new ValidationException("Incorrect group!");
        }
        if(entity.getAge() < 0) {
            throw new ValidationException("Incorrect group!");
        }
        if(entity.getAge() > 100) {
            throw new ValidationException("Incorrect group!");
        }
        if(entity.getSerialNumber() == null){
            throw new ValidationException("Incorrect serial number!");
        }
        if(entity.getSerialNumber().equals("")){
            throw new ValidationException("Incorrect serial number!");
        }
    }
}
