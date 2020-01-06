import core.MachineFactoryImpl;
import core.MachinesManagerImpl;
import core.PilotFactoryImpl;
import core.interfaces.MachineFactory;
import core.interfaces.MachinesManager;
import core.interfaces.PilotFactory;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PilotFactory pilotFactory = new PilotFactoryImpl();
        MachineFactory machineFactory = new MachineFactoryImpl();
        Map<String, Pilot> pilots = new LinkedHashMap<>();
        Map<String, Machine> machines = new LinkedHashMap<>();

        MachinesManager machinesManager
                = new MachinesManagerImpl(pilotFactory, machineFactory, pilots, machines);

        Scanner scanner = new Scanner(System.in);

        String [] input = scanner.nextLine().split("\\s+");
        String commandName = input[0];
        String output = "";

        while(!commandName.equals("Over")){

            switch (commandName){
                case "Hire":
                  output =  hirePilot(machinesManager,input);
                    break;
                case "ManufactureTank":
                    output = manufactureTank(machinesManager,input);
                    break;
                case"ManufactureFighter":
                    output = manufactureFighter(machinesManager,input);
                    break;
                case "Engage":
                    output = engage(machinesManager,input);
                    break;
                case"Attack":
                    output = attack(machinesManager,input);
                    break;
                case "AggressiveMode":
                    output = aggressiveMode(machinesManager,input);
                    break;
                case "DefenseMode":
                    output = defensiveMode(machinesManager,input);
                    break;
                case "Report":
                    output = getPilotReport(machinesManager,input);
                    break;

            }
            System.out.println(output);
            input = scanner.nextLine().split("\\s+");
            commandName = input[0];
        }

    }

    private static String defensiveMode(MachinesManager machinesManager, String[] input) {
        return machinesManager.toggleTankDefenseMode(input[1]);
    }

    private static String aggressiveMode(MachinesManager machinesManager, String[] input) {
        return machinesManager.toggleFighterAggressiveMode(input[1]);
    }

    private static String attack(MachinesManager machinesManager, String[] input) {
        return machinesManager.attackMachines(input[1],input[2]);
    }

    private static String engage(MachinesManager machinesManager, String[] input) {
        return machinesManager.engageMachine(input[1],input[2]);
    }

    private static String manufactureFighter(MachinesManager machinesManager, String[] input) {
        return machinesManager.manufactureFighter(input[1],Double.parseDouble(input[2]),Double.parseDouble(input[3]));
    }

    private static String manufactureTank(MachinesManager machinesManager, String[] input) {
        return machinesManager.manufactureTank(input[1],Double.parseDouble(input[2]),Double.parseDouble(input[3]));
    }

    private static String getPilotReport(MachinesManager machinesManager, String[] input) {
        return machinesManager.pilotReport(input[1]);
    }

    private static String hirePilot(MachinesManager machinesManager, String[] input) {
       return machinesManager.hirePilot(input[1]);
    }
}
