import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PetDatabase {
    private static final String FILE_NAME = "pets.txt";
    private ArrayList<Pet> pets = new ArrayList<>();
    private Scanner in = new Scanner(System.in);

    public PetDatabase() {
        loadPetsFromFile();
    }

    // Method to add Pet to the database
    public void addPet(String name, int age) {
        if (pets.size() >= 5) {
            System.out.println("Error: Database is full.");
        } else if (age < 1 || age > 20) {
            System.out.println("Error: " + age + " is not a valid age.");
        } else {
            pets.add(new Pet(name, age));
        }
    }

    // Method to display all Pets in the database
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
            System.out.println("Error: ID " + id + " does not exist.");
        }
    }

    // Load pet data from file
    private void loadPetsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    pets.add(new Pet(name, age));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading pet data from file.");
        }
    }

    // Save pet data to file
    private void savePetsToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Pet pet : pets) {
                pw.println(pet.getName() + " " + pet.getAge());
            }
        } catch (IOException e) {
            System.out.println("Error saving pet data to file.");
        }
    }

    // Main method to run the program
    public void run() {
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add new pets");
            System.out.println("3) Remove a pet");
            System.out.println("4) Exit program");
            System.out.print("Your choice: ");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    showPets();
                    break;
                case 2:
                    while (true) {
                        if (pets.size() >= 5) {
                            System.out.println("Error: Database is full.");
                            break;
                        }
                        System.out.print("add pet (name, age): ");
                        String userInput = in.nextLine();
                        if (userInput.equalsIgnoreCase("done")) {
                            System.out.println(pets.size() + " pets added.");
                            break;
                        }
                        String[] parts = userInput.split(" ");
                        if (parts.length == 2) {
                            String name = parts[0];
                            int age;
                            try {
                                age = Integer.parseInt(parts[1]);
                                if (age < 1 || age > 20) {
                                    System.out.println("Error: " + age + " is not a valid age.");
                                } else {
                                    addPet(name, age);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Error: " + parts[1] + " is not a valid age.");
                            }
                        } else {
                            System.out.println("Error: " + userInput + " is not a valid input.");
                        }
                    }
                    break;
                case 3:
                    showPets();
                    System.out.print("Enter the pet ID to remove: ");
                    int id = in.nextInt();
                    in.nextLine();
                    removePet(id);
                    break;
                case 4:
                    savePetsToFile();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        new PetDatabase().run();
    }
}