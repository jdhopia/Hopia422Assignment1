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

    public void addPet(String name, int age) {
        pets.add(new Pet(name, age));
    }

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

    public void removePet(int id) {
        if (id >= 0 && id < pets.size()) {
            pets.remove(id);
        } else {
            System.out.println("Error: ID " + id + " does not exist.");
        }
    }

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

    private void savePetsToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Pet pet : pets) {
                pw.println(pet.getName() + " " + pet.getAge());
            }
        } catch (IOException e) {
            System.out.println("Error saving pet data to file.");
        }
    }

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

    public static void main(String[] args) {
        new PetDatabase().run();
    }
}