package PersonalExceptions;

public class BeverageNotAvailable extends Exception{
    public BeverageNotAvailable(){
        super("Product not avaible. Please choose another one.");
    }
}
