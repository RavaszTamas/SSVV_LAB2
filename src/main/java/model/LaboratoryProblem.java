package model;

public class LaboratoryProblem {
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LaboratoryProblem(String ID, String description, String name) {
        this.ID = ID;
        this.description = description;
        Name = name;
    }

    private String ID;
    private String description;
    private String Name;

}
