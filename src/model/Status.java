package model;

public enum Status {
    NEW(1,"NEW"),
    IN_PROCESS(2, "IN_PROCESS"),
    FIXED(3, "FIXED"),
    RELEASED(4, "RELEASED");

    public int key;
    public String value;

    Status(int key, String value) {
        this.key=key;
        this.value=value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
