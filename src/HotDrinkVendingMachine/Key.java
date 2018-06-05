package HotDrinkVendingMachine;

import java.io.IOException;
import java.util.ArrayList;
import static java.lang.Double.parseDouble;

public class Key {
    private String ID;
    private double balance = 0;
    private int line;
    private Data data = new Data("src/TextFiles/KeyData.txt");
    private String currentLine;
    private boolean connected = false;

    public Key() {
        ArrayList<String[]> keyText = data.readFile();
        ID = keyText.get(0)[0];
        balance = parseDouble(keyText.get(0)[1]);
        line = 0;
        currentLine = keyText.get(0)[0] + "\t" + keyText.get(0)[1];
        // initKey();
    }

    private void initKey() {
        ArrayList<String[]> keyText = data.readFile();

        for (int i = 0; i < keyText.size(); i++) {
            String id = keyText.get(i)[0];
            if (ID.equals(id)) {
                balance = parseDouble(keyText.get(i)[1]);
                line = i;
                currentLine = keyText.get(i)[0] + "\t" + keyText.get(i)[1];
            }
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected() {
        connected = !connected;
    }

    public double getKeyBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        balance = (balance * 1000 + amount * 1000) / 1000;
        balance = Math.floor(balance * 100) / 100;
        String newLine = ID + "\t" + balance;
        newLine.replace(",","."); // Altrimenti al successivo riavvio non si riesce a leggere il file
        try {
            data.overwriteFile(newLine, currentLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean pay(double price) {
        if (balance > price) {
            balance -= price;
            balance = Math.floor(balance * 100) / 100;
            String newLine = ID + "\t" + balance;
            try {
                data.overwriteFile(newLine, currentLine);
            } catch (IOException e) {
            }
            setConnected(); // Si presuppone che dopo l'erogazione si estragga la chivetta
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\nsaldo: " + balance;
    }
}
