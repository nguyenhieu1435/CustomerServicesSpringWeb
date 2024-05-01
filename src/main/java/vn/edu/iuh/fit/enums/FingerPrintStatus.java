package vn.edu.iuh.fit.enums;

public enum FingerPrintStatus {
    // 0 repaired, 1 have problem, 2 normal, 3 have not

    REPAIRED(0), HAVE_PROBLEM(1), NORMAL(2), HAVE_NOT(3);

    private int value;

    private FingerPrintStatus(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public static FingerPrintStatus fromValue(int value)
    {
        for (FingerPrintStatus status : FingerPrintStatus.values())
        {
            if (status.getValue() == value)
            {
                return status;
            }
        }
        return null;
    }

}
