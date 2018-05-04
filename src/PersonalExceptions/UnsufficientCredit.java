package PersonalExceptions;

public class UnsufficientCredit extends Exception{
    public UnsufficientCredit(){
        super("Insufficient credit. Please insert more money.");
    }
}
