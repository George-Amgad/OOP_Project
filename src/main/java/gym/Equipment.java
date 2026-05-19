package gym;

public class Equipment {
    private String name;
    private String code;
    private int quantity;

    public Equipment(String name, String code, int quantity) {
        setName(name);
        setCode(code);
        setQuantity(quantity);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}