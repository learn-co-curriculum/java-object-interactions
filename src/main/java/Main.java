public class Main {

    public static void main(String[] args) {
        Motorcycle myHarley = new Motorcycle("Harley Davidson", "Sportster");
        System.out.println(myHarley);

        System.out.println("Ride through mud");
        myHarley.rideThroughMud();
        System.out.println(myHarley);

    }
}
