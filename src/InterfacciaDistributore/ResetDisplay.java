package InterfacciaDistributore;

import Distributore.Distributore;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class ResetDisplay extends Thread{

    private final String DEFAULTMESSAGE = "     SCEGLIERE UNA BEVANDA";
    private JTextArea display;
    private JTextField sugarDisplay;
    private Distributore distributoreR;

    public ResetDisplay(JTextArea display, JTextField sugarDisplay, Distributore distributore) {
        this.display = display;
        this.sugarDisplay = sugarDisplay;
        this.distributoreR = distributore;
    }

    /**
     * funzione che nel caso il credito sia 0 riporta la macchinetta ad uno stato di default
     */

    public void run(){
        java.util.Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (distributoreR.getCredit() == 0){
                    display.setText(DEFAULTMESSAGE);
                }
                else {
                    display.setText(DEFAULTMESSAGE + "\n\n\nCredito: " + String.format("%.2f",distributoreR.getCredit()));
                }
                distributoreR.setSugarToDefault();
                System.out.println(distributoreR.getCredit());
                setDots();
            }
        };

        if (distributoreR.getCredit() == 0) {
            timer.schedule(timerTask, 9000);
        }
        else {
            timer.schedule(timerTask, 4000);
        }
    }


    /**
     * Funzione che aggiorna il display dello zucchero in base alla quantità selezionata
     */

    public void setDots(){
        String quantity;
        switch (distributoreR.getSelected_sugar()){
            //u25cf è pallino pieno
            //u25cb è pallino vuoto
            case 0:
                quantity = "Senza zucchero";
                break;
            case 1:
                quantity = "- \u25cf \u25cb \u25cb \u25cb \u25cb +";
                break;
            case 2:
                quantity = "- \u25cf \u25cf \u25cb \u25cb \u25cb +";
                break;
            case 3:
                quantity = "- \u25cf \u25cf \u25cf \u25cb \u25cb +";
                break;
            case 4:
                quantity = "- \u25cf \u25cf \u25cf \u25cf \u25cb +";
                break;
            case 5:
                quantity = "- \u25cf \u25cf \u25cf \u25cf \u25cf +";
                break;
            default:
                quantity = "- \u25cf \u25cf \u25cf \u25cb \u25cb +";
                break;
        }

        sugarDisplay.setText(quantity);
    }
}
