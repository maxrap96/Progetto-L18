package VendingMachineGUI;

import HotDrinkVendingMachine.HotDrinkVendMachine;

import java.util.Timer;
import java.util.TimerTask;

public class ResetDisplay {
    private final String DEFAULT_MESSAGE = "Scegliere una bevanda";
    private Display display;
    private HotDrinkVendMachine vendMachine;
    private Timer timer;
    private TimerTask timerTask;

    public ResetDisplay(Display display, HotDrinkVendMachine vendMachine) {
        this.timer = new Timer();
        this.display = display;
        this.vendMachine = vendMachine;
    }

    /**
     * Funzione che nel caso il credito sia 0 riporta il distributore ad uno stato di default.
     */
    protected void runTimer() {
        resetTimer();
        int time;
        if (vendMachine.getCredit() == 0) {
            time = 5000;
        }
        else {
            time = 10000;
        }

        timerTask = new TimerTask() {
            @Override
            public void run() {
                display.setBeverage(DEFAULT_MESSAGE);
                display.setBeverageCost("");
                if (vendMachine.getCredit() == 0) {
                    vendMachine.setSugarToDefault();
                }
                else {
                    display.setCreditRow(String.format("%.2f", vendMachine.getCredit()));
                }

                setDots();
            }
        };
        timer.schedule(timerTask,time);
    }

    /**
     * Funzione per resettare il timer e reinizializzarlo.
     */
    private void resetTimer() {
        timer.purge();
        timer.cancel();
        timer = new Timer();
    }

    /**
     * Funzione che aggiorna il display dello zucchero in base alla quantità selezionata.
     */
    protected void setDots() {
        String quantity;
        switch (vendMachine.getSelectedSugar()) {
            // u25cf pallino pieno
            // u25cb pallino vuoto
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

        display.setSugar(quantity);
    }
}
