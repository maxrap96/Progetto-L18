import Distributore.Distributore;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Test {

    public static void main(String[] args) {
        Distributore macchinetta = new Distributore();
        macchinetta.textualInput();
        System.out.println("\ncheck");
    }
}