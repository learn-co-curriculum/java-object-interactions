public class Tire {
    private int airPressure;
    private boolean clean;
    public static int MIN_RECOMMENDED_PRESSURE = 28;
    public static int MAX_RECOMMENDED_PRESSURE = 34;

    public Tire(int airPressure, boolean clean) {
        this.airPressure = airPressure;
        this.clean = clean;
    }

    public boolean isClean() {
        return clean;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }

    public void checkAirPressure() {
        if (airPressure < MIN_RECOMMENDED_PRESSURE)
            airPressure = MAX_RECOMMENDED_PRESSURE;
    }

    @Override
    public String toString() {
        return "{" +
                "airPressure=" + airPressure +
                ", clean=" + clean +
                '}';
    }
}

