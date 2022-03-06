package Ability;

public enum ResultCode {

    SUCCESS(0),
    ON_COOLDOWN(10),
    ENERGY_SHORTAGE(11),
    NULL_ABILITY(12),
    NULL_COREDATA(13),
    ;

    private final int resultCode;
    ResultCode(int code) {
        this.resultCode = code;
    }

    public int getErrorCode() {
        return resultCode;
    }
}
