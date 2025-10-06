package ucu.edu.apps;

public enum FlowerColor {
    RED("#FF00000"), GREEN("#008000"), BLUE("#0000FF");

    private String hexColor;

    FlowerColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public String toString() {
        return this.hexColor;
    }
}
