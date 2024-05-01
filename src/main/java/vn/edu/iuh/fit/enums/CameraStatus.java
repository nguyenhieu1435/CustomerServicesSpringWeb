package vn.edu.iuh.fit.enums;

public enum CameraStatus {
    // 0 repaired, 1 have problem, 2 normal
    REPAIRED(0), HAVE_PROBLEM(1), NORMAL(2);

    private int value;

    private CameraStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CameraStatus fromValue(int value) {
        for (CameraStatus status : CameraStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }
}
