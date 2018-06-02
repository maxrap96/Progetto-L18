package ClientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static ServerSide.StringCommandList.END_SENDING;

public class RefillCommand implements Command {
    private ArrayList<String> arrayList;
    private BufferedReader bufferedReader;
    private ReceiverRefill receiverRefill;

    public RefillCommand(ArrayList<String> arrayList, BufferedReader bufferedReader, ReceiverRefill receiverRefill) {
        this.arrayList = arrayList;
        this.bufferedReader = bufferedReader;
        this.receiverRefill = receiverRefill;
    }

    @Override
    public void execute() {
        arrayList.clear();
        try {
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                if (!tmp.equals(END_SENDING)) {
                    arrayList.add(tmp);
                } else {
                    break;
                }
            }
            // Mettere funzione del receiver che salva l'array receiverRefill.doSomething();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
