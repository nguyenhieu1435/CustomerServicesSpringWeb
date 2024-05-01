package vn.edu.iuh.fit.enums;

public enum DeliveryStatus {
    DELIVERING(0), DELIVERED(1), CANCELLED(2), QUALITY_CHECK(3), RETURNED(4)
    , ACCOUNTED(5);

    private int value;

    private DeliveryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DeliveryStatus fromValue(int value) {
        for (DeliveryStatus status : DeliveryStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }
}
