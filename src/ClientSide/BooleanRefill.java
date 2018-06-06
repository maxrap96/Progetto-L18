package ClientSide;

public class BooleanRefill {
    private boolean isCoinsRefilled = false;
    private boolean isItemRefilled = false;
    private boolean isBeverageRefilled = false;

    public boolean isCoinsRefilled() {
        return isCoinsRefilled;
    }

    public boolean isItemRefilled() {
        return isItemRefilled;
    }

    public boolean isBeverageRefilled() {
        return isBeverageRefilled;
    }

    public void setCoinsRefilled(boolean coinsRefilled) {
        isCoinsRefilled = coinsRefilled;
    }

    public void setItemRefilled(boolean itemRefilled) {
        isItemRefilled = itemRefilled;
    }

    public void setBeverageRefilled(boolean beverageRefilled) {
        isBeverageRefilled = beverageRefilled;
    }
}
