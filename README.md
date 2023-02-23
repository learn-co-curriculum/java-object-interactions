# Object Interactions

## Learning Goals

- Discuss message passing in object-oriented programming
- Introduce **UML Sequence Diagrams** for visualizing object interactions
- Implement object interactions in Java

## Introduction

A Java program often consists of multiple related objects
that collaborate to implement the desired application functionality.
The collaboration can be viewed as "message passing", where one object
sends a message asking another object to do something.  In Java
we implement message passing through method invocation.

## Code Along

Fork and clone this lesson.  We will build an application
that demonstrates interactions among associated objects:

- a `Motorcycle` object is associated with two `Tire` objects
- a `Car` object is associated with four `Tire` objects

- The `Main` class contains a `main` method that creates an instance of `Motorcycle`
  and implicitly calls the `toString` and explicitly calls the`rideThroughMud` methods on the object.
- The `Motorcycle` class has fields for the `make` and `model`, along
  with  fields `frontTire` and `rearTire` that reference instances of the `Tire` class.
- The `Car` class has fields for the `make` and `model`, along
  with an array named `tires` that stores references to 4 `Tire` objects.
- The `Tire` class has instance variables `airPressure` and `clean`,
  along with class constants `MIN_RECOMMENDED_PRESSURE` and `MIN_RECOMMENDED_PRESSURE`.

```java
public class Main {

    public static void main(String[] args) {
        Motorcycle myHarley = new Motorcycle("Harley Davidson", "Sportster");
        System.out.println(myHarley);

        System.out.println("Ride through mud");
        myHarley.rideThroughMud();
        System.out.println(myHarley);
        
    }
}

```

## Visualizing Object Relations and Interactions


```java
public void rideThroughMud() {
    frontTire.setClean(false);
    rearTire.setClean(false);
}
```

Let's consider the sequence of method calls that occur when the `rideThroughMud()` method
is called by the `main` method.  This is depicted visually in the following **UML Sequence Diagram**.

