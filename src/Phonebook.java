import java.util.ArrayList;

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

    public String setContactName(int index, String newName) {
        String oldName = myContacts.get(index).getName();
        myContacts.get(index).setName(newName);
        return "%s changed to %s".formatted(oldName, newName);
    }

    public String setSecondValue(int index, String newValue) {
        String oldValue;
        if(myContacts.get(index) instanceof PersonalContact) {
            oldValue = ((PersonalContact) myContacts.get(index)).getFamilyName();
            ((PersonalContact) myContacts.get(index)).setFamilyName(newValue);
        } else {
            oldValue = ((BusinessContact) myContacts.get(index)).getCommercialCode();
            ((BusinessContact) myContacts.get(index)).setCommercialCode(newValue);
        }
        return "%s changed to %s".formatted(oldValue, newValue);
    }

    public String setNumber(int index, String oldNumber, String newNumber) {
        boolean result = myContacts.get(index).editNumber(oldNumber, newNumber);
        return result? "%s changed to %s".formatted(oldNumber, newNumber) : "The number you want to edit not found";
    }

    public boolean addPersonalContact(String name, String familyName, String number, NumberType type) {
        if(findContact(name, familyName) >= 0) return false;
        if (number.matches("[0-9]+") && number.length() == 10) {
            PersonalContact newContact = new PersonalContact(name, familyName);
            myContacts.add(newContact);
            newContact.addNumber(number, type);
            return true;
        }
        return false;
    }

    public boolean addBusinessContact(String name, String commercialCode, String number, NumberType type) {
        if(findContact(name) >= 0) return false;
        if (number.matches("[0-9]+") && number.length() == 10) {
            BusinessContact newContact = new BusinessContact(name, commercialCode);
            myContacts.add(newContact);
            newContact.addNumber(number, type);
            return true;
        }
        return false;
    }

    public boolean addNumber(int index, String number, String type) {
        return myContacts.get(index).addNumber(number, toNumberType(type));
    }

    public boolean deleteContact(int index) {
        return deleteContact(myContacts.get(index));
    }

    private boolean deleteContact(Contact contact) {
        return myContacts.remove(contact);
    }

    public void printContact(int index) {
        System.out.println(myContacts.get(index));
    }

    public void printContacts() {
        System.out.println(name + "'s Contacts");
        System.out.println("  Name        Number");
        System.out.println("-".repeat(22));
        for(Contact c : myContacts) {
            System.out.println(c);
        }
    }

    public int findNumber(String number) {
        for(Contact c : myContacts) {
            for(Number n : c.getNumbers()) {
                if(n.getNumber().replaceAll("[()-]", "").equals(number)) {
                    return myContacts.indexOf(c);
                }
            }
        }
        return -1;
    }

    public int findContact(String name) {
        for(Contact c : myContacts) {
            if(c instanceof PersonalContact) {
                if(((PersonalContact) c).getFamilyName().equalsIgnoreCase(name)) {
                    return myContacts.indexOf(c);
                }
            }
            if(c.getName().equalsIgnoreCase(name)) {
                return myContacts.indexOf(c);
            }
        }
        return -1;
    }

    public int findContact(String name, String familyName) {
        for(Contact c : myContacts) {
            if(c instanceof PersonalContact) {
                if(c.getName().equalsIgnoreCase(name) &&
                        ((PersonalContact) c).getFamilyName().equalsIgnoreCase(familyName)) {
                    return myContacts.indexOf(c);
                }
            }
        }
        return -1;
    }


    public NumberType toNumberType(String str) {
        try {
            NumberType type = NumberType.valueOf(str.toUpperCase());
            return type;
        } catch (IllegalArgumentException ie) {
            System.out.println("No Type found for " + str);
        }
        return null;
    }


}