import java.util.ArrayList;
import java.util.Scanner;

public class PetDatabase {
    // ArrayList to store pet attributes
    private ArrayList<Pet> pets = new ArrayList<>();
    private Scanner in = new Scanner(System.in);

    // Method to add Pet to the database
    public void addPet(String name, int age) {
        pets.add(new Pet(name, age));
    }

    //Method to display all Pets in the database
    public void showPets() {
        System.out.println("+-------------------------+");
        System.out.println("| ID  | NAME       |  AGE |");
        System.out.println("+-------------------------+");
        for (int i = 0; i < pets.size(); i++) {
            System.out.printf("| %3d | %-10s | %4d |\n", i, pets.get(i).getName(), pets.get(i).getAge());
        }
        System.out.println("+-------------------------+");
        System.out.println(pets.size() + " rows in set.");
    }

    // Method to Search Pets by name in the database
    public void searchPetsByName(String name) {
        System.out.println("+-------------------------+");
        System.out.println("| ID  | NAME       |  AGE |");
        System.out.println("+-------------------------+");
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getName().equalsIgnoreCase(name)) {
                System.out.printf("| %3d | %-10s | %4d |\n", i, pets.get(i).getName(), pets.get(i).getAge());
            }
        }
        System.out.println("+-------------------------+");
    }

    // Method to Search Pets by age in the database
    public void searchPetsByAge(int age) {
        System.out.println("+-------------------------+");
        System.out.println("| ID  | NAME       |  AGE |");
        System.out.println("+-------------------------+");
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getAge() == age) {
                System.out.printf("| %3d | %-10s | %4d |\n", i, pets.get(i).getName(), pets.get(i).getAge());
            }
        }
        System.out.println("+-------------------------+");
    }

    // Method to update Pet name, age in the database
    public void updatePet(int id, String newName, int newAge) {
        if (id >= 0 && id < pets.size()) {
            pets.get(id).setName(newName);
            pets.get(id).setAge(newAge);
        } else {
            System.out.println("Invalid pet ID.");
        }
    }

    // Method to remove a pet from the database
    public void removePet(int id) {
        if (id >= 0 && id < pets.size()) {
            pets.remove(id);
        } else {
            System.out.println("Invalid pet ID.");
        }
    }

    // Main method to run the program
    public void run() {
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Search pets by name");
            System.out.println("4) Search pets by age");
            System.out.println("5) Update an existing pet");
            System.out.println("6) Remove an existing pet");
            System.out.println("7) Exit program");
            System.out.print("Your choice: ");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                // view all pets
                    showPets();
                    break;
                case 2:
                // Add pets
                    while (true) {
                        System.out.print("add pet (name, age): ");
                        String userInput = in.nextLine();
                        if (userInput.equalsIgnoreCase("done")) {
                            System.out.println(pets.size() + " pets added.");
                            break;
                        }
                        String[] parts = userInput.split(" ");
                        if (parts.length == 2) {
                            String name = parts[0];
                            int age = Integer.parseInt(parts[1]);
                            addPet(name, age);
                        } else {
                            System.out.println("Invalid input. Please enter name and age.");
                        }
                    }
                    break;
                case 3:
                // Search for pets by name
                    System.out.print("Enter a name to search: ");
                    String name = in.nextLine();
                    searchPetsByName(name);
                    break;
                case 4:
                // Search for pets by age
                    System.out.print("Enter an age to search: ");
                    int age = in.nextInt();
                    in.nextLine(); // Consume newline
                    searchPetsByAge(age);
                    break;
                case 5:
                // Update pets name, age based on ID
                    showPets();
                    System.out.print("Enter the pet ID to update: ");
                    int idToUpdate = in.nextInt();
                    in.nextLine(); // Consume newline
                    System.out.print("Enter new name: ");
                    String newName = in.nextLine();
                    System.out.print("Enter new age: ");
                    int newAge = in.nextInt();
                    in.nextLine(); // Consume newline
                    updatePet(idToUpdate, newName, newAge);
                    break;
                case 6:
                // Remove pets by ID
                    showPets();
                    System.out.print("Enter the pet ID to remove: ");
                    int idToRemove = in.nextInt();
                    in.nextLine(); // Consume newline
                    removePet(idToRemove);
                    break;
                case 7:
                // Exits the program
                    System.out.println("Goodbye!");
                    return;
                default:
                // Error Handling message
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    // Main Method
    public static void main(String[] args) {
        new PetDatabase().run();
    }
}

