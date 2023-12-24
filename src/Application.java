import java.util.Scanner;

public class Application {
    Scanner scanner = new Scanner(System.in);

    public void menu(Phonebook phBook){
        System.out.println("Dear " + phBook.getName() + ", welcome to your Phonebook!");
        boolean menuRun = true;
        while (menuRun) {
            System.out.println("Please choose from the options:");
            System.out.println("""
                    1. Find (& edit) contact
                    2. Add contact
                    3. Print contacts
                    4. exit""");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> contactMenu(phBook);
                case "2" -> addContact(phBook);
                case "3" -> phBook.printContacts();
                case "4" -> menuRun = false;
                default -> System.out.println("Wrong input!");
            }
        }
    }

    public void contactMenu(Phonebook phonebook) {
        System.out.println("Enter your search:");
        String search = scanner.nextLine();
        int searchIndex = generalSearch(phonebook, search);
        if (searchIndex == -1) {
            System.out.println("Nothing found!");
        } else {
            phonebook.printContact(searchIndex);
        }
        System.out.println("You have the following options: ");
        System.out.println("""
                1. Delete Contact
                2. Edit Contact
                3. Add number
                4. return""");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> phonebook.deleteContact(searchIndex);
            case "2" -> editContact(phonebook, searchIndex);
            case "3" -> addNumber(phonebook, searchIndex);
            case "4" -> {}
            default -> System.out.println("Wrong input!");
        }
    }

    public void addContact(Phonebook phonebook) {
        System.out.println("""
                Please Choose type of Contact:
                1. Personal
                2. Business""");
        int contactType = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        if(contactType == 1) {
            System.out.println("Family name: ");
        } else {
            System.out.println("Commercial code: ");
        }
        String secondFactor = scanner.nextLine();

        System.out.println("Number: ");
        String number = scanner.nextLine();

        System.out.println("Number Type: ");
        String numberType = scanner.nextLine();

        boolean result = false;
        if(contactType == 1) {
            result = phonebook.addPersonalContact(name, secondFactor, number, phonebook.toNumberType(numberType));
        } else {
            result = phonebook.addBusinessContact(name, secondFactor, number, phonebook.toNumberType(numberType));
        }

        if(result) {
            System.out.println("Contact added.");
        } else {
            System.out.println("Error adding contact. Please enter correct information.");
        }
    }

    public void addNumber(Phonebook phBook, int index) {
        System.out.println("Number: ");
        String number = scanner.nextLine();

        System.out.println("Number Type: ");
        String numberType = scanner.nextLine();
        boolean result = phBook.addNumber(index, number, numberType);
        System.out.println(result? "number added!" : "problem with your number");
    }

    public void editContact(Phonebook phBook, int index) {
        System.out.println("Choose the item to edit: ");
        System.out.println("""
                1. name
                2. family name / commercial number
                3. edit number""");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("Enter new name: ");
                System.out.println(phBook.setContactName(index, scanner.nextLine()));
            }
            case 2 -> {
                System.out.println("Enter new value: ");
                System.out.println(phBook.setSecondValue(index, scanner.nextLine()));
            }
            case 3 -> {
                System.out.println("Enter the number you want to change: ");
                String oldNumber = scanner.nextLine();
                System.out.println("Enter the new number: ");
                String newNumber = scanner.nextLine();
                System.out.println(phBook.setNumber(index,oldNumber, newNumber));
            }
        }
    }






    public int generalSearch(Phonebook phonebook, String search) {
        int result = phonebook.findContact(search);
        if(result >= 0) return result;
        return phonebook.findNumber(search);
    }
}
