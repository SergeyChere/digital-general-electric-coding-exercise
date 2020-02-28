package service;

import model.Status;
import model.Type;
import model.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class VehicleServiceImpl implements VehicleService {

    private Map<Long, Vehicle> vehicles = new HashMap<>();
    private Set licensesByStatusNew = new HashSet();
    private Set licensesByStatusInProcess = new HashSet();
    private Set licensesByStatusFixed = new HashSet();
    private Set licensesByStatusReleased = new HashSet();
    private Map<Status, Set> setMap = new HashMap<>();
    private Map<Type, Integer> prices = new HashMap<>();
    private Integer totalProfit = 0;

    {
        createCar("car is good enough", 2009);
        createMotorcycle("motorcycle is your best friend", 1999, new Random().nextBoolean());
        createTruck("truck is better that nothing", 2019, new Random().nextDouble());

        prices.put(Type.CAR, 2000);
        prices.put(Type.MOTORCYCLE, 1000);
        prices.put(Type.TRUCK, 5000);

        setMap.put(Status.NEW, licensesByStatusNew);
        setMap.put(Status.IN_PROCESS, licensesByStatusInProcess);
        setMap.put(Status.FIXED, licensesByStatusFixed);
        setMap.put(Status.RELEASED, licensesByStatusReleased);
    }

    @Override
    public Vehicle.Truck createTruck(String description, Integer year, Double weightLimit) {
        Vehicle.Truck truck = new Vehicle.Truck.Builder()
                .setLicense(new Random().longs(0, 1000000).findFirst().getAsLong())
                .setType(Type.TRUCK)
                .setDescription(description)
                .setYear(year)
                .setStatus(Status.NEW)
                .setWeightLimit(weightLimit)
                .build();
        vehicles.put(truck.getLicense(), truck);
        licensesByStatusNew.add(truck.getLicense());
        return truck;
    }

    @Override
    public Vehicle.Car createCar(String description, Integer year) {
        Vehicle.Car car = new Vehicle.Car.Builder()
                .setLicense(new Random().longs(0, 1000000).findFirst().getAsLong())
                .setType(Type.CAR)
                .setDescription(description)
                .setYear(year)
                .setStatus(Status.NEW)
                .build();
        vehicles.put(car.getLicense(), car);
        licensesByStatusNew.add(car.getLicense());
        return car;
    }

    @Override
    public Vehicle.Motorcycle createMotorcycle(String description, Integer year, Boolean extraSit) {
        Vehicle.Motorcycle motorcycle = new Vehicle.Motorcycle.Builder()
                .setLicense(new Random().longs(0, 1000000).findFirst().getAsLong())
                .setType(Type.MOTORCYCLE)
                .setDescription(description)
                .setYear(year)
                .setStatus(Status.NEW)
                .setExtraSit(extraSit)
                .build();
        vehicles.put(motorcycle.getLicense(), motorcycle);
        licensesByStatusNew.add(motorcycle.getLicense());
        return motorcycle;
    }

    @Override
    public void changeStatusOfVehicle(Long license, Status status) {
        Type type = vehicles.get(license).getType();
        switch (type) {
            case CAR:
                Vehicle.Car car = (Vehicle.Car) vehicles.get(license);
                licensesByStatusNew.remove(car.getLicense());
                Set setCAR = setMap.get(status);
                setCAR.add(car.getLicense());
                if (status.equals(Status.RELEASED)) {
                    sumTotalProfit(car.getType());
                    vehicles.remove(car.getLicense());
                }
                vehicles.put(license, new Vehicle.Car.Builder()
                        .setLicense(license)
                        .setType(car.getType())
                        .setDescription(car.getDescription())
                        .setYear(car.getYear())
                        .setStatus(status)
                        .build());
                break;
            case TRUCK:
                Vehicle.Truck truck = (Vehicle.Truck) vehicles.get(license);
                licensesByStatusNew.remove(truck.getLicense());
                Set setTRUCK = setMap.get(status);
                setTRUCK.add(truck.getLicense());
                if (status.equals(Status.RELEASED)) {
                    sumTotalProfit(truck.getType());
                    vehicles.remove(truck.getLicense());
                }
                vehicles.put(license, new Vehicle.Truck.Builder()
                        .setLicense(license)
                        .setType(truck.getType())
                        .setDescription(truck.getDescription())
                        .setYear(truck.getYear())
                        .setStatus(status)
                        .setWeightLimit(truck.getWeightLimit())
                        .build());
                break;
            case MOTORCYCLE:
                Vehicle.Motorcycle motorcycle = (Vehicle.Motorcycle) vehicles.get(license);
                licensesByStatusNew.remove(motorcycle.getLicense());
                Set setMOTORCYCLE = setMap.get(status);
                setMOTORCYCLE.add(motorcycle.getLicense());
                if (status.equals(Status.RELEASED)) {
                    sumTotalProfit(motorcycle.getType());
                    vehicles.remove(motorcycle.getLicense());
                }
                vehicles.put(license, new Vehicle.Motorcycle.Builder()
                        .setLicense(license)
                        .setType(motorcycle.getType())
                        .setDescription(motorcycle.getDescription())
                        .setYear(motorcycle.getYear())
                        .setStatus(status)
                        .setExtraSit(motorcycle.getExtraSit())
                        .build());
                break;
            default:
                break;
        }
    }

    @Override
    public Vehicle getVehicleByLicense(Long license) {
        return vehicles.get(license);
    }

    private void sumTotalProfit(Type type) {
        totalProfit=totalProfit+prices.get(type);
    }

    @Override
    public Integer getTotalProfitOfVehicles() {
        return totalProfit;
    }

    @Override
    public List<Vehicle> getListOfVehiclesSortedByYear() {
        List<Vehicle> vehiclesArray = new ArrayList<>();
        for (Map.Entry<Long, Vehicle> entry : vehicles.entrySet()) {
            vehiclesArray.add(entry.getValue());
        }
        return vehiclesArray.stream().sorted(Comparator.comparingInt(Vehicle::getYear)).collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> getListOfVehiclesAlreadyLocatedInGarage(Status status) {
        List<Vehicle> vehicleArray = new ArrayList<>();
        switch (status) {
            case NEW:
                for (Object license : setMap.get(Status.NEW)) {
                    vehicleArray.add(vehicles.get(license));
                }
                break;
            case FIXED:
                for (Object license : setMap.get(Status.FIXED)) {
                    vehicleArray.add(vehicles.get(license));
                }
                break;
            case IN_PROCESS:
                for (Object license : setMap.get(Status.IN_PROCESS)) {
                    vehicleArray.add(vehicles.get(license));
                }
                break;
            case RELEASED:
                for (Object license : setMap.get(Status.RELEASED)) {
                    vehicleArray.add(vehicles.get(license));
                }
                break;
            default:
                break;
        }
        return vehicleArray;
    }

    @Override
    public List<Vehicle> getListOfVehiclesByKeyword(String keyword) {
        List<Vehicle> vehicleArray = new ArrayList<>();
        for (Map.Entry<Long, Vehicle> entry : vehicles.entrySet()) {
            String line = entry.getValue().getDescription();
            String[] arr = line.split(" ");
            for (String element : arr) {
                if (element.equals(keyword)) {
                    vehicleArray.add(entry.getValue());
                }
            }
        }
        return vehicleArray;
    }
}
