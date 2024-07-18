public class Pet {
    private String name;
    private int age;

    // initializes Pet Attributes
    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getter for Name
    public String getName() {
        return name;
    }

    // getter for Age
    public int getAge() {
        return age;
    }

    // setter for Name
    public void setName(String name) {
        this.name = name;
    }

    // getter for Age
    public void setAge(int age) {
        this.age = age;
    }

    // toString Method
    @Override
    public String toString() {
        return String.format("%-10s %4d", name, age);
    }
}