package entities;

import common.OutputMessages;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PilotImpl implements Pilot {
    private String name;
    private List<Machine>machines;

    public PilotImpl(String name){
        this.setName(name);
        this.machines = new ArrayList<>();
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(OutputMessages.illegalPilotName);
        }
        this.name = name;
    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public void addMachine(Machine machine) {
        if(machine == null){
            throw new NullPointerException("Null machine cannot be added to the pilot.");
        }
        this.machines.add(machine);
    }

    @Override
    public List<Machine> getMachines() {
        return Collections.unmodifiableList(this.machines);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %d machines",this.name,this.machines.size())).append(System.lineSeparator());
        for (Machine machine : machines) {
            sb.append("- ").append(machine.getName()).append(System.lineSeparator());
            sb.append(machine.toString());
        }

        return sb.toString().trim();
    }
}
