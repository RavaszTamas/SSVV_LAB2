package repository;


import model.Assignment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentRepository {
    private final String filename;
    private final List<Assignment> laboratoryProblems;
    public AssignmentRepository(String filename) {
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
                Assignment laboratoryProblem = fromLine(line);
                laboratoryProblems.add(laboratoryProblem);
            }

        }
        catch (IOException exception){
            throw new ValidationException("Can't load from file!");
        }
    }

    private Assignment fromLine(String line) {
        String[] params = line.split(",");
        return new Assignment(params[0], params[1], params[2]);

    }


    public void saveEntity(Assignment laboratoryProblem) {
        laboratoryProblems.add(laboratoryProblem);
        saveToFile(laboratoryProblem);
    }

    public List<Assignment> getAll() {
        return laboratoryProblems;
    }

    public void saveToFile(Assignment laboratoryProblem){
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
