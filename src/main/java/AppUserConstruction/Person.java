package AppUserConstruction;

public abstract class Person {
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
