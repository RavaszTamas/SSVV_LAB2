package ssvv.example.repository;

import ssvv.example.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final String filename;
    private final List<Student> students;
    public StudentRepository(String filename) {
        this.filename = filename;
        students = new ArrayList<>();
        loadFromFile();
    }

    public void loadFromFile() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                Student student = fromLine(line);
                students.add(student);
            }

        }
        catch (IOException exception){
            throw new ValidationException("Can't load from file!");
        }
    }

    private Student fromLine(String line) {
        String[] params = line.split(",");
        return new Student(params[0], params[1], params[2], Integer.parseInt(params[3]));

    }
    public Student findById(String id)
    {
        return students.stream().filter(item-> item.getID().equals(id)).findFirst().orElse(null);
    }


    public void saveEntity(Student student) {
        students.add(student);
        saveToFile(student);
    }

    public List<Student> getAll() {
        return students;
    }

    public void saveToFile(Student entity){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true))) {
            bufferedWriter.write(entity.toString());
            bufferedWriter.newLine();
        } catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }
    }

    public void deleteAll() {
        students.clear();
        writeToFile();
    }

    /**
     * Rescrie fisierul cu toate obiectele din memorie
     */
    public void writeToFile(){
        try (PrintWriter b = new PrintWriter(this.filename)) {
            students.forEach(e -> b.println(e.toString()));
        }
        catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }

    }

}
