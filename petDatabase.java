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

    public void searchPetsByName(String searchName) {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        int count = 0;
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).name.equalsIgnoreCase(searchName)) {
                System.out.printf("| %2d | %-10s | %3d |\n", i, pets.get(i).name, pets.get(i).age);
                count++;
            }
        }

        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", count);
    }

    public void searchPetsByAge(int searchAge) {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        int count = 0;
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).age == searchAge) {
                System.out.printf("| %2d | %-10s | %3d |\n", i, pets.get(i).name, pets.get(i).age);
                count++;
            }
        }

        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", count);
    }

    // New method to update a pet's information (V3)
    public void updatePet(int id, String newName, int newAge) {
        if (id >= 0 && id < pets.size()) { // Check if the ID is valid
            Pet pet = pets.get(id);
            System.out.printf("%s %d changed to %s %d.\n", pet.name, pet.age, newName, newAge);
            pet.name = newName; // Update name
            pet.age = newAge; // Update age
        } else {
            System.out.println("Invalid pet ID.");
        }
    }

    // New method to remove a pet from the database (V3)
    public void removePet(int id) {
        if (id >= 0 && id < pets.size()) { // Check if the ID is valid
            Pet removedPet = pets.remove(id); // Remove pet by ID
            System.out.printf("%s %d is removed.\n", removedPet.name, removedPet.age);
        } else {
            System.out.println("Invalid pet ID.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetDatabase database = new PetDatabase();

        while (true) {
            System.out.println("\nPet Database Program.");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Update an existing pet"); // New option for updating pets (V3)
            System.out.println("4) Remove an existing pet"); // New option for removing pets (V3)
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
            } else if (choice == 3) {
                // Prompt for ID and new information to update a pet
                database.showPets(); // Display pets for reference
                System.out.print("Enter the pet ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new name and new age: ");
                String newName = scanner.next();
                int newAge = scanner.nextInt();
                database.updatePet(id, newName, newAge); // Call update method
                scanner.nextLine(); // Consume newline
            } else if (choice == 4) {
                // Prompt for ID to remove a pet
                database.showPets(); // Display pets for reference
                System.out.print("Enter the pet ID to remove: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                database.removePet(id); // Call remove method
            } else if (choice == 5) {
                System.out.print("Enter a name to search: ");
                String searchName = scanner.nextLine();
                database.searchPetsByName(searchName);
            } else if (choice == 6) {
                System.out.print("Enter age to search: ");
                int searchAge = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                database.searchPetsByAge(searchAge);
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
