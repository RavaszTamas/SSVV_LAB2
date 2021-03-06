package ssvv.example.validator;


import ssvv.example.model.LaboratoryProblem;
import ssvv.example.repository.ValidationException;

public class LaboratoryProblemValidator implements Validator<LaboratoryProblem> {

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(LaboratoryProblem entity) throws ValidationException {
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
        if(entity.getDescription() == null){
            throw new ValidationException("Incorrect name!");
        }
        if(entity.getDescription().equals("")){
            throw new ValidationException("Incorrect name!");
        }
    }
}
