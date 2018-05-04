package PersonalExceptions;

public class FileNotWritable extends Exception {
    public FileNotWritable() {
        super("Error. Uncorrect file writing.");
    }
}