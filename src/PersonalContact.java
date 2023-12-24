import java.util.ArrayList;

public class PersonalContact extends Contact{
    private String familyName;

    public PersonalContact(String name, String familyName) {
        super(name, new ArrayList<Number>());
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
