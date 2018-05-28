//package GUI_FX_Server;
//
//import java.util.ArrayList;
//
//public class coinsObserver implements Observer {
//    private ArrayList<String> coins;
//    private HistogramChart hc;
//
//    public coinsObserver(HistogramChart hc, ArrayList<String> coins) {
//        this.coins = coins;
//        this.hc = hc;
//        this.hc.attach(this);
//    }
//
//    @Override
//    public void update() {
//        System.out.println("DEBUG\n");
//        hc.setBars(coins);
//        System.out.println("DEBUG\n");
//    }
//}
