import model.Status;
import model.Vehicle;
import service.VehicleService;
import service.VehicleServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static boolean flag = true;
    public static void main(String[] args) throws IOException, NumberFormatException {
        VehicleService vehicleService = new VehicleServiceImpl();
        String menu = "Please, insert command:\n" +
                "1: Insert a new vehicle\n" +
                "2: Change status of the vehicle based on license\n" +
                "3: Get vehicle based on license\n" +
                "4: Get list of vehicles currently based in garage\n" +
                "5: Get list of vehicles based on keyword\n" +
                "6: Get list of vehicles sorted by year\n" +
                "7: Get the total profit of vehicles that already released\n" +
                "8: Exit\n";

        while (flag) {
            System.out.println(menu);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line=bufferedReader.readLine())!=null) {
                switch (Integer.parseInt(line)) {
                    case 1:
                        System.out.println("Please, insert type of vehicle: 1-Car, 2-Motorcycle, 3-Truck:");
                        BufferedReader bufferedReaderType = new BufferedReader(new InputStreamReader(System.in));
                        String type;
                        while ((type=bufferedReaderType.readLine())!=null) {
                            switch (Integer.parseInt(type)) {
                                case 1:
                                    System.out.println("Insert description");
                                    BufferedReader bufferedReaderDescriptionCar = new BufferedReader(new InputStreamReader(System.in));
                                    String descriptionCar;
                                    while ((descriptionCar=bufferedReaderDescriptionCar.readLine())!=null) break;
                                    System.out.println("Insert year");
                                    BufferedReader bufferedReaderYear = new BufferedReader(new InputStreamReader(System.in));
                                    String yearCar;
                                    while ((yearCar=bufferedReaderYear.readLine())!=null) break;
                                    vehicleService.createCar(descriptionCar, Integer.parseInt(yearCar));
                                    break;
                                case 2:
                                    System.out.println("Insert description");
                                    BufferedReader bufferedReaderDescriptionMotorcycle = new BufferedReader(new InputStreamReader(System.in));
                                    String descriptionMotorcycle;
                                    while ((descriptionMotorcycle=bufferedReaderDescriptionMotorcycle.readLine())!=null) break;
                                    System.out.println("Insert year");
                                    BufferedReader bufferedReaderYearMotorcycle = new BufferedReader(new InputStreamReader(System.in));
                                    String yearMotorcycle;
                                    while ((yearMotorcycle=bufferedReaderYearMotorcycle.readLine())!=null) break;
                                    System.out.println("Insert extra sits");
                                    BufferedReader bufferedReaderSitsMotorcycle = new BufferedReader(new InputStreamReader(System.in));
                                    String sitsMotorcycle;
                                    while ((sitsMotorcycle=bufferedReaderSitsMotorcycle.readLine())!=null) break;
                                    vehicleService.createMotorcycle(descriptionMotorcycle, Integer.parseInt(yearMotorcycle), Boolean.parseBoolean(sitsMotorcycle));
                                    break;
                                case 3:
                                    System.out.println("Insert description");
                                    BufferedReader bufferedReaderDescriptionTruck = new BufferedReader(new InputStreamReader(System.in));
                                    String descriptionTruck;
                                    while ((descriptionTruck=bufferedReaderDescriptionTruck.readLine())!=null) break;
                                    System.out.println("Insert year");
                                    BufferedReader bufferedReaderYearTruck = new BufferedReader(new InputStreamReader(System.in));
                                    String yearTruck;
                                    while ((yearTruck=bufferedReaderYearTruck.readLine())!=null) break;
                                    System.out.println("Insert weight limits");
                                    BufferedReader bufferedReaderWeightLimitsTruck = new BufferedReader(new InputStreamReader(System.in));
                                    String weightLimitsTruck;
                                    while ((weightLimitsTruck=bufferedReaderWeightLimitsTruck.readLine())!=null) break;
                                    vehicleService.createTruck(descriptionTruck, Integer.parseInt(yearTruck), Double.parseDouble(weightLimitsTruck));
                                    break;
                                default:
                                    break;
                            }
                            System.out.println("\n"+menu);
                            break;
                        }
                        break;
                    case 2:
                        System.out.println("Please, insert license number:");
                        BufferedReader bufferedReaderLicense = new BufferedReader(new InputStreamReader(System.in));
                        String license;
                        while ((license=bufferedReaderLicense.readLine())!=null) break;
                        System.out.println("Please, insert status: 2-In process, 3-Fixed, 4-Released");
                        BufferedReader bufferedReaderStatus = new BufferedReader(new InputStreamReader(System.in));
                        String status;
                        while ((status=bufferedReaderStatus.readLine())!=null) break;
                        switch (Integer.parseInt(status)) {
                            case 2:
                                vehicleService.changeStatusOfVehicle(Long.parseLong(license), Status.IN_PROCESS);
                                break;
                            case 3:
                                vehicleService.changeStatusOfVehicle(Long.parseLong(license), Status.FIXED);
                                break;
                            case 4:
                                vehicleService.changeStatusOfVehicle(Long.parseLong(license), Status.RELEASED);
                                break;
                            default:
                                break;
                        }
                        System.out.println("\n"+menu);
                        break;
                    case 3:
                        System.out.println("Please, insert license number:");
                        BufferedReader bufferedReaderLicense3 = new BufferedReader(new InputStreamReader(System.in));
                        String license3;
                        while ((license3=bufferedReaderLicense3.readLine())!=null) break;
                        System.out.println(vehicleService.getVehicleByLicense(Long.parseLong(license3)));
                        System.out.println("\n"+menu);
                        break;
                    case 4:
                        List<Vehicle> vehicle4 = new ArrayList<>();
                        System.out.println("Please, insert status: 1-New, 2-In process, 3-Fixed");
                        BufferedReader bufferedReaderStatus2 = new BufferedReader(new InputStreamReader(System.in));
                        String status2;
                        while ((status2=bufferedReaderStatus2.readLine())!=null) break;
                        switch (Integer.parseInt(status2)) {
                            case 1:
                                vehicle4.addAll(vehicleService.getListOfVehiclesAlreadyLocatedInGarage(Status.NEW));
                                break;
                            case 2:
                                vehicle4.addAll(vehicleService.getListOfVehiclesAlreadyLocatedInGarage(Status.IN_PROCESS));
                                break;
                            case 3:
                                vehicle4.addAll(vehicleService.getListOfVehiclesAlreadyLocatedInGarage(Status.FIXED));
                                break;
                            default:
                                break;
                        }
                        for (Vehicle vehicle : vehicle4) {
                            System.out.println(vehicle);
                        }
                        vehicle4 = new ArrayList<>();
                        System.out.println("\n"+menu);
                        break;
                    case 5:
                        System.out.println("Please, insert keyword");
                        List<Vehicle> vehicle5 = new ArrayList<>();
                        BufferedReader bufferedReaderKeyword = new BufferedReader(new InputStreamReader(System.in));
                        String keyword;
                        while ((keyword=bufferedReaderKeyword.readLine())!=null) {
                            vehicle5.addAll(vehicleService.getListOfVehiclesByKeyword(keyword));
                            break;
                        }
                        for (Vehicle vehicle : vehicle5) {
                            System.out.println(vehicle);
                        }
                        vehicle5 = new ArrayList<>();
                        System.out.println("\n"+menu);
                        break;
                    case 6:
                        List<Vehicle> vehicle6 = new ArrayList<>();
                        vehicle6.addAll(vehicleService.getListOfVehiclesSortedByYear());
                        for (Vehicle vehicle : vehicle6) {
                            System.out.println(vehicle);
                        }
                        vehicle6 = new ArrayList<>();
                        System.out.println("\n"+menu);
                        break;
                    case 7:
                        System.out.println(vehicleService.getTotalProfitOfVehicles());
                        System.out.println("\n"+menu);
                        break;
                    case 8:
                        flag=false;
                        break;
                    default:
                        break;
                }
                if(flag==false) break;
            }
        }
    }
}
