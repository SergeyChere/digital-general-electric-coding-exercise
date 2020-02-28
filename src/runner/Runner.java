package runner;

import model.Status;
import model.Type;
import model.Vehicle;

import java.util.Random;

public class Runner {
    public Vehicle createAutomobiles() {
        Vehicle vehicle = null;
        switch (new Random().ints(1, 4).findFirst().getAsInt()) {
            case 1:
                vehicle = createCar();
                break;
            case 2:
                vehicle = createMotorcycle();
                break;
            case 3:
                vehicle = createTruck();
                break;
            default:
                break;
        }
        return vehicle;
    }

    private Vehicle.Truck createTruck() {
        return new Vehicle.Truck.Builder()
                .setLicense(new Random().nextLong())
                .setType(Type.TRUCK)
                .setDescription("truck")
                .setYear(1999)
                .setStatus(Status.NEW)
                .setWeightLimit(new Random().nextDouble())
                .build();
    }

    private Vehicle.Car createCar() {
       return new Vehicle.Car.Builder()
               .setLicense(new Random().nextLong())
               .setType(Type.CAR)
               .setDescription("car")
               .setYear(2009)
               .setStatus(Status.NEW)
               .build();
    }

    private Vehicle.Motorcycle createMotorcycle() {
        return new Vehicle.Motorcycle.Builder()
                .setLicense(new Random().nextLong())
                .setType(Type.MOTORCYCLE)
                .setDescription("motorcycle")
                .setYear(2019).setStatus(Status.NEW)
                .setExtraSit(new Random().nextBoolean())
                .build();
    }
}
