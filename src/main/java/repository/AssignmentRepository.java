package repository;


import model.Assignment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentRepository {
    private final String filename;
    private final List<Assignment> assignments;
    public AssignmentRepository(String filename) {
        this.filename = filename;
        assignments = new ArrayList<>();
        loadFromFile();
    }

    public void loadFromFile() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                Assignment laboratoryProblem = fromLine(line);
                assignments.add(laboratoryProblem);
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

    public Assignment findById(String id)
    {
        return assignments.stream().filter(item-> item.getId().equals(id)).findFirst().orElse(null);
    }

    public void saveEntity(Assignment laboratoryProblem) {
        assignments.add(laboratoryProblem);
        saveToFile(laboratoryProblem);
    }

    public List<Assignment> getAll() {
        return assignments;
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
        assignments.clear();
        writeToFile();
    }

    /**
     * Rescrie fisierul cu toate obiectele din memorie
     */
    public void writeToFile(){
        try (PrintWriter b = new PrintWriter(this.filename)) {
            assignments.forEach(e -> b.println(e.toString()));
        }
        catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }

    }

}
