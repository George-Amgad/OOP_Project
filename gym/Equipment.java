package gym;
public class Equipment{
    private String name, code;
    private int quantity;
    // constructor
    public Equipment (String name,String code,int quantity){
        setName(name);
        setCode(code);
        setQuantity(quantity);
    }
    // setters and getters
    public void setName(String name){
        this.name = name ; 
    }
    public String getName(){
        return name;
    }
    public void setCode(String code){
        this.code = code ; 
    }
    public String getCode(){
        return code;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity ; 
    }
    public int getQuantity(){
        return quantity;
    }
}