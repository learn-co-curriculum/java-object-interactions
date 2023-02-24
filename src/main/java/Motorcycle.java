import java.util.Random;

public class Motorcycle {

    private String make;
    private String model;
    private Tire frontTire, rearTire;

    public Motorcycle(String make, String model) {
        this.make = make;
        this.model = model;

        //initialize random tire pressure, both tires  clean
        Random random = new Random();
        frontTire = new Tire(random.nextInt(24, 34), true);
        rearTire = new Tire(random.nextInt(24, 34), true);
    }

    public void rideThroughMud() {
        frontTire.setClean(false);
        rearTire.setClean(false);
    }

    @Override
    public String toString() {
        return "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", frontTire=" + frontTire +
                ", rearTire=" + rearTire ;
    }
}
