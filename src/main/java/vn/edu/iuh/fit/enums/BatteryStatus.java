package vn.edu.iuh.fit.enums;

public enum BatteryStatus {
    // 0 repaired, 1 have problem, 2 normal
    REPAIRED(0), HAVE_PROBLEM(1), NORMAL(2);

    private int value;

    private BatteryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BatteryStatus fromValue(int value) {
        for (BatteryStatus status : BatteryStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }

}
