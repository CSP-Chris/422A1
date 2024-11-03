import java.util.ArrayList;
import java.util.Scanner;

class Pet {
    String name;
    int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class PetDatabase {
    private ArrayList<Pet> pets = new ArrayList<>();

    public void addPet(String name, int age) {
        pets.add(new Pet(name, age));
    }

    public void showPets() {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        for (int i = 0; i < pets.size(); i++) {
            System.out.printf("| %2d | %-10s | %3d |\n", i, pets.get(i).name, pets.get(i).age);
        }

        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", pets.size());
    }

    // New method for searching pets by name
    public void searchPetsByName(String searchName) {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        int count = 0;
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).name.equalsIgnoreCase(searchName)) { // Case-insensitive search
                System.out.printf("| %2d | %-10s | %3d |\n", i, pets.get(i).name, pets.get(i).age);
                count++;
            }
        }

        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", count); // Display count of matched rows
    }

    // New method for searching pets by age
    public void searchPetsByAge(int searchAge) {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        int count = 0;
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).age == searchAge) { // Match pets with the specified age
                System.out.printf("| %2d | %-10s | %3d |\n", i, pets.get(i).name, pets.get(i).age);
                count++;
            }
        }

        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", count); // Display count of matched rows
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetDatabase database = new PetDatabase();

        while (true) {
            System.out.println("\nPet Database Program.");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit program");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                database.showPets();
            } else if (choice == 2) {
                System.out.println("Add pets (type 'done' to finish):");
                while (true) {
                    System.out.print("Enter pet name and age: ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("done")) break;

                    String[] parts = input.split(" ");
                    if (parts.length == 2) {
                        String name = parts[0];
                        int age = Integer.parseInt(parts[1]);
                        database.addPet(name, age);
                        System.out.println("Pet added.");
                    } else {
                        System.out.println("Invalid input. Please enter 'name age'.");
                    }
                }
            } else if (choice == 5) {
                System.out.print("Enter a name to search: ");
                String searchName = scanner.nextLine();
                database.searchPetsByName(searchName); // Call the search by name method
            } else if (choice == 6) {
                System.out.print("Enter age to search: ");
                int searchAge = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                database.searchPetsByAge(searchAge); // Call the search by age method
            } else if (choice == 7) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
