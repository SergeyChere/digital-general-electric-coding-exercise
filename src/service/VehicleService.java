package service;

import model.Status;
import model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle.Truck createTruck(String description, Integer year, Double weightLimit);
    Vehicle.Car createCar(String description, Integer year);
    Vehicle.Motorcycle createMotorcycle(String description, Integer year, Boolean extraSit);
    void changeStatusOfVehicle(Long license, Status status);
    Vehicle getVehicleByLicense(Long license);
    List<Vehicle> getListOfVehiclesAlreadyLocatedInGarage(Status status);
    List<Vehicle> getListOfVehiclesByKeyword(String keyword);
    List<Vehicle> getListOfVehiclesSortedByYear();
    Integer getTotalProfitOfVehicles();
}
