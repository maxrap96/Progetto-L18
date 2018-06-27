package ClientSide;

/**
 * Classe che contiene vairabili booleane controllate dall'UpdateChecker.
 */

public class BooleanRefill {
    private boolean isCoinsRefilled = false;
    private boolean isItemRefilled = false;
    private boolean isIngredientsRefilled = false;

    public boolean isCoinsRefilled() {
        return isCoinsRefilled;
    }

    public boolean isItemRefilled() {
        return isItemRefilled;
    }

    public boolean isIngredientsRefilled() {
        return isIngredientsRefilled;
    }

    public void setCoinsRefilled(boolean coinsRefilled) {
        isCoinsRefilled = coinsRefilled;
    }

    public void setItemRefilled(boolean itemRefilled) {
        isItemRefilled = itemRefilled;
    }

    public void setIngredientsRefilled(boolean ingredientsRefilled) {
        isIngredientsRefilled = ingredientsRefilled;
    }
}
