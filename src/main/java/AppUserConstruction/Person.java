package AppUserConstruction;

public abstract class Person {
    //Instance variable representing a person's name
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    //Returns the name
    public String getName() {
        return name;
    }

    //Updates the name
    public void setName(String name) {
        this.name = name;
    }
}
