import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Pet {
    String name;
    int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + age; // For reading pets from the file
    }
}

public class PetDatabase {
    private static final int MAX_PETS = 5;
    private static final String FILE_NAME = "pets.txt"; // File to save and load pet data
    private ArrayList<Pet> pets = new ArrayList<>();

    public PetDatabase() {
        loadPetsFromFile(); // Load pets when program starts
    }

    // Adds a new pet to the database
    public void addPet(String name, int age) {
        if (pets.size() < MAX_PETS) {
            pets.add(new Pet(name, age));
            System.out.println("Pet added.");
        } else {
            System.out.println("Error: Database is full.");
        }
    }

    // Displays all pets
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

    // Searches pets by name
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

    // Searches pets by age
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

    // Updates a pet's information
    public void updatePet(int id, String newName, int newAge) {
        if (id >= 0 && id < pets.size()) {
            Pet pet = pets.get(id);
            System.out.printf("%s %d changed to %s %d.\n", pet.name, pet.age, newName, newAge);
            pet.name = newName;
            pet.age = newAge;
        } else {
            System.out.println("Invalid pet ID.");
        }
    }

    // Removes a pet from the database
    public void removePet(int id) {
        if (id >= 0 && id < pets.size()) {
            Pet removedPet = pets.remove(id);
            System.out.printf("%s %d is removed.\n", removedPet.name, removedPet.age);
        } else {
            System.out.println("Invalid pet ID.");
        }
    }

    // Loads pets from pets.txt file at the start
    private void loadPetsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null && pets.size() < MAX_PETS) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    pets.add(new Pet(name, age));
                }
            }
            System.out.println("Pet data loaded from " + FILE_NAME);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading pet data: " + e.getMessage());
        }
    }

    // Saves pets to pets.txt file before exiting
    public void savePetsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Pet pet : pets) {
                writer.println(pet.toString());
            }
            System.out.println("Pet data saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving pet data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetDatabase database = new PetDatabase();

        while (true) {
            System.out.println("\nPet Database Program.");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Update an existing pet");
            System.out.println("4) Remove an existing pet");
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
                while (database.pets.size() < MAX_PETS) {
                    System.out.print("Enter pet name and age: ");
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("done")) break;

                    String[] parts = input.split(" ");
                    if (parts.length == 2) {
                        String name = parts[0];
                        int age = Integer.parseInt(parts[1]);
                        if (age >= 1 && age <= 20) {
                            database.addPet(name, age);
                        } else {
                            System.out.println("Error: Age must be between 1 and 20.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter 'name age'.");
                    }
                }
            } else if (choice == 3) {
                database.showPets();
                System.out.print("Enter the pet ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter new name and new age: ");
                String newName = scanner.next();
                int newAge = scanner.nextInt();
                database.updatePet(id, newName, newAge);
                scanner.nextLine();
            } else if (choice == 4) {
                database.showPets();
                System.out.print("Enter the pet ID to remove: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                database.removePet(id);
            } else if (choice == 5) {
                System.out.print("Enter a name to search: ");
                String searchName = scanner.nextLine();
                database.searchPetsByName(searchName);
            } else if (choice == 6) {
                System.out.print("Enter age to search: ");
                int searchAge = scanner.nextInt();
                scanner.nextLine();
                database.searchPetsByAge(searchAge);
            } else if (choice == 7) {
                database.savePetsToFile(); // Save pets before exiting
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}