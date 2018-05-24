package Distributore;

import ServerSide.StringCommandList;

import java.io.BufferedReader;
import java.io.IOException;

public class OverwriteCommand implements Command, StringCommandList {

    private ReceiverOverwrite receiverOverwrite;
    private BufferedReader bufferedReader;

    public OverwriteCommand(ReceiverOverwrite receiverOverwrite, BufferedReader bufferedReader) {
        this.receiverOverwrite = receiverOverwrite;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void execute() {
        try {
            String tmp;
            while((tmp = bufferedReader.readLine()) != null) {
                if(!tmp.equals(END_SENDING)) {
                    receiverOverwrite.overwriteFile(tmp);
                } else {
                    break;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