![sequence rideThroughMud](https://curriculum-content.s3.amazonaws.com/6676/java-multipleclasses/sequence_ridethroughmud.png)

A sequence diagram displays the **lifeline** of
each object as a vertical dashed line. A solid horizontal line
represents a message from one object (or class) to another.
In the diagram above, the messages represent  method calls
`rideThroughMud()` and `setClean()`.

Let's use the debugger to step through the method calls and
observe how the object interactions affect object state.
Set a breakpoint in the `main` method after the `Motorcycle`
object has been created and the initial state printed
at the line of code `myHarley.rideThroughMud();`.

![breakpoint rideThroughMud](https://curriculum-content.s3.amazonaws.com/6676/java-multipleclasses/breakpoint_ridethroughmud.png)

- Use 'Step Over' for the print statements.
- Use 'Step Into' for the `Motorcycle` and `Tire` method calls.

Press the `debug` button to launch the debugger and stop at the breakpoint,
then switch to the Java Visualizer view (the yellow arrow is added to show the
current line of execution).  The `MotorCycle` object
stores references to `Tire` objects in the `frontTire` and `rearTire`
instance variables.  Air pressure is initialized to a random
value, so your values may differ.

![step0](https://curriculum-content.s3.amazonaws.com/6676/java-multipleclasses/object_interactions_step0.png)

Press "Step Into" to execute the method call `myHarley.rideThroughMud();`.

The debugger stops at the first line of code in the `rideThroughMud` method,
which is in the `Motorcycle` class.  Notice there is a new frame on the
call stack for the `rideThroughMud` method, and the implicit variable `this`
references the `Motorcycle` object.

![step1](https://curriculum-content.s3.amazonaws.com/6676/java-multipleclasses/object_interactions_step1.png)

Press "Step Into" to execute the method call `frontTire.setClean(true);`.

The debugger stops at the first line of code in the `setClean()` method,
which is in the `Tire` class.  We see a new frame on the
call stack for the `setClean` method, and the implicit variable `this`
references the front `Tire` object.

![step2](https://curriculum-content.s3.amazonaws.com/6676/java-multipleclasses/object_interactions_step2.png)

Pressing "Step Into" again to execute `this.clean = clean;`.
We see the front tire is no longer clean.  Notice we must use
`this.clean` to differentiate the instance variable named `clean`
from the parameter named `clean`.

![step3](https://curriculum-content.s3.amazonaws.com/6676/java-multipleclasses/object_interactions_step3.png)

Press "Step Into" to return
to the `driveThroughMud` method, and then continue stepping
through the code so the rear tire is also no longer clean.

## Challenge

Edit the `Motorcycle` class to add a new method named `wash()` that
results in both tires being clean.

![wash sequence](https://curriculum-content.s3.amazonaws.com/6676/java-multipleclasses/sequence_wash.png)

Edit the `main` method to call the new `wash()` method and print the resulting object state:

```java
public static void main(String[] args) {
    Motorcycle myHarley = new Motorcycle("Harley Davidson", "Sportster");
    System.out.println(myHarley);

    System.out.println("Ride through mud");
    myHarley.rideThroughMud();
    System.out.println(myHarley);

    System.out.println("Wash");
    myHarley.wash();
    System.out.println(myHarley);

}
```

Confirm the output shows both tires are clean after washing:

```text
make='Harley Davidson', model='Sportster', frontTire={airPressure=27, clean=true}, rearTire={airPressure=31, clean=true}
Ride through mud
make='Harley Davidson', model='Sportster', frontTire={airPressure=27, clean=false}, rearTire={airPressure=31, clean=false}
Wash the motorcycle
make='Harley Davidson', model='Sportster', frontTire={airPressure=27, clean=true}, rearTire={airPressure=31, clean=true}
```

Edit the `Motorcycle` class to add a new method named `wheelieThroughMud()` that
results in just the rear tire getting dirty (i.e. not clean).

![wheelie sequence](https://curriculum-content.s3.amazonaws.com/6676/java-multipleclasses/sequence_wheelie.png)

Edit the `main` method to call the new `wheelieThroughMud()` method and print the resulting object state:

```java
public static void main(String[] args) {
    Motorcycle myHarley = new Motorcycle("Harley Davidson", "Sportster");
    System.out.println(myHarley);

    System.out.println("Ride through mud");
    myHarley.rideThroughMud();
    System.out.println(myHarley);

    System.out.println("Wash the motorcycle");
    myHarley.wash();
    System.out.println(myHarley);

    System.out.println("Wheelie through mud");
    myHarley.wheelieThroughMud();
    System.out.println(myHarley);
}
```

Confirm the output shows the front tire is clean, while the rear tire
is not after doing a wheelie:

```text
make='Harley Davidson', model='Sportster', frontTire={airPressure=32, clean=true}, rearTire={airPressure=25, clean=true}
Ride through mud
make='Harley Davidson', model='Sportster', frontTire={airPressure=32, clean=false}, rearTire={airPressure=25, clean=false}
Wash the motorcycle
make='Harley Davidson', model='Sportster', frontTire={airPressure=32, clean=true}, rearTire={airPressure=25, clean=true}
Wheelie through mud
make='Harley Davidson', model='Sportster', frontTire={airPressure=32, clean=true}, rearTire={airPressure=25, clean=false}
```

Notice the project also contains a `Car` class, which uses an array having 4 `Tire`
objects:

```java
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
```

Edit the `Car` class to add a method named `checkTires()`. The method takes no parameters
and does not return a value.  The method should use a loop to all the `checkAirPressure`
method for each `Tire` object in the array.

![car sequence diagram](https://curriculum-content.s3.amazonaws.com/6676/java-multipleclasses/sequence_car.png)

Edit the `main` method to create a `Car` instance,
and print the object state before and after calling `checkTires`.

```java
public static void main(String[] args) {
    Motorcycle myHarley = new Motorcycle("Harley Davidson", "Sportster");
    System.out.println(myHarley);

    System.out.println("Ride through mud");
    myHarley.rideThroughMud();
    System.out.println(myHarley);

    System.out.println("Wash");
    myHarley.wash();
    System.out.println(myHarley);

    System.out.println("Wheelie through mud");
    myHarley.wheelieThroughMud();
    System.out.println(myHarley);


    Car myHonda = new Car("Honda", "Accord");
    System.out.println("Before checking the tires");
    System.out.println(myHonda);
    myHonda.checkTires();
    System.out.println("After checking the tires");
    System.out.println(myHonda);
}
```

All car tires are initially low (your values may differ), but
after checking the tires they should all have the maximum air pressure:

```text
make='Harley Davidson', model='Sportster', frontTire={airPressure=26, clean=true}, rearTire={airPressure=32, clean=true}
Ride through mud
make='Harley Davidson', model='Sportster', frontTire={airPressure=26, clean=false}, rearTire={airPressure=32, clean=false}
Wash
make='Harley Davidson', model='Sportster', frontTire={airPressure=26, clean=true}, rearTire={airPressure=32, clean=true}
Wheelie through mud
make='Harley Davidson', model='Sportster', frontTire={airPressure=26, clean=true}, rearTire={airPressure=32, clean=false}
Before checking the tires
make='Honda', model='Accord', tires=[{airPressure=27, clean=true}, {airPressure=25, clean=true}, {airPressure=26, clean=true}, {airPressure=30, clean=true}]
After checking the tires
make='Honda', model='Accord', tires=[{airPressure=34, clean=true}, {airPressure=34, clean=true}, {airPressure=34, clean=true}, {airPressure=30, clean=true}]
```

## Final Code Check


```java
public class Main {

    public static void main(String[] args) {
        Motorcycle myHarley = new Motorcycle("Harley Davidson", "Sportster");
        System.out.println(myHarley);

        System.out.println("Ride through mud");
        myHarley.rideThroughMud();
        System.out.println(myHarley);

        System.out.println("Wash");
        myHarley.wash();
        System.out.println(myHarley);

        System.out.println("Wheelie through mud");
        myHarley.wheelieThroughMud();
        System.out.println(myHarley);


        Car myHonda = new Car("Honda", "Accord");
        System.out.println("Before checking the tires");
        System.out.println(myHonda);
        myHonda.checkTires();
        System.out.println("After checking the tires");
        System.out.println(myHonda);

    }
}
```

```java
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

    public void wheelieThroughMud() {
        rearTire.setClean(false);
    }

    public void wash() {
        frontTire.setClean(true);
        rearTire.setClean(true);
    }

    @Override
    public String toString() {
        return "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", frontTire=" + frontTire +
                ", rearTire=" + rearTire ;
    }
}
```


```java
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

    public void checkTires() {
        for (Tire tire: tires)
            tire.checkAirPressure();
    }


    @Override
    public String toString() {
        return "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", tires=" + Arrays.toString(tires);
    }
}
```

```java
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

```

## Resources

- [UML Sequence Diagram](https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-sequence-diagram/)
- [Interaction Diagram Lesson](https://sites.cs.ucsb.edu/~mikec/cs48/project/InteractionLarman.pdf)
