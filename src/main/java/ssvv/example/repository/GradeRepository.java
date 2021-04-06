package ssvv.example.repository;


import ssvv.example.model.Grade;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GradeRepository {

    private final String filename;
    private final List<Grade> grades;
    public GradeRepository(String filename) {
        this.filename = filename;
        grades = new ArrayList<>();
        loadFromFile();
    }

    public void loadFromFile() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                Grade grade = fromLine(line);
                grades.add(grade);
            }

        }
        catch (IOException exception){
            throw new ValidationException("Can't load from file!");
        }
    }

    private Grade fromLine(String line) {
        String[] params = line.split(",");
        return new Grade(params[0], params[1],Integer.parseInt(params[2]));

    }

    public Grade findById(String id)
    {
        return grades.stream().filter(item-> item.getGradeId().equals(id)).findFirst().orElse(null);
    }

    public void saveEntity(Grade grade) {
        grades.add(grade);
        saveToFile(grade);
    }

    public List<Grade> getAll() {
        return grades;
    }

    public void saveToFile(Grade grade){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true))) {
            bufferedWriter.write(grade.toString());
            bufferedWriter.newLine();
        } catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }
    }

    public void deleteAll() {
        grades.clear();
        writeToFile();
    }

    /**
     * Rescrie fisierul cu toate obiectele din memorie
     */
    public void writeToFile(){
        try (PrintWriter b = new PrintWriter(this.filename)) {
            grades.forEach(e -> b.println(e.toString()));
        }
        catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }

    }

}
