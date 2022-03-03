package Data;

public enum Constants {

    DEFAULT_HEALTH(1000),
    DEFAULT_ENERGY(200),
    DEFAULT_HEALTH_REGEN(10),
    DEFAULT_ENERGY_REGEN(5),
    KEY_WAIT(20);

    final int var;

    Constants(int var) {
        this.var = var;
    }

    public int getValue() { return this.var; }
}
