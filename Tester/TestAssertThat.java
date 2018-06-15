import HotDrinkVendingMachine.HotDrinkVendMachine;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestAssertThat {

    @Test
    public void assertSugar () {
        // Creazione della macchinetta, lo zucchero ha valore di default
        HotDrinkVendMachine hotDrinkVendMachine = new HotDrinkVendMachine();
        assertEquals(3, hotDrinkVendMachine.getSelectedSugar());
    }

    @Test
    public void assertMoreSugar () {
        // Aumento dello zucchero
        HotDrinkVendMachine hotDrinkVendMachine = new HotDrinkVendMachine();
        hotDrinkVendMachine.moreSugar();
        assertEquals(4, hotDrinkVendMachine.getSelectedSugar());
    }

    @Test
    public void assertNoSugar () {
        // Prova di riduzione dello zucchero sotto la soglia, rimane a zero
        HotDrinkVendMachine hotDrinkVendMachine = new HotDrinkVendMachine();
        hotDrinkVendMachine.lessSugar();
        hotDrinkVendMachine.lessSugar();
        hotDrinkVendMachine.lessSugar();
        hotDrinkVendMachine.lessSugar();
        assertEquals(0, hotDrinkVendMachine.getSelectedSugar());
    }

    @Test
    public void assertCredit () {
        // I soldi non ci sono quando la macchinetta viene creata
        HotDrinkVendMachine hotDrinkVendMachine = new HotDrinkVendMachine();
        double credit = hotDrinkVendMachine.getCredit();
        assert credit == 0;
    }

    @Test
    public void assertCreditAdded () {
        // Aggiunta dei soldi
        HotDrinkVendMachine hotDrinkVendMachine = new HotDrinkVendMachine();
        hotDrinkVendMachine.addCredit(0.50);
        double credit = hotDrinkVendMachine.getCredit();
        assert credit == 0.50;
    }

    @Test
    public void assertCreditAddedAndCancel () {
        // Aggiunta dei soldi, non si compra niente e ci si fanno restituire i soldi
        HotDrinkVendMachine hotDrinkVendMachine = new HotDrinkVendMachine();
        hotDrinkVendMachine.addCredit(0.50);
        hotDrinkVendMachine.giveChange();
        double credit = hotDrinkVendMachine.getCredit();
        assert credit == 0;
    }

    @Test
    public void assertSaleNotGoneWell () {
        // Prova di acquisto di una bevanda che costa troppo, i soldi restano nella macchinetta
        HotDrinkVendMachine hotDrinkVendMachine = new HotDrinkVendMachine();
        hotDrinkVendMachine.addCredit(0.20);
        hotDrinkVendMachine.selectBeverage("01");
        double credit = hotDrinkVendMachine.getCredit();
        assert credit == 0.20;
    }
}
