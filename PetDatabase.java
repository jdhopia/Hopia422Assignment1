import java.util.ArrayList;
import java.util.Scanner;

public class PetDatabase {
    private ArrayList<Pet> pets = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addPet(String name, int age) {
        pets.add(new Pet(name, age));
    }

    public void showPets() {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
        for (int i = 0; i < pets.size(); i++) {
            System.out.printf("| %2d | %-10s | %3d |\n", i, pets.get(i).getName(), pets.get(i).getAge());
        }
        System.out.println("+----------------------+");
        System.out.println(pets.size() + " rows in set.");
    }

    public void run() {
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Exit program");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                showPets();
            } else if (choice == 2) {
                while (true) {
                    System.out.print("add pet (name, age): ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("done")) {
                        break;
                    }
                    String[] parts = input.split(" ");
                    if (parts.length == 2) {
                        String name = parts[0];
                        int age = Integer.parseInt(parts[1]);
                        addPet(name, age);
                    } else {
                        System.out.println("Invalid input. Please enter name and age.");
                    }
                }
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        new PetDatabase().run();
    }
}
