package model;

public enum Type {
    CAR(1, "CAR"),
    MOTORCYCLE(2, "MOTORCYCLE"),
    TRUCK(3, "TRUCK");

    public int index;
    public String value;

    Type(int index, String value) {
        this.index=index;
        this.value=value;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}
