import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Phonebook amir = new Phonebook("Amir");
        menu(amir);
    }

    public static void menu(Phonebook phBook){

        System.out.println("Dear " + phBook.getName() + ", welcome to your Phonebook!");
        boolean menuRun = true;
        while (menuRun) {
            System.out.println("Please choose from the options:");
            System.out.println("""
                    1. Add contact
                    2. Print contacts
                    3. exit""");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addContact(phBook);
                case "2" -> phBook.printContacts();
                case "3" -> menuRun = false;
                default -> System.out.println("Wrong input!");
            }
        }
    }

    public static void addContact(Phonebook phonebook) {
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Number: ");
        String number = scanner.nextLine();
        boolean result = phonebook.addContact(name,number);
        if(result) {
            System.out.println("Contact added.");
        } else {
            System.out.println("Error adding contact. Please enter correct information.");
        }
    }
}