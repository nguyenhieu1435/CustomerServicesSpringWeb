package vn.edu.iuh.fit.enums;

public enum DisplayStatus {
    // 0 repaired, 1 have problem, 2 normal
    REPAIRED(0), HAVE_PROBLEM(1), NORMAL(2);

    private int value;

    private DisplayStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DisplayStatus fromValue(int value) {
        for (DisplayStatus status : DisplayStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }
}
