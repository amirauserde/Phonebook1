public class Number {
    private NumberType type;
    private String number;

    public Number(NumberType type, String number) {
        this.type = type;
        this.number = numberFormat(number);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = numberFormat(number);
    }

    @Override
    public String toString() {
        return "%-10s%s".formatted(type, numberFormat(number));
    }
    private String numberFormat(String number) {
        return number.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
    }




}
