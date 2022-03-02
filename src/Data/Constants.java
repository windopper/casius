package Data;

public enum Constants {
    KEY_WAIT(20);


    int var;

    Constants(int var) {
        this.var = var;
    }

    public int getValue() { return this.var; }
}
