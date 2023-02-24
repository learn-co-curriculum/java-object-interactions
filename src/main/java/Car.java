import java.util.Arrays;
import java.util.Random;

public class Car {

    private String make;
    private String model;
    private Tire[] tires;

    public Car(String make, String model) {
        this.make = make;
        this.model = model;

        Random random = new Random();
        tires = new Tire[4];
        for (int i=0; i<tires.length; i++) {
            // all have low air pressure
            tires[i] = new Tire(random.nextInt(24,27), true);
        }
    }

    @Override
    public String toString() {
        return "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", tires=" + Arrays.toString(tires);
    }
}


