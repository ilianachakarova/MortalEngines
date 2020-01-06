package core;

import common.OutputMessages;
import core.interfaces.MachineFactory;
import core.interfaces.MachinesManager;
import core.interfaces.PilotFactory;
import entities.interfaces.Fighter;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;
import entities.interfaces.Tank;

import java.util.HashMap;
import java.util.Map;

public class MachinesManagerImpl implements MachinesManager {
    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;
    private Map<String,Machine> allocatedMachines;

    public MachinesManagerImpl(PilotFactory pilotFactory,
                               MachineFactory machineFactory,
                               Map<String, Pilot> pilots,
                               Map<String, Machine> machines) {
        this.pilotFactory = pilotFactory;
        this.machineFactory = machineFactory;
        this.pilots = pilots;
        this.machines = machines;
        this.allocatedMachines = new HashMap<>();
    }


    @Override
    public String hirePilot(String name) {
        Pilot pilot = this.pilotFactory.createPilot(name);
        if(pilots.containsKey(name)){
            return String.format(OutputMessages.pilotExists,name);
        }else {
            this.pilots.put(name, pilot);
            return String.format(OutputMessages.pilotHired, name);
        }

    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        boolean exists = this.machines.containsKey(name);

        if (exists) {
            return String.format(OutputMessages.machineExists, name);
        } else {
            this.machines.put(name,this.machineFactory.createTank(name, attackPoints, defensePoints));
            return String.format(OutputMessages.tankManufactured, name,attackPoints,defensePoints);
        }
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        boolean exists = this.machines.containsKey(name);


        if (exists) {
            return String.format(OutputMessages.machineExists, name);
        } else {
            this.machines.put(name,this.machineFactory.createFighter(name, attackPoints, defensePoints));
            return String.format(OutputMessages.fighterManufactured, name,attackPoints,defensePoints);
        }
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {
        Pilot pilot = this.pilots.get(selectedPilotName);
        Machine machine = this.machines.get(selectedMachineName);
        if (pilot == null)  {
            return String.format(OutputMessages.pilotNotFound,selectedPilotName);
        }
        if(machine == null){
            return String.format(OutputMessages.machineNotFound,selectedMachineName);
        }
        if(this.allocatedMachines.containsKey(selectedMachineName)){
            return String.format(OutputMessages.machineHasPilotAlready,selectedMachineName);
        }
        pilot.addMachine(machine);
        machine.setPilot(pilot);
        allocatedMachines.put(selectedMachineName,machine);


        return String.format(OutputMessages.machineEngaged, selectedPilotName, selectedMachineName);
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {
        Machine machine1 = this.machines.get(attackingMachineName);
        Machine machine2 = this.machines.get(defendingMachineName);

        if (machine1 == null) {
            return String.format(OutputMessages.machineNotFound, attackingMachineName);
        }
        if (machine2 == null) {
            return String.format(OutputMessages.machineNotFound, defendingMachineName);
        }

        if (machine1.getAttackPoints() > machine2.getDefensePoints()) {
            double healthPoints = machine2.getHealthPoints();
            machine2.setHealthPoints(healthPoints - machine1.getAttackPoints());
        }

        machine1.attack(defendingMachineName);

        return String.format(OutputMessages.attackSuccessful, defendingMachineName, attackingMachineName, machine2.getHealthPoints());

    }

    @Override
    public String pilotReport(String pilotName) {
        Pilot pilot = this.pilots.get(pilotName);
        if(this.pilots.containsKey(pilotName)){
            return pilot.report();
        }else{
            return String.format(OutputMessages.pilotNotFound,pilotName);
        }

    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {
        Fighter fighter = (Fighter) machines.get(fighterName);
        if(fighter != null){
            fighter.toggleAggressiveMode();
            return String.format(OutputMessages.fighterOperationSuccessful, fighterName);
        }else {
            return String.format(OutputMessages.pilotNotFound,fighterName);
        }

    }

    @Override
    public String toggleTankDefenseMode(String tankName) {
        Tank tank =  (Tank) machines.get(tankName);

        if (tank == null) {
            return String.format(OutputMessages.machineNotFound,tankName);
        }

         tank.toggleDefenseMode();
        return String.format(OutputMessages.tankOperationSuccessful,tankName);
    }


}
