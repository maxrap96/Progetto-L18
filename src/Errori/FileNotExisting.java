package Errori;

public class FileNotExisting extends Exception {
    public FileNotExisting (){
        super("File not existing. Please choose another one");
    }
}
