import java.util.ArrayList;
import java.util.Scanner;

public class PetDatabase {
    private ArrayList<Pet> pets = new ArrayList<>();
    private Scanner in = new Scanner(System.in);

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

    public void run() {
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Search pets by name");
            System.out.println("4) Search pets by age");
            System.out.println("5) Exit program");
            System.out.print("Your choice: ");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    showPets();
                    break;
                case 2:
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
                    System.out.print("Enter a name to search: ");
                    String name = in.nextLine();
                    searchPetsByName(name);
                    break;
                case 4:
                    System.out.print("Enter an age to search: ");
                    int age = in.nextInt();
                    in.nextLine(); // Consume newline
                    searchPetsByAge(age);
                    break;
                case 5:
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
