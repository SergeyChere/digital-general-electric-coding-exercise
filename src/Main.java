import model.Vehicle;
import runner.Runner;

import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<Integer, Vehicle> vehicles = new HashMap<>();

    public static void main(String[] args) {
        for (int i=1; i<=20; i++) {
            vehicles.put(i, new Runner().createAutomobiles());
        }
        for (Map.Entry<Integer, Vehicle> entry : vehicles.entrySet()) {
            System.out.println(entry.getKey()+": "+entry.getValue());
        }
    }
}
