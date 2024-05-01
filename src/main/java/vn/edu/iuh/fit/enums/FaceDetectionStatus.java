package vn.edu.iuh.fit.enums;

public enum FaceDetectionStatus
{
    // 0 repaired, 1 have problem, 2 normal, 3 have not
    REPAIRED(0), HAVE_PROBLEM(1), NORMAL(2), HAVE_NOT(3);

    private int value;

    private FaceDetectionStatus(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public static FaceDetectionStatus fromValue(int value)
    {
        for (FaceDetectionStatus status : FaceDetectionStatus.values())
        {
            if (status.getValue() == value)
            {
                return status;
            }
        }
        return null;
    }
}
