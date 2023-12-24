import java.util.ArrayList;

enum NumberType {
    HOME, OFFICE, MOBILE, FAX, OTHER
}
public abstract class Contact {
    private String name;
    private ArrayList<Number> numbers;

    public Contact(String name, ArrayList<Number> numbers) {
        this.name = name;
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Number> getNumbers() {
        return numbers;
    }

    public void setName(String name) {
        this.name = name;
    }

    boolean addNumber(String number, NumberType type) {
        if(findNumber(number) >= 0) return false;
        this.numbers.add(new Number(type, number));
        return true;
    }

    boolean removeNumber(String number) {
        int id = findNumber(number);
        if(id < 0) return false;
        numbers.remove(id);
        return true;
    }

    boolean editNumber(String oldNumber, String newNumber) {
        int id = findNumber(oldNumber);
        if(id < 0) return false;
        numbers.get(id).setNumber(newNumber);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder contact = new StringBuilder();
        contact.append(name).append("\n");
        for(Number n : numbers) {
            contact.append(n).append("\n");
        }
        return contact.toString();
    }


    private int findNumber(String number) {
        for(Number n : numbers) {
            if(number.equals(n.getNumber())) {
                return numbers.indexOf(n);
            }
        }
        return -1;
    }
}
