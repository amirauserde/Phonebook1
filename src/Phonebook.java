import java.util.ArrayList;

record Contact (String name, String number) {
    @Override
    public String toString() {
        return "%-10s%s".formatted(name, number);
    }
}
public class Phonebook {
    private String name;
    private ArrayList<Contact> myContacts;

    public Phonebook(String name) {
        this.name = name;
        myContacts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean addContact(String name, String number) {
        if(findContact(name)) return false;
        if (number.matches("[0-9]+") && number.length() == 10) {
            myContacts.add(new Contact(name, numberFormat(number)));
            return true;
        }
        return false;
    }

    public void printContacts() {
        System.out.println(name + "'s Contacts");
        System.out.println("  Name        Number");
        System.out.println("-".repeat(22));
        for(Contact c : myContacts) {
            System.out.println(c.toString());
        }
    }

    private String numberFormat(String number) {
        return number.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
    }

    private boolean findContact(String name) {
        for(Contact c : myContacts) {
            if(c.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}