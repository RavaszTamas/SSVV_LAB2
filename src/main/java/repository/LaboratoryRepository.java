package repository;

import model.Assignment;
import model.LaboratoryProblem;
import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryRepository {
    private final String filename;
    private final List<LaboratoryProblem> laboratoryProblems;
    public LaboratoryRepository(String filename) {
        this.filename = filename;
        laboratoryProblems = new ArrayList<>();
        loadFromFile();
    }

    public void loadFromFile() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                LaboratoryProblem laboratoryProblem = fromLine(line);
                laboratoryProblems.add(laboratoryProblem);
            }

        }
        catch (IOException exception){
            throw new ValidationException("Can't load from file!");
        }
    }

    private LaboratoryProblem fromLine(String line) {
        String[] params = line.split(",");
        return new LaboratoryProblem(params[0], params[1], params[2]);

    }


    public void saveEntity(LaboratoryProblem laboratoryProblem) {
        laboratoryProblems.add(laboratoryProblem);
        saveToFile(laboratoryProblem);
    }

    public List<LaboratoryProblem> getAll() {
        return laboratoryProblems;
    }

    public LaboratoryProblem findById(String id)
    {
        return laboratoryProblems.stream().filter(item-> item.getID().equals(id)).findFirst().orElse(null);
    }

    public void saveToFile(LaboratoryProblem laboratoryProblem){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true))) {
            bufferedWriter.write(laboratoryProblem.toString());
            bufferedWriter.newLine();
        } catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }
    }

    public void deleteAll() {
        laboratoryProblems.clear();
        writeToFile();
    }

    /**
     * Rescrie fisierul cu toate obiectele din memorie
     */
    public void writeToFile(){
        try (PrintWriter b = new PrintWriter(this.filename)) {
            laboratoryProblems.forEach(e -> b.println(e.toString()));
        }
        catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }

    }

}
