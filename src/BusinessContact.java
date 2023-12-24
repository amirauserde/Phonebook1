import java.util.ArrayList;

public class BusinessContact extends Contact{
    private String commercialCode;
    public BusinessContact(String name, String commercialCode) {
        super(name, new ArrayList<Number>());
        this.commercialCode = commercialCode;
    }

    public String getCommercialCode() {
        return commercialCode;
    }

    public void setCommercialCode(String commercialCode) {
        this.commercialCode = commercialCode;
    }
}
