package gym;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Equipment {
    private String name;
    private String code;
    private int quantity;

    @JsonCreator
    public Equipment(@JsonProperty("name") String name,
                     @JsonProperty("code") String code,
                     @JsonProperty("quantity") int quantity) {
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